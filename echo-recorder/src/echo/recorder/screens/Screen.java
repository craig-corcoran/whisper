package echo.recorder.screens;

import android.app.Activity;

public abstract class Screen {
	
	Activity activity;
	
	public Screen(Activity activity) {
		this.activity = activity;
	}
	
	public abstract void setContextView(Activity activity);
		
	public abstract void onPause();
	
	public abstract void onDestroy();
	
}
