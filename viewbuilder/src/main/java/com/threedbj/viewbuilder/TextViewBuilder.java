package com.threedbj.viewbuilder;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import com.threedbj.viewbuilder.generic.GenericTextViewBuilder;

public class TextViewBuilder extends GenericTextViewBuilder<TextViewBuilder, TextView> {

    public TextView build(Context c) {
        return  build(c, new TextView(c));
    }

    public static Typeface addFont(Context c, String path) {
        return GenericTextViewBuilder.addFont(c, path, false);
    }

    public static Typeface addFont(Context c, String path, boolean setDefault) {
        return GenericTextViewBuilder.addFont(c, path, setDefault);
    }

    public static Typeface addFont(Context c, String path, int id) {
        return GenericTextViewBuilder.addFont(c, path, id, false);
    }

    public static Typeface addFont(Context c, String path, int id, boolean setDefault) {
        return GenericTextViewBuilder.addFont(c, path, id, setDefault);
    }
}