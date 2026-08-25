package age.of.civilizations2.jakowski.lukasz;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;

public class AndroidLauncher extends AndroidApplication {
   protected void onCreate(Bundle param1) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.NullPointerException: Cannot invoke "org.jetbrains.java.decompiler.code.cfg.ExceptionRangeCFG.isCircular()" because "range" is null
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.graphToStatement(DomHelper.java:84)
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:203)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:166)
      //
      // Bytecode:
      // 000: aload 0
      // 001: aload 1
      // 002: invokespecial com/badlogic/gdx/backends/android/AndroidApplication.onCreate (Landroid/os/Bundle;)V
      // 005: new android/app/AlertDialog$Builder
      // 008: dup
      // 009: aload 0
      // 00a: invokespecial android/app/AlertDialog$Builder.<init> (Landroid/content/Context;)V
      // 00d: ldc "Добро пожаловать в Bloody Europe II"
      // 00f: invokevirtual android/app/AlertDialog$Builder.setTitle (Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;
      // 012: ldc "Bloody Europe II - модификация, действие которой происходит в Европе и её окрестностях. В моде присутствует три большие карты, большое количество сценариев и формируемых цивилизаций."
      // 014: invokevirtual android/app/AlertDialog$Builder.setMessage (Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;
      // 017: invokevirtual android/app/AlertDialog$Builder.create ()Landroid/app/AlertDialog;
      // 01a: astore 1
      // 01b: aload 1
      // 01c: bipush 1
      // 01d: invokevirtual android/app/AlertDialog.setCancelable (Z)V
      // 020: aload 1
      // 021: invokevirtual android/app/AlertDialog.create ()V
      // 024: aload 1
      // 025: invokevirtual android/app/AlertDialog.show ()V
      // 028: new com/badlogic/gdx/backends/android/AndroidApplicationConfiguration
      // 02b: dup
      // 02c: invokespecial com/badlogic/gdx/backends/android/AndroidApplicationConfiguration.<init> ()V
      // 02f: astore 1
      // 030: aload 1
      // 031: bipush 1
      // 032: putfield com/badlogic/gdx/backends/android/AndroidApplicationConfiguration.useImmersiveMode Z
      // 035: bipush 1
      // 036: istore 2
      // 037: bipush 1
      // 038: istore 3
      // 039: bipush 1
      // 03a: istore 4
      // 03c: iload 2
      // 03d: istore 5
      // 03f: iload 3
      // 040: istore 6
      // 042: new java/io/BufferedReader
      // 045: astore 7
      // 047: iload 2
      // 048: istore 5
      // 04a: iload 3
      // 04b: istore 6
      // 04d: new java/io/InputStreamReader
      // 050: astore 8
      // 052: iload 2
      // 053: istore 5
      // 055: iload 3
      // 056: istore 6
      // 058: aload 8
      // 05a: aload 0
      // 05b: ldc "config.ini"
      // 05d: invokevirtual age/of/civilizations2/jakowski/lukasz/AndroidLauncher.openFileInput (Ljava/lang/String;)Ljava/io/FileInputStream;
      // 060: invokespecial java/io/InputStreamReader.<init> (Ljava/io/InputStream;)V
      // 063: iload 2
      // 064: istore 5
      // 066: iload 3
      // 067: istore 6
      // 069: aload 7
      // 06b: aload 8
      // 06d: invokespecial java/io/BufferedReader.<init> (Ljava/io/Reader;)V
      // 070: iload 4
      // 072: istore 5
      // 074: iload 4
      // 076: istore 6
      // 078: aload 7
      // 07a: invokevirtual java/io/BufferedReader.readLine ()Ljava/lang/String;
      // 07d: astore 8
      // 07f: iload 4
      // 081: istore 6
      // 083: aload 8
      // 085: ifnull 0cd
      // 088: iload 4
      // 08a: istore 5
      // 08c: iload 4
      // 08e: istore 6
      // 090: aload 8
      // 092: ldc ";"
      // 094: ldc ""
      // 096: invokevirtual java/lang/String.replace (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
      // 099: ldc "="
      // 09b: invokevirtual java/lang/String.split (Ljava/lang/String;)[Ljava/lang/String;
      // 09e: astore 8
      // 0a0: iload 4
      // 0a2: istore 5
      // 0a4: iload 4
      // 0a6: istore 6
      // 0a8: aload 8
      // 0aa: bipush 0
      // 0ab: aaload
      // 0ac: ldc "LANDSCAPE"
      // 0ae: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 0b1: ifeq 070
      // 0b4: iload 4
      // 0b6: istore 5
      // 0b8: iload 4
      // 0ba: istore 6
      // 0bc: aload 8
      // 0be: bipush 1
      // 0bf: aaload
      // 0c0: invokestatic java/lang/Boolean.parseBoolean (Ljava/lang/String;)Z
      // 0c3: istore 4
      // 0c5: goto 070
      // 0c8: astore 7
      // 0ca: bipush 1
      // 0cb: istore 6
      // 0cd: iload 6
      // 0cf: ifeq 101
      // 0d2: aload 0
      // 0d3: bipush 6
      // 0d5: invokevirtual age/of/civilizations2/jakowski/lukasz/AndroidLauncher.setRequestedOrientation (I)V
      // 0d8: aload 0
      // 0d9: new age/of/civilizations2/jakowski/lukasz/AoCGame
      // 0dc: dup
      // 0dd: new age/of/civilizations2/jakowski/lukasz/AndroidLinkHandler
      // 0e0: dup
      // 0e1: aload 0
      // 0e2: invokespecial age/of/civilizations2/jakowski/lukasz/AndroidLinkHandler.<init> (Landroid/app/Activity;)V
      // 0e5: invokespecial age/of/civilizations2/jakowski/lukasz/AoCGame.<init> (Lage/of/civilizations2/jakowski/lukasz/LinkHandler;)V
      // 0e8: aload 1
      // 0e9: invokevirtual age/of/civilizations2/jakowski/lukasz/AndroidLauncher.initialize (Lcom/badlogic/gdx/ApplicationListener;Lcom/badlogic/gdx/backends/android/AndroidApplicationConfiguration;)V
      // 0ec: getstatic com/badlogic/gdx/Gdx.files Lcom/badlogic/gdx/Files;
      // 0ef: checkcast com/badlogic/gdx/backends/android/AndroidFiles
      // 0f2: bipush 4
      // 0f3: bipush 0
      // 0f4: invokevirtual com/badlogic/gdx/backends/android/AndroidFiles.setAPKExpansion (II)Z
      // 0f7: pop
      // 0f8: return
      // 0f9: astore 7
      // 0fb: bipush 1
      // 0fc: istore 6
      // 0fe: goto 0cd
      // 101: aload 0
      // 102: bipush 1
      // 103: invokevirtual age/of/civilizations2/jakowski/lukasz/AndroidLauncher.setRequestedOrientation (I)V
      // 106: goto 0d8
      // 109: astore 1
      // 10a: goto 0f8
      // 10d: astore 7
      // 10f: iload 5
      // 111: istore 6
      // 113: goto 0cd
      // 116: astore 7
      // 118: goto 0cd
   }
}
