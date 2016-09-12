package com.chy.appleirbuyer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	private MainButton mButton = new MainButton();
	private Button refreshButton;

	private final String appleIrUrl = "https://reserve-hk.apple.com/HK/zh_HK/reserve/iPhone?partNumber=[ModelNo]&channel=1&rv=&path=&sourceID=&iPP=false&appleCare=&iUID=&iuToken=&carrier=&store=[storeNo]";
	private static final String availabilityJson = "https://reserve.cdn-apple.com/HK/zh_HK/reserve/iPhone/availability.json";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Intent intent = new Intent(this, WebViewActivity.class);
		startActivity(intent);

		initButton();
		// checkModelIsAvaliable();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent = new Intent(this, InformationActivity.class);
			startActivity(intent);
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		int intID = v.getId();
		if (intID == refreshButton.getId()) {
			checkModelIsAvaliable();
		} else {
			String webviewIdNameInApp = getResources().getResourceName(intID);
			String webViewIdName = webviewIdNameInApp.substring(webviewIdNameInApp.indexOf("/") + 1);
			String storeId = CommonMapping.STOREMAP.get(webViewIdName.substring(0, webViewIdName.indexOf("_")));
			String modelId = CommonMapping.MODELMAP.get(webViewIdName.substring(webViewIdName.indexOf("_") + 1));

			String requestUrl = appleIrUrl.replace("[ModelNo]", modelId).replace("[storeNo]", storeId);

			Intent intent = new Intent(this, WebViewActivity.class);
			intent.putExtra("requestUrl", requestUrl);
			startActivity(intent);
		}
	}

	public void checkModelIsAvaliable() {
		MainButton.setModelButtonDisable(mButton);
		Thread thread = new Thread(checkIsAvailableThread);
		thread.start();
	}

	private Runnable checkIsAvailableThread = new Runnable() {
		public void run() {
			try {
				JSONObject responseJson = getJSONObjectFromURL(availabilityJson);
				
				JSONObject ifcStoreStatus = responseJson.getJSONObject("R428");
				JSONObject cwbStoreStatus = responseJson.getJSONObject("R409");
				JSONObject crStoreStatus = responseJson.getJSONObject("R499");
				JSONObject fwStoreStatus = responseJson.getJSONObject("R485");
				JSONObject ntpStoreStatus = responseJson.getJSONObject("R610");
				
				ArrayList<Button> enableButtonList = new ArrayList<Button>();

				if (!ifcStoreStatus.getString("MN4L2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_IFC_256_Plus_JBlack);
				}

				if (!ifcStoreStatus.getString("MN4E2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_IFC_256_Plus_Black);
				}

				if (!ifcStoreStatus.getString("MN4F2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_IFC_256_Plus_Sliver);
				}

				if (!ifcStoreStatus.getString("MN4J2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_IFC_256_Plus_Gold);
				}

				if (!ifcStoreStatus.getString("MN4K2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_IFC_256_Plus_Pink);
				}

				if (!ifcStoreStatus.getString("MN4D2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_IFC_128_Plus_JBlack);
				}

				if (!ifcStoreStatus.getString("MN482ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_IFC_128_Plus_Black);
				}

				if (!ifcStoreStatus.getString("MN492ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_IFC_128_Plus_Sliver);
				}

				if (!ifcStoreStatus.getString("MN4A2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_IFC_128_Plus_Gold);
				}

				if (!ifcStoreStatus.getString("MN4C2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_IFC_128_Plus_Pink);
				}

				if (!ifcStoreStatus.getString("MNQH2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_IFC_32_Plus_Black);
				}

				if (!ifcStoreStatus.getString("MNQJ2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_IFC_32_Plus_Sliver);
				}

				if (!ifcStoreStatus.getString("MNQK2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_IFC_32_Plus_Gold);
				}

				if (!ifcStoreStatus.getString("MNQL2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_IFC_32_Plus_Pink);
				}

				/*----------------------------------*/

				if (!cwbStoreStatus.getString("MN4L2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CWB_256_Plus_JBlack);
				}

				if (!cwbStoreStatus.getString("MN4E2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CWB_256_Plus_Black);
				}

				if (!cwbStoreStatus.getString("MN4F2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CWB_256_Plus_Sliver);
				}

				if (!cwbStoreStatus.getString("MN4J2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CWB_256_Plus_Gold);
				}

				if (!cwbStoreStatus.getString("MN4K2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CWB_256_Plus_Pink);
				}

				if (!cwbStoreStatus.getString("MN4D2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CWB_128_Plus_JBlack);
				}

				if (!cwbStoreStatus.getString("MN482ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CWB_128_Plus_Black);
				}

				if (!cwbStoreStatus.getString("MN492ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CWB_128_Plus_Sliver);
				}

				if (!cwbStoreStatus.getString("MN4A2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CWB_128_Plus_Gold);
				}

				if (!cwbStoreStatus.getString("MN4C2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CWB_128_Plus_Pink);
				}

				if (!cwbStoreStatus.getString("MNQH2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CWB_32_Plus_Black);
				}

				if (!cwbStoreStatus.getString("MNQJ2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CWB_32_Plus_Sliver);
				}

				if (!cwbStoreStatus.getString("MNQK2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CWB_32_Plus_Gold);
				}

				if (!cwbStoreStatus.getString("MNQL2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CWB_32_Plus_Pink);
				}

				/*----------------------------------*/

				if (!crStoreStatus.getString("MN4L2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CR_256_Plus_JBlack);
				}

				if (!crStoreStatus.getString("MN4E2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CR_256_Plus_Black);
				}

				if (!crStoreStatus.getString("MN4F2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CR_256_Plus_Sliver);
				}

				if (!crStoreStatus.getString("MN4J2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CR_256_Plus_Gold);
				}

				if (!crStoreStatus.getString("MN4K2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CR_256_Plus_Pink);
				}

				if (!crStoreStatus.getString("MN4D2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CR_128_Plus_JBlack);
				}

				if (!crStoreStatus.getString("MN482ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CR_128_Plus_Black);
				}

				if (!crStoreStatus.getString("MN492ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CR_128_Plus_Sliver);
				}

				if (!crStoreStatus.getString("MN4A2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CR_128_Plus_Gold);
				}

				if (!crStoreStatus.getString("MN4C2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CR_128_Plus_Pink);
				}

				if (!crStoreStatus.getString("MNQH2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CR_32_Plus_Black);
				}

				if (!crStoreStatus.getString("MNQJ2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CR_32_Plus_Sliver);
				}

				if (!crStoreStatus.getString("MNQK2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CR_32_Plus_Gold);
				}

				if (!crStoreStatus.getString("MNQL2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_CR_32_Plus_Pink);
				}

				/*----------------------------------*/

				if (!fwStoreStatus.getString("MN4L2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_FW_256_Plus_JBlack);
				}

				if (!fwStoreStatus.getString("MN4E2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_FW_256_Plus_Black);
				}

				if (!fwStoreStatus.getString("MN4F2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_FW_256_Plus_Sliver);
				}

				if (!fwStoreStatus.getString("MN4J2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_FW_256_Plus_Gold);
				}

				if (!fwStoreStatus.getString("MN4K2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_FW_256_Plus_Pink);
				}

				if (!fwStoreStatus.getString("MN4D2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_FW_128_Plus_JBlack);
				}

				if (!fwStoreStatus.getString("MN482ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_FW_128_Plus_Black);
				}

				if (!fwStoreStatus.getString("MN492ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_FW_128_Plus_Sliver);
				}

				if (!fwStoreStatus.getString("MN4A2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_FW_128_Plus_Gold);
				}

				if (!fwStoreStatus.getString("MN4C2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_FW_128_Plus_Pink);
				}

				if (!fwStoreStatus.getString("MNQH2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_FW_32_Plus_Black);
				}

				if (!fwStoreStatus.getString("MNQJ2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_FW_32_Plus_Sliver);
				}

				if (!fwStoreStatus.getString("MNQK2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_FW_32_Plus_Gold);
				}

				if (!fwStoreStatus.getString("MNQL2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_FW_32_Plus_Pink);
				}

				/*----------------------------------*/

				if (!ntpStoreStatus.getString("MN4L2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_NTP_256_Plus_JBlack);
				}

				if (!ntpStoreStatus.getString("MN4E2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_NTP_256_Plus_Black);
				}

				if (!ntpStoreStatus.getString("MN4F2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_NTP_256_Plus_Sliver);
				}

				if (!ntpStoreStatus.getString("MN4J2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_NTP_256_Plus_Gold);
				}

				if (!ntpStoreStatus.getString("MN4K2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_NTP_256_Plus_Pink);
				}

				if (!ntpStoreStatus.getString("MN4D2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_NTP_128_Plus_JBlack);
				}

				if (!ntpStoreStatus.getString("MN482ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_NTP_128_Plus_Black);
				}

				if (!ntpStoreStatus.getString("MN492ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_NTP_128_Plus_Sliver);
				}

				if (!ntpStoreStatus.getString("MN4A2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_NTP_128_Plus_Gold);
				}

				if (!ntpStoreStatus.getString("MN4C2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_NTP_128_Plus_Pink);
				}

				if (!ntpStoreStatus.getString("MNQH2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_NTP_32_Plus_Black);
				}

				if (!ntpStoreStatus.getString("MNQJ2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_NTP_32_Plus_Sliver);
				}

				if (!ntpStoreStatus.getString("MNQK2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_NTP_32_Plus_Gold);
				}

				if (!ntpStoreStatus.getString("MNQL2ZP/A").equals("NONE")) {
					enableButtonList.add(mButton.Button_NTP_32_Plus_Pink);
				}
				
				for (int i = 0; i < enableButtonList.size(); i++) {
					Button temp = enableButtonList.get(i);
					
					setButtonEnable(temp);
				}

			} catch (Exception e) {
				System.out.println("Exception");
			}
		}
		
		public void setButtonEnable(final Button btn){
		    runOnUiThread(new Runnable() {
		        @Override
		        public void run() {
		        	btn.setEnabled(true);
		        	btn.setTextColor(Color.parseColor("#000000"));
		        }
		    });
		}
	};
	

	public static JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {
		HttpURLConnection urlConnection = null;

		URL url = new URL(urlString);
		urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("GET");
		urlConnection.setReadTimeout(10000 /* milliseconds */);
		urlConnection.setConnectTimeout(15000 /* milliseconds */);
		urlConnection.setDoOutput(true);
		urlConnection.connect();

		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

		@SuppressWarnings("unused")
		char[] buffer = new char[1024];

		String jsonString = new String();

		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}
		br.close();

		jsonString = sb.toString();
		System.out.println("JSON: " + jsonString);
		return new JSONObject(jsonString);
	}

	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this).setMessage("Are you sure want to exit?").setCancelable(false)
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						MainActivity.this.finish();
					}
				}).setNegativeButton("No", null).show();
	}

	public void initButton() {
		mButton.Button_IFC_256_Plus_JBlack = (Button) findViewById(R.id.IFC_256_Plus_JBlack);
		mButton.Button_IFC_256_Plus_Black = (Button) findViewById(R.id.IFC_256_Plus_Black);
		mButton.Button_IFC_256_Plus_Sliver = (Button) findViewById(R.id.IFC_256_Plus_Sliver);
		mButton.Button_IFC_256_Plus_Gold = (Button) findViewById(R.id.IFC_256_Plus_Gold);
		mButton.Button_IFC_256_Plus_Pink = (Button) findViewById(R.id.IFC_256_Plus_Pink);
		mButton.Button_IFC_128_Plus_JBlack = (Button) findViewById(R.id.IFC_128_Plus_JBlack);
		mButton.Button_IFC_128_Plus_Black = (Button) findViewById(R.id.IFC_128_Plus_Black);
		mButton.Button_IFC_128_Plus_Sliver = (Button) findViewById(R.id.IFC_128_Plus_Sliver);
		mButton.Button_IFC_128_Plus_Gold = (Button) findViewById(R.id.IFC_128_Plus_Gold);
		mButton.Button_IFC_128_Plus_Pink = (Button) findViewById(R.id.IFC_128_Plus_Pink);
		mButton.Button_IFC_32_Plus_Black = (Button) findViewById(R.id.IFC_32_Plus_Black);
		mButton.Button_IFC_32_Plus_Sliver = (Button) findViewById(R.id.IFC_32_Plus_Sliver);
		mButton.Button_IFC_32_Plus_Gold = (Button) findViewById(R.id.IFC_32_Plus_Gold);
		mButton.Button_IFC_32_Plus_Pink = (Button) findViewById(R.id.IFC_32_Plus_Pink);

		mButton.Button_CWB_256_Plus_JBlack = (Button) findViewById(R.id.CWB_256_Plus_JBlack);
		mButton.Button_CWB_256_Plus_Black = (Button) findViewById(R.id.CWB_256_Plus_Black);
		mButton.Button_CWB_256_Plus_Sliver = (Button) findViewById(R.id.CWB_256_Plus_Sliver);
		mButton.Button_CWB_256_Plus_Gold = (Button) findViewById(R.id.CWB_256_Plus_Gold);
		mButton.Button_CWB_256_Plus_Pink = (Button) findViewById(R.id.CWB_256_Plus_Pink);
		mButton.Button_CWB_128_Plus_JBlack = (Button) findViewById(R.id.CWB_128_Plus_JBlack);
		mButton.Button_CWB_128_Plus_Black = (Button) findViewById(R.id.CWB_128_Plus_Black);
		mButton.Button_CWB_128_Plus_Sliver = (Button) findViewById(R.id.CWB_128_Plus_Sliver);
		mButton.Button_CWB_128_Plus_Gold = (Button) findViewById(R.id.CWB_128_Plus_Gold);
		mButton.Button_CWB_128_Plus_Pink = (Button) findViewById(R.id.CWB_128_Plus_Pink);
		mButton.Button_CWB_32_Plus_Black = (Button) findViewById(R.id.CWB_32_Plus_Black);
		mButton.Button_CWB_32_Plus_Sliver = (Button) findViewById(R.id.CWB_32_Plus_Sliver);
		mButton.Button_CWB_32_Plus_Gold = (Button) findViewById(R.id.CWB_32_Plus_Gold);
		mButton.Button_CWB_32_Plus_Pink = (Button) findViewById(R.id.CWB_32_Plus_Pink);

		mButton.Button_CR_256_Plus_JBlack = (Button) findViewById(R.id.CR_256_Plus_JBlack);
		mButton.Button_CR_256_Plus_Black = (Button) findViewById(R.id.CR_256_Plus_Black);
		mButton.Button_CR_256_Plus_Sliver = (Button) findViewById(R.id.CR_256_Plus_Sliver);
		mButton.Button_CR_256_Plus_Gold = (Button) findViewById(R.id.CR_256_Plus_Gold);
		mButton.Button_CR_256_Plus_Pink = (Button) findViewById(R.id.CR_256_Plus_Pink);
		mButton.Button_CR_128_Plus_JBlack = (Button) findViewById(R.id.CR_128_Plus_JBlack);
		mButton.Button_CR_128_Plus_Black = (Button) findViewById(R.id.CR_128_Plus_Black);
		mButton.Button_CR_128_Plus_Sliver = (Button) findViewById(R.id.CR_128_Plus_Sliver);
		mButton.Button_CR_128_Plus_Gold = (Button) findViewById(R.id.CR_128_Plus_Gold);
		mButton.Button_CR_128_Plus_Pink = (Button) findViewById(R.id.CR_128_Plus_Pink);
		mButton.Button_CR_32_Plus_Black = (Button) findViewById(R.id.CR_32_Plus_Black);
		mButton.Button_CR_32_Plus_Sliver = (Button) findViewById(R.id.CR_32_Plus_Sliver);
		mButton.Button_CR_32_Plus_Gold = (Button) findViewById(R.id.CR_32_Plus_Gold);
		mButton.Button_CR_32_Plus_Pink = (Button) findViewById(R.id.CR_32_Plus_Pink);

		mButton.Button_FW_256_Plus_JBlack = (Button) findViewById(R.id.FW_256_Plus_JBlack);
		mButton.Button_FW_256_Plus_Black = (Button) findViewById(R.id.FW_256_Plus_Black);
		mButton.Button_FW_256_Plus_Sliver = (Button) findViewById(R.id.FW_256_Plus_Sliver);
		mButton.Button_FW_256_Plus_Gold = (Button) findViewById(R.id.FW_256_Plus_Gold);
		mButton.Button_FW_256_Plus_Pink = (Button) findViewById(R.id.FW_256_Plus_Pink);
		mButton.Button_FW_128_Plus_JBlack = (Button) findViewById(R.id.FW_128_Plus_JBlack);
		mButton.Button_FW_128_Plus_Black = (Button) findViewById(R.id.FW_128_Plus_Black);
		mButton.Button_FW_128_Plus_Sliver = (Button) findViewById(R.id.FW_128_Plus_Sliver);
		mButton.Button_FW_128_Plus_Gold = (Button) findViewById(R.id.FW_128_Plus_Gold);
		mButton.Button_FW_128_Plus_Pink = (Button) findViewById(R.id.FW_128_Plus_Pink);
		mButton.Button_FW_32_Plus_Black = (Button) findViewById(R.id.FW_32_Plus_Black);
		mButton.Button_FW_32_Plus_Sliver = (Button) findViewById(R.id.FW_32_Plus_Sliver);
		mButton.Button_FW_32_Plus_Gold = (Button) findViewById(R.id.FW_32_Plus_Gold);
		mButton.Button_FW_32_Plus_Pink = (Button) findViewById(R.id.FW_32_Plus_Pink);

		mButton.Button_NTP_256_Plus_JBlack = (Button) findViewById(R.id.NTP_256_Plus_JBlack);
		mButton.Button_NTP_256_Plus_Black = (Button) findViewById(R.id.NTP_256_Plus_Black);
		mButton.Button_NTP_256_Plus_Sliver = (Button) findViewById(R.id.NTP_256_Plus_Sliver);
		mButton.Button_NTP_256_Plus_Gold = (Button) findViewById(R.id.NTP_256_Plus_Gold);
		mButton.Button_NTP_256_Plus_Pink = (Button) findViewById(R.id.NTP_256_Plus_Pink);
		mButton.Button_NTP_128_Plus_JBlack = (Button) findViewById(R.id.NTP_128_Plus_JBlack);
		mButton.Button_NTP_128_Plus_Black = (Button) findViewById(R.id.NTP_128_Plus_Black);
		mButton.Button_NTP_128_Plus_Sliver = (Button) findViewById(R.id.NTP_128_Plus_Sliver);
		mButton.Button_NTP_128_Plus_Gold = (Button) findViewById(R.id.NTP_128_Plus_Gold);
		mButton.Button_NTP_128_Plus_Pink = (Button) findViewById(R.id.NTP_128_Plus_Pink);
		mButton.Button_NTP_32_Plus_Black = (Button) findViewById(R.id.NTP_32_Plus_Black);
		mButton.Button_NTP_32_Plus_Sliver = (Button) findViewById(R.id.NTP_32_Plus_Sliver);
		mButton.Button_NTP_32_Plus_Gold = (Button) findViewById(R.id.NTP_32_Plus_Gold);
		mButton.Button_NTP_32_Plus_Pink = (Button) findViewById(R.id.NTP_32_Plus_Pink);

		refreshButton = (Button) findViewById(R.id.Refresh_Store);

		mButton.Button_IFC_256_Plus_JBlack.setOnClickListener(this);
		mButton.Button_IFC_256_Plus_Black.setOnClickListener(this);
		mButton.Button_IFC_256_Plus_Sliver.setOnClickListener(this);
		mButton.Button_IFC_256_Plus_Gold.setOnClickListener(this);
		mButton.Button_IFC_256_Plus_Pink.setOnClickListener(this);
		mButton.Button_IFC_128_Plus_JBlack.setOnClickListener(this);
		mButton.Button_IFC_128_Plus_Black.setOnClickListener(this);
		mButton.Button_IFC_128_Plus_Sliver.setOnClickListener(this);
		mButton.Button_IFC_128_Plus_Gold.setOnClickListener(this);
		mButton.Button_IFC_128_Plus_Pink.setOnClickListener(this);
		mButton.Button_IFC_32_Plus_Black.setOnClickListener(this);
		mButton.Button_IFC_32_Plus_Sliver.setOnClickListener(this);
		mButton.Button_IFC_32_Plus_Gold.setOnClickListener(this);
		mButton.Button_IFC_32_Plus_Pink.setOnClickListener(this);

		mButton.Button_CWB_256_Plus_JBlack.setOnClickListener(this);
		mButton.Button_CWB_256_Plus_Black.setOnClickListener(this);
		mButton.Button_CWB_256_Plus_Sliver.setOnClickListener(this);
		mButton.Button_CWB_256_Plus_Gold.setOnClickListener(this);
		mButton.Button_CWB_256_Plus_Pink.setOnClickListener(this);
		mButton.Button_CWB_128_Plus_JBlack.setOnClickListener(this);
		mButton.Button_CWB_128_Plus_Black.setOnClickListener(this);
		mButton.Button_CWB_128_Plus_Sliver.setOnClickListener(this);
		mButton.Button_CWB_128_Plus_Gold.setOnClickListener(this);
		mButton.Button_CWB_128_Plus_Pink.setOnClickListener(this);
		mButton.Button_CWB_32_Plus_Black.setOnClickListener(this);
		mButton.Button_CWB_32_Plus_Sliver.setOnClickListener(this);
		mButton.Button_CWB_32_Plus_Gold.setOnClickListener(this);
		mButton.Button_CWB_32_Plus_Pink.setOnClickListener(this);

		mButton.Button_CR_256_Plus_JBlack.setOnClickListener(this);
		mButton.Button_CR_256_Plus_Black.setOnClickListener(this);
		mButton.Button_CR_256_Plus_Sliver.setOnClickListener(this);
		mButton.Button_CR_256_Plus_Gold.setOnClickListener(this);
		mButton.Button_CR_256_Plus_Pink.setOnClickListener(this);
		mButton.Button_CR_128_Plus_JBlack.setOnClickListener(this);
		mButton.Button_CR_128_Plus_Black.setOnClickListener(this);
		mButton.Button_CR_128_Plus_Sliver.setOnClickListener(this);
		mButton.Button_CR_128_Plus_Gold.setOnClickListener(this);
		mButton.Button_CR_128_Plus_Pink.setOnClickListener(this);
		mButton.Button_CR_32_Plus_Black.setOnClickListener(this);
		mButton.Button_CR_32_Plus_Sliver.setOnClickListener(this);
		mButton.Button_CR_32_Plus_Gold.setOnClickListener(this);
		mButton.Button_CR_32_Plus_Pink.setOnClickListener(this);

		mButton.Button_FW_256_Plus_JBlack.setOnClickListener(this);
		mButton.Button_FW_256_Plus_Black.setOnClickListener(this);
		mButton.Button_FW_256_Plus_Sliver.setOnClickListener(this);
		mButton.Button_FW_256_Plus_Gold.setOnClickListener(this);
		mButton.Button_FW_256_Plus_Pink.setOnClickListener(this);
		mButton.Button_FW_128_Plus_JBlack.setOnClickListener(this);
		mButton.Button_FW_128_Plus_Black.setOnClickListener(this);
		mButton.Button_FW_128_Plus_Sliver.setOnClickListener(this);
		mButton.Button_FW_128_Plus_Gold.setOnClickListener(this);
		mButton.Button_FW_128_Plus_Pink.setOnClickListener(this);
		mButton.Button_FW_32_Plus_Black.setOnClickListener(this);
		mButton.Button_FW_32_Plus_Sliver.setOnClickListener(this);
		mButton.Button_FW_32_Plus_Gold.setOnClickListener(this);
		mButton.Button_FW_32_Plus_Pink.setOnClickListener(this);

		mButton.Button_NTP_256_Plus_JBlack.setOnClickListener(this);
		mButton.Button_NTP_256_Plus_Black.setOnClickListener(this);
		mButton.Button_NTP_256_Plus_Sliver.setOnClickListener(this);
		mButton.Button_NTP_256_Plus_Gold.setOnClickListener(this);
		mButton.Button_NTP_256_Plus_Pink.setOnClickListener(this);
		mButton.Button_NTP_128_Plus_JBlack.setOnClickListener(this);
		mButton.Button_NTP_128_Plus_Black.setOnClickListener(this);
		mButton.Button_NTP_128_Plus_Sliver.setOnClickListener(this);
		mButton.Button_NTP_128_Plus_Gold.setOnClickListener(this);
		mButton.Button_NTP_128_Plus_Pink.setOnClickListener(this);
		mButton.Button_NTP_32_Plus_Black.setOnClickListener(this);
		mButton.Button_NTP_32_Plus_Sliver.setOnClickListener(this);
		mButton.Button_NTP_32_Plus_Gold.setOnClickListener(this);
		mButton.Button_NTP_32_Plus_Pink.setOnClickListener(this);

		refreshButton.setOnClickListener(this);
	}
}
