package android.sgz.com.zxing.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.sgz.com.R;
import android.sgz.com.utils.AppManager;
import android.sgz.com.utils.StatusUtils;
import android.sgz.com.zxing.camera.CameraManager;
import android.sgz.com.zxing.view.ViewfinderView;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * 这个activity打开相机，在后台线程做常规的扫描；它绘制了一个结果view来帮助正确地显示条形码，在扫描的时候显示反馈信息，
 * 然后在扫描成功的时候覆盖扫描结果
 * 
 */
public final class CaptureActivity extends Activity implements
		SurfaceHolder.Callback, View.OnClickListener {

	private static final String TAG = CaptureActivity.class.getSimpleName();

	// 相机控制
	private CameraManager cameraManager;
	private CaptureActivityHandler handler;
	private ViewfinderView viewfinderView;
	private boolean hasSurface;
	private IntentSource source;
	private Collection<BarcodeFormat> decodeFormats;
	private Map<DecodeHintType, ?> decodeHints;
	private String characterSet;
	// 电量控制
	private InactivityTimer inactivityTimer;
	// 声音、震动控制
	private BeepManager beepManager;

	private ImageButton imageButton_back;
	private RelativeLayout mRl_child;
	private boolean isOpen = false;
	private TextView tvFlashLight;

	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}

	public Handler getHandler() {
		return handler;
	}

	public CameraManager getCameraManager() {
		return cameraManager;
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();
	}

	/**
	 * OnCreate中初始化一些辅助类，如InactivityTimer（休眠）、Beep（声音）以及AmbientLight（闪光灯）
	 */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		// 保持Activity处于唤醒状态
		Window window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.capture);

		hasSurface = false;

		inactivityTimer = new InactivityTimer(this);
		beepManager = new BeepManager(this);

		mRl_child = (RelativeLayout) findViewById(R.id.rl_child);

		imageButton_back = (ImageButton) findViewById(R.id.capture_imageview_back);
		imageButton_back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		AppManager.getInstance().addActivity(CaptureActivity.this);
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	@Override
	protected void onResume() {
		super.onResume();
		// CameraManager必须在这里初始化，而不是在onCreate()中。
		// 这是必须的，因为当我们第一次进入时需要显示帮助页，我们并不想打开Camera,测量屏幕大小
		// 当扫描框的尺寸不正确时会出现bug
		cameraManager = new CameraManager(getApplication());

		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
		viewfinderView.setCameraManager(cameraManager);

		LinearLayout layoutVehicelNumber = (LinearLayout) findViewById(R.id.layout_vehicel_number);
		LinearLayout layoutFlashLight = (LinearLayout) findViewById(R.id.layout_flashlight);
		tvFlashLight = (TextView) findViewById(R.id.tv_flashlight);
		layoutVehicelNumber.setOnClickListener(this);
		layoutFlashLight.setOnClickListener(this);

		handler = null;

		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			// activity在paused时但不会stopped,因此surface仍旧存在；
			// surfaceCreated()不会调用，因此在这里初始化camera
			initCamera(surfaceHolder);
		} else {
			// 重置callback，等待surfaceCreated()来初始化camera
			surfaceHolder.addCallback(this);
		}

		beepManager.updatePrefs();
		inactivityTimer.onResume();

		source = IntentSource.NONE;
		decodeFormats = null;
		characterSet = null;
		setStatus();
	}

	@Override
	protected void onPause() {
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		inactivityTimer.onPause();
		beepManager.close();
		cameraManager.closeDriver();
		if (!hasSurface) {
			SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
			SurfaceHolder surfaceHolder = surfaceView.getHolder();
			surfaceHolder.removeCallback(this);
		}
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	/**
	 * 扫描成功，处理反馈信息
	 * 
	 * @param rawResult
	 * @param barcode
	 * @param scaleFactor
	 */
	public void handleDecode(Result rawResult, Bitmap barcode, float scaleFactor) {
		inactivityTimer.onActivity();

		boolean fromLiveScan = barcode != null;
		//这里处理解码完成后的结果，此处将参数回传到Activity处理
		if (fromLiveScan) {
			beepManager.playBeepSoundAndVibrate();
//			Toast.makeText(this, "扫描成功" +rawResult.getText(), Toast.LENGTH_SHORT).show();
			Intent intent = getIntent();
			intent.putExtra("codedContent", rawResult.getText());
			intent.putExtra("codedBitmap", barcode);
			setResult(RESULT_OK, intent);
			finish();
		}

	}

	/**
	 * 初始化Camera
	 * 
	 * @param surfaceHolder
	 */
	private void initCamera(SurfaceHolder surfaceHolder) {
		if (surfaceHolder == null) {
			throw new IllegalStateException("No SurfaceHolder provided");
		}
		if (cameraManager.isOpen()) {
			return;
		}
		try {
			// 打开Camera硬件设备
			cameraManager.openDriver(surfaceHolder);
			// 创建一个handler来打开预览，并抛出一个运行时异常
			if (handler == null) {
				handler = new CaptureActivityHandler(this, decodeFormats, decodeHints, characterSet, cameraManager);
			}
		} catch (IOException ioe) {
			Log.w(TAG, ioe);
			displayFrameworkBugMessageAndExit();
		} catch (RuntimeException e) {
			Log.w(TAG, "Unexpected error initializing camera", e);
			displayFrameworkBugMessageAndExit();
		}
	}

	/**
	 * 显示底层错误信息并退出应用
	 */
	private void displayFrameworkBugMessageAndExit() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(getString(R.string.app_name));
		builder.setMessage(getString(R.string.msg_camera_framework_bug));
		builder.setPositiveButton(R.string.button_ok, new FinishListener(this));
		builder.setOnCancelListener(new FinishListener(this));
		builder.show();
	}


	private void setStatus() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
					|| StatusUtils.MIUISetStatusBarLightMode(this.getWindow(), true)
					|| StatusUtils.FlymeSetStatusBarLightMode(this.getWindow(), true)) {
				StatusUtils.StatusBarLightMode(this);
				//                    StatusUtils.setStatusBarColor(this, R.color.white);
				StatusUtils.setStatusBarColor(this, R.color.background_headline);
				setStatusBar();
			} else {
				StatusUtils.setStatusBarColor(this, R.color.background_headline);
				setStatusBar();
			}
		}
	}
	/**
	 * android 6.0 设置状态栏颜色
	 */
	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	protected void setStatusBar() {
		if (mRl_child == null) {
			return;
		}
		FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mRl_child.getLayoutParams();
		if (StatusUtils.MIUISetStatusBarLightMode(this.getWindow(), true)) {
			params.setMargins(0, 0, 0, 0);
		} else {
			params.setMargins(0, getStatusBarHeight(), 0, 0);
		}
	}

	/**
	 * 获取状态栏高度
	 * @return
	 */
	public int getStatusBarHeight() {
		int statusBarHeight = 0;
		if (statusBarHeight == 0) {
			int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
			if (resourceId > 0) {
				statusBarHeight = getResources().getDimensionPixelSize(resourceId);
			}
		}
		return statusBarHeight;
	}

	@Override
	public void onClick(View v) {
		Camera camera = cameraManager.getCamera();
		Camera.Parameters parameter = camera.getParameters();
		switch (v.getId()) {
			case R.id.layout_vehicel_number://输入车辆编号
				//startActivity(new Intent(CaptureActivity.this,EnterVehicelNumberActivity.class));
				break;
			case R.id.layout_flashlight:
				if (isOpen) {
					tvFlashLight.setText("打开手电筒");
					isOpen = false;
					parameter.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
					camera.setParameters(parameter);
				} else {
					tvFlashLight.setText("关闭手电筒");
					isOpen = true;
					parameter.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
					camera.setParameters(parameter);
				}
				break;
			default:
				break;
		}
	}
}
