package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.dialog.sticker;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;

import androidx.annotation.NonNull;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.R;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.base.BaseDialog;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.DialogStickerBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.dialog.rate.IClickDialogRate;


public class StickerDialog extends BaseDialog<DialogStickerBinding> {
    private ClickStickerCallBack iClickSticker;
    private final Context context;
    public StickerDialog(@NonNull Context context, boolean canAble) {
        super(context, canAble);
        this.context = context;
    }
    @Override
    protected DialogStickerBinding setBinding() {
        return DialogStickerBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        Window window = getWindow();
        if (window != null) {
            window.setGravity(Gravity.BOTTOM);
        }
    }

    @Override
    protected void bindView() {
        binding.tvMilestones.setOnClickListener(view -> {
            resetChange();
            binding.tvMilestones.setBackgroundResource(R.drawable.bg_item_s);
            iClickSticker.milestone();
        });
        binding.tvAccessory.setOnClickListener(view -> {
            resetChange();
            binding.tvAccessory.setBackgroundResource(R.drawable.bg_item_s);
            iClickSticker.accessory();
        });
        binding.tvAlphabet.setOnClickListener(view -> {
            resetChange();
            binding.tvAlphabet.setBackgroundResource(R.drawable.bg_item_s);
            iClickSticker.alphabet();
        });
        binding.tvShapes.setOnClickListener(view -> {
            resetChange();
            binding.tvShapes.setBackgroundResource(R.drawable.bg_item_s);
            iClickSticker.shape();
        });
        binding.tvStatus.setOnClickListener(view -> {
            resetChange();
            binding.tvStatus.setBackgroundResource(R.drawable.bg_item_s);
            iClickSticker.status();
        });
        binding.tvMotherDay.setOnClickListener(view -> {
            resetChange();
            binding.tvMotherDay.setBackgroundResource(R.drawable.bg_item_s);
            iClickSticker.motherday();
        });
        binding.tvFatherDay.setOnClickListener(view -> {
            resetChange();
            binding.tvFatherDay.setBackgroundResource(R.drawable.bg_item_s);
            iClickSticker.fatherday();
        });
        binding.tvPregnancy.setOnClickListener(view -> {
            resetChange();
            binding.tvPregnancy.setBackgroundResource(R.drawable.bg_item_s);
            iClickSticker.pregnancy();
        });
        binding.tvToys.setOnClickListener(view -> {
            resetChange();
            binding.tvToys.setBackgroundResource(R.drawable.bg_item_s);
            iClickSticker.toys();
        });
        binding.tvBabyBoy.setOnClickListener(view -> {
            resetChange();
            binding.tvBabyBoy.setBackgroundResource(R.drawable.bg_item_s);
            iClickSticker.babyboy();
        });
        binding.tvBabyGirl.setOnClickListener(view -> {
            resetChange();
            binding.tvBabyGirl.setBackgroundResource(R.drawable.bg_item_s);
            iClickSticker.babygirl();
        });
        binding.tv1stTime.setOnClickListener(view -> {
            resetChange();
            binding.tv1stTime.setBackgroundResource(R.drawable.bg_item_s);
            iClickSticker._1sttime();
        });
        binding.tvAnnouncement.setOnClickListener(view -> {
            resetChange();
            binding.tvAnnouncement.setBackgroundResource(R.drawable.bg_item_s);
            iClickSticker.announcement();
        });
        binding.tvSummer.setOnClickListener(view -> {
            resetChange();
            binding.tvSummer.setBackgroundResource(R.drawable.bg_item_s);
            iClickSticker.summer();
        });
        binding.tvHoliday.setOnClickListener(view -> {
            resetChange();
            binding.tvHoliday.setBackgroundResource(R.drawable.bg_item_s);
            iClickSticker.holiday();
        });
        binding.tvNewYear.setOnClickListener(view -> {
            resetChange();
            binding.tvNewYear.setBackgroundResource(R.drawable.bg_item_s);
            iClickSticker.newyear();
        });
    }
    public void init(ClickStickerCallBack iClickSticker) {
        this.iClickSticker = iClickSticker;
    }
    private void resetChange() {
        binding.tvMilestones.setBackgroundResource(R.drawable.bg_item_sn);
        binding.tvAccessory.setBackgroundResource(R.drawable.bg_item_sn);
        binding.tvAlphabet.setBackgroundResource(R.drawable.bg_item_sn);
        binding.tvShapes.setBackgroundResource(R.drawable.bg_item_sn);
        binding.tvStatus.setBackgroundResource(R.drawable.bg_item_sn);
        binding.tvMotherDay.setBackgroundResource(R.drawable.bg_item_sn);
        binding.tvFatherDay.setBackgroundResource(R.drawable.bg_item_sn);
        binding.tvPregnancy.setBackgroundResource(R.drawable.bg_item_sn);
        binding.tvToys.setBackgroundResource(R.drawable.bg_item_sn);
        binding.tvBabyBoy.setBackgroundResource(R.drawable.bg_item_sn);
        binding.tvBabyGirl.setBackgroundResource(R.drawable.bg_item_sn);
        binding.tv1stTime.setBackgroundResource(R.drawable.bg_item_sn);
        binding.tvToys.setBackgroundResource(R.drawable.bg_item_sn);
        binding.tvBabyBoy.setBackgroundResource(R.drawable.bg_item_sn);
        binding.tvBabyGirl.setBackgroundResource(R.drawable.bg_item_sn);
        binding.tv1stTime.setBackgroundResource(R.drawable.bg_item_sn);
        binding.tvAnnouncement.setBackgroundResource(R.drawable.bg_item_sn);
        binding.tvSummer.setBackgroundResource(R.drawable.bg_item_sn);
        binding.tvHoliday.setBackgroundResource(R.drawable.bg_item_sn);
        binding.tvNewYear.setBackgroundResource(R.drawable.bg_item_sn);
    }
}
