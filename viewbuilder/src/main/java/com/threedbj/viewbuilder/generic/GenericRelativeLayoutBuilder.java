package com.threedbj.viewbuilder.generic;

import android.widget.RelativeLayout;

public abstract class GenericRelativeLayoutBuilder<B extends GenericRelativeLayoutBuilder<B, V>, V extends RelativeLayout> extends GenericViewGroupBuilder<B, V> {

    public B load(GenericRelativeLayoutBuilder from) {
        return super.load(from);
    }
}
