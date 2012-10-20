package echo.recorder.screens;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import echo.recorder.model.AudioManager;
import echo.recorder.model.LineRenderer;
import echo.recorder.model.VisualizerView;
import echo.recorder.ui.PlayButton;
import echo.recorder.ui.RecordButton;

public class RecordScreen extends Screen {
	
	AudioManager manager;
	VisualizerView mVisualizerView;
	
	public RecordScreen(Activity activity){
		super(activity);
		manager = new AudioManager();
        setContextView(activity);
	}
	
	public void setContextView(Activity activity){
		
//		MediaPlayer mPlayer = MediaPlayer.create(activity, R.raw.test);
//		mPlayer.setLooping(true);
//		mPlayer.start();
//
//	    // We need to link the visualizer view to the media player so that
//	    // it displays something
//	    mVisualizerView = new VisualizerView(activity);//(VisualizerView) findViewById(R.id.visualizerView);
//	    mVisualizerView.link(manager.mPlayer);

	    // Start with just line renderer
//	    addLineRenderer();
//	    activity.setContentView(mVisualizerView);
		
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
        
        //ll.addView(mVisualizerView);
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
	
	private void addLineRenderer()
	  {
	    Paint linePaint = new Paint();
	    linePaint.setStrokeWidth(1f);
	    linePaint.setAntiAlias(true);
	    linePaint.setColor(Color.argb(88, 0, 128, 255));

	    Paint lineFlashPaint = new Paint();
	    lineFlashPaint.setStrokeWidth(5f);
	    lineFlashPaint.setAntiAlias(true);
	    lineFlashPaint.setColor(Color.argb(188, 255, 255, 255));
	    LineRenderer lineRenderer = new LineRenderer(linePaint, lineFlashPaint, true);
	    mVisualizerView.addRenderer(lineRenderer);
	  }

}
