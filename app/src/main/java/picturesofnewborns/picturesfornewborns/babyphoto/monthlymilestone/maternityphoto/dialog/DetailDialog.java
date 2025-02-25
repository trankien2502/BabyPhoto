package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;

import androidx.annotation.NonNull;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.base.BaseBottomSheetDialog;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.DialogMoreBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.DialogOpacityBinding;


public class DetailDialog extends BaseBottomSheetDialog<DialogMoreBinding> {
    public DetailDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected DialogMoreBinding setBinding() {
        return DialogMoreBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void bindView() {

    }
}
