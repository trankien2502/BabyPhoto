package com.tkt.spin_wheel.dialog;

import android.content.Context;

import androidx.annotation.NonNull;

import com.tkt.spin_wheel.base.BaseDialog;
import com.tkt.spin_wheel.databinding.DialogLoadingBinding;


public class LoadingDialog extends BaseDialog<DialogLoadingBinding> {
    public LoadingDialog(@NonNull Context context, boolean canAble) {
        super(context, canAble);
    }

    @Override
    protected DialogLoadingBinding setBinding() {
        return DialogLoadingBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void bindView() {

    }
}
