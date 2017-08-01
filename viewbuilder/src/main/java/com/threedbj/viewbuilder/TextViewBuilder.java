package com.threedbj.viewbuilder;

import android.content.Context;
import android.widget.TextView;

import com.threedbj.viewbuilder.generic.GenericTextViewBuilder;

public class TextViewBuilder extends GenericTextViewBuilder<TextViewBuilder, TextView> {

    public TextView build(Context c) {
        return  build(c, new TextView(c));
    }
}