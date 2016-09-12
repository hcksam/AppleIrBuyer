package com.chy.appleirbuyer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class WebViewActivity extends Activity {
	private static WebViewActivity inst;
	private WebView webView;
	private ProgressBar progress;
	private EditText sendSmsCodeField;
	private EditText receiverNumber;
	private EditText previousSMSCodeField;
	private Button sendSMS;
	private SharedPreferences sharedPreferences;
	
	public static WebViewActivity instance() {
        return inst;
    }
	
	@Override
    public void onStart() {
        super.onStart();
        inst = this;
    }
	
	public void updateSMSCode(String receiveSmsCode) {
        sendSmsCodeField.setText(receiveSmsCode);
        sharedPreferences.edit().putString("previousSMSCode", receiveSmsCode).apply();
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		sharedPreferences = getSharedPreferences("irData", MODE_PRIVATE);
		
		webView = (WebView) findViewById(R.id.webView);
		progress = (ProgressBar) findViewById(R.id.progressBar);
		sendSmsCodeField = (EditText) findViewById(R.id.sendSmsCodeField);
		receiverNumber = (EditText) findViewById(R.id.receiverNumber);
		previousSMSCodeField = (EditText) findViewById(R.id.previousSMSCodeField);
		progress.setMax(100);
		
		previousSMSCodeField.setText(sharedPreferences.getString("previousSMSCode", ""));
		
		sendSMS = (Button) findViewById(R.id.goButton);
		sendSMS.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				if (sendSmsCodeField.getText().toString().length() > 3 && receiverNumber.getText().toString().length() == 8) {
					SmsManager sms = SmsManager.getDefault();
					sms.sendTextMessage(receiverNumber.getText().toString(), null, sendSmsCodeField.getText().toString(), null,
							null);
					Toast.makeText(view.getContext(), "Message sended", Toast.LENGTH_SHORT).show();
				}

			}
		});

		initWebViewSetting();
	}

	private void initWebViewSetting() {
		clearCookie();
		webView.clearCache(true);
		webView.clearHistory();
		webView.setWebChromeClient(new JsPopupWebViewChrome());
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient());

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String requestUrl = extras.getString("requestUrl");
			webView.loadUrl(requestUrl);
		}
	}

	@SuppressLint("NewApi")
	public void clearCookie() {
		CookieManager.getInstance().removeAllCookies(null);
		CookieManager.getInstance().flush();
	}

	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this).setMessage("Are you sure you want to exit?").setCancelable(false)
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						WebViewActivity.this.finish();
					}
				}).setNegativeButton("No", null).show();
	}

	public class JsPopupWebViewChrome extends WebChromeClient {
		@Override
		public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
			AlertDialog.Builder b = new AlertDialog.Builder(view.getContext()).setTitle(view.getTitle())
					.setMessage(message).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							result.confirm();
						}
					}).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							result.cancel();
						}
					});

			b.show();

			// Indicate that we're handling this manually
			return true;
		}

		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			WebViewActivity.this.setValue(newProgress);
			super.onProgressChanged(view, newProgress);
		}
	}

	public void setValue(int progress) {
		this.progress.setProgress(progress);
	}

}
