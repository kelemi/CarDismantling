package com.aofan.cardismantling.util;


import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.aofan.cardismantling.R;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.VersionInfo;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.widget.CustomProgressDialog;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;



public class AutoUpdate {


	//private final int PERMISSIONS_CODE_WRITE_STORAGE = 1000;

	private int newVerCode = 0;
	private String newVerName = "";
	private String downurl = "";
	private String apkName = new String();
	private String log = "";
	private Handler handler = new Handler();
	private CustomProgressDialog mcustomProgressDialog = null;

	private CustomApplication mApplication;
	/* 下载中 */
	private static final int DOWNLOAD = 1;
	/* 下载结束 */
	private static final int DOWNLOAD_FINISH = 2;
	/* 记录进度条数量 */
	private int progress;
	/* 是否取消更新 */
	private boolean cancelUpdate = false;
	/* 更新进度条 */

	private Dialog mDownloadDialog;
	//进度条
    private ProgressBar mProgress;
    //显示下载数值
    private TextView mProgressText;
    
  //下载文件大小
  	private String apkFileSize;
  	//已下载文件大小
  	private String tmpFileSize;
	
	private Context mContext;

	/*private SharePreferenceUtil mSharedUtil;*/

	public void doupdate(final Context context, VersionInfo versionInfo, final boolean isShowNoUpdate) {

		mContext = context;
		mApplication = CustomApplication.getInstance();

		newVerCode = Integer.valueOf(versionInfo.getVersioncode());
		newVerName = versionInfo.getVersionname();
		downurl =  versionInfo.getDownurl();
		apkName = downurl.substring(downurl.lastIndexOf("/")+1);
		int vercode = AppUtils.getVersionCode(mContext);
		if (newVerCode > vercode) {
			//MPermissions.requestPermissions(mActivityHome, PERMISSIONS_CODE_WRITE_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION);
			doNewVersionUpdate(context);
		} else if (isShowNoUpdate) {
			notNewVersionShow(mContext);
		}

	}




	private void cancelProgress() {
		Message message = new Message();
		message.what = 3;
		mHandler.sendMessage(message);
	}

	public void notNewVersionShow(Context context) {
		int verCode = AppUtils.getVersionCode(context
				.getApplicationContext());
		String verName = AppUtils.getVersionName(context
				.getApplicationContext());
		StringBuffer sb = new StringBuffer();
		sb.append("当前版本:");
		sb.append(verName);
		/*sb.append(" Code:");*/
		/*sb.append(verCode);*/
		sb.append(",\n已是最新版,无需更新!");
		Dialog dialog = new Builder(new ContextThemeWrapper(
				context, android.R.style.Theme_Holo_Light_Dialog))
				.setTitle("软件更新").setMessage(sb.toString())// 设置内容
				.setPositiveButton("确定",// 设置确定按钮
						null).create();// 创建
		// 显示对话框
		dialog.show();
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			// 正在下载
			case DOWNLOAD:
				// 设置进度条位置
				//mDownloadDialog.setProgress(progress);
				mProgress.setProgress(progress);
				mProgressText.setText(tmpFileSize + "/" + apkFileSize);
				break;
			case DOWNLOAD_FINISH:
				// 安装文件
				// installApk();
				break;
			case 3:
				try {
					if (mcustomProgressDialog != null) {
						mcustomProgressDialog.dismiss();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			default:
				break;
			}
		};
	};

