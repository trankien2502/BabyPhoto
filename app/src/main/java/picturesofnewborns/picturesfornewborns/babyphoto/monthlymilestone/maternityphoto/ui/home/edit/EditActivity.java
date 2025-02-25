package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.provider.Settings;
import android.text.Layout;
import android.util.Log;
import android.util.Size;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.R;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ads.IsNetWork;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.api_data.ConstantApiData;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.base.BaseActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.custom_sticker.DrawableSticker;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.custom_sticker.Effect;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.custom_sticker.Sticker;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.custom_sticker.StickerView;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.custom_sticker.TextSticker;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.database.design.DesignDatabase;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.database.design.DesignModel;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.database.icon.IconDatabase;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.database.icon.IconModel;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.ActivityEditBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.dialog.BorderDialog;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.dialog.FilterDialog;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.dialog.FontDialog;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.dialog.FormatDialog;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.dialog.FrameDialog;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.dialog.LoadingDialog;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.dialog.NoInternetDialog;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.dialog.OpacityDialog;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.dialog.TextDialog;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.dialog.sticker.ClickStickerCallBack;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.dialog.sticker.StickerDialog;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.creation.DesignSuccessActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.crop.CropActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.color.ColorAdapter;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.color.ColorClickCallBack;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.color.ColorModel;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.filter.FilterAdapter;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.filter.FilterClickCallBack;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.filter.FilterModel;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.font.FontAdapter;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.font.FontClickCallBack;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.font.FontModel;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.frame.FrameAdapter;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.sticker.StickerAdapter;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.start.IconClickCallBack;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.util.EventTracking;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.util.ImageUtils;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.util.SPUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EditActivity extends BaseActivity<ActivityEditBinding> {

    String type;
    LoadingDialog loadingDialog;
    Drawable drawable;
    boolean isAddPhoto = false;
    int state = 1;
    IconModel frameCurrent = null;
    private static final int RESULT_CODE_CHANGE_PHOTO = 1234;
    private static final int RESULT_CODE_ADD_PHOTO = 5678;
    private static final int STATE_NONE = 0;
    private static final int STATE_MAIN_PHOTO = 1;
    private static final int STATE_FRAME = 2;
    private static final int STATE_STICKER = 3;
    private static final int STATE_TEXT = 4;
    private static final int STATE_PHOTO = 5;
    boolean isSave = false;

    List<IconModel> listHolidayFrame = new ArrayList<>();
    List<ColorModel> listColor = new ArrayList<>();
    List<FilterModel> listFilter = new ArrayList<>();
    List<FontModel> listFont = new ArrayList<>();
    List<IconModel> listMilestonesFrame = new ArrayList<>();
    List<IconModel> listAITrendFrame = new ArrayList<>();
    List<IconModel> listMilestonesSticker = new ArrayList<>();
    List<IconModel> listAccessory = new ArrayList<>();
    List<IconModel> listAlphabet = new ArrayList<>();
    List<IconModel> listShape = new ArrayList<>();
    List<IconModel> listStatus = new ArrayList<>();
    List<IconModel> listMotherDay = new ArrayList<>();
    List<IconModel> listFatherDay = new ArrayList<>();
    List<IconModel> listPregnancy = new ArrayList<>();
    List<IconModel> listToys = new ArrayList<>();
    List<IconModel> listBabyBoy = new ArrayList<>();
    List<IconModel> listBabyGirl = new ArrayList<>();
    List<IconModel> list1stTime = new ArrayList<>();
    List<IconModel> listAnnouncement = new ArrayList<>();
    List<IconModel> listSummer = new ArrayList<>();
    List<IconModel> listHolidaySticker = new ArrayList<>();
    List<IconModel> listNewYear = new ArrayList<>();


    @Override
    public ActivityEditBinding getBinding() {
        return ActivityEditBinding.inflate(getLayoutInflater());
    }

    NoInternetDialog dialog;

    private void showNoInternetDialog() {
        dialog = new NoInternetDialog(this, false);
        dialog.binding.btnDeny.setOnClickListener(view -> {
            dialog.dismiss();
            setResult(RESULT_OK);
            finish();
        });
        dialog.binding.btnAllow.setOnClickListener(view -> {
//            AppOpenManager.getInstance().disableAppResumeWithActivity(CreateCatActivity.class);
            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
            startActivity(intent);
            dialog.dismiss();
        });
        dialog.show();
    }

    private final BroadcastReceiver networkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (!IsNetWork.haveNetworkConnection(context)) {
                showNoInternetDialog();
            } else {
                if (dialog != null && dialog.isShowing()) dialog.dismiss();
            }
        }
    };

    private void hideOption() {
        if (binding.stickerView.getOnStickerOperationListener() != null) {
            binding.stickerView.getOnStickerOperationListener().onStickerHideOptionIcon();
        }
        binding.stickerView.unSelectStickerCurrent();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(networkReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkReceiver, filter);
    }

    @Override
    public void initView() {
        IconModel iconModel = (IconModel) getIntent().getSerializableExtra(SPUtils.TEMPLATE);
        if (iconModel != null) {
            type = SPUtils.TEMPLATE;
            String category = "";
            if (Objects.equals(iconModel.getCategory(), ConstantApiData.HOLIDAY_DEMO))
                category = ConstantApiData.HOLIDAY_FRAME;
            if (Objects.equals(iconModel.getCategory(), ConstantApiData.AI_TREND_DEMO))
                category = ConstantApiData.AI_TREND;
            if (Objects.equals(iconModel.getCategory(), ConstantApiData.MILESTONES_DEMO))
                category = ConstantApiData.MILESTONES;
            IconModel icon = IconDatabase.getInstance(this).iconDAO().getIconByCategoryAndSortASC(category, iconModel.getSortasc());
            if (icon != null){
                Glide.with(this).load(icon.getUrl()).into(binding.ivFrameEdit);
                frameCurrent = icon;
            }


            state = STATE_NONE;
            changeState();
        } else {
            type = SPUtils.TYPE_EDIT;
            String imagePath = getIntent().getStringExtra("image_path");
            String imageUriString = getIntent().getStringExtra("image_uri");
            if (imageUriString != null) {
                Uri imageUri = Uri.parse(imageUriString);
                Glide.with(getBaseContext())
                        .load(imageUri)
                        .into(new CustomTarget<Drawable>() {
                            @Override
                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                drawable = resource;
                                DrawableSticker drawableSticker = new DrawableSticker(drawable, "");
                                binding.stickerPhoto.removeAllStickers();
                                binding.stickerPhoto.addSticker(drawableSticker);
                                binding.llAddPhoto.setVisibility(GONE);
                                binding.ivBaby.setVisibility(GONE);
                                isAddPhoto = true;
                                if (imagePath != null) {
                                    File file = new File(imagePath);
                                    // Xóa file sau khi load ảnh thành công
                                    boolean deleted = file.delete();
                                    if (deleted) {
                                        Log.d("CacheCleanup", "File cache đã được xóa: " + imagePath);
                                    } else {
                                        Log.e("CacheCleanup", "Xóa file cache thất bại");
                                    }
                                }
                            }

                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {
                                Toast.makeText(getBaseContext(), "fail", Toast.LENGTH_SHORT).show();
                                if (imagePath != null) {
                                    File file = new File(imagePath);
                                    // Xóa file sau khi load ảnh thành công
                                    boolean deleted = file.delete();
                                    if (deleted) {
                                        Log.d("CacheCleanup", "File cache đã được xóa: " + imagePath);
                                    } else {
                                        Log.e("CacheCleanup", "Xóa file cache thất bại");
                                    }
                                }
                            }
                        });
            } else Toast.makeText(this, "image uri: null", Toast.LENGTH_SHORT).show();
        }
        initData();
        binding.stickerView.setLocked(false);
        binding.stickerView.setConstrained(true);
        binding.stickerPhoto.setLocked(false);
        binding.stickerPhoto.setConstrained(true);
        binding.stickerPhoto.setOnStickerOperationListener(new StickerView.OnStickerOperationListener() {
            @Override
            public void onStickerAdded(@NonNull Sticker sticker) {
                state = STATE_MAIN_PHOTO;
                changeState();
                hideOption();
            }

            @Override
            public void onStickerClicked(@NonNull Sticker sticker) {
                state = STATE_MAIN_PHOTO;
                changeState();
                hideOption();
            }

            @Override
            public void onStickerDeleted(@NonNull Sticker sticker) {

            }

            @Override
            public void onStickerDragFinished(@NonNull Sticker sticker) {

            }

            @Override
            public void onStickerTouchedDown(@NonNull Sticker sticker) {

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
        binding.stickerView.setOnStickerOperationListener(new StickerView.OnStickerOperationListener() {
            @Override
            public void onStickerAdded(@NonNull Sticker sticker) {
                if (binding.stickerView.getCurrentSticker() != null) {
                    if (binding.stickerView.getCurrentSticker().getStickerType() == Sticker.StickerType.STICKER) {
                        state = STATE_STICKER;
                    } else if (binding.stickerView.getCurrentSticker().getStickerType() == Sticker.StickerType.PICTURE) {
                        state = STATE_PHOTO;
                    } else if (binding.stickerView.getCurrentSticker().getStickerType() == Sticker.StickerType.TEXT) {
                        state = STATE_TEXT;
                    }
                    changeState();
                }
            }

            @Override
            public void onStickerClicked(@NonNull Sticker sticker) {
                if (binding.stickerView.getCurrentSticker() != null) {
                    if (binding.stickerView.getCurrentSticker().getStickerType() == Sticker.StickerType.STICKER) {
                        state = STATE_STICKER;
                    } else if (binding.stickerView.getCurrentSticker().getStickerType() == Sticker.StickerType.PICTURE) {
                        state = STATE_PHOTO;
                    } else if (binding.stickerView.getCurrentSticker().getStickerType() == Sticker.StickerType.TEXT) {
                        state = STATE_TEXT;
                    }
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
                if (binding.stickerView.getCurrentSticker() != null) {
                    if (binding.stickerView.getCurrentSticker().getStickerType() == Sticker.StickerType.STICKER) {
                        state = STATE_STICKER;
                    } else if (binding.stickerView.getCurrentSticker().getStickerType() == Sticker.StickerType.PICTURE) {
                        state = STATE_PHOTO;
                    } else if (binding.stickerView.getCurrentSticker().getStickerType() == Sticker.StickerType.TEXT) {
                        state = STATE_TEXT;
                    }
                    changeState();
                }
            }

            @Override
            public void onStickerTouchedDown(@NonNull Sticker sticker) {
                if (binding.stickerView.getCurrentSticker() != null) {
                    if (binding.stickerView.getCurrentSticker().getStickerType() == Sticker.StickerType.STICKER) {
                        state = STATE_STICKER;
                    } else if (binding.stickerView.getCurrentSticker().getStickerType() == Sticker.StickerType.PICTURE) {
                        state = STATE_PHOTO;
                    } else if (binding.stickerView.getCurrentSticker().getStickerType() == Sticker.StickerType.TEXT) {
                        state = STATE_TEXT;
                    }
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
        changeState();
    }

    @Override
    public void bindView() {
        binding.tvChange.setOnClickListener(view -> {
            binding.ivFrameEdit.setAlpha(((float)binding.sbOpacityFrame.getProgress() / 255));
        });
        binding.ivBack.setOnClickListener(view -> {
            onBack();
        });
        binding.tvSave.setOnClickListener(view -> {
            isSave = true;
            EventTracking.logEvent(getBaseContext(), "design_done_click");
            hideOption();
            DesignModel designModel;
            Bitmap bitmap = ImageUtils.captureViewBitmap(binding.preview);
            String fileName = "IMG_DESIGN_" + System.currentTimeMillis() + ".png";
            ImageUtils.saveImageToInternalStorage(this, bitmap, fileName);
            String path = ImageUtils.getImagePathFromInternalStorage(this, fileName);
            if (path != null) {
                designModel = new DesignModel(path);
                DesignDatabase.getInstance(this).designDAO().insert(designModel);
                Intent intent = new Intent(this, DesignSuccessActivity.class);
                intent.putExtra("DESIGN_IMAGE", path);
                resultLauncher.launch(intent);
            } else {
                Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
            }
        });
        binding.preview.setOnClickListener(view -> {
            if (isAddPhoto) {
                hideOption();
                state = STATE_MAIN_PHOTO;
                changeState();
            } else {
                Intent intent = new Intent(this, CropActivity.class);
                intent.putExtra("CROP_IMAGE_STATUS", RESULT_CODE_CHANGE_PHOTO);
                resultLauncher.launch(intent);
            }
        });
        binding.llChangePhoto.setOnClickListener(view -> {
            Intent intent = new Intent(this, CropActivity.class);
            intent.putExtra("CROP_IMAGE_STATUS", RESULT_CODE_CHANGE_PHOTO);
            resultLauncher.launch(intent);
        });
        binding.llAddPhoto.setOnClickListener(view -> {
            Intent intent = new Intent(this, CropActivity.class);
            intent.putExtra("CROP_IMAGE_STATUS", RESULT_CODE_CHANGE_PHOTO);
            resultLauncher.launch(intent);
        });
        binding.llMainPhotoFlipH.setOnClickListener(view -> {
            binding.stickerPhoto.flip(binding.stickerPhoto.getStickers().get(0), StickerView.FLIP_HORIZONTALLY);
            binding.stickerPhoto.invalidate();
        });
        binding.llMainPhotoFlipV.setOnClickListener(view -> {
            binding.stickerPhoto.flip(binding.stickerPhoto.getStickers().get(0), StickerView.FLIP_VERTICALLY);
            binding.stickerPhoto.invalidate();
        });
        binding.clFrame.setOnClickListener(view -> {
            showFrameDialog();
        });
        binding.clText.setOnClickListener(view -> {
            showTextDialog();
        });
        binding.llTextRemove.setOnClickListener(view -> {
            binding.stickerView.removeCurrentSticker();
        });
        binding.llTextEdit.setOnClickListener(view -> {
            showTextEditDialog();
        });
        binding.llTextFont.setOnClickListener(view -> {
            showFontDialog();
        });
        binding.llTextFomart.setOnClickListener(view -> {
            showFormatDialog();
        });
        binding.llTextShadow.setOnClickListener(view -> {
            showShadowDialog();
        });
        binding.llTextBackground.setOnClickListener(view -> {
            showBackgroundDialog();
        });
        binding.clSticker.setOnClickListener(view -> {
            showStickerDialog();
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
            showOpacityDialog();
        });

        binding.clPhoto.setOnClickListener(view -> {
            Intent intent = new Intent(this, CropActivity.class);
            intent.putExtra("CROP_IMAGE_STATUS", RESULT_CODE_ADD_PHOTO);
            resultLauncher.launch(intent);
        });
        binding.llPhotoRemove.setOnClickListener(view -> {
            binding.stickerView.removeCurrentSticker();
        });
        binding.llPhotoFlipH.setOnClickListener(v -> {
            binding.stickerView.flipCurrentSticker(StickerView.FLIP_HORIZONTALLY);
        });
        binding.llPhotoFlipV.setOnClickListener(v -> {
            binding.stickerView.flipCurrentSticker(StickerView.FLIP_VERTICALLY);
        });
        binding.llPhotoFilter.setOnClickListener(v -> {
            showFilterDialog();
        });
        binding.llPhotoOpacity.setOnClickListener(v -> {
            showOpacityDialog();
        });
        binding.llPhotoBorder.setOnClickListener(v -> {
            showBorderDialog();
        });
    }


    public ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            //ads
            Log.d("activity_check", "home");
        } else if (result.getResultCode() == RESULT_CODE_ADD_PHOTO) {
            String imagePath = SPUtils.getString(this, SPUtils.IMAGE_PATH, "");
            String imageUriString = SPUtils.getString(this, SPUtils.IMAGE_URI, "");
            Log.e("check_crop_image", "path: " + imagePath);
            Log.e("check_crop_image", "imageuri: " + imageUriString);
            Uri imageUri = Uri.parse(imageUriString);
            Glide.with(getBaseContext())
                    .load(imageUri)
                    .into(new CustomTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            drawable = resource;
                            DrawableSticker drawableSticker = new DrawableSticker(drawable, "");
                            drawableSticker.setStickerType(Sticker.StickerType.PICTURE);
                            binding.stickerView.addSticker(drawableSticker);
                            File file = new File(imagePath);
                            boolean deleted = file.delete();
                            if (deleted) {
                                Log.d("CacheCleanup", "File cache đã được xóa: " + imagePath);
                            } else {
                                Log.e("CacheCleanup", "Xóa file cache thất bại");
                            }
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                            Toast.makeText(getBaseContext(), R.string.load_image_failed, Toast.LENGTH_SHORT).show();
                            File file = new File(imagePath);
                            boolean deleted = file.delete();
                            if (deleted) {
                                Log.d("CacheCleanup", "File cache đã được xóa: " + imagePath);
                            } else {
                                Log.e("CacheCleanup", "Xóa file cache thất bại");
                            }
                        }
                    });
            SPUtils.setString(getBaseContext(), SPUtils.IMAGE_PATH, "");
            SPUtils.setString(getBaseContext(), SPUtils.IMAGE_URI, "");
        } else if (result.getResultCode() == RESULT_CODE_CHANGE_PHOTO) {
            String imagePath = SPUtils.getString(this, SPUtils.IMAGE_PATH, "");
            String imageUriString = SPUtils.getString(this, SPUtils.IMAGE_URI, "");
            Log.e("check_crop_image", "path: " + imagePath);
            Log.e("check_crop_image", "imageuri: " + imageUriString);
            Uri imageUri = Uri.parse(imageUriString);
            Glide.with(getBaseContext())
                    .load(imageUri)
                    .into(new CustomTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            drawable = resource;
                            DrawableSticker drawableSticker = new DrawableSticker(drawable, "");
                            binding.stickerPhoto.removeAllStickers();
                            binding.stickerPhoto.addSticker(drawableSticker);
                            binding.llAddPhoto.setVisibility(GONE);
                            binding.ivBaby.setVisibility(GONE);
                            isAddPhoto = true;
                            File file = new File(imagePath);
                            boolean deleted = file.delete();
                            if (deleted) {
                                Log.d("CacheCleanup", "File cache đã được xóa: " + imagePath);
                            } else {
                                Log.e("CacheCleanup", "Xóa file cache thất bại");
                            }
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                            Toast.makeText(getBaseContext(), R.string.load_image_failed, Toast.LENGTH_SHORT).show();
                            File file = new File(imagePath);
                            boolean deleted = file.delete();
                            if (deleted) {
                                Log.d("CacheCleanup", "File cache đã được xóa: " + imagePath);
                            } else {
                                Log.e("CacheCleanup", "Xóa file cache thất bại");
                            }
                        }
                    });

            SPUtils.setString(getBaseContext(), SPUtils.IMAGE_PATH, "");
            SPUtils.setString(getBaseContext(), SPUtils.IMAGE_URI, "");
        }
    });

    private void loadImageFromURL(String urlString) {
        showLoadingDialogEdit();
        new Thread(() -> {
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(input);

                runOnUiThread(() -> {
                    dismissLoadingDialogEdit();
                    if (bitmap != null) {
                        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                        DrawableSticker drawableSticker = new DrawableSticker(drawable, "");
                        drawableSticker.setStickerType(Sticker.StickerType.STICKER);
                        binding.stickerView.addSticker(drawableSticker);
                    } else {
                        Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void showFormatDialog() {
        FormatDialog dialog = new FormatDialog(this, false);
        TextSticker sticker = (TextSticker) binding.stickerView.getCurrentSticker();
        if (sticker != null) {
            final boolean[] isBold = {sticker.isBold()};
            final boolean[] isItalic = {sticker.isItalic()};
            final boolean[] isUnderLine = {sticker.isUnderLine()};
            final boolean[] isLineCentral = {sticker.isLineCentral()};
            final Layout.Alignment[] alignment = {sticker.getTextAlign()};
            if (alignment[0] == Layout.Alignment.ALIGN_CENTER)
                dialog.binding.ivCenter.setBackgroundResource(R.drawable.bg_edit_text);
            else dialog.binding.ivCenter.setBackgroundResource(0);
            if (alignment[0] == Layout.Alignment.ALIGN_OPPOSITE)
                dialog.binding.ivOpposite.setBackgroundResource(R.drawable.bg_edit_text);
            else dialog.binding.ivOpposite.setBackgroundResource(0);
            if (alignment[0] == Layout.Alignment.ALIGN_NORMAL)
                dialog.binding.ivStart.setBackgroundResource(R.drawable.bg_edit_text);
            else dialog.binding.ivStart.setBackgroundResource(0);
            if (isBold[0]) dialog.binding.ivBold.setBackgroundResource(R.drawable.bg_edit_text);
            else dialog.binding.ivBold.setBackgroundResource(0);
            if (isItalic[0]) dialog.binding.ivItalic.setBackgroundResource(R.drawable.bg_edit_text);
            else dialog.binding.ivItalic.setBackgroundResource(0);
            if (isUnderLine[0])
                dialog.binding.ivUnderline.setBackgroundResource(R.drawable.bg_edit_text);
            else dialog.binding.ivUnderline.setBackgroundResource(0);
            if (isLineCentral[0])
                dialog.binding.ivCross.setBackgroundResource(R.drawable.bg_edit_text);
            else dialog.binding.ivCross.setBackgroundResource(0);
            final int[] color = {Color.BLACK};
            int position = 0;
            color[0] = sticker.getColorText();
            for (int i = 0; i < listColor.size(); i++) {
                ColorModel colorModel = listColor.get(i);
                colorModel.setSelect(colorModel.getColor() == color[0]);
                if (colorModel.getColor() == color[0]) position = i;
            }
            ColorAdapter colorAdapter = new ColorAdapter(this, listColor, new ColorClickCallBack() {
                @Override
                public void select(ColorModel colorModel) {
                    color[0] = colorModel.getColor();
                }
            });

            dialog.binding.sbOpacity.setProgress(sticker != null ? binding.stickerView.getCurrentSticker().getAlpha() : 0);
            dialog.binding.ivBack.setOnClickListener(view -> {
                dialog.dismiss();
            });
            dialog.binding.ivGone.setOnClickListener(view -> {
                if (binding.stickerView.getCurrentSticker() != null) {
                    ((TextSticker) binding.stickerView.getCurrentSticker()).setColorText(color[0]);
                    binding.stickerView.getCurrentSticker().setAlpha(dialog.binding.sbOpacity.getProgress());
                    ((TextSticker) binding.stickerView.getCurrentSticker()).setBold(isBold[0]);
                    ((TextSticker) binding.stickerView.getCurrentSticker()).setUnderLine(isUnderLine[0]);
                    ((TextSticker) binding.stickerView.getCurrentSticker()).setItalic(isItalic[0]);
                    ((TextSticker) binding.stickerView.getCurrentSticker()).setLineCentral(isLineCentral[0]);
                    ((TextSticker) binding.stickerView.getCurrentSticker()).setTextAlign(alignment[0]);
                    binding.stickerView.invalidate();
                }
                dialog.dismiss();
            });
            dialog.binding.ivBold.setOnClickListener(v -> {
                isBold[0] = !isBold[0];
                if (isBold[0]) dialog.binding.ivBold.setBackgroundResource(R.drawable.bg_edit_text);
                else dialog.binding.ivBold.setBackgroundResource(0);
            });
            dialog.binding.ivItalic.setOnClickListener(v -> {
                isItalic[0] = !isItalic[0];
                if (isItalic[0])
                    dialog.binding.ivItalic.setBackgroundResource(R.drawable.bg_edit_text);
                else dialog.binding.ivItalic.setBackgroundResource(0);
            });
            dialog.binding.ivCross.setOnClickListener(v -> {
                isLineCentral[0] = !isLineCentral[0];
                if (isLineCentral[0])
                    dialog.binding.ivCross.setBackgroundResource(R.drawable.bg_edit_text);
                else dialog.binding.ivCross.setBackgroundResource(0);
            });
            dialog.binding.ivUnderline.setOnClickListener(v -> {
                isUnderLine[0] = !isUnderLine[0];
                if (isUnderLine[0])
                    dialog.binding.ivUnderline.setBackgroundResource(R.drawable.bg_edit_text);
                else dialog.binding.ivUnderline.setBackgroundResource(0);
            });
            dialog.binding.ivStart.setOnClickListener(v -> {
                alignment[0] = Layout.Alignment.ALIGN_NORMAL;
                dialog.binding.ivCenter.setBackgroundResource(0);
                dialog.binding.ivOpposite.setBackgroundResource(0);
                dialog.binding.ivStart.setBackgroundResource(R.drawable.bg_edit_text);
            });
            dialog.binding.ivCenter.setOnClickListener(v -> {
                alignment[0] = Layout.Alignment.ALIGN_CENTER;
                dialog.binding.ivCenter.setBackgroundResource(R.drawable.bg_edit_text);
                dialog.binding.ivOpposite.setBackgroundResource(0);
                dialog.binding.ivStart.setBackgroundResource(0);
            });
            dialog.binding.ivOpposite.setOnClickListener(v -> {
                alignment[0] = Layout.Alignment.ALIGN_OPPOSITE;
                dialog.binding.ivCenter.setBackgroundResource(0);
                dialog.binding.ivOpposite.setBackgroundResource(R.drawable.bg_edit_text);
                dialog.binding.ivStart.setBackgroundResource(0);
            });
            dialog.show();
            int finalPosition = position;
            dialog.binding.rcvColor.post(new Runnable() {
                @Override
                public void run() {
                    dialog.binding.rcvColor.setAdapter(colorAdapter);
                    dialog.binding.rcvColor.smoothScrollToPosition(finalPosition);
                }
            });
        } else {
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
        }

    }

    private void showShadowDialog() {
        BorderDialog dialog = new BorderDialog(this, false);
        dialog.binding.tvTitle.setText(R.string.shadow);
        dialog.binding.iv1.setImageResource(R.drawable.design);
        dialog.binding.sbBorder.setMax(255);
        TextSticker sticker = (TextSticker) binding.stickerView.getCurrentSticker();
        if (sticker != null) {
            dialog.binding.sbBorder.setProgress(sticker.getAlphaShadow());
            final int[] color = {Color.BLACK};
            int position = 0;
            color[0] = sticker.getColorShadow();
            for (int i = 0; i < listColor.size(); i++) {
                ColorModel colorModel = listColor.get(i);
                colorModel.setSelect(colorModel.getColor() == color[0]);
                if (colorModel.getColor() == color[0]) position = i;
            }
            ColorAdapter colorAdapter = new ColorAdapter(this, listColor, new ColorClickCallBack() {
                @Override
                public void select(ColorModel colorModel) {
                    color[0] = colorModel.getColor();
                }
            });
            dialog.binding.ivBack.setOnClickListener(view -> {
                dialog.dismiss();
            });
            dialog.binding.ivGone.setOnClickListener(view -> {
                if (binding.stickerView.getCurrentSticker() != null) {
                    ((TextSticker) binding.stickerView.getCurrentSticker()).setColorShadow(color[0]);
                    ((TextSticker) binding.stickerView.getCurrentSticker()).setAlphaShadow(dialog.binding.sbBorder.getProgress());
                    binding.stickerView.invalidate();
                }
                dialog.dismiss();
            });
            dialog.show();
            int finalPosition = position;
            dialog.binding.rcvColor.post(new Runnable() {
                @Override
                public void run() {
                    dialog.binding.rcvColor.setAdapter(colorAdapter);
                    dialog.binding.rcvColor.smoothScrollToPosition(finalPosition);
                }
            });
        } else {
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
        }

    }

    private void showFontDialog() {
        FontDialog dialog = new FontDialog(this, false);
        TextSticker sticker = (TextSticker) binding.stickerView.getCurrentSticker();
        if (sticker != null) {
            final String[] font = {""};
            int position = 0;
            font[0] = sticker.getTypeface();
            for (int i = 0; i < listFont.size(); i++) {
                if (Objects.equals(font[0], listFont.get(i).getFontPath())) {
                    listFont.get(i).setSelect(true);
                    position = i;
                } else {
                    listFont.get(i).setSelect(false);
                }
            }
            FontAdapter fontAdapter = new FontAdapter(this, listFont, new FontClickCallBack() {
                @Override
                public void select(FontModel fontModel) {
                    font[0] = fontModel.getFontPath();
                }
            });

            dialog.binding.ivBack.setOnClickListener(view -> {
                dialog.dismiss();
            });
            dialog.binding.ivGone.setOnClickListener(view -> {
                ((TextSticker) binding.stickerView.getCurrentSticker()).setTypeface(font[0]);
                binding.stickerView.invalidate();
                dialog.dismiss();
            });
            dialog.show();
            int finalPosition = position;
            dialog.binding.rcvFont.post(new Runnable() {
                @Override
                public void run() {
                    dialog.binding.rcvFont.setAdapter(fontAdapter);
                    dialog.binding.rcvFont.smoothScrollToPosition(finalPosition);
                }
            });
        } else {
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
        }
    }

    private void showBackgroundDialog() {
        BorderDialog dialog = new BorderDialog(this, false);
        dialog.binding.tvTitle.setText(R.string.background_color);
        dialog.binding.iv1.setImageResource(R.drawable.design);
        dialog.binding.sbBorder.setMax(255);
        Sticker sticker = binding.stickerView.getCurrentSticker();
        final int[] color = {Color.BLACK};
        int position = 0;
        if (sticker != null) {
            color[0] = sticker.getColorBackground();
            for (int i = 0; i < listColor.size(); i++) {
                ColorModel colorModel = listColor.get(i);
                colorModel.setSelect(colorModel.getColor() == color[0]);
                if (colorModel.getColor() == color[0]) position = i;
            }
        }
        ColorAdapter colorAdapter = new ColorAdapter(this, listColor, new ColorClickCallBack() {
            @Override
            public void select(ColorModel colorModel) {
                color[0] = colorModel.getColor();
            }
        });

        dialog.binding.sbBorder.setProgress(sticker != null ? sticker.getAlphaBackground() : 0);
        dialog.binding.ivBack.setOnClickListener(view -> {
            dialog.dismiss();
        });
        dialog.binding.ivGone.setOnClickListener(view -> {
            if (binding.stickerView.getCurrentSticker() != null) {
                binding.stickerView.getCurrentSticker().setAlphaBackground(dialog.binding.sbBorder.getProgress());
                binding.stickerView.getCurrentSticker().setColorBackground(color[0]);
                binding.stickerView.invalidate();
            }
            dialog.dismiss();
        });
        dialog.binding.sbBorder.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        dialog.show();
        int finalPosition = position;
        dialog.binding.rcvColor.post(new Runnable() {
            @Override
            public void run() {
                dialog.binding.rcvColor.setAdapter(colorAdapter);
                dialog.binding.rcvColor.smoothScrollToPosition(finalPosition);
            }
        });
    }

    private void showBorderDialog() {
        BorderDialog dialog = new BorderDialog(this, false);
        Sticker sticker = binding.stickerView.getCurrentSticker();
        final int[] color = {Color.BLACK};
        int position = 0;
        if (sticker != null) {
            color[0] = sticker.getColorBorder();
            for (int i = 0; i < listColor.size(); i++) {
                ColorModel colorModel = listColor.get(i);
                colorModel.setSelect(colorModel.getColor() == color[0]);
                if (colorModel.getColor() == color[0]) position = i;
            }
        }
        ColorAdapter colorAdapter = new ColorAdapter(this, listColor, new ColorClickCallBack() {
            @Override
            public void select(ColorModel colorModel) {
                color[0] = colorModel.getColor();
            }
        });

        dialog.binding.sbBorder.setProgress(sticker != null ? sticker.getBorderWidth() : 0);
        dialog.binding.ivBack.setOnClickListener(view -> {
            dialog.dismiss();
        });
        dialog.binding.ivGone.setOnClickListener(view -> {
            if (binding.stickerView.getCurrentSticker() != null) {
                binding.stickerView.getCurrentSticker().setBorderWidth(dialog.binding.sbBorder.getProgress());
                binding.stickerView.getCurrentSticker().setColorBorder(color[0]);
                binding.stickerView.invalidate();
            }
            dialog.dismiss();
        });
        dialog.binding.sbBorder.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        dialog.show();
        int finalPosition = position;
        dialog.binding.rcvColor.post(new Runnable() {
            @Override
            public void run() {
                dialog.binding.rcvColor.setAdapter(colorAdapter);
                dialog.binding.rcvColor.smoothScrollToPosition(finalPosition);
            }
        });
    }

    public void showKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        view.requestFocus();
        view.postDelayed(() -> imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT), 100);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void showTextEditDialog() {
        TextDialog dialog = new TextDialog(this, false);
        TextSticker sticker = (TextSticker) binding.stickerView.getCurrentSticker();
        if (sticker != null) {
            dialog.binding.edtText.setText(sticker.getText());
            dialog.binding.edtText.requestFocus();
            showKeyboard(this, dialog.binding.edtText);
            dialog.binding.edtText.setOnTouchListener((v, event) -> {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    showKeyboard(this, dialog.binding.edtText);
                }
                return false;
            });
            dialog.binding.ivClose.setOnClickListener(view -> {
                dialog.dismiss();
            });
            dialog.binding.ivDone.setOnClickListener(view -> {
                if (!dialog.binding.edtText.getText().toString().trim().isEmpty()) {
                    ((TextSticker) binding.stickerView.getCurrentSticker()).setText(dialog.binding.edtText.getText().toString().trim());
                    binding.stickerView.invalidate();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getBaseContext(), R.string.please_entry_text, Toast.LENGTH_SHORT).show();
                }

            });
            dialog.show();
        } else {
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void showTextDialog() {
        TextDialog dialog = new TextDialog(this, false);
        dialog.binding.edtText.requestFocus();
        showKeyboard(this, dialog.binding.edtText);
        dialog.binding.edtText.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                showKeyboard(this, dialog.binding.edtText);
            }
            return false;
        });
        dialog.binding.ivClose.setOnClickListener(view -> {
            dialog.dismiss();
        });
        dialog.binding.ivDone.setOnClickListener(view -> {
            if (!dialog.binding.edtText.getText().toString().trim().isEmpty()) {
                TextSticker textSticker = new TextSticker(this)
                        .setText(dialog.binding.edtText.getText().toString().trim())
                        .resizeText();
                textSticker.setStickerType(Sticker.StickerType.TEXT);
                binding.stickerView.addSticker(textSticker);
                dialog.dismiss();
            } else {
                Toast.makeText(getBaseContext(), R.string.please_entry_text, Toast.LENGTH_SHORT).show();
            }

        });
        dialog.show();
    }

    private void showFilterDialog() {
        FilterDialog dialog = new FilterDialog(this, false);
        DrawableSticker sticker = (DrawableSticker) binding.stickerView.getCurrentSticker();
        if (sticker != null) {
            final ColorMatrixColorFilter[] filter = {sticker.getFilter()};
            int position = 0;
            filter[0] = sticker.getFilter();
            for (int i = 0; i < listFilter.size(); i++) {
                FilterModel colorModel = listFilter.get(i);
                colorModel.setSelect(colorModel.getFilter() == filter[0]);
                if (colorModel.getFilter() == filter[0]) position = i;
            }
            FilterAdapter filterAdapter = new FilterAdapter(this, listFilter, new FilterClickCallBack() {
                @Override
                public void select(FilterModel filterModel) {
                    filter[0] = filterModel.getFilter();
                }
            });
            dialog.binding.ivBack.setOnClickListener(view -> {
                dialog.dismiss();
            });
            dialog.binding.ivGone.setOnClickListener(view -> {
                if (binding.stickerView.getCurrentSticker() != null) {
                    ((DrawableSticker) binding.stickerView.getCurrentSticker()).setFilter(filter[0]);
                    binding.stickerView.invalidate();
                }
                dialog.dismiss();
            });
            dialog.show();
            int finalPosition = position;
            dialog.binding.rcvFilter.post(new Runnable() {
                @Override
                public void run() {
                    dialog.binding.rcvFilter.setAdapter(filterAdapter);
                    dialog.binding.rcvFilter.smoothScrollToPosition(finalPosition);
                }
            });
        } else {
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
        }

    }

    private void showOpacityDialog() {
        OpacityDialog dialog = new OpacityDialog(this, false);
        Sticker sticker = binding.stickerView.getCurrentSticker();
        dialog.binding.sbOpacity.setProgress(sticker != null ? sticker.getAlpha() : 255);
        dialog.binding.ivBack.setOnClickListener(view -> {
            dialog.dismiss();
        });
        dialog.binding.ivGone.setOnClickListener(view -> {
            if (binding.stickerView.getCurrentSticker() != null) {
                binding.stickerView.getCurrentSticker().setAlpha(dialog.binding.sbOpacity.getProgress());
                binding.stickerView.invalidate();
            }
            dialog.dismiss();
        });
        dialog.binding.sbOpacity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        dialog.show();
    }

    private void showFrameDialog() {
        FrameDialog frameDialog = new FrameDialog(this, false);
        IconClickCallBack iconClickCallBack = null;
        FrameAdapter frameAdapter = new FrameAdapter(this, listHolidayFrame, iconClickCallBack);
        iconClickCallBack = new IconClickCallBack() {
            @Override
            public void select(IconModel iconModel) {
                frameAdapter.setCheck(iconModel);
            }
        };
        frameAdapter.setIconClickCallBack(iconClickCallBack);
        if (frameCurrent != null) frameAdapter.setCheck(frameCurrent);
        frameDialog.binding.ivBack.setOnClickListener(view -> {
            frameDialog.dismiss();
            state = STATE_NONE;
            changeState();
        });
        frameDialog.binding.ivGone.setOnClickListener(view -> {
            IconModel current = frameAdapter.getCheck();
            if (current != null) {
                Glide.with(getBaseContext()).load(current.getUrl()).into(binding.ivFrameEdit);
                frameCurrent = current;
                frameDialog.dismiss();
                state = STATE_FRAME;
                changeState();
            } else {
                Toast.makeText(getBaseContext(), R.string.please_choose_a_frame_first, Toast.LENGTH_SHORT).show();
            }

        });
        frameDialog.binding.tvAITrend.setOnClickListener(view -> {
            frameDialog.binding.tvAITrend.setBackgroundResource(R.drawable.bg_item_s);
            frameDialog.binding.tvHoliday.setBackgroundResource(R.drawable.bg_item_sn);
            frameDialog.binding.tvMilestones.setBackgroundResource(R.drawable.bg_item_sn);
            frameAdapter.setIconModelList(listAITrendFrame);
            if (frameCurrent != null) frameAdapter.setCheck(frameCurrent);
        });
        frameDialog.binding.tvHoliday.setOnClickListener(view -> {
            frameDialog.binding.tvAITrend.setBackgroundResource(R.drawable.bg_item_sn);
            frameDialog.binding.tvHoliday.setBackgroundResource(R.drawable.bg_item_s);
            frameDialog.binding.tvMilestones.setBackgroundResource(R.drawable.bg_item_sn);
            frameAdapter.setIconModelList(listHolidayFrame);
            if (frameCurrent != null) frameAdapter.setCheck(frameCurrent);
        });
        frameDialog.binding.tvMilestones.setOnClickListener(view -> {
            frameDialog.binding.tvAITrend.setBackgroundResource(R.drawable.bg_item_sn);
            frameDialog.binding.tvHoliday.setBackgroundResource(R.drawable.bg_item_sn);
            frameDialog.binding.tvMilestones.setBackgroundResource(R.drawable.bg_item_s);
            frameAdapter.setIconModelList(listMilestonesFrame);
            if (frameCurrent != null) frameAdapter.setCheck(frameCurrent);
        });
        frameDialog.show();
        frameDialog.binding.rcvFrameList.post(new Runnable() {
            @Override
            public void run() {
                frameDialog.binding.rcvFrameList.setAdapter(frameAdapter);
            }
        });

    }

    private void showStickerDialog() {
        StickerDialog frameDialog = new StickerDialog(this, false);
        StickerAdapter stickerAdapter = new StickerAdapter(this, listMilestonesSticker, new IconClickCallBack() {
            @Override
            public void select(IconModel iconModel) {
                loadImageFromURL(iconModel.getUrl());
                frameDialog.dismiss();
            }
        });
        frameDialog.binding.ivBack.setOnClickListener(v -> {
            frameDialog.dismiss();
        });
        frameDialog.binding.ivGone.setOnClickListener(v -> {
            Toast.makeText(getBaseContext(), R.string.please_choose_a_sticker, Toast.LENGTH_SHORT).show();
        });
        frameDialog.init(new ClickStickerCallBack() {
            @Override
            public void milestone() {
                stickerAdapter.setIconModelList(listMilestonesSticker);
            }

            @Override
            public void accessory() {
                stickerAdapter.setIconModelList(listAccessory);
            }

            @Override
            public void alphabet() {
                stickerAdapter.setIconModelList(listAlphabet);
            }

            @Override
            public void shape() {
                stickerAdapter.setIconModelList(listShape);
            }

            @Override
            public void status() {
                stickerAdapter.setIconModelList(listStatus);
            }

            @Override
            public void motherday() {
                stickerAdapter.setIconModelList(listMotherDay);
            }

            @Override
            public void fatherday() {
                stickerAdapter.setIconModelList(listFatherDay);
            }

            @Override
            public void pregnancy() {
                stickerAdapter.setIconModelList(listPregnancy);
            }

            @Override
            public void toys() {
                stickerAdapter.setIconModelList(listToys);
            }

            @Override
            public void babyboy() {
                stickerAdapter.setIconModelList(listBabyBoy);
            }

            @Override
            public void babygirl() {
                stickerAdapter.setIconModelList(listBabyGirl);
            }

            @Override
            public void _1sttime() {
                stickerAdapter.setIconModelList(list1stTime);
            }

            @Override
            public void announcement() {
                stickerAdapter.setIconModelList(listAnnouncement);
            }

            @Override
            public void summer() {
                stickerAdapter.setIconModelList(listSummer);
            }

            @Override
            public void holiday() {
                stickerAdapter.setIconModelList(listHolidaySticker);
            }

            @Override
            public void newyear() {
                stickerAdapter.setIconModelList(listNewYear);
            }
        });

        frameDialog.show();
        frameDialog.binding.rcvFrameList.post(new Runnable() {
            @Override
            public void run() {
                frameDialog.binding.rcvFrameList.setAdapter(stickerAdapter);
            }
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
        binding.clOpacityFrame.setVisibility(INVISIBLE);
    }

    private void changeState() {
        resetChange();
        switch (state) {
            case STATE_NONE:
                binding.hscrItem.setVisibility(INVISIBLE);
                break;
            case STATE_MAIN_PHOTO:
                binding.llMainPhoto.setVisibility(VISIBLE);
                break;
            case STATE_FRAME:
                binding.hscrItem.setVisibility(INVISIBLE);
                binding.clOpacityFrame.setVisibility(VISIBLE);
                binding.sbOpacityFrame.setProgress((int) (binding.ivFrameEdit.getAlpha() * 255));
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

    private void showLoadingDialogEdit() {
        loadingDialog = new LoadingDialog(this, false);
        loadingDialog.show();
    }

    private void dismissLoadingDialogEdit() {
        if (loadingDialog != null && loadingDialog.isShowing()) loadingDialog.dismiss();
    }

    private void initData() {
        listHolidayFrame = IconDatabase.getInstance(this).iconDAO().getIconByCategory(ConstantApiData.HOLIDAY_FRAME);
        listMilestonesFrame = IconDatabase.getInstance(this).iconDAO().getIconByCategory(ConstantApiData.MILESTONES);
        listAITrendFrame = IconDatabase.getInstance(this).iconDAO().getIconByCategory(ConstantApiData.AI_TREND);
        listMilestonesSticker = IconDatabase.getInstance(this).iconDAO().getIconByCategory(ConstantApiData.MILESTONES_STICKER);
        listAccessory = IconDatabase.getInstance(this).iconDAO().getIconByCategory(ConstantApiData.ACCESSORY);
        listAlphabet = IconDatabase.getInstance(this).iconDAO().getIconByCategory(ConstantApiData.ALPHABET);
        listShape = IconDatabase.getInstance(this).iconDAO().getIconByCategory(ConstantApiData.SHAPES);
        listStatus = IconDatabase.getInstance(this).iconDAO().getIconByCategory(ConstantApiData.STATUS);
        listMotherDay = IconDatabase.getInstance(this).iconDAO().getIconByCategory(ConstantApiData.MOTHER_DAY);
        listFatherDay = IconDatabase.getInstance(this).iconDAO().getIconByCategory(ConstantApiData.FATHER_DAY);
        listPregnancy = IconDatabase.getInstance(this).iconDAO().getIconByCategory(ConstantApiData.PREGNANCY);
        listToys = IconDatabase.getInstance(this).iconDAO().getIconByCategory(ConstantApiData.TOYS);
        listBabyBoy = IconDatabase.getInstance(this).iconDAO().getIconByCategory(ConstantApiData.BABY_BOY);
        listBabyGirl = IconDatabase.getInstance(this).iconDAO().getIconByCategory(ConstantApiData.BABY_GIRL);
        list1stTime = IconDatabase.getInstance(this).iconDAO().getIconByCategory(ConstantApiData._1ST_TIME);
        listAnnouncement = IconDatabase.getInstance(this).iconDAO().getIconByCategory(ConstantApiData.ANNOUNCEMENT);
        listSummer = IconDatabase.getInstance(this).iconDAO().getIconByCategory(ConstantApiData.SUMMER);
        listHolidaySticker = IconDatabase.getInstance(this).iconDAO().getIconByCategory(ConstantApiData.HOLIDAY_STICKER);
        listNewYear = IconDatabase.getInstance(this).iconDAO().getIconByCategory(ConstantApiData.NEW_YEAR);

        listColor.add(new ColorModel(Color.parseColor("#FFFFFF")));
        listColor.add(new ColorModel(Color.parseColor("#CCCCCC")));
        listColor.add(new ColorModel(Color.parseColor("#999999")));
        listColor.add(new ColorModel(Color.parseColor("#666666")));
        listColor.add(new ColorModel(Color.parseColor("#333333")));
        listColor.add(new ColorModel(Color.parseColor("#000000")));
        listColor.add(new ColorModel(Color.parseColor("#FFE5E5")));
        listColor.add(new ColorModel(Color.parseColor("#FFCCCC")));
        listColor.add(new ColorModel(Color.parseColor("#FF9999")));
        listColor.add(new ColorModel(Color.parseColor("#FF3333")));
        listColor.add(new ColorModel(Color.parseColor("#CC0000")));
        listColor.add(new ColorModel(Color.parseColor("#660000")));
        listColor.add(new ColorModel(Color.parseColor("#FFF2E5")));
        listColor.add(new ColorModel(Color.parseColor("#FFE5CC")));
        listColor.add(new ColorModel(Color.parseColor("#FFCC99")));
        listColor.add(new ColorModel(Color.parseColor("#FF9933")));
        listColor.add(new ColorModel(Color.parseColor("#CC6600")));
        listColor.add(new ColorModel(Color.parseColor("#663300")));
        listColor.add(new ColorModel(Color.parseColor("#FFFDE5")));
        listColor.add(new ColorModel(Color.parseColor("#FFFBCC")));
        listColor.add(new ColorModel(Color.parseColor("#FFF799")));
        listColor.add(new ColorModel(Color.parseColor("#FFEE33")));
        listColor.add(new ColorModel(Color.parseColor("#CCBB00")));
        listColor.add(new ColorModel(Color.parseColor("#665E00")));
        listColor.add(new ColorModel(Color.parseColor("#EEFF99")));
        listColor.add(new ColorModel(Color.parseColor("#DDFF33")));
        listColor.add(new ColorModel(Color.parseColor("#D5FF00")));
        listColor.add(new ColorModel(Color.parseColor("#AACC00")));
        listColor.add(new ColorModel(Color.parseColor("#809900")));
        listColor.add(new ColorModel(Color.parseColor("#556600")));
        listColor.add(new ColorModel(Color.parseColor("#BBFF99")));
        listColor.add(new ColorModel(Color.parseColor("#77FF33")));
        listColor.add(new ColorModel(Color.parseColor("#55FF00")));
        listColor.add(new ColorModel(Color.parseColor("#44CC00")));
        listColor.add(new ColorModel(Color.parseColor("#339900")));
        listColor.add(new ColorModel(Color.parseColor("#226600")));
        listColor.add(new ColorModel(Color.parseColor("#CCFFF6")));
        listColor.add(new ColorModel(Color.parseColor("#80FFEA")));
        listColor.add(new ColorModel(Color.parseColor("#00FFD4")));
        listColor.add(new ColorModel(Color.parseColor("#00CCAA")));
        listColor.add(new ColorModel(Color.parseColor("#006655")));
        listColor.add(new ColorModel(Color.parseColor("#00332B")));
        listColor.add(new ColorModel(Color.parseColor("#CCE5FF")));
        listColor.add(new ColorModel(Color.parseColor("#80BFFF")));
        listColor.add(new ColorModel(Color.parseColor("#0080FF")));
        listColor.add(new ColorModel(Color.parseColor("#0066CC")));
        listColor.add(new ColorModel(Color.parseColor("#003366")));
        listColor.add(new ColorModel(Color.parseColor("#001A33")));
        listColor.add(new ColorModel(Color.parseColor("#DDCCFF")));
        listColor.add(new ColorModel(Color.parseColor("#AA80FF")));
        listColor.add(new ColorModel(Color.parseColor("#7733FF")));
        listColor.add(new ColorModel(Color.parseColor("#4D00E5")));
        listColor.add(new ColorModel(Color.parseColor("#330099")));
        listColor.add(new ColorModel(Color.parseColor("#220066")));
        listColor.add(new ColorModel(Color.parseColor("#FFCCFF")));
        listColor.add(new ColorModel(Color.parseColor("#FF80FF")));
        listColor.add(new ColorModel(Color.parseColor("#FF33FF")));
        listColor.add(new ColorModel(Color.parseColor("#E500E5")));
        listColor.add(new ColorModel(Color.parseColor("#B200B2")));
        listColor.add(new ColorModel(Color.parseColor("#660066")));
        listFont.add(new FontModel("font/aeonik_pro_regular.otf"));
        listFont.add(new FontModel("font/baloo2_edium.ttf"));
        listFont.add(new FontModel("font/be_vietnam_pro.ttf"));
        listFont.add(new FontModel("font/bowlby_one_sc.ttf"));
        listFont.add(new FontModel("font/druk_text_wide_medium_trial.otf"));
        listFont.add(new FontModel("font/galada.ttf"));
        listFont.add(new FontModel("font/gamja_flower.ttf"));
        listFont.add(new FontModel("font/ghochi_hand.ttf"));
        listFont.add(new FontModel("font/grand_hotel.ttf"));
        listFont.add(new FontModel("font/gurajada.ttf"));
        listFilter.add(new FilterModel(Effect.getEffect0()));
        listFilter.add(new FilterModel(Effect.getEffect1()));
        listFilter.add(new FilterModel(Effect.getEffect2()));
        listFilter.add(new FilterModel(Effect.getEffect3()));
        listFilter.add(new FilterModel(Effect.getEffect4()));
        listFilter.add(new FilterModel(Effect.getEffect5()));
        listFilter.add(new FilterModel(Effect.getEffect6()));
        listFilter.add(new FilterModel(Effect.getEffect7()));
        listFilter.add(new FilterModel(Effect.getEffect8()));
        listFilter.add(new FilterModel(Effect.getEffect9()));
        listFilter.add(new FilterModel(Effect.getEffect10()));
        listFilter.add(new FilterModel(Effect.getEffect11()));
        listFilter.add(new FilterModel(Effect.getEffect12()));
        listFilter.add(new FilterModel(Effect.getEffect13()));
        listFilter.add(new FilterModel(Effect.getEffect14()));
        listFilter.add(new FilterModel(Effect.getEffect15()));
        listFilter.add(new FilterModel(Effect.getEffect16()));
        listFilter.add(new FilterModel(Effect.getEffect17()));
        listFilter.add(new FilterModel(Effect.getEffect18()));
        listFilter.add(new FilterModel(Effect.getEffect19()));
        listFilter.add(new FilterModel(Effect.getEffect20()));
        listFilter.add(new FilterModel(Effect.getEffect21()));
        listFilter.add(new FilterModel(Effect.getEffect22()));
    }
}