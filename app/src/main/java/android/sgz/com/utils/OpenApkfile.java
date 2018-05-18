package android.sgz.com.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.sgz.com.BuildConfig;
import android.support.v4.content.FileProvider;

import java.io.File;


/**
 * 打开apk文件
 *
 * @author Administrator
 *
 */
public class OpenApkfile {

	public static Intent openFile(String filePath,Context context) {

		File file = new File(filePath);
		if (!file.exists())
			return null;
		/* 取得扩展名 */
		String end = file.getName().substring(file.getName().lastIndexOf(".") + 1,file.getName().length()).toLowerCase();
		/* 依扩展名的类型决定MimeType */
		if (end.equals("apk")) {
			return getApkFileIntent(filePath,context);
		} else {
			return getAllIntent(filePath);
		}
	}

	/**
	 * Android获取一个用于打开所有文件的intent
	 *
	 * @param param
	 * @return
	 */
	public static Intent getAllIntent(String param) {

		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "*/*");
		return intent;
	}

	/**
	 * Android获取一个用于打开APK文件的intent
	 *
	 * @param param
	 * @return
	 */
	public static Intent getApkFileIntent(String param, Context context) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			Uri contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", new File(param));
			intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
		} else {
			intent.setDataAndType(Uri.fromFile(new File(param)), "application/vnd.android.package-archive");
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		}
		return intent;
	}
}
