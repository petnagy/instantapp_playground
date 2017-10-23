package com.playground.instant.base_module.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.playground.instant.base.R;


/**
 * Created by petnagy on 2017. 10. 23..
 */

public class ErrorDialog extends DialogFragment {

    private static final String ERROR_MESSAGE_EXTRA = "errorMessageExtra";

    public static ErrorDialog newInstance(String message) {
        ErrorDialog frag = new ErrorDialog();
        Bundle args = new Bundle();
        args.putString(ERROR_MESSAGE_EXTRA, message);
        frag.setArguments(args);
        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String errorMessage = getArguments().getString(ERROR_MESSAGE_EXTRA);

        return new AlertDialog.Builder(getActivity())
                .setIcon(android.R.drawable.stat_notify_error)
                .setTitle(R.string.error_dialog_title)
                .setMessage(errorMessage)
                .setNegativeButton(R.string.error_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dismissAllowingStateLoss();
                            }
                        }
                )
                .create();
    }
}
