package echo.recorder.model;

import java.io.File;
import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

public class AudioManager {
	
	private static final String LOG_TAG = "AudioRecordTest";
	
    private static String mFileName = null;

    public MediaRecorder mRecorder = null;

    public MediaPlayer mPlayer = null;
	
	public AudioManager() {
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/most-recent-recording.aac";
	}
	
	/**
	 * On record. Called from record button 
	 *
	 *
	 * @param start 
	 */
	public void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    /**
     * On play.
     *
     * @param start the start button
     */
    public void onPlay(boolean start) {
        if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }

    private void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        Log.i("mic","" + MediaRecorder.AudioSource.MIC);
        Log.i("path","" + mFileName);
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mRecorder.setOutputFile(mFileName);
        
        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        new MakeJson(new File(mFileName));
    }

}
