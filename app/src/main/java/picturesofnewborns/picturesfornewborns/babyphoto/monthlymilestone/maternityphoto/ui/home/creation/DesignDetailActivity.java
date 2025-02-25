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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.R;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ads.IsNetWork;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.base.BaseActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.database.design.DesignDatabase;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.database.design.DesignModel;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.ActivityDesignDetailBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.dialog.DeleteDialog;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.crop.CropActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.util.EventTracking;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.util.ImageUtils;

import java.io.File;

public class DesignDetailActivity extends BaseActivity<ActivityDesignDetailBinding> {

    private static final int REQUEST_CODE_STORAGE_PERMISSION = 100;
    Bitmap bitmap;
    private String AUTHORITY = ".provider";
    DesignModel designModel;

    @Override
    public ActivityDesignDetailBinding getBinding() {
        return ActivityDesignDetailBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
        EventTracking.logEvent(getBaseContext(), "album_my_design_item_view_click");
        designModel = (DesignModel) getIntent().getSerializableExtra("DESIGN_MODEL");
        if (designModel != null) {
            File imgFile = new File(designModel.getPath());
            if (imgFile.exists()) {
                bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                binding.ivPicture.setImageBitmap(bitmap);
            } else {
                Log.e("img_check", "Image file does not exist at path: " + designModel.getPath());
            }
        } else {
            Log.e("img_check", "design null: ");
        }
    }


    @Override
    public void bindView() {
        binding.ivBack.setOnClickListener(view -> {
            onBack();
        });
        binding.ivDelete.setOnClickListener(view -> {
            EventTracking.logEvent(getBaseContext(), "album_my_design_item_view_delete_click");
            showDeleteDialog();
        });
        binding.clCreateNew.setOnClickListener(v -> {
            resultLauncher.launch(new Intent(this, CropActivity.class));
        });
        binding.clDownload.setOnClickListener(view -> {
            EventTracking.logEvent(getBaseContext(), "album_my_design_item_view_download_click");
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
            EventTracking.logEvent(getBaseContext(), "album_my_design_item_view_share_click");
            try {
                File file = new File(designModel.getPath());
                Uri fileUri = FileProvider.getUriForFile(this, getPackageName() + AUTHORITY, file);
                shareImage(fileUri);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("uricheck", "e:", e);
                Log.e("uricheck", "path:" + designModel.getPath());
                Toast.makeText(this, R.string.failed_to_get_image, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDeleteDialog() {
        DeleteDialog dialog = new DeleteDialog(this, false);
        dialog.binding.btnDeny.setOnClickListener(view -> {
            dialog.dismiss();
        });
        dialog.binding.btnAllow.setOnClickListener(view -> {
            dialog.dismiss();
            DesignDatabase.getInstance(this).designDAO().delete(designModel.getId());
            File file = new File(designModel.getPath());
            boolean deleted = file.delete();
            if (deleted) {
                Log.d("CacheCleanup", "File cache đã được xóa: " + designModel.getPath());
            } else {
                Log.e("CacheCleanup", "Xóa file cache thất bại");
            }
            setResult(RESULT_OK);
            finish();
        });
        dialog.show();
    }

    public ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK || result.getResultCode() == RESULT_CANCELED) {
        }
    });

    private boolean checkStoragePermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private void shareImage(Uri uri) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/png");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        AppOpenManager.getInstance().disableAppResumeWithActivity(DesignDetailActivity.class);
        Intent chooser = Intent.createChooser(shareIntent, "Share Image via");
        resultLauncher.launch(chooser);
    }

    @Override
    public void onBack() {
        EventTracking.logEvent(getBaseContext(), "album_my_design_item_view_back_click");
        setResult(RESULT_OK);
        finish();
    }

}