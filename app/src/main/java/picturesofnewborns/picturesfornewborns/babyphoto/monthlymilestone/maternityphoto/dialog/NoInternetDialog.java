package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.dialog;

import android.content.Context;

import androidx.annotation.NonNull;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.base.BaseDialog;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.DialogNoInternetBinding;


public class NoInternetDialog extends BaseDialog<DialogNoInternetBinding> {
    public NoInternetDialog(@NonNull Context context, boolean canAble) {
        super(context, canAble);
    }

    @Override
    protected DialogNoInternetBinding setBinding() {
        return DialogNoInternetBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void bindView() {

    }
}
