package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.custom_sticker;

import android.graphics.Bitmap;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.widget.ImageView;

public class Effect {
    private static ColorMatrix getColorMatrixSepia() {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        ColorMatrix colorMatrix2 = new ColorMatrix();
        colorMatrix2.setScale(1.0f, 1.0f, 0.8f, 1.0f);
        colorMatrix.postConcat(colorMatrix2);
        return colorMatrix;
    }

    public static ColorMatrixColorFilter getEffect0() {
        return new ColorMatrixColorFilter(new float[]{
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0
        });
    }

    public static ColorMatrixColorFilter getEffect1() {
        return new ColorMatrixColorFilter(new float[]{
                0.213f, 0.715f, 0.072f, 0, 0,
                0.213f, 0.715f, 0.072f, 0, 0,
                0.213f, 0.715f, 0.072f, 0, 0,
                0, 0, 0, 1, 0
        });
    }

    public static ColorMatrixColorFilter getEffect2() {
        return new ColorMatrixColorFilter(getColorMatrixSepia());
    }

    public static ColorMatrixColorFilter getEffect3() {
        return new ColorMatrixColorFilter(new float[]{
                -1, 0, 0, 0, 255,
                0, -1, 0, 0, 255,
                0, 0, -1, 0, 255,
                0, 0, 0, 1, 0
        });
    }

    public static ColorMatrixColorFilter getEffect4() {
        return new ColorMatrixColorFilter(new float[]{
                3.2f, 0, 0, 0, -280.5f,
                0, 3.2f, 0, 0, -280.5f,
                0, 0, 3.2f, 0, -280.5f,
                0, 0, 0, 1, 0
        });
    }

    public static ColorMatrixColorFilter getEffect5() {
        return new ColorMatrixColorFilter(new float[]{
                1.5f, 0, 0, 0, -63.75f,
                0, 1.5f, 0, 0, -63.75f,
                0, 0, 1.5f, 0, -63.75f,
                0, 0, 0, 1, 0
        });
    }

    public static ColorMatrixColorFilter getEffect6() {
        return new ColorMatrixColorFilter(new float[]{
                2, 0, 0, 0, 0,
                0, 2, 0, 0, 0,
                0, 0, 2, 0, 0,
                0, 0, 0, 0.5f, 0
        });
    }

    public static ColorMatrixColorFilter getEffect7() {
        return new ColorMatrixColorFilter(new float[]{
                2, 0, 0, 0, 0,
                0, 2, 0, 0, 0,
                0, 0, 2, 0, 0,
                0, 0, 0, 1, 0
        });
    }

    public static ColorMatrixColorFilter getEffect8() {
        return new ColorMatrixColorFilter(new float[]{
                1, 0, 0, 0, 30,
                0, 1, 0, 0, 30,
                0, 0, 1, 0, 30,
                0, 0, 0, 1, 0
        });
    }

    public static ColorMatrixColorFilter getEffect9() {
        return new ColorMatrixColorFilter(new float[]{
                1, 0, 0, 0, -60,
                0, 1, 0, 0, -60,
                0, 0, 1, 0, -90,
                0, 0, 0, 1, 0
        });
    }

    public static ColorMatrixColorFilter getEffect10() {
        return new ColorMatrixColorFilter(new float[]{
                1.438f, -0.062f, -0.062f, 0, 0,
                -0.122f, 1.378f, -0.122f, 0, 0,
                -0.016f, -0.016f, 1.483f, 0, 0,
                0, 0, 0, 1, 0
        });
    }

    public static ColorMatrixColorFilter getEffect11() {
        return new ColorMatrixColorFilter(new float[]{
                0.5f, 0.5f, 0.5f, 0, 0,
                0.5f, 0.5f, 0.5f, 0, 0,
                0.5f, 0.5f, 0.5f, 0, 0,
                0, 0, 0, 1, 0
        });
    }

