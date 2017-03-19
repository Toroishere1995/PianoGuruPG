package com.example.pianogurupg;

import android.app.Activity;
import android.content.Intent;

import android.media.MediaPlayer;
import android.os.Bundle;

public class SplashScreen extends Activity {
	MediaPlayer onSong;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		onSong = MediaPlayer.create(SplashScreen.this, R.raw.entry);

		onSong.start();

		Thread th = new Thread() {
			public void run() {
				try {
					sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent onstartingPoint = new Intent(
							"com.example.pianogurupg.PLAYGURU");
					startActivity(onstartingPoint);
				}
			}
		};
		th.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		onSong.release();
		finish();
	}

}
