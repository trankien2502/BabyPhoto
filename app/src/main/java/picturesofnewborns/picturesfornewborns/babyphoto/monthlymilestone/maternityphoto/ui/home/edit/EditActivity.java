package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.util.Size;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.R;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.api_data.ConstantApiData;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.base.BaseActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.custom_sticker.DrawableSticker;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.custom_sticker.Sticker;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.custom_sticker.StickerView;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.database.icon.IconDatabase;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.database.icon.IconModel;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.ActivityEditBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.dialog.FrameDialog;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.crop.CropActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.color.ColorModel;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.frame.FrameAdapter;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.start.IconClickCallBack;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.util.SPUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    List<IconModel> listHolidayFrame = new ArrayList<>();
    List<ColorModel> listColor = new ArrayList<>();
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

    private void hideOption() {
        if (binding.stickerView.getOnStickerOperationListener() != null) {
            binding.stickerView.getOnStickerOperationListener().onStickerHideOptionIcon();
        }
        binding.stickerView.unSelectStickerCurrent();
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
            if (icon != null)
                Glide.with(this).load(icon.getUrl()).into(binding.ivBaby);
        } else {
            type = SPUtils.TYPE_EDIT;
            String imagePath = getIntent().getStringExtra("image_path");
            String imageUriString = getIntent().getStringExtra("image_uri");
            if (imageUriString != null) {
                Uri imageUri = Uri.parse(imageUriString);
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) binding.preview.getLayoutParams();
                Size size = getImageSizeFromUri(imageUri);
                if (size != null) {
                    x = size.getWidth();
                    y = size.getHeight();
                } else {
                    x = 412;
                    y = 550;
                }
                params.dimensionRatio = x + ":" + y;
                binding.preview.setLayoutParams(params);
                binding.ivBaby.setImageURI(imageUri);
            } else Toast.makeText(this, "image uri: null", Toast.LENGTH_SHORT).show();
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
        initData();
        binding.stickerView.setLocked(false);
        binding.stickerView.setConstrained(true);

        binding.stickerView.setOnStickerOperationListener(new StickerView.OnStickerOperationListener() {
            @Override
            public void onStickerAdded(@NonNull Sticker sticker) {
                if (binding.stickerView.getCurrentSticker() != null) {
                    state = STATE_STICKER;
                    changeState();
                }
            }

            @Override
            public void onStickerClicked(@NonNull Sticker sticker) {
                if (binding.stickerView.getCurrentSticker() != null) {
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
                if (binding.stickerView.getCurrentSticker() != null) {
                    state = STATE_STICKER;
                    changeState();
                }
            }

            @Override
            public void onStickerTouchedDown(@NonNull Sticker sticker) {
                if (binding.stickerView.getCurrentSticker() != null) {
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
        changeState();
    }

    public Size getImageSizeFromUri(Uri uri) {
        try {
            ContentResolver contentResolver = getContentResolver();
            InputStream inputStream = contentResolver.openInputStream(uri);

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, options);
            if (inputStream != null) {
                inputStream.close();
            }
            return new Size(options.outWidth, options.outHeight);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void bindView() {
        binding.ivBack.setOnClickListener(view -> {
            onBack();
        });
        binding.tvSave.setOnClickListener(view -> {

        });
        binding.preview.setOnClickListener(view -> {
            hideOption();
            state = STATE_MAIN_PHOTO;
            changeState();
        });
        binding.llChangePhoto.setOnClickListener(view -> {
            Intent intent = new Intent(this, CropActivity.class);
            intent.putExtra("CROP_IMAGE_STATUS", RESULT_CODE_CHANGE_PHOTO);
            resultLauncher.launch(intent);
        });
        binding.llMainPhotoFlipH.setOnClickListener(view -> {
            if (binding.ivBaby.getScaleX() == 1) binding.ivBaby.setScaleX(-1);
            else binding.ivBaby.setScaleX(1);
        });
        binding.llMainPhotoFlipV.setOnClickListener(view -> {
            if (binding.ivBaby.getScaleY() == 1) binding.ivBaby.setScaleY(-1);
            else binding.ivBaby.setScaleY(1);
        });
        binding.clFrame.setOnClickListener(view -> {
            state = STATE_FRAME;
            changeState();
            showFrameDialog();
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
            alp -= 20;
            if (binding.stickerView.getCurrentSticker() != null) {
                binding.stickerView.getCurrentSticker().setAlpha((alp > 0 ? alp : 1));
                binding.stickerView.invalidate();
            }
        });

        binding.clPhoto.setOnClickListener(view -> {
            Intent intent = new Intent(this, CropActivity.class);
            intent.putExtra("CROP_IMAGE_STATUS", RESULT_CODE_ADD_PHOTO);
            resultLauncher.launch(intent);
        });
        binding.llPhotoOpacity.setOnClickListener(view -> {

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
                            binding.stickerView.addSticker(drawableSticker);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                            Toast.makeText(getBaseContext(), "fail", Toast.LENGTH_SHORT).show();
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
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) binding.preview.getLayoutParams();
            Size size = getImageSizeFromUri(imageUri);
            if (size != null) {
                x = size.getWidth();
                y = size.getHeight();
            } else {
                x = 412;
                y = 550;
            }
            params.dimensionRatio = x + ":" + y;
            binding.preview.setLayoutParams(params);
            binding.ivBaby.setImageURI(imageUri);
            File file = new File(imagePath);
            boolean deleted = file.delete();
            if (deleted) {
                Log.d("CacheCleanup", "File cache đã được xóa: " + imagePath);
            } else {
                Log.e("CacheCleanup", "Xóa file cache thất bại");
            }
            SPUtils.setString(getBaseContext(), SPUtils.IMAGE_PATH, "");
            SPUtils.setString(getBaseContext(), SPUtils.IMAGE_URI, "");
        }
    });

    private void showFrameDialog() {
        FrameDialog frameDialog = new FrameDialog(this, false);
        FrameAdapter frameAdapter = new FrameAdapter(this, listHolidayFrame, new IconClickCallBack() {
            @Override
            public void select(IconModel iconModel) {
                Glide.with(getBaseContext()).load(iconModel.getUrl()).into(binding.ivFrameEdit);
                frameDialog.dismiss();
                state = STATE_NONE;
                changeState();
            }
        });

        frameDialog.binding.ivBack.setOnClickListener(view -> {
            frameDialog.dismiss();
            state = STATE_NONE;
            changeState();
        });
        frameDialog.binding.ivGone.setOnClickListener(view -> {
            state = STATE_NONE;
            changeState();
            frameDialog.dismiss();
        });
        frameDialog.binding.tvAITrend.setOnClickListener(view -> {
            frameDialog.binding.tvAITrend.setBackgroundResource(R.drawable.bg_item_s);
            frameDialog.binding.tvHoliday.setBackgroundResource(R.drawable.bg_item_sn);
            frameDialog.binding.tvMilestones.setBackgroundResource(R.drawable.bg_item_sn);
            frameAdapter.setIconModelList(listAITrendFrame);
        });
        frameDialog.binding.tvHoliday.setOnClickListener(view -> {
            frameDialog.binding.tvAITrend.setBackgroundResource(R.drawable.bg_item_sn);
            frameDialog.binding.tvHoliday.setBackgroundResource(R.drawable.bg_item_s);
            frameDialog.binding.tvMilestones.setBackgroundResource(R.drawable.bg_item_sn);
            frameAdapter.setIconModelList(listHolidayFrame);
        });
        frameDialog.binding.tvMilestones.setOnClickListener(view -> {
            frameDialog.binding.tvAITrend.setBackgroundResource(R.drawable.bg_item_sn);
            frameDialog.binding.tvHoliday.setBackgroundResource(R.drawable.bg_item_sn);
            frameDialog.binding.tvMilestones.setBackgroundResource(R.drawable.bg_item_s);
            frameAdapter.setIconModelList(listMilestonesFrame);
        });
        frameDialog.show();
        frameDialog.binding.rcvFrameList.post(new Runnable() {
            @Override
            public void run() {
                frameDialog.binding.rcvFrameList.setAdapter(frameAdapter);
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
    }
}