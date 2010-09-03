package de.owkwo.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTML.Tag;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import de.owkwo.model.posts.ParentNewsPost;
import de.owkwo.service.delegate.interfaces.WKWServiceDelegatable;

public class WKWService {

	/**
	 * Constants for all the URLs needed on Wer-kennt-wen.de
	 */

	public static final String WKW_START_PAGE = "http://mobil.wer-kennt-wen.de/";

	public static final String LOGIN_URL = "http://mobil.wer-kennt-wen.de/index/";
	public static final String LOGOUT_URL = "http://mobil.wer-kennt-wen.de/index/logout/";
	public static final String LOGIN_REDIRECT_SUCCESS = "/start/";
	public static final String LOGIN_REDIRECT_FAIL = "/";
	public static final String LOGIN_USERNAME_PARAM = "email";
	public static final String LOGIN_PASSWPORD_PARAM = "password";
	public static final String LOGIN_HASH_PARAM = "_hash";
	public static final String LOGIN_HASHKEY_PARAM = "_hashKey";
	public static final String LOGIN_SEND_PARAM_VALUE = "Login";
	public static final String LOGIN_SEND_PARAM = "send";
	public static final String LOGIN_SETCOOKIE_PARAM = "setCookie";
	public static final String LOGIN_SETCOOKIE_VALUE = "0";

	public static final String PROFILE_URL = "http://www.wer-kennt-wen.de/person";
	public static final String PROFILE_ANY_URL = "http://www.wer-kennt-wen.de/person/";

	public static final String SETTINGS_MAIN_URL = "http://www.wer-kennt-wen.de/settings/main/";
	public static final String SETTINGS_CONTACT_URL = "http://www.wer-kennt-wen.de/settings/contact/";
	public static final String SETTINGS_SCHOOL_URL = "http://www.wer-kennt-wen.de/settings/school/";
	public static final String SETTINGS_PERSONAL_URL = "http://www.wer-kennt-wen.de/settings/personal/";
	public static final String SETTINGS_PHOTO_URL = "http://www.wer-kennt-wen.de/settings/photo/";

	public static final String NEWS_URL = "http://mobil.wer-kennt-wen.de/justnow/news/";
	public static final String NEWS_PAGE_ANY_URL = "http://mobil.wer-kennt-wen.de/justnow/news/page/";

	private WKWServiceDelegatable delegate;
	private DefaultHttpClient client;

	// Used for cashing the hash-Keys
	private String hash;
	private String hashKey;

	public void setDelegate(WKWServiceDelegatable delegate) {

		this.delegate = delegate;

	}

	public WKWServiceDelegatable getDelegate() {

		return this.delegate;

	}

	public WKWService(WKWServiceDelegatable delegate) {

		this.delegate = delegate;
		this.client = new DefaultHttpClient();
		client.getParams().setParameter(ClientPNames.COOKIE_POLICY,
				CookiePolicy.BROWSER_COMPATIBILITY);

	}

	public void logOut() {

		new Thread(new Runnable() {

			@Override
			public void run() {

				HttpGet httpget = new HttpGet(LOGOUT_URL);
				try {

					HttpResponse resp = client.execute(httpget);

					resp.getEntity().consumeContent();

					delegate.onLogOut(WKWServiceDelegatable.STATUS_SUCCESSFUL);

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

				// First of alle create hashkeys to emulate browser usage for
				// wkw
				initHashKeys();

				HttpPost httppost = new HttpPost(LOGIN_URL);
				httppost.addHeader(
						"User-Agent",
						"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_4; en-US) AppleWebKit/534.3 (KHTML, like Gecko) Chrome/6.0.472.53 Safari/534.3");
				httppost.addHeader(
						"Accept",
						"application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
				httppost.addHeader("Content-Type",
						"application/x-www-form-urlencoded");

				List<NameValuePair> formparams = new ArrayList<NameValuePair>();
				formparams.add(new BasicNameValuePair(LOGIN_USERNAME_PARAM,
						user));
				formparams.add(new BasicNameValuePair(LOGIN_PASSWPORD_PARAM,
						password));
				formparams.add(new BasicNameValuePair(LOGIN_SETCOOKIE_PARAM,
						LOGIN_SETCOOKIE_VALUE));
				formparams.add(new BasicNameValuePair("_hash", hash));
				formparams.add(new BasicNameValuePair("_hashKey", hashKey));
				formparams.add(new BasicNameValuePair("LOGIN_SEND_PARAM",
						"LOGIN_SEND_PARAM_VALUE"));

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
					Header[] headers = response.getHeaders("location");
					String redirectHeader = headers[0].getValue();
					System.out.println(redirectHeader);
					HttpEntity respEntity = response.getEntity();
					respEntity.consumeContent();

					// System.out.println(IOUtils.toString(resp.getEntity()
					// .getContent()));

					// List<Cookie> cookies =
					// client.getCookieStore().getCookies();
					// if (cookies.isEmpty()) {
					// System.out.println("None");
					// } else {
					// System.out.println("Cookie Store:");
					// for (int i = 0; i < cookies.size(); i++) {
					// System.out.println("- " + cookies.get(i).toString());
					// }
					// }
					//

					delegate.onLogIn((redirectHeader
							.equals(LOGIN_REDIRECT_SUCCESS) ? WKWServiceDelegatable.STATUS_SUCCESSFUL
							: WKWServiceDelegatable.STATUS_ERROR));

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

	protected void initHashKeys() {

		HttpGet httpget = new HttpGet(WKW_START_PAGE);
		try {

			HttpResponse resp = client.execute(httpget);

			String htmlPage = IOUtils.toString(resp.getEntity().getContent());

			HtmlCleaner cleaner = new HtmlCleaner();
			TagNode node = cleaner.clean(htmlPage);
			Object[] hashResults = node
					.evaluateXPath("//input[@name='_hash']/@value");
			Object[] hashKeyResults = node
					.evaluateXPath("//input[@name='_hashKey']/@value");

			this.hash = hashResults[0].toString();
			this.hashKey = hashKeyResults[0].toString();

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPatherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * --------------------------------------- FOR TESTING AND DEBUGGING
	 * PURPOSES ---------------------------------------
	 * 
	 * @throws InterruptedException
	 */

	public static void main(String[] args) throws InterruptedException {

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

				System.out.println("Log Out Handler Call");
				System.out.println(status);

			}

			@Override
			public void onLogIn(String status) {

				System.out.println("Log In Handler Call");
				System.out.println(status);

			}

			@Override
			public void onComment(ArrayList<ParentNewsPost> posts, String status) {
				// TODO Auto-generated method stub

			}
		});

		// Call the service functions
		
		//Try to login
		access.logIn("email", "pass");

	}

}
