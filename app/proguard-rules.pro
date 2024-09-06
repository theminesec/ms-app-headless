# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify

-verbose
-printmapping proguardMapping.txt
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-keepattributes *Annotation*

-keepattributes Signature

-keepattributes SourceFile,LineNumberTable
#-keepclasseswithmembernames class * {
#    native <methods>;
#}
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View

-keep class android.support.** {*;}

-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**

-keep class **.R$* {*;}

-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keepclassmembers class * {
    void *(**On*Event);
    void *(**On*Listener);
}

#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#    public *;
#}
#-keepclassmembers class * extends android.webkit.webViewClient {
#    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
#    public boolean *(android.webkit.WebView, java.lang.String);
#}
#-keepclassmembers class * extends android.webkit.webViewClient {
#    public void *(android.webkit.webView, jav.lang.String);
#}
# gson
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

# Rxjava
-dontwarn sun.misc.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

#-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
#    rx.internal.util.atomic.LinkedQueueNode producerNode;
#}
#
#-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
#    rx.internal.util.atomic.LinkedQueueNode consumerNode;
#}
#
## Jackson
#-keep @com.fasterxml.jackson.annotation.JsonIgnoreProperties class * { *; }
## for amex kernel
#-keep class com.fasterxml.jackson.core.type.TypeReference
## -keep class com.fasterxml.** { *; }
## -keep class org.codehaus.** { *; }
## -keepnames class com.fasterxml.jackson.** { *; }
#-keepclassmembers public final enum com.fasterxml.jackson.annotation.JsonAutoDetect$Visibility {
#    public static final com.fasterxml.jackson.annotation.JsonAutoDetect$Visibility *;
#}

# General
-keepattributes SourceFile,LineNumberTable,*Annotation*,EnclosingMethod,Signature,Exceptions,InnerClasses

#-dontnote rx.internal.util.PlatformDependent

-keep class kotlinx.parcelize.** { *; }
-keep @kotlinx.parcelize.Parcelize class * { *; }
-dontwarn kotlinx.parcelize.**
-keep class kotlin.Metadata { *; }

-keep class lombok.** { *; }
-dontwarn lombok.**

# Retrofit
-dontwarn retrofit2.**
-dontwarn org.codehaus.mojo.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keepattributes *Annotation*

#zxing
# -keep class com.google.zxing.{ *;}
-dontwarn com.google.zxing.**

# OkHttp
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

# OkHttp3
-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

-keepattributes EnclosingMethod

#android-async-http
-dontwarn com.loopj.android.http.**
-keep class com.loopj.android.http.**{*;}

#brightcove
-dontwarn com.brightcove.**
-keep class com.brightcove.** {*;}

#comscore
-keep class com.comscore.** { *; }
-dontwarn com.comscore.**

#ad
-keep public class com.google.android.gms.ads.** {
   public *;
}
-keep public class com.google.ads.** {
   public *;
}

-keep class com.fasterxml.jackson.databind.** { *; }
-dontwarn com.fasterxml.jackson.databind.**
-keepattributes *Annotation*

#Crashlytics
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**

#netty
-keepattributes Signature,InnerClasses
-keepclasseswithmembers class io.netty.** {
    *;
}
-dontwarn io.netty.**

-keep public class com.americanexpress.mobilepayments.softposkernel.** { *; }
-keep public class com.minesec.msa.client.sdk.management.model.**{*;}
-keep public class com.minesec.msa.client.sdk.management.model_k.**{*;}
-keep public class com.minesec.msa.client.sdk.management.model.LoginResponseDto{*;}
-keep public class com.minesec.msa.client.sdk.management.model.UserInfoDto{*;}
-keep public class com.minesec.msa.client.sdk.ResultDto{*;}
-keep class com.minesec.msa.client.sdk.payment.model.**{*;}
-keep class com.minesec.msa.client.sdk.management.tms.**{ *; }


-obfuscationdictionary proguard_dict.txt
-classobfuscationdictionary proguard_dict.txt
-packageobfuscationdictionary proguard_dict.txt

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# R8 full mode strips generic signatures from return types if not kept.
-if interface * { @retrofit2.http.* public *** *(...); }
-keep,allowoptimization,allowshrinking,allowobfuscation class <3>

# Please add these rules to your existing keep rules in order to suppress warnings.
# This is generated automatically by the Android Gradle plugin.
-dontwarn com.americanexpress.**
-dontwarn com.huawei.hmf.**
-dontwarn com.huawei.hms.**
-dontwarn java.beans.**
-dontwarn javax.mail.**
-dontwarn javax.naming.**
-dontwarn org.ietf.jgss.**
-dontwarn com.theminesec.minehadescore.**