	public void doNewVersionUpdate(final Context context) {
		int verCode = AppUtils.getVersionCode(context);
		String verName = AppUtils.getVersionName(context);
		StringBuffer sb = new StringBuffer();
		sb.append("当前版本:");
		sb.append(verName);
		sb.append("\n发现新版本:");
		sb.append(newVerName);
		sb.append("\n修改日志:\n"+log);
		sb.append("\n是否更新?");
		Dialog dialog = new Builder(new ContextThemeWrapper(
				context, android.R.style.Theme_Holo_Light_Dialog))
				.setTitle("软件更新")
				.setMessage(sb.toString())
				// 设置内容
				.setPositiveButton("更新",// 设置确定按钮
						new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
								showDownloadDialog(context);
								downFile(downurl);
							}

						})
				.setNegativeButton("暂不更新",
						new OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								// 点击"取消"按钮之后退出程序
								//exit
								/*mSharedUtil.setupdateEnable(false);*/
								dialog.dismiss();
							}
						}).create();// 创建
		// 显示对话框
		dialog.show();
	}

	/**
	 * 显示软件下载对话框
	 * 
	 * @param
	 */
	private void showDownloadDialog(Context context) {
		
		Builder builder = new Builder(new ContextThemeWrapper(
				context, android.R.style.Theme_Holo_Light_Dialog));
		builder.setTitle("正在下载新版本");
		
		final LayoutInflater inflater = LayoutInflater.from(context);
		View vprogress = inflater.inflate( R.layout.view_update_progress, null);
		mProgress = (ProgressBar)vprogress.findViewById(R.id.update_progress);
		mProgressText = (TextView) vprogress.findViewById(R.id.update_progress_text);
		
		builder.setView(vprogress);
		builder.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				cancelUpdate = true;
			}
		});
		builder.setOnCancelListener(new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				dialog.dismiss();
				cancelUpdate = true;
			}
		});
		
		mDownloadDialog = builder.create();
		mDownloadDialog.setCanceledOnTouchOutside(false);
		mDownloadDialog.show();

	}

	public void downFile(final String url) {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			Log.e("ishavesdcard", "have");

			new Thread() {
				public void run() {

					try {
						HttpClient client = new DefaultHttpClient();
						//HttpGet get = new HttpGet(url);
						HttpGet get = new HttpGet(url);

						HttpResponse response;
						response = client.execute(get);
						HttpEntity entity = response.getEntity();
						long length = entity.getContentLength();
						InputStream is = entity.getContent();
						FileOutputStream fileOutputStream = null;
						if (is != null) {

							if (!FileUtils.checkFileExists(Config.APP_BASE_FOLDER)) {
								FileUtils.createDirectory(Config.APP_BASE_FOLDER);
							}

							File file = FileUtils.createFile(
									Environment.getExternalStorageDirectory()
											+ Config.APP_BASE_FOLDER, apkName);
							fileOutputStream = new FileOutputStream(file);

							//显示文件大小格式：2个小数点显示
					    	DecimalFormat df = new DecimalFormat("0.00");
					    	//进度条下面显示的总文件大小
					    	apkFileSize = df.format((float) length / 1024 / 1024) + "MB";
					    	
							byte[] buf = new byte[1024];
							int ch = -1;
							int count = 0;
							while ((ch = is.read(buf)) != -1) {
								fileOutputStream.write(buf, 0, ch);
								// 计算进度条位置
								progress = (int) (((float) count / length) * 100);
								//进度条下面显示的当前下载文件大小
					    		tmpFileSize = df.format((float) count / 1024 / 1024) + "MB";
								// 更新进度
								mHandler.sendEmptyMessage(DOWNLOAD);
								count += ch;
								if (cancelUpdate)
									return;
							}

						}
						// 下载完成
						mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
						fileOutputStream.flush();
						if (fileOutputStream != null) {
							fileOutputStream.close();
						}
						if (!cancelUpdate)
							down();
					} catch (Exception e) {
						e.printStackTrace();
						Log.e(this.getClass().getSimpleName(), e.getMessage());

					} finally {
						
						cancelUpdate = false;
						mDownloadDialog.dismiss();
					}
				}

			}.start();
		} else {
			Toast.makeText(mContext, "没有存储卡，暂时不能升级！",
					Toast.LENGTH_SHORT).show();
		}

	}

	public void down() {

		handler.post(new Runnable() {
			public void run() {
				update();
			}
		});

	}

	public void update() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(
				Uri.fromFile(new File(Environment.getExternalStorageDirectory()
						+ Config.APP_BASE_FOLDER, apkName)),
				"application/vnd.android.package-archive");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(intent);
	}

}
