package com.my.volleycachedemo.volley;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.android.volley.toolbox.ImageLoader;
import com.my.volleycachedemo.MD5Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Risky on 15/10/20.
 */
public class DiskCache implements ImageLoader.ImageCache {

    private static final String cacheDir = "/sdcard/963/";
//    static String cacheDir = SDCardHelper.getSdCardPath();

    @Override
    public Bitmap getBitmap(String url) {
        url = MD5Util.getMD5Str(url);
        return BitmapFactory.decodeFile(cacheDir + url+".jpg");
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        File file = new File(cacheDir);
        if (!file.exists()) {
            file.mkdir();
        }
        url = MD5Util.getMD5Str(url);
//        File imageFile = new File(cacheDir, url+".jpg");
        File imageFile = new File(cacheDir, url+".ss");     //这里如果是jpg用户可以在相册中看到去掉jpg或者用其他的代替用户在相册就看不到
        if (!imageFile.exists()) {
            FileOutputStream fileOutputStream = null;
            try {
                imageFile.createNewFile();
                fileOutputStream = new FileOutputStream(imageFile);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileOutputStream != null) {
                    CloseHelper.close(fileOutputStream);
                }
            }
        }
    }
}
