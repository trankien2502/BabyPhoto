package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.R;

public class ImageUtils {
    public static byte[] captureView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Matrix matrix = new Matrix();
        view.getMatrix().invert(matrix);
        canvas.concat(view.getMatrix());
        view.draw(canvas);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public static Bitmap captureViewBitmap(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Matrix matrix = new Matrix();
        view.getMatrix().invert(matrix);
        canvas.concat(view.getMatrix());
        view.draw(canvas);

        return bitmap;
    }


    public static void saveImageToInternalStorage(Context context, Bitmap bitmap, String fileName) {
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Bitmap createSquareBitmapWithTransparentBackground(Bitmap original) {
        // Kích thước mới cho ảnh
        int targetSize = 512;

        // Tính tỷ lệ để ảnh vừa với khung 512x512
        int originalWidth = original.getWidth();
        int originalHeight = original.getHeight();
        float scale = (float) targetSize / Math.max(originalWidth, originalHeight);

        int newWidth = Math.round(originalWidth * scale);
        int newHeight = Math.round(originalHeight * scale);

        // Resize ảnh gốc theo tỷ lệ
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(original, newWidth, newHeight, true);

        // Tạo một bitmap mới 512x512 với nền trong suốt
        Bitmap outputBitmap = Bitmap.createBitmap(targetSize, targetSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(outputBitmap);

        // Vẽ nền trong suốt
        canvas.drawColor(Color.TRANSPARENT);

        // Tính toán vị trí để vẽ ảnh gốc vào giữa khung
        int left = (targetSize - newWidth) / 2;
        int top = (targetSize - newHeight) / 2;

        // Vẽ ảnh gốc vào canvas
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawBitmap(scaledBitmap, left, top, paint);

        return outputBitmap;
    }

    public static Bitmap loadImageFromInternalStorage(Context context, String fileName) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            return BitmapFactory.decodeStream(fis);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap loadBitmapImageFromInternalStorage(String filePath) {
        try {
            File imgFile = new File(filePath);
            if (imgFile.exists()) {
                return BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public static String getImagePathFromInternalStorage(Context context, String fileName) {
        try {
            File file = new File(context.getFilesDir(), fileName);
            if (file.exists()) {
                return file.getAbsolutePath();
            } else {
                Log.e("img_check", "File not found");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveImageToMediaStore(Context context, Bitmap bitmap) {
        ContentResolver resolver = context.getContentResolver();
        Uri imageUri;
        OutputStream fos = null;
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Downloads.DISPLAY_NAME, "CatMaker_" + System.currentTimeMillis() + ".png");
        contentValues.put(MediaStore.Downloads.MIME_TYPE, "image/png");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            contentValues.put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS + "/CatMaker");
            imageUri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues);
            try {
                if (imageUri != null) {
                    fos = resolver.openOutputStream(imageUri);
                    assert fos != null;
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    Toast.makeText(context, context.getString(R.string.download_success), Toast.LENGTH_SHORT).show();
                    fos.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(context, context.getString(R.string.download_failed), Toast.LENGTH_SHORT).show();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void saveBitmap(Context context, Bitmap bitmap) {
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/Download/CatMaker");
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }
        File imageFile = new File(storageDir, "CatMaker" + System.currentTimeMillis() + ".png");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(imageFile);

            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            Toast.makeText(context, context.getString(R.string.download_success), Toast.LENGTH_SHORT).show();
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("img_check", "failed down: ", e);
            Toast.makeText(context, context.getString(R.string.download_failed), Toast.LENGTH_SHORT).show();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
