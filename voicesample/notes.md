1.录音报Java.lang.RuntimeException: setAudioSource failed
    原因：没声明权限
    解决方案：添加 <uses-permission android:name="android.permission.RECORD_AUDIO"/>即可 
    
2.录音报java.io.IOException: No valid output file
    原因：一定要指定输出文件
    解决方案;setOutputFile/setOutputFormat/setAudioEncoder一个都不能少
    
3.MediaRecorder.getMaxAmplitude()条件判断失效
    原因：调用时机，判断表达式和判断体内调用的值已发生变化
    解决方案：利用local变量引用同一份数据