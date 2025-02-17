package com.tkt.spin_wheel.ui.home.crop;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;

import com.tkt.spin_wheel.base.BaseActivity;
import com.tkt.spin_wheel.databinding.ActivityCropBinding;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.InputStream;

public class CropActivity extends BaseActivity<ActivityCropBinding> {

    private ActivityResultLauncher<Intent> imagePickerLauncher;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int UCROP_REQUEST_CODE = 2;
    private Uri sourceUri, destinationUri;

    @Override
    public ActivityCropBinding getBinding() {
        return ActivityCropBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        // Nhận URI của ảnh đã chọn
                        Intent data = result.getData();
                        sourceUri = data.getData();
                        binding.cropImageView.setImageURI(sourceUri);
                    }
                }
        );
        Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK);
        pickPhotoIntent.setType("image/*");
        imagePickerLauncher.launch(pickPhotoIntent);
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

    @Override
    public void bindView() {

    }

    @Override
    public void onBack() {

    }


}