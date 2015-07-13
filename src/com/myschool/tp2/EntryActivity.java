package com.myschool.tp2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class EntryActivity extends Activity {

	private final static String USER_CURRENT_LOGIN = "CURRENT_LOGIN";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

	}

	@Override
	protected void onStart() {
		super.onStart();
		checkSharedPreferences();
	}

	private void checkSharedPreferences() {

		SharedPreferences userCurrentLogin = getSharedPreferences(USER_CURRENT_LOGIN, 0);
		if (userCurrentLogin != null) {
			String email = userCurrentLogin.getString("email", null);
			if (email != null && !email.isEmpty()) {
				SharedPreferences userDatas = getSharedPreferences(email, 0);
				String user = userDatas.getString("name", null);
				if (user != null && !user.isEmpty()) {
					goToLoginActivity(email);
					return;
				}
			}
		}

		// show menu
		goToMenuActivity();

	}

	private void goToMenuActivity() {
		Intent intent = new Intent(this, MenuActivity.class);
		startActivity(intent);
	}

	private void goToLoginActivity(String email) {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}

}
