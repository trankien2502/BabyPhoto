package com.tkt.spin_wheel.dialog;

import android.content.Context;

import androidx.annotation.NonNull;

import com.tkt.spin_wheel.base.BaseDialog;
import com.tkt.spin_wheel.databinding.DialogPermissionBinding;


public class GoToSettingDialog extends BaseDialog<DialogPermissionBinding> {
    public GoToSettingDialog(@NonNull Context context, boolean canAble) {
        super(context, canAble);
    }

    @Override
    protected DialogPermissionBinding setBinding() {
        return DialogPermissionBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void bindView() {

    }
}
