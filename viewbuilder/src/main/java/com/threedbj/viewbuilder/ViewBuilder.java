package com.threedbj.viewbuilder;

import android.content.Context;
import android.view.View;

import com.threedbj.viewbuilder.generic.GenericViewBuilder;

public class ViewBuilder extends GenericViewBuilder<ViewBuilder, View> {

    public View build(Context c) {
        return build(c, new View(c));
    }
}
