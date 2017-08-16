package com.masker.discover.user.view;


import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.masker.discover.R;
import com.masker.discover.base.BaseMvpActivity;
import com.masker.discover.model.entity.UserInfoBean;
import com.masker.discover.rx.RxBus;
import com.masker.discover.rx.event.ProfileChangeEvent;
import com.masker.discover.user.contract.EditProfileContract;
import com.masker.discover.user.presenter.EditProfilePresenter;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: masker
 * Date: 2017/8/16
 * Description:edit personal profile
 */

public class EditProfileActivity extends BaseMvpActivity implements EditProfileContract.View {
    public static final String KEY_USER = "user";
    public static final int CODE = 100;

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.edit_user_name)
    TextInputEditText mEditUserName;
    @BindView(R.id.edit_first_name)
    TextInputEditText mEditFirstName;
    @BindView(R.id.edit_last_name)
    TextInputEditText mEditLastName;
    @BindView(R.id.edit_mail)
    TextInputEditText mEditMail;
    @BindView(R.id.edit_personal_site)
    TextInputEditText mEditPersonalSite;
    @BindView(R.id.edit_instagram)
    TextInputEditText mEditInstagram;
    @BindView(R.id.edit_location)
    TextInputEditText mEditLocation;
    @BindView(R.id.edit_biography)
    TextInputEditText mEditBiography;
    @BindView(R.id.btn_save)
    Button mBtnSave;
    @BindView(R.id.loading_view)
    AVLoadingIndicatorView mLoadingView;
    @BindView(R.id.container)
    LinearLayout mContainer;

    private EditProfilePresenter mPresenter;
    private UserInfoBean mInfoBean;

    @Override
    protected void attach() {
        mPresenter = new EditProfilePresenter(this);
    }

    @Override
    protected void detach() {
        mPresenter.onUnsubscribe();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_profile;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        mToolbar.setTitle(getString(R.string.edit_profile));
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void initData() {
        if (mInfoBean != null) {
            mEditUserName.setText(mInfoBean.getUsername());
            mEditFirstName.setText(mInfoBean.getFirst_name());
            mEditLastName.setText(mInfoBean.getLast_name());
            mEditMail.setText(mInfoBean.getEmail());
            mEditPersonalSite.setText(mInfoBean.getPortfolio_url());
            mEditInstagram.setText(mInfoBean.getInstagram_username());
            mEditLocation.setText(mInfoBean.getLocation());
            mEditBiography.setText(mInfoBean.getBio());
        }
    }

    @Override
    protected void initListeners() {
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = mEditUserName.getText().toString();
                String firstName = mEditFirstName.getText().toString();
                String lastName = mEditLastName.getText().toString();
                String email = mEditMail.getText().toString();
                String url = mEditPersonalSite.getText().toString();
                String instagram = mEditInstagram.getText().toString();
                String location = mEditLocation.getText().toString();
                String bio = mEditBiography.getText().toString();
                mLoadingView.smoothToShow();
                mPresenter.updateMyInfo(userName, firstName, lastName, email, url, location, bio, instagram);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showError() {
        mLoadingView.smoothToHide();
        Snackbar.make(mContainer,getString(R.string.edit_error),Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void updateSuccess(UserInfoBean userInfo) {
        mLoadingView.smoothToHide();
        mInfoBean = userInfo;
        RxBus.post(new ProfileChangeEvent(mInfoBean));
        Snackbar.make(mContainer,getString(R.string.edit_success),Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void handleIntent() {
        mInfoBean = getIntent().getParcelableExtra(KEY_USER);
    }

    public static void start(Activity activity, UserInfoBean info) {
        Intent intent = new Intent(activity, EditProfileActivity.class);
        intent.putExtra(KEY_USER, info);
        activity.startActivityForResult(intent,CODE);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(KEY_USER,mInfoBean);
        setResult(RESULT_OK,intent);
        super.onBackPressed();
    }
}
