package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.crop.callback;

import android.graphics.Bitmap;

public interface CropCallback extends Callback {
  void onSuccess(Bitmap cropped);
}
