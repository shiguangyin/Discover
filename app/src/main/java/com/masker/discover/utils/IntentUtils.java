package com.masker.discover.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import com.masker.discover.R;

import java.util.List;

/**
 * Author: masker
 * Date: 2017/9/2
 * Description:
 */

public class IntentUtils {
    public static void openUrl(Context context,String url){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        List<ResolveInfo> result = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_ALL);
        if(result != null && result.size() > 0){
            context.startActivity(intent);
        }
    }


    public static void share(Context context,String title,String content){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, title);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent = Intent.createChooser(intent, context.getString(R.string.APP_NAME));
        context.startActivity(intent);
    }
}
