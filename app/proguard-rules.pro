# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}

# ???????????????0~7???????5??????????
-optimizationpasses 5
-ignorewarnings
# ??????????����??????????????����
-dontusemixedcaseclassnames

# ???????????????????
-dontskipnonpubliclibraryclasses

-dontoptimize

# ?????��?�preverify??proguard?????????????Android?????preverify???????????????????????
-dontpreverify

# ??��???????????????????????????
# ??????????->?????????????????
-verbose

# ????????????????????????????????????
# ???????????????????????????????
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#????Annotation??????
-keepattributes *Annotation*,InnerClasses

# ??????????????????????????Application?????��????????
# ?????��?????��??????????
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
#-keep public class com.android.vending.licensing.ILicensingService

#
-keep class android.support.** {*;}
-keep public class * extends android.support.v4.app.Fragment

#
-ignorewarning

#apk
-dump proguard/class_files.txt
#
-printseeds proguard/seeds.txt
#
-printusage proguard/unused.txt

-printmapping proguard/mapping.txt

-dontwarn android.support.**

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

# ??????��????????onXXEvent??**On*Listener????????????
-keepclassmembers class * {
    void *(**On*Event);
    void *(**On*Listener);
}

# ????Serializable???��???????????
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# ???????????????
-keepclassmembers enum * {
  public static **[] values();
  public static ** valueOf(java.lang.String);
}

-keepclassmembers class * {
    public void *ButtonClicked(android.view.View);
}

# ????R????????
-keepclassmembers class **.R$* {
    public static <fields>;
}

#-assumenosideeffects class android.util.Log {
#    public static *** v(...);
#    public static *** i(...);
#    public static *** d(...);
#    public static *** w(...);
#    public static *** e(...);
#}

#netty
-keep class io.netty.** { *; }
-dontwarn com.hd.patrol.netty.**
-keep class com.hd.patrol.netty.**{*;}

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.** { *; }
-keep class com.google.gson.stream.** { *; }

#mob
-keep class android.net.http.SslError
-keep class android.webkit.**{*;}
-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class m.framework.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-dontwarn cn.sharesdk.**
-dontwarn **.R$*

### greenDAO 3
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keepclassmembers class com.hd.patrol.database.greendao.** {
public static java.lang.String TABLENAME;
}
-dontwarn com.hd.patrol.database.greendao.**
-keep public class com.hd.patrol.database.greendao.** {*;}
-keep class **$Properties
# If you do not use SQLCipher:
-dontwarn org.greenrobot.greendao.database.**
-keep public class org.greenrobot.greendao.**{*;}
-keep public class data.db.dao.*$Properties {
    public static <fields>;
}
-keepclassmembers class data.db.dao.** {
    public static final <fields>;
}

#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

-keep class com.matrix.app.entity.json.** { *; }
-keep class com.matrix.appsdk.network.model.** { *; }


-keep class com.nineoldandroids.** { *; }
-keep interface com.nineoldandroids.** { *; }
-dontwarn com.nineoldandroids.**
#
-keep class in.srain.cube.** { *; }
-keep interface in.srain.cube.** { *; }
-dontwarn in.srain.cube.**
#
-keep class com.github.ksoichiro.** { *; }
-keep interface com.github.ksoichiro.** { *; }
-dontwarn com.github.ksoichiro.**

#okhttputils
-dontwarn com.zhy.http.**
-keep class com.zhy.http.**{*;}


#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}

#okio
-dontwarn okio.**
-keep class okio.**{*;}

#-libraryjars libs/baidumapapi_v3_1_0.jar  baidu???
-keep class com.baidu.** { *; }
-keep class vi.com.gdi.bgl.android.**{*;}

#eventbus
-keep class de.greenrobot.event.** {*;}
-keepclassmembers class ** {
    public void onEvent*(**);
    void onEvent*(**);
}

-keep class java.applet.**{*;}
-keep class bolts.tasks.**{*;}
-keep class com.mikhaellopez.circularprogressbar.**{*;}
-keep class org.litepal.**{*;}
-keep class com.jcodecraeer.xrecyclerview.**{*;}
-keep class com.bumptech.glide.**{*;}

-keep class cn.jzvd.**{*;}
-keep class com.bm.photoview.library.**{*;}
-keep class com.dinuscxj.recycleritemdecoration.**{*;}
-keep class com.bigkoo.svprogresshud.**{*;}
-keep class jp.wasabeef.glide.transformations.**{*;}
-keep class jp.co.cyberagent.android.gpuimage.**{*;}
-keep class com.liulishuo.filedownloader.**{*;}
-keep class org.hamcrest.**{*;}
-keep class org.junit.**{*;}
-keep class com.liulishuo.filedownloader.**{*;}
-keep class com.bm.library.**{*;}
-keep class com.ogaclejapan.smarttablayout.**{*;}
-keep class org.videolan.**{*;}

