package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.start;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.base.BaseFragment;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.FragmentHomeBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.HomeActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.crop.CropActivity;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {


    @Override
    public FragmentHomeBinding setBinding(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        return FragmentHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {

    }

    @Override
    public void bindView() {
        binding.clStart.setOnClickListener(view -> {
            startArc(new Intent(requireActivity(), CropActivity.class));
        });
    }

    public void startArc(Intent intent) {
        if (getContext() instanceof HomeActivity) {
            HomeActivity main = (HomeActivity) getContext();
            main.resultLauncher.launch(intent);
        }
    }
}