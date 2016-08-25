package cn.bvin.lib.voicesample;

import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    /*ValidVoice表示声音振幅大于设定值(例如把振幅大于6000的表示是人耳能听见的声音)的一个点*/
    //以下声音都是表示ValidVoice，即有效声音，不包括听不见的声音
    private boolean mValidVoiceBreak;//持续声音是否结束
    private long mValidVoiceStart;//持续声音开始时间
    private long mValidVoiceEnd;//持续声音结束时间
    private long mCurrentValidVoiceTime;//当前声音的时间

    public static final int VALID_VOICE_AMPLITUDE = 6000;//人耳可听见声音阈值
    public static final int AFTER_VALID_VOICE_DURATION = 1000*5;//持续声音过后多长时间的算一段持续的声音
    public static final int VALID_VOICE_DURATION_MIN = 1000*5;//持续5秒以上的声音才算一段持续的声音

    private long[] mContinueVoiceDuration = new long[2];
    private List<long[]> mContinueVoiceList = new ArrayList<>();//持续时间列表

    private void startCaptureSample(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mRecording){//录制中则采集，停止录制时退出采集线程
                    if (mMediaRecorder != null) {
                        try {
                            int maxAmplitude = mMediaRecorder.getMaxAmplitude();
                            if (maxAmplitude > 0) {
                                if (maxAmplitude > VALID_VOICE_AMPLITUDE) {//高音分支
                                    if (mValidVoiceBreak || mCurrentValidVoiceTime == 0) {//一进来就有声音|连续声音告一段落
                                        mValidVoiceStart = System.currentTimeMillis();//直有断了才去更新开始时间
                                        mContinueVoiceDuration[0] = mValidVoiceStart;
                                    }
                                    mCurrentValidVoiceTime = System.currentTimeMillis();
                                    mValidVoiceBreak = false;
                                    Log.d("声音高: ", String.valueOf(maxAmplitude));
                                } else {
                                    if (System.currentTimeMillis() - mCurrentValidVoiceTime > AFTER_VALID_VOICE_DURATION) {//高音时间断开后5s表示，持续声音已断开
                                        mValidVoiceBreak = true;
                                        if (mValidVoiceEnd == mCurrentValidVoiceTime){
                                            Log.d("持续低音: ", String.valueOf(maxAmplitude));
                                            continue;
                                        }
                                        mValidVoiceEnd = mCurrentValidVoiceTime;//记载声音最后持续时间段的尾节点
                                        mContinueVoiceDuration[1] = mValidVoiceEnd;
                                        mContinueVoiceList.add(mContinueVoiceDuration);
                                        long duration = mValidVoiceEnd - mValidVoiceStart;
                                        if (duration>=VALID_VOICE_DURATION_MIN) {
                                            Log.d("连续声音时间: ", String.valueOf(duration));
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    String startTime = formatTimestamp(mValidVoiceStart);
                                                    String endTime = formatTimestamp(mValidVoiceEnd);
                                                    ((TextView) findViewById(R.id.text)).append(startTime + "——>" + endTime + "\n");
                                                }
                                            });
                                        }
                                    }
                                    Log.d("声音低: ", String.valueOf(maxAmplitude));
                                }
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

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA);

    private String formatTimestamp(long ms){
        Date date = new Date(ms);
        return sdf.format(date);
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
