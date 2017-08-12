package com.masker.discover.global;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import com.masker.discover.R;

import java.io.File;

/**
 * Author: masker
 * Date: 2017/8/11
 * Description:
 */

public class PhotoManager {
    public static final String DOWNLOAD_DIR = "discover";

    private static volatile PhotoManager sInstance;
    private DownloadManager mDownloadManager;
    private Context mContext;

    private PhotoManager(Context context){
        mContext = context;
        mDownloadManager = (DownloadManager)
                context.getSystemService(Context.DOWNLOAD_SERVICE);
    }

    public static PhotoManager getInstance(Context context){
        if(sInstance == null){
            synchronized (PhotoManager.class){
                if(sInstance == null){
                    sInstance = new PhotoManager(context);
                }
            }
        }
        return sInstance;
    }

    public void download(String url,String name){
        File dir = Environment.getExternalStoragePublicDirectory(DOWNLOAD_DIR);
        if(!dir.exists()){
            dir.mkdirs();
        }
        try{
            Uri uri = Uri.parse(url);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setDestinationInExternalPublicDir(DOWNLOAD_DIR,name);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
            request.setTitle(name);
            request.setDescription(mContext.getString(R.string.downloading));
            mDownloadManager.enqueue(request);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
