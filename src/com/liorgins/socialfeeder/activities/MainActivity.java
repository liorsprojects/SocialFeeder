package com.liorgins.socialfeeder.activities;


import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;


import com.liorgins.socialfeeder.Commons;
import com.liorgins.socialfeeder.accounts.Account;
import com.liorgins.socialfeeder.accounts.AccountManager;
import com.liorgins.socialfeedr.R;

public class MainActivity extends Activity {

	private Account account;
	private TextView tvToken;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		account = AccountManager.getInstance().getAccount();
		if(account.isUserLoggedIn(this)) {
			Log.d(this.getLocalClassName(), "user is logged in");
			initializeUI();
		} else {
			try {
				login();
			} catch (Exception e) {
				
			}
		}
	}

	/**
	 * Check if the user is logged in by checking if there is a preference key related to the 
	 * AccessToken. If not, then a WebView is displayed and authentication is completed and AccessToken is set.
	 * The AccessToken is then written to the Preferences.
	 * @throws TwitterException 
	 */

	private void login() throws TwitterException {
		WebView webView = new WebView(this);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				Log.d("WEBVIEW", url);
				if(url.contains(Commons.CALLBACK_URL)) {
					Uri uri = Uri.parse(url);

					String oAuthVerifier = uri.getQueryParameter(Commons.OAUTH_VERIFIER);
					AccessToken token = null;
					try {
						token = account.getAccessToken(oAuthVerifier);
					} catch (TwitterException e) {
						// TODO Auto-generated catch block
						Log.d(this.getClass().getName(), "Failed to get Access Token");
					}

					account.setAccessToken(token);
					account.writeTokenToPrefs(token);
					setContentView(R.layout.activity_main);
					initializeUI();
				}
			}
		});
		
		setContentView(webView);
		webView.loadUrl(account.getAuthenticationUrl());

	}
	
	private void initializeUI() {
		AccessToken token = account.getTokenFromPreferences();
		account.setAccessToken(token);
		setContentView(R.layout.activity_main);
		tvToken = (TextView) findViewById(R.id.tvToken);
		tvToken.setText(token.getToken());
	}
}
