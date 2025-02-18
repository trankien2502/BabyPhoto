package com.tkt.spin_wheel.ui.home.edit;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.tkt.spin_wheel.R;
import com.tkt.spin_wheel.base.BaseActivity;
import com.tkt.spin_wheel.custom_sticker.DrawableSticker;
import com.tkt.spin_wheel.custom_sticker.Sticker;
import com.tkt.spin_wheel.custom_sticker.StickerView;
import com.tkt.spin_wheel.databinding.ActivityEditBinding;
import com.tkt.spin_wheel.ui.home.crop.CropActivity;
import com.tkt.spin_wheel.util.SPUtils;

import java.io.File;
import java.util.List;

public class EditActivity extends BaseActivity<ActivityEditBinding> {

    String type;
    Bitmap bitmap;
    Drawable drawable;
    int x, y;
    int state = 1;
    int alp = 255;
    private static final int RESULT_CODE_CHANGE_PHOTO = 1234;
    private static final int RESULT_CODE_ADD_PHOTO = 5678;
    private static final int STATE_NONE = 0;
    private static final int STATE_MAIN_PHOTO = 1;
    private static final int STATE_FRAME = 2;
    private static final int STATE_STICKER = 3;
    private static final int STATE_TEXT = 4;
    private static final int STATE_PHOTO = 5;

    @Override
    public ActivityEditBinding getBinding() {
        return ActivityEditBinding.inflate(getLayoutInflater());
    }

    private void hideOption() {
        if (binding.stickerView.getOnStickerOperationListener() != null) {
            binding.stickerView.getOnStickerOperationListener().onStickerHideOptionIcon();
        }
        binding.stickerView.unSelectStickerCurrent();
    }

