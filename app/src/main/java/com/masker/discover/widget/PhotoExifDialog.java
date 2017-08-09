package com.masker.discover.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.masker.discover.R;
import com.masker.discover.model.entity.PhotoBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: masker
 * Date: 2017/8/9
 * Description:
 */

public class PhotoExifDialog extends DialogFragment {
    @BindView(R.id.tv_make)
    TextView mTvMake;
    @BindView(R.id.tv_model)
    TextView mTvModel;
    @BindView(R.id.tv_exposure)
    TextView mTvExposure;
    @BindView(R.id.tv_aperture)
    TextView mTvAperture;
    @BindView(R.id.tv_iso)
    TextView mTvIso;
    @BindView(R.id.tv_focale)
    TextView mTvFocale;
    Unbinder unbinder;
    private PhotoBean mPhotoBean;
    private View mView;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mView = LayoutInflater.from(getContext()).inflate(R.layout.layout_dialog_photoinfo, null);
        unbinder = ButterKnife.bind(this, mView);
        initData();
        return new AlertDialog.Builder(getContext())
                .setView(mView)
                .create();
    }

    public void setData(PhotoBean photoBean) {
        mPhotoBean = photoBean;
    }

    private void initData() {
        String unknown = "Unknown";
        PhotoBean.ExifBean exif = mPhotoBean.getExif();
        String make = "Make : "+(TextUtils.isEmpty(exif.getMake())?unknown:exif.getMake());
        mTvMake.setText(make);
        String model = "Model : "+(TextUtils.isEmpty(exif.getModel())?unknown:exif.getModel());
        mTvModel.setText(model);
        String exposure = "Exposure time : "+(TextUtils.isEmpty(exif.getExposure_time())?unknown:exif.getExposure_time());
        mTvExposure.setText(exposure);
        String aperture = "Aperture : "+(TextUtils.isEmpty(exif.getAperture())?unknown:exif.getAperture());
        mTvAperture.setText(aperture);
        String iso = "ISO : "+exif.getIso();
        mTvIso.setText(iso);
        String focal = "Focal length : "+(TextUtils.isEmpty(exif.getFocal_length())?unknown:exif.getFocal_length());
        mTvFocale.setText(focal);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
