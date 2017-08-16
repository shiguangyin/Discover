package com.masker.discover.downloads;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.masker.discover.rx.event.DownloadFinishEvent;
import com.masker.discover.rx.RxBus;

/**
 * Author: masker
 * Date: 2017/8/14
 * Description: download finish receiver
 */

public class CompleteReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,-1);
        RxBus.post(new DownloadFinishEvent(id));
    }
}
