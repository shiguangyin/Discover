package com.masker.discover.tag;

import android.support.annotation.NonNull;

import com.masker.discover.global.App;
import com.masker.discover.base.BasePresenter;
import com.masker.discover.model.entity.TagBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * CreatedBy: masker
 * Date: 2017/4/30
 * Description:
 */

public class TagListPresenter extends BasePresenter<TagListContract.View> implements TagListContract.Presenter{


    public TagListPresenter(@NonNull TagListContract.View view) {
        super(view);
    }

    @Override
    public void loadTags() {
        final String path = "tags.json";
        Observable.just(path)
                .map(new Func1<String, List<TagBean>>() {
                    @Override
                    public List<TagBean> call(String s) {
                        InputStream is ;
                        BufferedReader reader;
                        List<TagBean> tags = new ArrayList<>();
                        StringBuilder sb = new StringBuilder();
                        String content;
                        try {
                            is = App.getApp().getAssets().open(path);
                            reader = new BufferedReader(new InputStreamReader(is));
                            char[] buff = new char[128];
                            while(reader.read(buff)!=-1){
                                sb.append(buff);
                            }
                            content = sb.toString();
                            JSONArray array = new JSONArray(content);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                TagBean tag = new TagBean();
                                tag.setTitle(obj.getString("title"));
                                tag.setUrl(obj.getString("url"));
                                tags.add(tag);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return tags;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<TagBean>>() {
                    @Override
                    public void call(List<TagBean> tags) {
                        mView.showTags(tags);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError();
                    }
                });
    }
}
