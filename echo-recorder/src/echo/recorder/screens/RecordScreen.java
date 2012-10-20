package echo.recorder.screens;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import echo.recorder.model.AudioManager;
import echo.recorder.ui.PlayButton;
import echo.recorder.ui.RecordButton;

public class RecordScreen extends Screen {
	
	AudioManager manager = null;
	
	public RecordScreen(Activity activity){
		super(activity);
		manager = new AudioManager();
        setContextView(activity);
	}
	
	public void setContextView(Activity activity){
		
		LinearLayout ll = new LinearLayout(activity);
		
        RecordButton recordButton = new RecordButton(activity, manager);
        ll.addView(recordButton,
            new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0));
        
        PlayButton playButton = new PlayButton(activity, manager);
        ll.addView(playButton,
            new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0));
        activity.setContentView(ll);
		
	}
	
	@Override
	public void onPause() {

        if (manager.mRecorder != null) {
            manager.mRecorder.release();
            manager.mRecorder = null;
        }

        if (manager.mPlayer != null) {
        	manager.mPlayer.release();
        	manager.mPlayer = null;
        }
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		
	}

}
