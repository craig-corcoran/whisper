package echo.recorder.ui;

import java.lang.reflect.InvocationTargetException;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import echo.recorder.EchoActivity;
import echo.recorder.screens.Screen;

public class ScreenDoneClickListener<S extends Screen> implements OnClickListener {
		
	Class<S> nextScreen;
	Activity activity = null;
	
	public ScreenDoneClickListener(Activity activity, Class<S> nextScreen){
		this.nextScreen = nextScreen;
		this.activity = activity;
	}

	public void onClick(View v) {
		try {
			Screen next = nextScreen.getConstructor(Activity.class).newInstance(activity);
			((EchoActivity) v.getContext()).setCurrentScreen(next);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
}

