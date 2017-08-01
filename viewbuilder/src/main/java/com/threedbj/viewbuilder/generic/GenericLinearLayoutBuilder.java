package com.threedbj.viewbuilder.generic;

import android.content.Context;
import android.widget.LinearLayout;

import static android.widget.LinearLayout.HORIZONTAL;
import static android.widget.LinearLayout.VERTICAL;

@SuppressWarnings("unchecked")
public abstract class GenericLinearLayoutBuilder<B extends GenericLinearLayoutBuilder<B, V>, V extends LinearLayout> extends GenericViewGroupBuilder<B, V> {
    private int orientation = HORIZONTAL;

    public B load(GenericLinearLayoutBuilder from) {
        this.orientation = from.orientation;
        return super.load(from);
    }

    public V build(Context c, V v) {
        v.setOrientation(this.orientation);
        return super.build(c, v);
    }

    public B vertical() {
        this.orientation = VERTICAL;
        return (B)this;
    }

    public B horizontal() {
        this.orientation = HORIZONTAL;
        return (B)this;
    }

}