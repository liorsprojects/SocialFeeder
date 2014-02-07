package com.liorgins.socialfeeder.accounts;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import twitter4j.DirectMessage;
import twitter4j.IDs;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

import com.liorgins.socialfeeder.Commons;

public class TwitterAccount extends Account {
	private Twitter twitter;
	
	public static final int TIMELINE = 1;
	public static final int MENTIONS = 2;
	public static final int DIRECT_MESSAGES = 3;
	
	private RequestToken tempRequestToken = null;
	private AccessToken tempAccessToken = null;
	
	public TwitterAccount() {
		twitter = getTwitterInstance();
		twitter.setOAuthConsumer(Commons.CONSUMER_KEY, Commons.CONSUMER_SECRET);
	}
	
	private Twitter getTwitterInstance() {
		twitter = new TwitterFactory().getInstance();
		return twitter;
	}
	
	public RequestToken getRequestToken() throws TwitterException {
		GetRequestTokenTask task = new GetRequestTokenTask();
		task.execute(0);
		SystemClock.sleep(5000);
		return tempRequestToken;
	}
	
	class GetRequestTokenTask extends AsyncTask<Integer, Integer, Void> {
		
		@Override
		protected Void doInBackground(Integer... params) {
			Log.d(this.getClass().getName(), "Inside doInBackground of getRequestTokenTask");
			try {
				tempRequestToken = twitter.getOAuthRequestToken(Commons.CALLBACK_URL);
			} catch (TwitterException e) {
				Log.e("AAAAAAAAA",e.getMessage());
			}
			return null;
		}
	}
	
	public String getAuthenticationUrl() throws TwitterException {
		getRequestToken();
		return tempRequestToken.getAuthenticationURL();
	}
	
	public AccessToken getAccessToken(String oAuthVerifier) throws TwitterException {
		GetOAuthAccessTokenTask task = new GetOAuthAccessTokenTask();
		task.execute(oAuthVerifier);
		SystemClock.sleep(5000);
		return tempAccessToken;
	}
	
	class GetOAuthAccessTokenTask extends AsyncTask<String, Integer, Void> {
		
		@Override
		protected Void doInBackground(String... params) {
			Log.d(this.getClass().getName(), "Inside doInBackground of getOAuthAccessTokenTask");
			try {
				tempAccessToken = twitter.getOAuthAccessToken(tempRequestToken, params[0]);
			} catch (TwitterException e) {
				Log.e("AAAAAAAAA",e.getMessage());
			}
			return null;
		}
	}
	
	
	
	public void setAccessToken(AccessToken accessToken) {
		twitter.setOAuthAccessToken(accessToken);
	}

	
	
	
	public ResponseList<Status> getFriendsTimeline() throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseList<Status> getFriendsTimeline(Paging arg0)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseList<Status> getHomeTimeline() throws TwitterException {
		// TODO Auto-generated method stub
		return twitter.getHomeTimeline();
	}

	public ResponseList<Status> getHomeTimeline(Paging page)
			throws TwitterException {
		return twitter.getHomeTimeline(page);
	}

	public ResponseList<Status> getMentions() throws TwitterException {
		return twitter.getMentions();
	}

	public ResponseList<Status> getMentions(Paging paging)
			throws TwitterException {
		return twitter.getMentions(paging);
	}

	public ResponseList<Status> getPublicTimeline() throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseList<Status> getRetweetedByMe() throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseList<Status> getRetweetedByMe(Paging arg0)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseList<Status> getRetweetedByUser(String arg0, Paging arg1)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseList<Status> getRetweetedByUser(long arg0, Paging arg1)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseList<Status> getRetweetedToMe() throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseList<Status> getRetweetedToMe(Paging arg0)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseList<Status> getRetweetedToUser(String arg0, Paging arg1)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseList<Status> getRetweetedToUser(long arg0, Paging arg1)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseList<Status> getRetweetsOfMe() throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseList<Status> getRetweetsOfMe(Paging arg0)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseList<Status> getUserTimeline() throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseList<Status> getUserTimeline(String arg0)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseList<Status> getUserTimeline(long arg0)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseList<Status> getUserTimeline(Paging arg0)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseList<Status> getUserTimeline(String arg0, Paging arg1)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseList<Status> getUserTimeline(long arg0, Paging arg1)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseList<DirectMessage> getDirectMessages()
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseList<DirectMessage> getDirectMessages(Paging paging)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseList<DirectMessage> getSentDirectMessages()
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseList<DirectMessage> getSentDirectMessages(Paging paging)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public DirectMessage sendDirectMessage(String screenName, String text)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public DirectMessage sendDirectMessage(long userId, String text)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public DirectMessage destroyDirectMessage(long id) throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public DirectMessage showDirectMessage(long id) throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public Status showStatus(long id) throws TwitterException {
		return twitter.showStatus(id);
	}

	public Status updateStatus(String status) throws TwitterException {
		return twitter.updateStatus(status);
	}

	public Status updateStatus(StatusUpdate latestStatus)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public Status destroyStatus(long statusId) throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public Status retweetStatus(long statusId) throws TwitterException {
		return twitter.retweetStatus((long)statusId);
	}

	public ResponseList<Status> getRetweets(long statusId)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseList<User> getRetweetedBy(long statusId)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseList<User> getRetweetedBy(long statusId, Paging paging)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public IDs getRetweetedByIDs(long statusId) throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	public IDs getRetweetedByIDs(long statusId, Paging paging)
			throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