    @Override
    public void initView() {
        type = getIntent().getStringExtra(SPUtils.TYPE_EDIT);
        String imagePath = getIntent().getStringExtra("image_path");
        String imageUriString = getIntent().getStringExtra("image_uri");
        if (imageUriString != null) {
            Uri imageUri = Uri.parse(imageUriString);
            binding.ivBaby.setImageURI(imageUri);
        } else  Toast.makeText(this, "image uri: null", Toast.LENGTH_SHORT).show();
        if (imagePath != null) {
            File file = new File(imagePath);
            // Đọc ảnh
            bitmap = BitmapFactory.decodeFile(imagePath);
            // Xóa file sau khi load ảnh thành công
            boolean deleted = file.delete();
            if (deleted) {
                Log.d("CacheCleanup", "File cache đã được xóa: " + imagePath);
            } else {
                Log.e("CacheCleanup", "Xóa file cache thất bại");
            }
        }

        binding.stickerView.setLocked(false);
        binding.stickerView.setConstrained(true);

        binding.stickerView.setOnStickerOperationListener(new StickerView.OnStickerOperationListener() {
            @Override
            public void onStickerAdded(@NonNull Sticker sticker) {
                if (binding.stickerView.getCurrentSticker() != null){
                    state = STATE_STICKER;
                    changeState();
                }
            }

            @Override
            public void onStickerClicked(@NonNull Sticker sticker) {
                if (binding.stickerView.getCurrentSticker() != null){
                    state = STATE_STICKER;
                    changeState();
                }
            }

            @Override
            public void onStickerDeleted(@NonNull Sticker sticker) {
                state = STATE_NONE;
                changeState();
            }

            @Override
            public void onStickerDragFinished(@NonNull Sticker sticker) {
                if (binding.stickerView.getCurrentSticker() != null){
                    state = STATE_STICKER;
                    changeState();
                }
            }

            @Override
            public void onStickerTouchedDown(@NonNull Sticker sticker) {
                if (binding.stickerView.getCurrentSticker() != null){
                    state = STATE_STICKER;
                    changeState();
                }
            }

            @Override
            public void onStickerZoomFinished(@NonNull Sticker sticker) {

            }

            @Override
            public void onStickerFlipped(@NonNull Sticker sticker) {

            }

            @Override
            public void onStickerDoubleTapped(@NonNull Sticker sticker) {

            }

            @Override
            public void onStickerHideOptionIcon() {

            }

            @Override
            public void onUndoDeleteSticker(@NonNull List<Sticker> stickers) {

            }

            @Override
            public void onUndoUpdateSticker(@NonNull List<Sticker> stickers) {

            }

            @Override
            public void onUndoDeleteAll() {

            }

            @Override
            public void onReplaceSticker(@NonNull Sticker sticker) {

            }
        });
        if (bitmap != null) {
            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
            DrawableSticker drawableSticker = new DrawableSticker(drawable, "");
            binding.stickerView.addSticker(drawableSticker);
//            binding.ivBaby.setImageBitmap(bitmap);
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) binding.preview.getLayoutParams();
            x = bitmap.getWidth();
            y = bitmap.getHeight();
            params.dimensionRatio = x + ":" + y;
            binding.preview.setLayoutParams(params);
        }
        changeState();
    }
    public ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            //ads
            Log.d("activity_check", "home");
        } else if (result.getResultCode() == RESULT_CODE_ADD_PHOTO) {
//                        Glide.with(getBaseContext())
//                                .load(sourceUri)
//                                .into(new CustomTarget<Drawable>() {
//                                    @Override
//                                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                                        drawable = resource;
//                                        if (drawable!= null) Toast.makeText(getBaseContext(), "not null", Toast.LENGTH_SHORT).show();
//                                        else Toast.makeText(getBaseContext(), "null", Toast.LENGTH_SHORT).show();
//                                        binding.cropImageView.setImageDrawable(drawable);
//                                    }
//
//                                    @Override
//                                    public void onLoadCleared(@Nullable Drawable placeholder) {
//                                        Toast.makeText(getBaseContext(), "fail", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
        } else if (result.getResultCode() == RESULT_CODE_CHANGE_PHOTO && result.getData()!=null) {
            Intent data = result.getData();
            String imagePath = data.getStringExtra("image_path");
            if (imagePath != null) {
                File file = new File(imagePath);
                // Đọc ảnh
                bitmap = BitmapFactory.decodeFile(imagePath);
                if (bitmap!=null){
                    binding.ivBaby.setImageBitmap(bitmap);
                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) binding.preview.getLayoutParams();
                    x = bitmap.getWidth();
                    y = bitmap.getHeight();
                    params.dimensionRatio = x + ":" + y;
                    binding.preview.setLayoutParams(params);
                }
                // Xóa file sau khi load ảnh thành công
                boolean deleted = file.delete();
                if (deleted) {
                    Log.d("CacheCleanup", "File cache đã được xóa: " + imagePath);
                } else {
                    Log.e("CacheCleanup", "Xóa file cache thất bại");
                }
            }
            binding.ivBaby.setImageURI(data.getData());
        }
    });
    @Override
    public void bindView() {
        binding.ivBack.setOnClickListener(view -> {
            onBack();
        });
        binding.tvSave.setOnClickListener(view -> {

        });
        binding.preview.setOnClickListener(view -> {
            hideOption();
            state = STATE_NONE;
            changeState();
        });
        binding.llChangePhoto.setOnClickListener(view -> {
            resultLauncher.launch(new Intent(this, CropActivity.class));
        });
        binding.llMainPhotoFlipH.setOnClickListener(view -> {
            if (binding.stickerView.getScaleY()==1) binding.stickerView.setScaleY(-1);
            else binding.stickerView.setScaleY(1);
        });
        binding.llMainPhotoFlipV.setOnClickListener(view -> {
            if (binding.stickerView.getScaleX()==1) binding.stickerView.setScaleX(-1);
            else binding.stickerView.setScaleX(1);
        });
        binding.clFrame.setOnClickListener(view -> {
            state = STATE_FRAME;
            changeState();
        });
        binding.clText.setOnClickListener(view -> {
            state = STATE_TEXT;
            changeState();
        });
        binding.clSticker.setOnClickListener(view -> {
            state = STATE_STICKER;
            changeState();
        });
        binding.llStickerRemove.setOnClickListener(view -> {
            binding.stickerView.removeCurrentSticker();
        });
        binding.llStickerFlipH.setOnClickListener(view -> {
            binding.stickerView.flipCurrentSticker(StickerView.FLIP_HORIZONTALLY);
        });
        binding.llStickerFlipV.setOnClickListener(view -> {
            binding.stickerView.flipCurrentSticker(StickerView.FLIP_VERTICALLY);
        });
        binding.llStickerOpacity.setOnClickListener(view -> {
            alp-=20;
            if (binding.stickerView.getCurrentSticker() != null){
                binding.stickerView.getCurrentSticker().setAlpha((alp > 0 ? alp : 1));
                binding.stickerView.invalidate();
            }
        });

        binding.clPhoto.setOnClickListener(view -> {
            state = STATE_PHOTO;
            changeState();
        });
        binding.llPhotoOpacity.setOnClickListener(view -> {

        });
    }

    private void resetChange() {
        binding.ivFrame.setImageResource(R.drawable.edit_frame_sn);
        binding.ivSticker.setImageResource(R.drawable.edit_sticker_sn);
        binding.ivText.setImageResource(R.drawable.edit_text_sn);
        binding.ivPhoto.setImageResource(R.drawable.edit_photo_sn);

        binding.llMainPhoto.setVisibility(GONE);
        binding.llSticker.setVisibility(GONE);
        binding.llText.setVisibility(GONE);
        binding.llPhoto.setVisibility(GONE);

        binding.hscrItem.setVisibility(VISIBLE);
        binding.scrBottom.setVisibility(VISIBLE);
    }

    private void changeState() {
        resetChange();
        switch (state) {
            case STATE_NONE:
                binding.hscrItem.setVisibility(GONE);
                break;
            case STATE_MAIN_PHOTO:
                binding.llMainPhoto.setVisibility(VISIBLE);
                break;
            case STATE_FRAME:
                binding.ivFrame.setImageResource(R.drawable.edit_frame_s);
                break;
            case STATE_STICKER:
                binding.ivSticker.setImageResource(R.drawable.edit_sticker_s);
                binding.llSticker.setVisibility(VISIBLE);
                break;
            case STATE_TEXT:
                binding.llText.setVisibility(VISIBLE);
                binding.ivText.setImageResource(R.drawable.edit_text_s);
                break;
            case STATE_PHOTO:
                binding.ivPhoto.setImageResource(R.drawable.edit_photo_s);
                binding.llPhoto.setVisibility(VISIBLE);
                break;
        }
    }

    @Override
    public void onBack() {
        setResult(RESULT_OK);
        finish();
    }
}