/*
 * The application needs to have the permission to write to external storage
 * if the output file is written to the external storage, and also the
 * permission to record audio. These permissions must be set in the
 * application's AndroidManifest.xml file, with something like:
 *
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 * <uses-permission android:name="android.permission.RECORD_AUDIO" />
 *
 */
//package com.android.audiorecordtest;

package echo.recorder;

import android.app.Activity;
import android.os.Bundle;
import echo.recorder.model.GPSTracker;
import echo.recorder.screens.MainMenu;
import echo.recorder.screens.Screen;


public class EchoActivity extends Activity {
	
	Screen currentScreen;
	public static GPSTracker gps;
	
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        gps = new GPSTracker(this);
        currentScreen = new MainMenu(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        currentScreen.onPause();
    }
    
    @Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
    
    public void setCurrentScreen(Screen screen) {
    	currentScreen = screen;
    	screen.setContextView(this);
    }


}