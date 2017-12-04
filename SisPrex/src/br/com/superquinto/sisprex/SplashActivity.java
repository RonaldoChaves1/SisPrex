package br.com.superquinto.sisprex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		Handler handle = new Handler();
		handle.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				mostarLogin();
			}
		}, 2000);
	}
	
	public void mostarLogin() {
		Intent intent = new Intent(this, CargaActivity.class);
		startActivity(intent);
		finish();
	}
}
