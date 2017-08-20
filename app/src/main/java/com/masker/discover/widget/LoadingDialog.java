package com.masker.discover.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.masker.discover.R;

/**
 * Author: masker
 * Date: 2017/8/20
 * Description:
 */

public class LoadingDialog extends DialogFragment{

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_dialog_loading,null);
        return new AlertDialog.Builder(getContext())
                .setView(view)
                .create();
    }
}