#apache
-keep class android.net.**{*;}
-keep class com.android.internal.http.multipart.**{*;}
-keep class org.apache.**{*;}

#
-keep class app.dinus.com.itemdecoration.**{*;}
-keep class com.dinuscxj.itemdecoration.**{*;}

-keep class com.bigkoo.svprogresshud.**{*;}
-keep class com.squareup.wire.**{*;}
-keep class com.jcodecraeer.xrecyclerview.**{*;}
-keep class com.umeng.message.lib.**{*;}
-keep class com.umeng_social_sdk_res_lib.**{*;}
-keep class com.alibaba.sdk.android.oss.**{*;}




-dontshrink
-dontoptimize
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**

-keep public class javax.**
-keep public class android.webkit.**
-dontwarn android.support.v4.**

#???????????
-keepattributes Exceptions,InnerClasses,Signature

# ???????????????��?
-keepattributes SourceFile,LineNumberTable

-keep public class com.tencent.** {*;}


-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}

-keep class com.tencent.** {*;}
-dontwarn com.tencent.**

-keep class com.tencent.open.TDialog$*
-keep class com.tencent.open.TDialog$* {*;}
-keep class com.tencent.open.PKDialog
-keep class com.tencent.open.PKDialog {*;}
-keep class com.tencent.open.PKDialog$*
-keep class com.tencent.open.PKDialog$* {*;}

-keep class com.sina.** {*;}
-dontwarn com.sina.**
-keep class  com.alipay.share.sdk.** {
*;
}

# ????Parcelable???��?????????
-keepnames class * implements android.os.Parcelable {
 public static final ** CREATOR;
}

# ????Parcelable???��?????????
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keep class com.linkedin.** { *; }

-dontwarn com.ut.mini.**
-dontwarn okio.**
-dontwarn com.xiaomi.**
-dontwarn com.squareup.wire.**
-dontwarn android.support.v4.**


-keep class android.support.v4.** { *; }
-keep interface android.support.v4.app.** { *; }

-keep class okio.** {*;}
-keep class com.squareup.wire.** {*;}

-keep class com.umeng.message.protobuffer.* {
 public <fields>;
      public <methods>;
}

-keep class com.umeng.message.* {
 public <fields>;
      public <methods>;
}

-keep class org.android.agoo.impl.* {
 public <fields>;
      public <methods>;
}

-keep class org.android.agoo.service.* {*;}

-keep class org.android.spdy.**{*;}

-keep public class **.R$*{
 public static final int *;
}

#
-dontwarn org.apache.http.**
-dontwarn android.webkit.**
-keep class org.apache.http.** { *; }
-keep class org.apache.commons.codec.** { *; }
-keep class org.apache.commons.logging.** { *; }
-keep class android.net.compatibility.** { *; }
-keep class android.net.http.** { *; }


#js


#Bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

#Mta
-keep class com.tencent.stat.*{*;}
-keep class com.tencent.mid.*{*;}
#androidPdfViewer
-dontwarn com.github.barteksc.pdfviewer.**
-keep public class com.github.barteksc.pdfviewer.**{*;}
-dontwarn com.shockwave.pdfium.**
-keep public class com.shockwave.pdfium.**{*;}
#BaseRecyclerViewAdapterHelper
-dontwarn com.chad.library.**
-keep public class com.chad.library.**{*;}
#bottomNavigationBar
-dontwarn com.ashokvarma.bottomnavigation.**
-keep public class com.ashokvarma.bottomnavigation.**{*;}


# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
-dontnote rx.internal.util.PlatformDependent
-dontwarn io.reactivex.**
-keep class io.reactivex.**{*;}
#-dontwarn com.hd.restful.**
#-keep class com.hd.restful.**{*;}
-dontwarn java.lang.invoke.**



#utils
-dontwarn com.coremedia.iso.**
-keep public class com.coremedia.iso.**{*;}
-dontwarn com.googlecode.mp4parser.**
-keep public class com.googlecode.mp4parser.**{*;}
-dontwarn com.mp4parser.**
-keep public class com.mp4parser.**{*;}
#http
-keep class retrofit2.** { *; }
-dontwarn retrofit2.**
-keepattributes Signature
-keepattributes Exceptions
-dontwarn okio.**
-dontwarn javax.annotation.**

#this is for app
 -dontwarn sdk.pay.it.evergrandelib.com.paysdk.bean.**
 -keep public class sdk.pay.it.evergrandelib.com.paysdk.bean.**{*;}
 -dontwarn sdk.pay.it.evergrandelib.com.paysdk.order.**
 -keep public class sdk.pay.it.evergrandelib.com.paysdk.order.**{*;}
 -dontwarn sdk.pay.it.evergrandelib.com.paysdk.qrview.**
 -keep public class sdk.pay.it.evergrandelib.com.paysdk.qrview.**{*;}
#this is  for pos sdk
 -dontwarn cn.evergrande.it.**
 -keep public class cn.evergrande.it.**{*;}






