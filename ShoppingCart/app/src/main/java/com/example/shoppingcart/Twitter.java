package com.example.shoppingcart;


import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Twitter extends Activity {
WebView t;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_twitter);
		t=(WebView) findViewById(R.id.webt);
		//t.loadUrl("https://twitter.com/");
		startWebView("http://www.Twitter.com");
	}
	private void startWebView(String url) {

		// Create new webView Client to show progress dialog
		// When opening a urL or click on link

		t.setWebViewClient(new WebViewClient() {
			ProgressDialog progressDialog;

			// If you will not use this method urL links are open in new browser
			// not in webView
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			// Show loader on urL load
			public void onLoadResource(WebView view, String url) {
				if (progressDialog == null) {
					// in standard case YourActivity.this
					progressDialog = new ProgressDialog(Twitter.this);
					progressDialog.setMessage("Loading...");
					progressDialog.show();
				}
			}

			public void onPageFinished(WebView view, String url) {
				try {
					if (progressDialog.isShowing()) {
						progressDialog.dismiss();
						progressDialog = null;
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}

		});

		// JavaScript enabled on webView
		t.getSettings().setJavaScriptEnabled(true);

		// Other webView options
		/*
		 * webView.getSettings().setLoadWithOverviewMode(true);
		 * webView.getSettings().setUseWideViewPort(true);
		 * webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		 * webView.setScrollbarFadingEnabled(false);
		 * webView.getSettings().setBuiltInZoomControls(true);
		 */

		/*
		 * String summary =
		 * "<htmL><body>You scored <b>192</b> points.</body></htmL>";
		 * webview.loadData(summary, "text/htmL", null);
		 */

		// Load urL in webView
		t.loadUrl(url);

	}

	// Open previous opened link from history on webView when back button
	// pressed

	@Override
	// Detect when the back button is pressed
	public void onBackPressed() {
		if (t.canGoBack()) {
			t.goBack();
		} else {
			// Let the system handle the back button
			super.onBackPressed();
		}
	}






	
}
