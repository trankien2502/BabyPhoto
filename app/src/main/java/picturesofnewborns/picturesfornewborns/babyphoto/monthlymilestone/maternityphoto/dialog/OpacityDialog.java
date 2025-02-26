package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.base.BaseDialog;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.DialogFrameBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.DialogOpacityBinding;


public class OpacityDialog extends BaseDialog<DialogOpacityBinding> {
    public OpacityDialog(@NonNull Context context, boolean canAble) {
        super(context, canAble);
    }

    @Override
    protected DialogOpacityBinding setBinding() {
        return DialogOpacityBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        Window window = getWindow();
        if (window != null) {
            window.setGravity(Gravity.BOTTOM);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // Xóa nền mờ
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); // Không làm mờ nền
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    protected void bindView() {

    }
}
