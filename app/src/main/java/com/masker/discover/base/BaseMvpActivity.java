package com.masker.discover.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * CreatedBy: masker
 * Date: 2017/6/2
 * Description:
 */

public abstract class BaseMvpActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        attach();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detach();
    }

    protected abstract void attach();
    protected abstract void detach();
}
