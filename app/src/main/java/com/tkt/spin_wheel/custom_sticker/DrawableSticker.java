package com.tkt.spin_wheel.custom_sticker;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.annotation.IntRange;

/**
 * Created by shashi on 6/6/18
 */

public class DrawableSticker extends Sticker {

    private Drawable drawable;
    private Drawable originalDrawable;

    private String drawablePath;

    private Rect realBounds;

    public DrawableSticker(Drawable drawable2, String drawablePath) {
        this.drawable = drawable2;
        this.drawablePath = drawablePath;
        originalDrawable = drawable2.getConstantState().newDrawable().mutate();
        realBounds = new Rect(0, 0, getWidth(), getHeight());
        clearDirty();
    }

    @Override
    public Drawable getDrawable() {
        return drawable;
    }

    @Override
    public String getDrawablePath() {
        return drawablePath;
    }

    @Override
    public DrawableSticker setDrawable(Drawable drawable) {
        this.drawable = drawable;
        markAsDirty();
        return this;
    }

    public Drawable getOriginalDrawable() {
        return this.originalDrawable.getConstantState().newDrawable().mutate();
    }

    public void setOriginalDrawable(Drawable drawable1) {
        this.originalDrawable = drawable1.getConstantState().newDrawable().mutate();
        markAsDirty();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.concat(getMatrix());
        if (drawable != null) {
            drawable.setBounds(realBounds);
        }
        drawable.draw(canvas);
        canvas.restore();
        clearDirty();
    }

    @Override
    public DrawableSticker setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        drawable.setAlpha(alpha);
        markAsDirty();
        return this;
    }

    @Override
    public int getWidth() {
        return drawable.getIntrinsicWidth();
    }

    @Override
    public int getHeight() {
        return drawable.getIntrinsicHeight();
    }

    @Override
    public void release() {
        super.release();
        if (drawable != null) {
            drawable = null;
        }
    }
}