    public static ColorMatrixColorFilter getEffect12() {
        return new ColorMatrixColorFilter(new float[]{
                1.5f, -0.5f, -0.5f, 0, 0,
                -0.5f, 1.5f, -0.5f, 0, 0,
                -0.5f, -0.5f, 1.5f, 0, 0,
                0, 0, 0, 1, 0
        });
    }

    public static ColorMatrixColorFilter getEffect13() {
        return new ColorMatrixColorFilter(new float[]{
                0.393f, 0.769f, 0.189f, 0, 0,
                0.349f, 0.686f, 0.168f, 0, 0,
                0.272f, 0.534f, 0.131f, 0, 0,
                0, 0, 0, 1, 0
        });
    }

    public static ColorMatrixColorFilter getEffect14() {
        return new ColorMatrixColorFilter(new float[]{
                1.2f, 0.2f, 0.2f, 0, 0,
                0.2f, 1.2f, 0.2f, 0, 0,
                0.2f, 0.2f, 1.2f, 0, 0,
                0, 0, 0, 1, 0
        });
    }

    public static ColorMatrixColorFilter getEffect15() {
        return new ColorMatrixColorFilter(new float[]{
                1.195f, 0, 0, 0, 0,
                0, 0.672f, 0, 0, 0,
                0, 0, 0.398f, 0, 0,
                0, 0, 0, 1.867f, 0
        });
    }

    public static ColorMatrixColorFilter getEffect16() {
        return new ColorMatrixColorFilter(new float[]{1.0390625f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.3671875f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.4921875f, 0.0f});
    }

    public static ColorMatrixColorFilter getEffect17() {
        return new ColorMatrixColorFilter(new float[]{1.5625f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.565625f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.5234375f, 0.0f});
    }

    public static ColorMatrixColorFilter getEffect18() {
        return new ColorMatrixColorFilter(new float[]{0.9609375f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.6171875f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.40625f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.8046875f, 0.0f});
    }

    public static ColorMatrixColorFilter getEffect19() {
        return new ColorMatrixColorFilter(new float[]{0.7109375f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.34375f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.35625f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.9921875f, 0.0f});
    }

    public static ColorMatrixColorFilter getEffect20() {
        return new ColorMatrixColorFilter(new float[]{1.0703125f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.25f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.140625f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.4140625f, 0.0f});
    }

    public static ColorMatrixColorFilter getEffect21() {
        return new ColorMatrixColorFilter(new float[]{1.1953125f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.671875f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.3984375f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.7265625f, 0.0f});
    }


    public static ColorMatrixColorFilter getEffect22() {
        return new ColorMatrixColorFilter(new float[]{
                1.195f, 0, 0, 0, 0,
                0, 0.672f, 0, 0, 0,
                0, 0, 0.398f, 0, 0,
                0, 0, 0, 1.867f, 0
        });
    }

