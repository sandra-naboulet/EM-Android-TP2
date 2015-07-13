package com.myschool.tp2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity implements OnClickListener {

	private final static String USER_CURRENT_LOGIN = "CURRENT_LOGIN";

	Button mLoginButton = null;
	EditText mEmailEditText = null;
	EditText mPasswordEditText = null;

	SharedPreferences mSharedPrefs;
	String mEmailPrefs;
	String mPasswordPrefs;
	String mEmail = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		mLoginButton = (Button) findViewById(R.id.login_button);
		mEmailEditText = (EditText) findViewById(R.id.login_email);
		mPasswordEditText = (EditText) findViewById(R.id.login_password);
		mLoginButton.setOnClickListener(this);

	

	}

	@Override
	protected void onStart() {
		initWithUserPrefs();
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	private void initWithUserPrefs() {

		SharedPreferences userCurrentLogin = getSharedPreferences(USER_CURRENT_LOGIN, 0);
		if (userCurrentLogin != null) {
			mEmailPrefs = userCurrentLogin.getString("email", null);
			if (mEmailPrefs != null && !mEmailPrefs.isEmpty()) {
				SharedPreferences userDatas = getSharedPreferences(mEmailPrefs, 0);
				String user = userDatas.getString("name", "");
				mPasswordPrefs = userDatas.getString("password", "");
				if (user != null && !user.isEmpty()) {
					mEmailEditText.setText(mEmailPrefs);
					return;
				}
			}
		}
		mEmailEditText.setText("");
		mPasswordEditText.setText("");
	}

	private boolean hasErrors() {

		String inputEmail = mEmailEditText.getText().toString().trim();
		String inputPass = mPasswordEditText.getText().toString().trim();
		
		if (inputEmail.isEmpty()) {
			mEmailEditText.setError(getResources().getString(R.string.email_error));
			return true;
		}

		if (inputPass.isEmpty()) {
			mPasswordEditText.setError(getResources().getString(R.string.password_error));
			return true;
		}
		
		if(inputEmail.equals(mEmailPrefs) && !inputPass.equalsIgnoreCase(mPasswordPrefs)){
			mEmailEditText.setError(getResources().getString(R.string.login_error));
			mPasswordEditText.setError(getResources().getString(R.string.login_error));
			return true;
		}
		
		return false;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.login_button) {
			if (!hasErrors()) {
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);
			}
		}
	}

}
