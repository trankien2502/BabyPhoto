package com.tkt.spin_wheel.custom_sticker;

public class StickerAction {
    boolean status; //true: sticker đang được add mới, false: kéo thả vị trí
    Sticker sticker;

    public StickerAction(boolean status, Sticker sticker) {
        this.status = status;
        this.sticker = sticker;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Sticker getSticker() {
        return sticker;
    }

    public void setSticker(Sticker sticker) {
        this.sticker = sticker;
    }
}
