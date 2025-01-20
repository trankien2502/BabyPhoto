package com.tkt.spin_wheel.ui.permission;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.tkt.spin_wheel.R;
import com.tkt.spin_wheel.base.BaseActivity;
import com.tkt.spin_wheel.databinding.ActivityPermissionBinding;
import com.tkt.spin_wheel.dialog.GoToSettingDialog;
import com.tkt.spin_wheel.ui.home.HomeActivity;
import com.tkt.spin_wheel.util.EventTracking;
import com.tkt.spin_wheel.util.PermissionManager;
import com.tkt.spin_wheel.util.SPUtils;
import com.tkt.spin_wheel.util.SystemUtil;


public class PermissionActivity extends BaseActivity<ActivityPermissionBinding> {

    private static final int REQUEST_CODE_CAMERA_PERMISSION = 120;
    private static final int REQUEST_CODE_NOTIFICATION_PERMISSION = 130;
    private int countCamera = 0;
    private int countNotification = 0;

    @Override
    public ActivityPermissionBinding getBinding() {
        return ActivityPermissionBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
        EventTracking.logEvent(this, "permission_open");
        countCamera = SPUtils.getInt(this, SPUtils.CAMERA, 0);
        countNotification = SPUtils.getInt(this, SPUtils.NOTIFICATION, 0);
    }

    @Override
    public void bindView() {
        binding.tvContinue.setOnClickListener(v -> {
            EventTracking.logEvent(this, "permission_continue_click");
            startNextActivity(HomeActivity.class, null);
            finishAffinity();
        });
        binding.swPerNotification.setOnClickListener(view -> {
            if (!PermissionManager.checkNotificationPermission(this)) {
                EventTracking.logEvent(this, "permission_allow_click");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    ActivityCompat.requestPermissions(PermissionActivity.this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQUEST_CODE_NOTIFICATION_PERMISSION);
                }
            }
        });
        binding.swPer.setOnClickListener(view -> {
            if (!PermissionManager.checkCameraPermission(this)) {
                EventTracking.logEvent(this, "permission_allow_click");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_CAMERA_PERMISSION);
            }
        });
    }

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK || result.getResultCode() == RESULT_CANCELED) {
            //ads
            Log.d("activity_check", "home");
        }
    });

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkSwCamera();
            }
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                checkSwCamera();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        countCamera++;
//                        AppOpenManager.getInstance().disableAppResumeWithActivity(PermissionActivity.class);
                        SPUtils.setInt(this, SPUtils.CAMERA, countCamera);
                        if (countCamera > 1) {
                            showDialogGotoSetting(2);
                        }
                    }

                }
            }
        }
        if (requestCode == REQUEST_CODE_NOTIFICATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkSwNotification();
            }

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                checkSwNotification();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                        countNotification++;
//                        AppOpenManager.getInstance().disableAppResumeWithActivity(PermissionActivity.class);
                        SPUtils.setInt(this, SPUtils.NOTIFICATION, countNotification);
                        if (countNotification > 1) {
                            showDialogGotoSetting(1);
                        }
                    }
                }
            }
        }
    }

    private void showDialogGotoSetting(int type) {
        GoToSettingDialog dialog = new GoToSettingDialog(this, true);
        SystemUtil.setLocale(this);

        if (type == 1) {
            dialog.binding.tvContent.setText(R.string.content_dialog_per_noti);
        } else if (type == 2) {
            dialog.binding.tvContent.setText(R.string.content_dialog_per_camera);
        }

        dialog.binding.tvStay.setOnClickListener(view -> {
            dialog.dismiss();
        });
        dialog.binding.tvContent.setOnClickListener(view -> {
            dialog.dismiss();
        });
        dialog.binding.tvAgree.setOnClickListener(view -> {
//            AppOpenManager.getInstance().disableAppResumeWithActivity(PermissionActivity.class);
            dialog.dismiss();
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent.setData(uri);
            resultLauncher.launch(intent);
        });
        dialog.show();
    }

    @Override
    public void onBack() {
        finishAffinity();
    }


    @SuppressLint("ClickableViewAccessibility")
    private void checkSwCamera() {
        if (PermissionManager.checkCameraPermission(this)) {
            binding.swPer.setChecked(true);
            binding.swPer.setOnTouchListener((view, motionEvent) -> true);
        } else {
            binding.swPer.setChecked(false);
            binding.swPer.setOnTouchListener((view, motionEvent) -> false);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void checkSwNotification() {
        if (PermissionManager.checkNotificationPermission(this)) {
            binding.swPerNotification.setChecked(true);
            binding.swPerNotification.setOnTouchListener((view, motionEvent) -> true);
        } else {
            binding.swPerNotification.setChecked(false);
            binding.swPerNotification.setOnTouchListener((view, motionEvent) -> false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkSwNotification();
        checkSwCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
