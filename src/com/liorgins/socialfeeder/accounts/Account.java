package com.liorgins.socialfeeder.accounts;

import com.liorgins.socialfeeder.Commons;

import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.SyncStateContract.Constants;
import android.util.Log;


public abstract class Account {
	
	private SharedPreferences mPrefs;

	/**
	 * Returns the request token for the account.
	 * @return
	 * RequestToken
	 * @throws TwitterException 
	 */
	public abstract RequestToken getRequestToken() throws TwitterException;
	
	/**
	 * Returns the Authentication Url for the account.
	 * @return
	 * Url String which can be used to Authenticate the user.
	 * @throws TwitterException 
	 */
	
	public abstract String getAuthenticationUrl() throws TwitterException;
	
	/**
	 * Generates the Access Token for the account. This can be used to authenticate the account. 
	 * @param oAuthVerifier
	 * Verifier string which can be used for generating the AccessToken
	 * @return
	 * Returns the access token.
	 * @throws TwitterException 
	 */
	public abstract AccessToken getAccessToken(String oAuthVerifier) throws TwitterException;
	
	
	/**
	 * Sets the Access Token received from the response to the account instance.
	 * @param token
	 * AccessToken obtained after authentication.
	 */
	
	public abstract void setAccessToken(AccessToken token);
	
	/**
	 * Checks if the user is logged in with proper authentication details.
	 * @param context
	 * Context of in which the method is called. Ideally this would be the instance of the Activity 
	 * in which it is called.
	 * @return
	 * Returns true if the user is logged in and false otherwise.
	 */
	
	public boolean isUserLoggedIn(Context context) {
		mPrefs = context.getSharedPreferences(Commons.PREFERENCES_NAME, Context.MODE_PRIVATE);
		if(mPrefs.getString(Commons.ACCESS_TOKEN, null) != null) {
			Log.d(this.getClass().getName() , Commons.ACCESS_TOKEN + " found");
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Writes the Access Token and Access Token secret to the Shared Preferences.
	 * @param accessToken
	 * Access Token received after authentication.
	 */
	
	public void writeTokenToPrefs(AccessToken accessToken) {
		SharedPreferences.Editor editor = mPrefs.edit();
		String token = accessToken.getToken();
		String tokenSecret = accessToken.getTokenSecret();
		editor.putString(Commons.ACCESS_TOKEN, token);
		editor.putString(Commons.ACCESS_TOKEN_SECRET, tokenSecret);
		editor.commit();
		
		Log.d(this.getClass().getName(), mPrefs.getString(Commons.ACCESS_TOKEN, null));
	}
	
	/**
	 * Retrieves the Access Token and Access Token secret from the Shared Preferences.
	 * @return
	 * Returns the Access Token generated from the Access Token and Access Token Secret.
	 */
	
	public AccessToken getTokenFromPreferences() {
		Log.d(this.getClass().getName(), "getTokenFromPreferences called");
		String token = mPrefs.getString(Commons.ACCESS_TOKEN, null);
		String tokenSecret = mPrefs.getString(Commons.ACCESS_TOKEN_SECRET, null);
		AccessToken accessToken = null;
		accessToken = new AccessToken(token, tokenSecret);
		return accessToken;
	}
}
