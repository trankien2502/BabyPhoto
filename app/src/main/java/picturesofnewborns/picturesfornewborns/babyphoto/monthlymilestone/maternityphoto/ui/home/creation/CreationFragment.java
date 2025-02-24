package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.creation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.R;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.base.BaseFragment;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.database.design.DesignDatabase;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.database.design.DesignModel;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.FragmentCreationBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.HomeActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.util.EventTracking;

public class CreationFragment extends BaseFragment<FragmentCreationBinding> {
    List<DesignModel> list = new ArrayList<>();
    DesignAdapter designAdapter;

    @Override
    public FragmentCreationBinding setBinding(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        return FragmentCreationBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
        list.clear();
        list.addAll(DesignDatabase.getInstance(requireContext()).designDAO().getDesigns());
        designAdapter = new DesignAdapter(requireContext(), list, new ClickDesignCallBack() {
            @Override
            public void select(DesignModel designModel) {
                EventTracking.logEvent(requireContext(),"album_my_design_item_click");
                Intent intent = new Intent(requireContext(), DesignDetailActivity.class);
                intent.putExtra("DESIGN_MODEL", designModel);
                startArc(intent);
            }
        });
        if (list.isEmpty()) binding.llNoData.setVisibility(View.VISIBLE);
        else binding.llNoData.setVisibility(View.GONE);
        binding.rcvDesign.setAdapter(designAdapter);
    }

    public void startArc(Intent intent) {
        if (getContext() instanceof HomeActivity) {
            HomeActivity main = (HomeActivity) getContext();
            main.resultLauncher.launch(intent);
        }
    }

    @Override
    public void bindView() {

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onResume() {
        super.onResume();
        if (designAdapter != null) {
            list.clear();
            list.addAll(DesignDatabase.getInstance(requireContext()).designDAO().getDesigns());
            designAdapter.notifyDataSetChanged();
            if (list.isEmpty()) binding.llNoData.setVisibility(View.VISIBLE);
            else binding.llNoData.setVisibility(View.GONE);
        }
    }
}