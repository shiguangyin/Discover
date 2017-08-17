package com.masker.discover.utils;

import android.graphics.Bitmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author: masker
 * Date: 2017/8/17
 * Description:
 */

public class FileUtils {
    public static boolean saveBitmap(Bitmap bitmap, File imgFile, Bitmap.CompressFormat format){
        File parent = imgFile.getParentFile();
        if(!parent.exists()){
            parent.mkdirs();
        }
        FileOutputStream fos = null;
        try {
            if(!imgFile.exists()){
                imgFile.createNewFile();
            }
            fos = new FileOutputStream(imgFile);
            bitmap.compress(format,100,fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            CloseUtils.close(fos);
        }
        return true;
    }
}
