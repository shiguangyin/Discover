package com.masker.discover.activity;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.masker.discover.R;
import com.masker.discover.base.BaseActivity;
import com.masker.discover.global.App;
import com.masker.discover.global.Constans;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Author: masker
 * Date: 2017/8/9
 * Description:
 */

public class SettingActivity extends BaseActivity {
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.container)
    FrameLayout mContainer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);
        ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(getString(R.string.settings));
        }
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container,new SettingFragment())
                .commit();
    }

    @Override
    protected void initData() {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public static class SettingFragment extends PreferenceFragment{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getPreferenceManager().setSharedPreferencesName(Constans.SP_SETTINGS);
            addPreferencesFromResource(R.xml.preference_setting);
            initData();
        }

        private void initData(){
            final Preference prefClearCache = findPreference(getString(R.string.key_clear_cache));
            prefClearCache.setSummary(getString(R.string.cache_size,(int)dirSize(Glide.getPhotoCacheDir(App.getApp()))));
            prefClearCache.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Observable.create(new Observable.OnSubscribe<Boolean>() {
                        @Override
                        public void call(Subscriber<? super Boolean> subscriber) {
                            Glide.get(App.getApp()).clearDiskCache();
                            subscriber.onNext(true);
                        }
                    }).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<Boolean>() {
                                @Override
                                public void call(Boolean aBoolean) {
                                    prefClearCache.setSummary(getString(R.string.cache_size,(int)dirSize(Glide.getPhotoCacheDir(App.getApp()))));
                                    View view = getActivity().findViewById(R.id.container);
                                    Snackbar.make(view,getString(R.string.clear_success),Snackbar.LENGTH_SHORT).show();
                                }
                            });
                    return true;
                }
            });
            PrefChangedListener listener = new PrefChangedListener();
            Preference prefLoadQuality = findPreference(getString(R.string.key_load_quality));
            prefLoadQuality.setOnPreferenceChangeListener(listener);
            Preference prefDownloadQuality = findPreference(getString(R.string.key_download_quality));
            prefDownloadQuality.setOnPreferenceChangeListener(listener);
            Preference prefWallpaperQuality = findPreference(getString(R.string.key_wallpaper_quality));
            prefWallpaperQuality.setOnPreferenceChangeListener(listener);
        }

        private class PrefChangedListener implements Preference.OnPreferenceChangeListener{

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.setSummary((String)newValue);
                return true;
            }
        }

    }

    private static long dirSize(File dir) {
        if (dir.exists()) {
            long result = 0;
            File[] fileList = dir.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    result += dirSize(aFileList);
                } else {
                    result += aFileList.length();
                }
            }
            return result / 1024 / 1024;
        }
        return 0;
    }
}
