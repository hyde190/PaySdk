package sdk.pay.it.evergrandelib.com.paysdk.qrview;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import cn.evergrande.it.common.zxing.core.BeepManager;
import cn.evergrande.it.common.zxing.view.QRCodeView;
import cn.evergrande.it.common.zxing.view.ZXingView;
import sdk.pay.it.evergrandelib.com.paysdk.R;


public class QrScanActivity extends AppCompatActivity implements View.OnClickListener,
        QRCodeView.Delegate  {

    private BeepManager mBeepManager;
    private ZXingView mZXingView;
    private ImageView mFlashImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_scanner);
        initUi();
    }

    private void initUi() {
        mZXingView = findViewById(R.id.zxing_view);
        mZXingView.setDelegate(this);

        mFlashImageView = findViewById(R.id.flash_light_iv);
        mFlashImageView.setOnClickListener(this);

        initBeepManager();
    }

    private void initBeepManager() {
        mBeepManager = new BeepManager(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mZXingView != null) {
            mZXingView.startCamera();
            mZXingView.startSpotAndShowRect();
        }

        if (mBeepManager != null) {
            mBeepManager.updatePrefs();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mZXingView != null) {
            mZXingView.closeFlashlight();
            mZXingView.stopCamera();
        }

        if (mBeepManager != null) {
            mBeepManager.close();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mZXingView != null) {
            mZXingView.onDestroy();
        }

        if (mBeepManager != null) {
            mBeepManager.onDestroy();
        }

    }

    private void toggleFlashLight() {
        final boolean isOpened = mZXingView.changedFlashLight();
        refreshFlashLightView(isOpened);
    }

    private void refreshFlashLightView(boolean isOpened) {
        if (isOpened) {
            mFlashImageView.setBackgroundResource(R.drawable.btn_flashlight_active_copy);
        } else {
            mFlashImageView.setBackgroundResource(R.drawable.btn_flashlight_normal_copy);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.flash_light_iv) {
            if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
                Toast.makeText(this, getString(R.string.qr_num_light_tip), Toast.LENGTH_SHORT).show();
            } else {
                toggleFlashLight();
            }
        }
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        mZXingView.stopSpot();
        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
        if (mBeepManager != null) {
            mBeepManager.playBeepSoundAndVibrate();
        }

        // 获取用户计算后的结果
        Intent intent = new Intent();
        intent.putExtra("result", result); //将计算的值回传回去
        setResult(2, intent);
        finish();

    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Toast.makeText(getApplicationContext(),R.string.qr_scanner_error_open,Toast.LENGTH_SHORT).show();
        finish();
    }
}
