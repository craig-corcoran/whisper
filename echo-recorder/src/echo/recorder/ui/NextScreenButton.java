package echo.recorder.ui;

import android.app.Activity;
import android.widget.Button;
import echo.recorder.screens.Screen;

public class NextScreenButton<S extends Screen> extends Button {
	
	S nextScreen;
	OnClickListener clickListener;
	
	public NextScreenButton(Activity activity, Class<S> nextScreen) {
		super(activity);
		clickListener = new ScreenDoneClickListener<S>(activity, nextScreen);
		
        setText("Next Screen");
        setOnClickListener(clickListener);
	}
	
	public void isDone(){
		
	}

}
