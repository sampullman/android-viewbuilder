package com.threedbj.viewbuilder.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

public class Util {
    private static int[] dpToPxCache = new int[40];

    public static float dpToPxFloat(int dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static int dpToPx(int dp) {
        if(dp >= dpToPxCache.length) {

            return (int)dpToPxFloat(dp);

        } else if(dpToPxCache[dp] == 0) {

            dpToPxCache[dp] = (int)dpToPxFloat(dp);
        }
        return dpToPxCache[dp];
    }

}
