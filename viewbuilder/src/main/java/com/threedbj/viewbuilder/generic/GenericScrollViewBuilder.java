package com.threedbj.viewbuilder.generic;

import android.content.Context;
import android.widget.ScrollView;

import static android.view.View.OVER_SCROLL_ALWAYS;

@SuppressWarnings("unchecked")
public abstract class GenericScrollViewBuilder <B extends GenericScrollViewBuilder<B, V>, V extends ScrollView> extends GenericFrameLayoutBuilder<B, V> {
    private boolean fillViewport = false;
    private int overScrollMode = OVER_SCROLL_ALWAYS;

    public B load(GenericScrollViewBuilder from) {
        this.fillViewport = from.fillViewport;
        this.overScrollMode = from.overScrollMode;
        return super.load(from);
    }

    public V build(Context c, V v) {
        super.build(c, v);
        v.setFillViewport(fillViewport);
        v.setOverScrollMode(overScrollMode);
        return v;
    }

    public B fillViewport(boolean fill) {
        this.fillViewport = fill;
        return (B)this;
    }

    public B overScrollMode(int mode) {
        this.overScrollMode = mode;
        return (B)this;
    }
}