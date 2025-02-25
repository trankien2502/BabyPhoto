package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.base.BaseDialog;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.DialogFrameBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.DialogPermissionBinding;


public class FrameDialog extends BaseDialog<DialogFrameBinding> {
    public FrameDialog(@NonNull Context context, boolean canAble) {
        super(context, canAble);
    }

    @Override
    protected DialogFrameBinding setBinding() {
        return DialogFrameBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        Window window = getWindow();
        if (window != null) {
            window.setGravity(Gravity.BOTTOM);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    protected void bindView() {

    }
}
