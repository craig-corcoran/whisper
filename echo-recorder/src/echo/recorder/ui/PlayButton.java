package echo.recorder.ui;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import echo.recorder.model.AudioManager;

public class PlayButton extends Button {
    boolean mStartPlaying = true;
    AudioManager recorder;

    OnClickListener clicker = new OnClickListener() {
        public void onClick(View v) {
            recorder.onPlay(mStartPlaying);
            if (mStartPlaying) {
                setText("Stop playing");
            } else {
                setText("Start playing");
            }
            mStartPlaying = !mStartPlaying;
        }
    };

    public PlayButton(Context ctx, AudioManager recorder) {
        super(ctx);
        this.recorder = recorder;
        setText("Start playing");
        setOnClickListener(clicker);
    }
}
