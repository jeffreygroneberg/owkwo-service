package de.owkwo.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import de.owkwo.model.posts.ParentNewsPost;
import de.owkwo.service.delegate.interfaces.WKWServiceDelegatable;

public class WKWService {

	/**
	 * Constants for all the URLs needed on Wer-kennt-wen.de
	 */

	public final String LOGIN_URL = "https://secure.wer-kennt-wen.de/login/index";
	public final String LOGOUT_URL = "http://www.wer-kennt-wen.de/logout/";

	public final String LOGIN_USERNAME_PARAM = "loginName";
	public final String LOGIN_PASSWPORD_PARAM = "pass";

	public final String PROFILE_URL = "http://www.wer-kennt-wen.de/person";
	public final String PROFILE_ANY_URL = "http://www.wer-kennt-wen.de/person/";

	public final String SETTINGS_MAIN_URL = "http://www.wer-kennt-wen.de/settings/main/";
	public final String SETTINGS_CONTACT_URL = "http://www.wer-kennt-wen.de/settings/contact/";
	public final String SETTINGS_SCHOOL_URL = "http://www.wer-kennt-wen.de/settings/school/";
	public final String SETTINGS_PERSONAL_URL = "http://www.wer-kennt-wen.de/settings/personal/";
	public final String SETTINGS_PHOTO_URL = "http://www.wer-kennt-wen.de/settings/photo/";

	public final String NEWS_URL = "http://www.wer-kennt-wen.de/news/justnow";
	public final String NEWS_PAGE_ANY_URL = "http://www.wer-kennt-wen.de/news/justnow/0/page/";

	private WKWServiceDelegatable delegate;
	HttpClient client;

	public void setDelegate(WKWServiceDelegatable delegate) {

		this.delegate = delegate;

	}

	public WKWServiceDelegatable getDelegate() {

		return this.delegate;

	}

	public WKWService(WKWServiceDelegatable delegate) {

		this.delegate = delegate;
		this.client = new DefaultHttpClient();

	}

	public void logOut() {

		new Thread(new Runnable() {

			@Override
			public void run() {

				HttpGet httpget = new HttpGet(LOGOUT_URL);
				try {

					HttpResponse resp = client.execute(httpget);

					// System.out.println(IOUtils.toString(resp.getEntity()
					// .getContent()));
					delegate.onLogOut("" + resp.getStatusLine().getStatusCode());

				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();

	}

	public void logIn(final String user, final String password) {

		new Thread(new Runnable() {

			@Override
			public void run() {

				HttpPost httppost = new HttpPost(LOGIN_URL);

				List<NameValuePair> formparams = new ArrayList<NameValuePair>();
				formparams.add(new BasicNameValuePair(LOGIN_USERNAME_PARAM,
						user));
				formparams.add(new BasicNameValuePair(LOGIN_PASSWPORD_PARAM,
						password));
				UrlEncodedFormEntity entity;
				try {

					entity = new UrlEncodedFormEntity(formparams, HTTP.UTF_8);
					httppost.setEntity(entity);

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				HttpResponse response;
				try {

					response = client.execute(httppost);
					delegate.onLogIn(null, ""
							+ response.getStatusLine().getStatusCode());

					// HttpEntity respEntity = response.getEntity();
					// System.out.println(IOUtils.toString(respEntity.getContent()));

				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();

	}

	/**
	 * --------------------------------------- 
	 * FOR TESTING AND DEBUGGING
	 * PURPOSES 
	 * ---------------------------------------
	 */

	public static void main(String[] args) {

		// First decline service and delegate

		WKWService access = new WKWService(new WKWServiceDelegatable() {

			@Override
			public void onUpdate(ArrayList<ParentNewsPost> posts, String status) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPost(ArrayList<ParentNewsPost> posts, String status) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onLogOut(String status) {

				System.out.println("Logged Out");
				System.out.println(status);

			}

			@Override
			public void onLogIn(ArrayList<ParentNewsPost> posts, String status) {

				System.out.println("Logged In");
				System.out.println(status);

			}

			@Override
			public void onComment(ArrayList<ParentNewsPost> posts, String status) {
				// TODO Auto-generated method stub

			}
		});

		// Call the service functions

		// access.logIn("user", "password");
		access.logOut();
	}

}
