package com.tkt.spin_wheel.custom_sticker;

/**
 * Created by shashi on 6/6/18
 */

public class FlipBothDirectionsEvent extends AbstractFlipEvent {

  @Override
  @StickerView.Flip protected int getFlipDirection() {
    return StickerView.FLIP_VERTICALLY | StickerView.FLIP_HORIZONTALLY;
  }
}
