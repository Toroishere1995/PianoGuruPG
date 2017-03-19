package com.example.pianogurupg;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PlayGuru extends Activity implements OnClickListener {
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (arg0 == bplay) {
			notetext = notes.getText().toString().toLowerCase();
			notes.setText("");
			String word[] = notetext.trim().split("\\s+");

			for (String str : word) {
				while (mp.isPlaying()) {
					mp.setOnCompletionListener(new OnCompletionListener() {

						@Override
						public void onCompletion(MediaPlayer arg0) {

							// TODO Auto-generated method stub
							mp.reset();

						}
					});
				}
				if (str.equals("a1") || str.equals("a1s") || str.equals("b1")
						|| str.equals("c1") || str.equals("c1s")
						|| str.equals("c2") || str.equals("d1")
						|| str.equals("d1s") || str.equals("e1")
						|| str.equals("f1") || str.equals("f1s")
						|| str.equals("g1") || str.equals("g1s")) {
					String mtr = "sounds/" + str + ".wav";
					try {

						am = this.getAssets();
						afd = am.openFd(mtr);

						mp.reset();

						mp.setDataSource(afd.getFileDescriptor(),
								afd.getStartOffset(), afd.getLength());

						mp.prepare();

						mp.start();

					} catch (Exception e) {
						Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT)
								.show();
					} finally {
						try {
							afd.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block

						}
					}
				} else if (str.equals(".")) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT)
								.show();
					}
				} else {
					Toast.makeText(this, "Enter Valid Text", Toast.LENGTH_SHORT)
							.show();
				}
			}
		} else if (arg0 == bstop) {

			try {
				if (mp != null && mp.isPlaying()) {
					mp.stop();

					mp.reset();
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block

			}

		}

	}

	boolean loaded = false;// class variable
	// in onCreate:
	int c = 0;
	EditText notes;
	Button bplay, bstop;
	String notetext;
	AssetManager am;
	AssetFileDescriptor afd;

	MediaPlayer mp = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bing);
		notes = (EditText) findViewById(R.id.etnote);
		bplay = (Button) findViewById(R.id.play);
		bstop = (Button) findViewById(R.id.stop);
		bplay.setOnClickListener(this);
		bstop.setOnClickListener(this);

		mp = new MediaPlayer();
	}

}
