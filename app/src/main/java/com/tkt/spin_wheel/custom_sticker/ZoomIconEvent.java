package com.tkt.spin_wheel.custom_sticker;

import android.view.MotionEvent;

/**
 * Created by shashi on 6/6/18
 */

public class ZoomIconEvent implements StickerIconEvent {
  @Override
  public void onActionDown(StickerView stickerView, MotionEvent event) {

  }

  @Override
  public void onActionMove(StickerView stickerView, MotionEvent event) {
    stickerView.zoomCurrentSticker(event);
  }

  @Override
  public void onActionUp(StickerView stickerView, MotionEvent event) {
    if (stickerView.getOnStickerOperationListener() != null) {
      stickerView.getOnStickerOperationListener()
          .onStickerZoomFinished(stickerView.getCurrentSticker());
    }
  }
}
