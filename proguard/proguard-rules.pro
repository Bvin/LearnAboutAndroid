# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\android-sdk-windows/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#-keep class cn.bvin.app.proguard.Foo{*;}
# gralde开启混淆,没有保留这个Foo类，Toast弹出的是空的字符串
#-keepnames class * implements java.io.Serializable
#-keepclassmembers class * implements java.io.Serializable {
    #private <fields>;
    #private <methods>;
    #!代表不保留，即会混淆
#}
-keep class cn.bvin.app.proguard.Bar{*;}
-keep public class * extends cn.bvin.app.proguard.Bar