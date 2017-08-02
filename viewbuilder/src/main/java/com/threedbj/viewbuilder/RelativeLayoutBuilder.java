package com.threedbj.viewbuilder;

import android.content.Context;
import android.widget.RelativeLayout;

import com.threedbj.viewbuilder.generic.GenericRelativeLayoutBuilder;

public class RelativeLayoutBuilder extends GenericRelativeLayoutBuilder<RelativeLayoutBuilder, RelativeLayout> {

    public RelativeLayout build(Context c) {
        return  build(c, new RelativeLayout(c));
    }
}
