package com.threedbj.viewbuilder;

import android.content.Context;
import android.widget.ScrollView;

import com.threedbj.viewbuilder.generic.GenericScrollViewBuilder;

public class ScrollViewBuilder extends GenericScrollViewBuilder<ScrollViewBuilder, ScrollView> {

    public ScrollView build(Context c) {
        return super.build(c, new ScrollView(c));
    }
}
