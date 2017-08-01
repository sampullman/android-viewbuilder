package com.threedbj.viewbuilder;

import android.content.Context;
import android.widget.FrameLayout;

import com.threedbj.viewbuilder.generic.GenericFrameLayoutBuilder;

public class FrameLayoutBuilder extends GenericFrameLayoutBuilder<FrameLayoutBuilder, FrameLayout> {

    public FrameLayout build(Context c) {
        return  build(c, new FrameLayout(c));
    }
}