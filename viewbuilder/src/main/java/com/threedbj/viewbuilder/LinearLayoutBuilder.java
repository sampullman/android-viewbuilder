package com.threedbj.viewbuilder;

import android.content.Context;
import android.widget.LinearLayout;

import com.threedbj.viewbuilder.generic.GenericLinearLayoutBuilder;
import com.threedbj.viewbuilder.style.Style;

public class LinearLayoutBuilder extends GenericLinearLayoutBuilder<LinearLayoutBuilder, LinearLayout> {

    public LinearLayoutBuilder() {}

    public LinearLayoutBuilder(Style style) {
        style(style);
    }

    public LinearLayout build(Context c) {
        return  build(c, new LinearLayout(c));
    }
}