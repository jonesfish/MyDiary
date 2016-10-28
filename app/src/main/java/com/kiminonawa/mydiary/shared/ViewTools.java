package com.kiminonawa.mydiary.shared;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by daxia on 2016/10/28.
 */

public class ViewTools {

    public static int dpToPixel(final Resources r, final int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }
}
