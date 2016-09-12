package com.chy.appleirbuyer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class IncomingSms extends BroadcastReceiver {

	// Get the object of SmsManager
	final SmsManager sms = SmsManager.getDefault();
	String smsPattern = "你的預訂代碼。Your registration code is: ([a-zA-Z0-9]+)";
	//你的預訂代碼。Your registration code is: 8RYJJPEN.
	public void onReceive(Context context, Intent intent) {
		// Retrieves a map of extended data from the intent.
		final Bundle bundle = intent.getExtras();
		try {
			if (bundle != null) {
				final Object[] pdusObj = (Object[]) bundle.get("pdus");
				for (int i = 0; i < pdusObj.length; i++) {
					SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
					String phoneNumber = currentMessage.getDisplayOriginatingAddress();

					String senderNum = phoneNumber;
					String message = currentMessage.getDisplayMessageBody();
					Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);
					Pattern pattern = Pattern.compile(smsPattern);
					Matcher matcher = pattern.matcher(message);
					if (matcher.find()) {
						WebViewActivity inst = WebViewActivity.instance();
						inst.updateSMSCode(matcher.group(1));
					}
				}
			}

		} catch (Exception e) {
			Log.e("SmsReceiver", "Exception smsReceiver" + e);

		}
	}
}