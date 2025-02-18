package com.tkt.spin_wheel.custom_sticker;

import android.view.MotionEvent;

public class DeleteIconEvent implements StickerIconEvent {
  @Override
  public void onActionDown(StickerView stickerView, MotionEvent event) {

  }

  @Override
  public void onActionMove(StickerView stickerView, MotionEvent event) {

  }

  @Override
  public void onActionUp(StickerView stickerView, MotionEvent event) {
    if (!stickerView.isLockCurrent()) {
      stickerView.removeCurrentSticker();
    }
  }
}
