package com.masker.discover.utils;

import android.content.Context;
import android.content.Intent;

import com.masker.discover.R;

/**
 * Author: masker
 * Date: 2017/7/18
 * Description:
 */

public class ShareUtils {
    public static void share(Context context,String title,String content){
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, title);
        shareIntent.putExtra(Intent.EXTRA_TEXT, content);
        shareIntent = Intent.createChooser(shareIntent, context.getString(R.string.APP_NAME));
        context.startActivity(shareIntent);
    }
}
