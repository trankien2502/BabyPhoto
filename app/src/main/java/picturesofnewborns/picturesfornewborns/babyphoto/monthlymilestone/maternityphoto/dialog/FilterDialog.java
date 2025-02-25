package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;

import androidx.annotation.NonNull;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.base.BaseDialog;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.DialogFilterBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.DialogOpacityBinding;


public class FilterDialog extends BaseDialog<DialogFilterBinding> {
    public FilterDialog(@NonNull Context context, boolean canAble) {
        super(context, canAble);
    }

    @Override
    protected DialogFilterBinding setBinding() {
        return DialogFilterBinding.inflate(getLayoutInflater());
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

    }
}
