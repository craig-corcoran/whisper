package echo.recorder.screens;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import echo.recorder.ui.NextScreenButton;

public class MainMenu extends Screen {

	public MainMenu(Activity activity) {
		super(activity);
		setContextView(activity);
		
	}
	
	@Override
	public void setContextView(Activity activity) {
		LinearLayout ll = new LinearLayout(activity);
        
		NextScreenButton<RecordScreen> nextButton = new NextScreenButton<RecordScreen>(activity, RecordScreen.class);

		ll.addView(nextButton,
				new LinearLayout.LayoutParams(
		                ViewGroup.LayoutParams.WRAP_CONTENT,
		                ViewGroup.LayoutParams.WRAP_CONTENT,
		                0));
		
        activity.setContentView(ll);
		
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		
	}

}
