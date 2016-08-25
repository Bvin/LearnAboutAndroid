package cn.bvin.lib.voicesample;

import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaRecorder mMediaRecorder;
    private boolean mRecording;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startRecord();
        startCaptureSample();
    }

    private void startRecord(){
        if (mMediaRecorder == null) {
            mMediaRecorder = new MediaRecorder();
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            File file = new File(getCacheDir(),"tmp.amr");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            mMediaRecorder.setOutputFile(file.getAbsolutePath());
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            try {
                mMediaRecorder.prepare();
                mMediaRecorder.start();
                mRecording = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private boolean mVoiceBreak;//持续声音是否结束
    private long mHightVoiceStart;
    private long mHightVoiceEnd;
    private long mHightVoice;//当前声音
    private void startCaptureSample(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mRecording){
                    if (mMediaRecorder != null) {
                        try {
                            int maxAmplitude = mMediaRecorder.getMaxAmplitude();
                            if (maxAmplitude > 0) {
                                if (maxAmplitude > 6000) {//高音分支
                                    if (mVoiceBreak || mHightVoice == 0)//一进来就有声音|连续声音告一段落
                                        mHightVoiceStart = System.currentTimeMillis();//直有断了才去更新开始时间
                                    mHightVoice = System.currentTimeMillis();
                                    mVoiceBreak = false;
                                    Log.d("声音高: ", String.valueOf(maxAmplitude));
                                } else {
                                    if (System.currentTimeMillis() - mHightVoice > 5000) {//高音时间断开后5s表示，持续声音已断开
                                        mVoiceBreak = true;
                                        mHightVoiceEnd = mHightVoice;//记载声音最后持续时间段的尾节点
                                        long duration = mHightVoiceEnd - mHightVoiceStart;
                                        Log.d("连续声音时间: ", String.valueOf(duration));
                                        //Log.d("声音断: ", String.valueOf(maxAmplitude));
                                    }
                                    Log.d("声音低: ", String.valueOf(maxAmplitude));
                                }
                                /*runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((TextView)findViewById(R.id.text)).setText();
                                    }
                                });*/
                            }
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public void stopRecord(){
        mRecording = false;
        mMediaRecorder.stop();
        mMediaRecorder.release();
        mMediaRecorder = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopRecord();
    }
}
