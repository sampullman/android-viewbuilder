package com.threedbj.viewbuilder;

import android.content.Context;
import android.widget.LinearLayout;

import com.threedbj.viewbuilder.generic.GenericLinearLayoutBuilder;

public class LinearLayoutBuilder extends GenericLinearLayoutBuilder<LinearLayoutBuilder, LinearLayout> {

    public LinearLayout build(Context c) {
        return  build(c, new LinearLayout(c));
    }
}