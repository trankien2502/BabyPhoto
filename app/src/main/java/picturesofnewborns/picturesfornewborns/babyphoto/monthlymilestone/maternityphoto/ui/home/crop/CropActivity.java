package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.crop;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.FileProvider;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.R;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.base.BaseActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.ActivityCropBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.crop.callback.CropCallback;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.EditActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.util.SPUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CropActivity extends BaseActivity<ActivityCropBinding> {

    Drawable drawable;
    private ActivityResultLauncher<Intent> imagePickerLauncher;
    private Uri sourceUri;
    String path;
    int code;

    @Override
    public ActivityCropBinding getBinding() {
        return ActivityCropBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
        code = getIntent().getIntExtra("CROP_IMAGE_STATUS", 0);
        Log.e("check_crop_image", "code: " + code);
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        sourceUri = data.getData();
                        binding.cropImageView.setImageURI(sourceUri);
                    } else {
                        setResult(RESULT_OK);
                        finish();
                    }
                }
        );
        Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK);
        pickPhotoIntent.setType("image/*");
        imagePickerLauncher.launch(pickPhotoIntent);
    }

    public String saveBitmapToCache(Bitmap bitmap, Context context) {
        File cachePath = new File(context.getCacheDir(), "images");
        cachePath.mkdirs();
        File file = new File(cachePath, "image.png");
        try (FileOutputStream stream = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }

    public File saveBitmapToCache(Bitmap bitmap) {
        File cachePath = new File(getCacheDir(), "images");
        cachePath.mkdirs();
        File file = new File(cachePath, "image_" + System.currentTimeMillis() + ".png");
        try (FileOutputStream stream = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            path = file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public Bitmap getBitmapFromUri(Uri uri) {
        Bitmap bitmap = null;
        try {
            ContentResolver contentResolver = getContentResolver();
            InputStream inputStream = contentResolver.openInputStream(uri);
            bitmap = BitmapFactory.decodeStream(inputStream);
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            //ads
            Log.d("activity_check", "home");
        } else if (result.getResultCode() == 2502) {
            Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK);
            pickPhotoIntent.setType("image/*");
            imagePickerLauncher.launch(pickPhotoIntent);
        }
    });

    @Override
    public void bindView() {
        binding.ivBack.setOnClickListener(view -> {
            onBack();
        });
        binding.tvNext.setOnClickListener(view -> {
            binding.cropImageView.cropAsync(new CropCallback() {
                @Override
                public void onSuccess(Bitmap cropped) {
                    if (code != 0) {
                        File imageFile = saveBitmapToCache(cropped);
                        Uri imageUri = FileProvider.getUriForFile(CropActivity.this, getPackageName() + ".provider", imageFile);
                        SPUtils.setString(getBaseContext(), SPUtils.IMAGE_PATH, path);
                        SPUtils.setString(getBaseContext(), SPUtils.IMAGE_URI, imageUri.toString());
                        Log.e("check_crop_image", "path: " + path);
                        Log.e("check_crop_image", "imageuri: " + imageUri.toString());
                        setResult(code);
                        finish();
                    } else {
                        File imageFile = saveBitmapToCache(cropped);
                        Uri imageUri = FileProvider.getUriForFile(CropActivity.this, getPackageName() + ".provider", imageFile);
                        Intent intent = new Intent(CropActivity.this, EditActivity.class);
                        intent.putExtra("image_path", path);
                        intent.putExtra("image_uri", imageUri.toString());
                        resultLauncher.launch(intent);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    Toast.makeText(CropActivity.this, "Crop failed!", Toast.LENGTH_SHORT).show();
                }
            });
        });
        binding.llOrigin.setOnClickListener(view -> {
            resetChange();
            binding.ivOrigin.setImageResource(R.drawable.crop_origin_s);
            binding.cropImageView.setCropMode(CropImageView.CropMode.FREE);
        });
        binding.ll11.setOnClickListener(view -> {
            resetChange();
            binding.iv11.setImageResource(R.drawable.crop_11_s);
            binding.cropImageView.setCropMode(CropImageView.CropMode.SQUARE);
        });
        binding.ll23.setOnClickListener(view -> {
            resetChange();
            binding.iv23.setImageResource(R.drawable.crop_23_s);
            binding.cropImageView.setCropMode(CropImageView.CropMode.RATIO_2_3);
        });
        binding.ll34.setOnClickListener(view -> {
            resetChange();
            binding.iv34.setImageResource(R.drawable.crop_34_s);
            binding.cropImageView.setCropMode(CropImageView.CropMode.RATIO_3_4);
        });
        binding.ll45.setOnClickListener(view -> {
            resetChange();
            binding.iv45.setImageResource(R.drawable.crop_45_s);
            binding.cropImageView.setCropMode(CropImageView.CropMode.RATIO_4_5);
        });
        binding.ll916.setOnClickListener(view -> {
            resetChange();
            binding.iv916.setImageResource(R.drawable.crop_916_s);
            binding.cropImageView.setCropMode(CropImageView.CropMode.RATIO_9_16);
        });
    }

    private void resetChange() {
        binding.ivOrigin.setImageResource(R.drawable.crop_origin_sn);
        binding.iv11.setImageResource(R.drawable.crop_11_sn);
        binding.iv23.setImageResource(R.drawable.crop_23_sn);
        binding.iv34.setImageResource(R.drawable.crop_34_sn);
        binding.iv45.setImageResource(R.drawable.crop_45_sn);
        binding.iv916.setImageResource(R.drawable.crop_916_sn);
    }

    @Override
    public void onBack() {
        setResult(RESULT_OK);
        finish();
    }


}