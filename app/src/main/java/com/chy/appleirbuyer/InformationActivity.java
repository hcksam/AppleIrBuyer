package com.chy.appleirbuyer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class InformationActivity extends Activity {
	Button saveDataBtn;
	EditText appleId, password, phoneNumber, lastName, firstName, email;
	Spinner selectedGovtIdType, firstTimeSolt, secondTimeSolt, thirdTimeSolt;
	EditText govidnumber;
	SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information);
		sharedPreferences = getSharedPreferences("irData", MODE_PRIVATE);

		saveDataBtn = (Button) findViewById(R.id.Save_Data);
		appleId = (EditText) findViewById(R.id.appleId);
		password = (EditText) findViewById(R.id.password);
		phoneNumber = (EditText) findViewById(R.id.phoneNumber);
		lastName = (EditText) findViewById(R.id.lastName);
		firstName = (EditText) findViewById(R.id.firstName);
		email = (EditText) findViewById(R.id.email);
		selectedGovtIdType = (Spinner) findViewById(R.id.selectedGovtIdType);
		govidnumber = (EditText) findViewById(R.id.govidnumber);
		firstTimeSolt = (Spinner) findViewById(R.id.firstTimeSolt);
		secondTimeSolt = (Spinner) findViewById(R.id.secondTimeSolt);
		thirdTimeSolt = (Spinner) findViewById(R.id.thirdTimeSolt);

		setInitTextData();

		saveDataBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View view) {
				saveData();
				InformationActivity.this.finish();
				Toast.makeText(view.getContext(), "Saved", Toast.LENGTH_SHORT).show();
				// Toast.makeText(view.getContext(),String.valueOf(selectedGovtIdType.getSelectedItem()),
				// Toast.LENGTH_SHORT).show();
			}

		});
	}

	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this).setMessage("Are you want to exit with out save?").setCancelable(false)
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						InformationActivity.this.finish();
					}
				}).setNegativeButton("No", null).show();
	}

	private void setInitTextData() {
		appleId.setText(sharedPreferences.getString("appleId", ""));
		password.setText(sharedPreferences.getString("password", ""));
		phoneNumber.setText(sharedPreferences.getString("phoneNumber", ""));
		lastName.setText(sharedPreferences.getString("lastName", ""));
		firstName.setText(sharedPreferences.getString("firstName", ""));
		email.setText(sharedPreferences.getString("email", ""));
		selectedGovtIdType.setSelection(getSpinnerSelectedIndex(selectedGovtIdType,
				CommonMapping.GOVIDTYPEMAP.get(sharedPreferences.getString("selectedGovtIdType", ""))));
		govidnumber.setText(sharedPreferences.getString("govidnumber", ""));
		firstTimeSolt.setSelection(getSpinnerSelectedIndex(firstTimeSolt,
				CommonMapping.NUMBERENGTIMEMAPPING.get(sharedPreferences.getString("firstTimeSolt", ""))));
		secondTimeSolt.setSelection(getSpinnerSelectedIndex(secondTimeSolt,
				CommonMapping.NUMBERENGTIMEMAPPING.get(sharedPreferences.getString("secondTimeSolt", ""))));
		thirdTimeSolt.setSelection(getSpinnerSelectedIndex(thirdTimeSolt,
				CommonMapping.NUMBERENGTIMEMAPPING.get(sharedPreferences.getString("thirdTimeSolt", ""))));
		
	}

	private void saveData() {
		sharedPreferences.edit().putString("appleId", appleId.getText().toString()).apply();
		sharedPreferences.edit().putString("password", password.getText().toString()).apply();
		sharedPreferences.edit().putString("phoneNumber", phoneNumber.getText().toString()).apply();
		sharedPreferences.edit().putString("lastName", lastName.getText().toString()).apply();
		sharedPreferences.edit().putString("firstName", firstName.getText().toString()).apply();
		sharedPreferences.edit().putString("email", email.getText().toString()).apply();
		sharedPreferences.edit().putString("selectedGovtIdType", CommonMapping.GOVIDTYPEMAP.get(String.valueOf(selectedGovtIdType.getSelectedItem()))).apply();
		sharedPreferences.edit().putString("govidnumber", govidnumber.getText().toString()).apply();
		sharedPreferences.edit().putString("firstTimeSolt", CommonMapping.ENGTIMENUMBERMAPPING.get(String.valueOf(firstTimeSolt.getSelectedItem()))).apply();
		sharedPreferences.edit().putString("secondTimeSolt", CommonMapping.ENGTIMENUMBERMAPPING.get(String.valueOf(secondTimeSolt.getSelectedItem()))).apply();
		sharedPreferences.edit().putString("thirdTimeSolt", CommonMapping.ENGTIMENUMBERMAPPING.get(String.valueOf(thirdTimeSolt.getSelectedItem()))).apply();
	}

	private int getSpinnerSelectedIndex(Spinner spinner, String inString) {
		int index = 0;

		if (inString == null || inString.equals("")) {
			return index;
		}

		for (int i = 0; i < spinner.getCount(); i++) {
			if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(inString)) {
				index = i;
				break;
			}
		}
		return index;
	}
}
