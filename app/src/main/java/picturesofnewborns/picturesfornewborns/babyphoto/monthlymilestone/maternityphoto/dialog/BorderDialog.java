package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import java.util.List;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.base.BaseDialog;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.DialogBorderBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.DialogOpacityBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.color.ColorModel;


public class BorderDialog extends BaseDialog<DialogBorderBinding> {

    public BorderDialog(@NonNull Context context, boolean canAble) {
        super(context, canAble);
    }

    @Override
    protected DialogBorderBinding setBinding() {
        return DialogBorderBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        Window window = getWindow();
        if (window != null) {
            window.setGravity(Gravity.BOTTOM);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // Xóa nền mờ
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); // Không làm mờ nền
        }
    }

    @Override
    protected void bindView() {

    }
}
