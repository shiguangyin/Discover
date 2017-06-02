package com.masker.discover.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * CreatedBy: masker
 * Date: 2017/6/2
 * Description:
 */

public abstract class BaseMvpFragment extends BaseFragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        attach();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        detach();
    }

    protected abstract void attach();
    protected abstract void detach();
}
