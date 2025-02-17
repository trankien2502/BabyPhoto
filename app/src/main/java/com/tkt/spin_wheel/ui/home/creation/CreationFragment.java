package com.tkt.spin_wheel.ui.home.creation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tkt.spin_wheel.R;
import com.tkt.spin_wheel.base.BaseFragment;
import com.tkt.spin_wheel.databinding.FragmentCreationBinding;

public class CreationFragment extends BaseFragment<FragmentCreationBinding> {


    @Override
    public FragmentCreationBinding setBinding(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        return FragmentCreationBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {

    }

    @Override
    public void bindView() {

    }
}