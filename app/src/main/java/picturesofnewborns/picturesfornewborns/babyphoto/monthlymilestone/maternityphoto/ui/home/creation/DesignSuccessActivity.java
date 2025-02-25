package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.creation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.R;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ads.IsNetWork;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.base.BaseActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.ActivityDesignSuccessBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.HomeActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.crop.CropActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.util.EventTracking;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.util.ImageUtils;

import java.io.File;

public class DesignSuccessActivity extends BaseActivity<ActivityDesignSuccessBinding> {

    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    Bitmap bitmap;
    private String AUTHORITY = ".provider";
    String path;
    int x = 362;
    int y = 504;

    @Override
    public ActivityDesignSuccessBinding getBinding() {
        return ActivityDesignSuccessBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
        EventTracking.logEvent(getBaseContext(), "success_view");
        path = getIntent().getStringExtra("DESIGN_IMAGE");
        if (path != null) {
            File imgFile = new File(path);
            if (imgFile.exists()) {
                bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                binding.ivPicture.setImageBitmap(bitmap);
            } else {
                Log.e("img_check", "Image file does not exist at path: " + path);
            }
        } else {
            Log.e("img_check", "path null: ");
        }
    }

    public ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK || result.getResultCode() == RESULT_CANCELED) {

        }
    });


    private void shareImage(Uri uri) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/png");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        AppOpenManager.getInstance().disableAppResumeWithActivity(DesignSuccessActivity.class);
        Intent chooser = Intent.createChooser(shareIntent, "Share Image via");
        resultLauncher.launch(chooser);
    }

    @Override
    public void bindView() {
        binding.ivBack.setOnClickListener(view -> {
            onBack();
        });
        binding.clCreateNew.setOnClickListener(v -> {
            resultLauncher.launch(new Intent(this, CropActivity.class));
        });
        binding.ivHome.setOnClickListener(view -> {
            EventTracking.logEvent(getBaseContext(), "success_home_click");
            startNextActivity(HomeActivity.class, null);
            finishAffinity();
        });
        binding.clDownload.setOnClickListener(view -> {
            EventTracking.logEvent(getBaseContext(), "success_download_click");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ImageUtils.saveImageToMediaStore(this, bitmap);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!checkStoragePermission()) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE_PERMISSION);
                    } else {
                        ImageUtils.saveBitmap(this, bitmap);
                    }
                } else {
                    ImageUtils.saveBitmap(this, bitmap);
                }
            }
        });
        binding.clShare.setOnClickListener(view -> {
            EventTracking.logEvent(getBaseContext(), "success_share_click");
            try {
                File file = new File(path);
                Uri fileUri = FileProvider.getUriForFile(this, getPackageName() + AUTHORITY, file);
                shareImage(fileUri);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e("uricheck", "e:", e);
                Log.e("uricheck", "path:" + path);
                Toast.makeText(this, R.string.failed_to_get_image, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkStoragePermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onBack() {
        EventTracking.logEvent(getBaseContext(), "success_back_click");
        setResult(RESULT_OK);
        finish();
    }


}