    public static ImageView applyEffect0(ImageView imageView) {
        imageView.setColorFilter(new ColorMatrixColorFilter(new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        return imageView;
    }

    public static ImageView applyEffect1(ImageView imageView) {
        imageView.setColorFilter(new ColorMatrixColorFilter(new float[]{0.213f, 0.715f, 0.072f, 0.0f, 0.0f, 0.213f, 0.715f, 0.072f, 0.0f, 0.0f, 0.213f, 0.715f, 0.072f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        return imageView;
    }

    public static ImageView applyEffect2(ImageView imageView) {
        imageView.setColorFilter(new ColorMatrixColorFilter(getColorMatrixSepia()));
        return imageView;
    }

    public static ImageView applyEffect4(ImageView imageView) {
        imageView.setColorFilter(new ColorMatrixColorFilter(new float[]{3.2f, 0.0f, 0.0f, 0.0f, -280.5f, 0.0f, 3.2f, 0.0f, 0.0f, -280.5f, 0.0f, 0.0f, 3.2f, 0.0f, -280.5f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        return imageView;
    }

    public static ImageView applyEffect5(ImageView imageView) {
        imageView.setColorFilter(new ColorMatrixColorFilter(new float[]{1.5f, 0.0f, 0.0f, 0.0f, -63.75f, 0.0f, 1.5f, 0.0f, 0.0f, -63.75f, 0.0f, 0.0f, 1.5f, 0.0f, -63.75f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        return imageView;
    }

    public static ImageView applyEffect6(ImageView imageView) {
        imageView.setColorFilter(new ColorMatrixColorFilter(new float[]{2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f}));
        return imageView;
    }

    public static ImageView applyEffect7(ImageView imageView) {
        imageView.setColorFilter(new ColorMatrixColorFilter(new float[]{2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, -0.1f, -0.1f, -0.1f, 0.0f, 1.0f}));
        return imageView;
    }

    public static ImageView applyEffect9(ImageView imageView) {
        imageView.setColorFilter(new ColorMatrixColorFilter(new float[]{1.0f, 0.0f, 0.0f, 0.0f, -60.0f, 0.0f, 1.0f, 0.0f, 0.0f, -60.0f, 0.0f, 0.0f, 1.0f, 0.0f, -90.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        return imageView;
    }

    public static ImageView applyEffect11(ImageView imageView) {
        imageView.setColorFilter(new ColorMatrixColorFilter(new float[]{1.0f, 0.0f, 0.0f, 0.0f, -60.0f, 0.0f, 1.0f, 0.0f, 0.0f, -60.0f, 0.0f, 0.0f, 1.0f, 0.0f, -90.0f, -0.213f, -0.715f, -0.072f, 0.0f, 255.0f}));
        return imageView;
    }

    public static ImageView applyEffect12(ImageView imageView) {
        imageView.setColorFilter(new ColorMatrixColorFilter(new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 90.0f, 0.213f, 0.715f, 0.072f, 0.0f, 255.0f}));
        return imageView;
    }

    public static ImageView applyEffect14(ImageView imageView) {
        imageView.setColorFilter(new ColorMatrixColorFilter(new float[]{1.375f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.390625f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.1640625f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.6796875f, 0.0f}));
        return imageView;
    }

    public static ImageView applyEffect15(ImageView imageView) {
        imageView.setColorFilter(new ColorMatrixColorFilter(new float[]{1.5234375f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.203125f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.015625f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.28125f, 0.0f}));
        return imageView;
    }

    public static ImageView applyEffect16(ImageView imageView) {
        imageView.setColorFilter(new ColorMatrixColorFilter(new float[]{1.0390625f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.3671875f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.4921875f, 0.0f}));
        return imageView;
    }

    public static ImageView applyEffect17(ImageView imageView) {
        imageView.setColorFilter(new ColorMatrixColorFilter(new float[]{1.5625f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.565625f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.5234375f, 0.0f}));
        return imageView;
    }

    public static ImageView applyEffect18(ImageView imageView) {
        imageView.setColorFilter(new ColorMatrixColorFilter(new float[]{0.9609375f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.6171875f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.40625f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.8046875f, 0.0f}));
        return imageView;
    }

    public static ImageView applyEffect19(ImageView imageView) {
        imageView.setColorFilter(new ColorMatrixColorFilter(new float[]{0.7109375f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.34375f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.35625f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.9921875f, 0.0f}));
        return imageView;
    }

    public static ImageView applyEffect20(ImageView imageView) {
        imageView.setColorFilter(new ColorMatrixColorFilter(new float[]{1.0703125f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.25f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.140625f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.4140625f, 0.0f}));
        return imageView;
    }

    public static ImageView applyEffect21(ImageView imageView) {
        imageView.setColorFilter(new ColorMatrixColorFilter(new float[]{1.1953125f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.671875f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.3984375f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.7265625f, 0.0f}));
        return imageView;
    }

    public static ImageView applyEffect22(ImageView imageView) {
        imageView.setColorFilter(new ColorMatrixColorFilter(new float[]{1.1953125f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.671875f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.3984375f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.8671875f, 0.0f}));
        return imageView;
    }
}
