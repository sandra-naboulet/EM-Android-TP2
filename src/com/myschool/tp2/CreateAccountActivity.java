package com.myschool.tp2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CreateAccountActivity extends Activity implements OnClickListener {

	private final static String USER_CURRENT_LOGIN = "CURRENT_LOGIN";

	Button mCreateAccountButton = null;
	EditText mNameEditText = null;
	EditText mEmailEditText = null;
	EditText mPasswordEditText = null;

	SharedPreferences mSharedPrefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account);

		mCreateAccountButton = (Button) findViewById(R.id.create_button);
		mNameEditText = (EditText) findViewById(R.id.create_account_name);
		mEmailEditText = (EditText) findViewById(R.id.create_account_email);
		mPasswordEditText = (EditText) findViewById(R.id.create_account_password);

		mCreateAccountButton.setOnClickListener(this);

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	private boolean hasErrors() {

		if (mNameEditText.getText().toString().trim().isEmpty()) {
			mNameEditText.setError(getResources().getString(R.string.name_error));
			return true;
		}

		if (mEmailEditText.getText().toString().trim().isEmpty()) {
			mEmailEditText.setError(getResources().getString(R.string.email_error));
			return true;
		}

		if (mPasswordEditText.getText().toString().trim().isEmpty()) {
			mPasswordEditText.setError(getResources().getString(R.string.password_error));
			return true;
		}
		return false;
	}

	private void saveInSharedPreferences() {
		String userEmail = mEmailEditText.getText().toString().trim();

		// Save ids
		SharedPreferences userSettings = getSharedPreferences(userEmail, 0);
		SharedPreferences.Editor editor = userSettings.edit();
		editor.putString("name", mNameEditText.getText().toString().trim());
		editor.putString("password", mPasswordEditText.getText().toString().trim());

		SharedPreferences userLoginPrefs = getSharedPreferences(USER_CURRENT_LOGIN, 0);
		SharedPreferences.Editor editor2 = userLoginPrefs.edit();
		editor2.putString("email", userEmail);

		editor.commit();
		editor2.commit();

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.create_button) {
			if (!hasErrors()) {
				saveInSharedPreferences();
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);
			}
		}
	}

}
