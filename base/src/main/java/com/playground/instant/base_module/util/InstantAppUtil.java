package com.playground.instant.base_module.util;


import android.content.Context;

import com.google.android.instantapps.InstantApps;
import com.playground.instant.base.R;

/**
 * Created by petnagy on 2017. 10. 23..
 */

public final class InstantAppUtil {

    private InstantAppUtil() {
    }

    public static String getAppType(Context context) {
        String result;
        if (InstantApps.isInstantApp(context)) {
            result = context.getString(R.string.instant_app_toast_message);
        } else {
            result = context.getString(R.string.installed_app_toast_message);
        }
        return result;
    }

}
