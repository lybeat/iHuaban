package com.lybeat.huaban;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.lybeat.huaban.model.Image;

import java.util.ArrayList;

/**
 * Author: lybeat
 * Date: 2016/2/19
 */
public class ImageLoader {

    public static final String TAG = "ImageLoader";

    private Context context;

    public ImageLoader(Context context) {
        this.context = context;
    }

    public ArrayList<Image> queryImage() {
        ArrayList<Image> images = new ArrayList<>();
        ContentResolver cr = context.getContentResolver();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = new String[] {
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.MIME_TYPE,
                MediaStore.Images.Media.SIZE,
                MediaStore.Images.Media._ID,
        };
        Cursor cursor = cr.query(uri, projection,null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String path = cursor.getString(cursor.getColumnIndex(
                        MediaStore.Images.Media.DATA));
                String name = cursor.getString(cursor.getColumnIndex(
                        MediaStore.Images.Media.DISPLAY_NAME));
                String format = cursor.getString(cursor.getColumnIndex(
                        MediaStore.Images.Media.MIME_TYPE));
                long size = cursor.getInt(cursor.getColumnIndex(
                        MediaStore.Images.Media.SIZE));
                long id = cursor.getLong(cursor.getColumnIndex(
                        MediaStore.Images.Media._ID));

                Image image = new Image(path,
                        name,
                        format,
                        size);
                images.add(image);

                Log.i(TAG, "image name: " + name);
                Log.i(TAG, "image format: " + format);
                Log.i(TAG, "image size: " + size);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return images;
    }
}
