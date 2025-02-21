package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.start;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.R;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.api_data.ConstantApiData;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.base.BaseFragment;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.database.icon.IconDatabase;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.database.icon.IconModel;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.FragmentHomeBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.HomeActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.crop.CropActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.EditActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.util.SPUtils;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    List<IconModel> listHoliday = new ArrayList<>();
    List<IconModel> listMilestones = new ArrayList<>();
    List<IconModel> listAITrend = new ArrayList<>();
    List<IconModel> listGeneral = new ArrayList<>();
    IconAdapter iconAdapter;
    int state = 1;
    private static final int STATE_HOLIDAY = 1;
    private static final int STATE_MILESTONES = 2;
    private static final int STATE_AI_TREND = 3;
    private static final int STATE_GENERAL = 4;

    @Override
    public FragmentHomeBinding setBinding(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        return FragmentHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
        listHoliday = IconDatabase.getInstance(requireContext()).iconDAO().getIconByCategory(ConstantApiData.HOLIDAY_DEMO);
        listMilestones = IconDatabase.getInstance(requireContext()).iconDAO().getIconByCategory(ConstantApiData.MILESTONES_DEMO);
        listAITrend = IconDatabase.getInstance(requireContext()).iconDAO().getIconByCategory(ConstantApiData.AI_TREND_DEMO);
        iconAdapter = new IconAdapter(requireContext(), listHoliday, new IconClickCallBack() {
            @Override
            public void select(IconModel iconModel) {
                Intent intent = new Intent(requireActivity(), EditActivity.class);
                intent.putExtra(SPUtils.TEMPLATE, iconModel);
                startArc(intent);
            }
        });
        binding.rcvFrame.setAdapter(iconAdapter);
        changeState();
    }

    @Override
    public void bindView() {
        binding.clStart.setOnClickListener(view -> {
            startArc(new Intent(requireActivity(), CropActivity.class));
        });
        binding.tvMilestones.setOnClickListener(view -> {
            state = STATE_MILESTONES;
            changeState();
        });
        binding.tvHoliday.setOnClickListener(view -> {
            state = STATE_HOLIDAY;
            changeState();
        });
        binding.tvAITrend.setOnClickListener(view -> {
            state = STATE_AI_TREND;
            changeState();
        });
        binding.tvGeneral.setOnClickListener(view -> {
            state = STATE_GENERAL;
            changeState();
        });
    }

    private void resetChange() {
        binding.tvHoliday.setTextColor(Color.parseColor("#A3A3A3"));
        binding.tvMilestones.setTextColor(Color.parseColor("#A3A3A3"));
        binding.tvAITrend.setTextColor(Color.parseColor("#A3A3A3"));
        binding.tvGeneral.setTextColor(Color.parseColor("#A3A3A3"));

        binding.tvHoliday.setBackgroundResource(0);
        binding.tvMilestones.setBackgroundResource(0);
        binding.tvAITrend.setBackgroundResource(0);
        binding.tvGeneral.setBackgroundResource(0);
    }

    public void startArc(Intent intent) {
        if (getContext() instanceof HomeActivity) {
            HomeActivity main = (HomeActivity) getContext();
            main.resultLauncher.launch(intent);
        }
    }

    private void changeState() {
        resetChange();
        switch (state) {
            case STATE_HOLIDAY:
                binding.tvHoliday.setTextColor(Color.parseColor("#064380"));
                binding.tvHoliday.setBackgroundResource(R.drawable.bg_home_item);
                iconAdapter.setIconModelList(listHoliday);
                break;
            case STATE_MILESTONES:
                binding.tvMilestones.setTextColor(Color.parseColor("#064380"));
                binding.tvMilestones.setBackgroundResource(R.drawable.bg_home_item);
                iconAdapter.setIconModelList(listMilestones);
                break;
            case STATE_AI_TREND:
                binding.tvAITrend.setTextColor(Color.parseColor("#064380"));
                binding.tvAITrend.setBackgroundResource(R.drawable.bg_home_item);
                iconAdapter.setIconModelList(listAITrend);
                break;
            case STATE_GENERAL:
                binding.tvGeneral.setTextColor(Color.parseColor("#064380"));
                binding.tvGeneral.setBackgroundResource(R.drawable.bg_home_item);
                iconAdapter.setIconModelList(listGeneral);
                break;
        }
    }
}