package echo.recorder.ui;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import echo.recorder.model.AudioManager;

public class RecordButton extends Button {
	
        boolean mStartRecording = true;
        AudioManager recorder;

        OnClickListener clicker = new OnClickListener() {
            public void onClick(View v) {
                recorder.onRecord(mStartRecording);
                if (mStartRecording) {
                    setText("Stop recording");
                } else {
                    setText("Start recording");
                }
                mStartRecording = !mStartRecording;
            }
        };

        public RecordButton(Context ctx, AudioManager recorder) {
            super(ctx);
            this.recorder = recorder;
            setText("Start recording");
            setOnClickListener(clicker);
        }
}
	

