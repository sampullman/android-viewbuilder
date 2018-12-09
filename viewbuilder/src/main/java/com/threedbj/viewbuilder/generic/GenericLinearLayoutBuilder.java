package com.threedbj.viewbuilder.generic;

import android.content.Context;
import android.widget.LinearLayout;

import static android.widget.LinearLayout.HORIZONTAL;
import static android.widget.LinearLayout.VERTICAL;

@SuppressWarnings("unchecked")
public abstract class GenericLinearLayoutBuilder<B extends GenericLinearLayoutBuilder<B, V>, V extends LinearLayout> extends GenericViewGroupBuilder<B, V> {
    private int orientation = HORIZONTAL;
    private float weightSum = -1.0f;

    public B load(GenericLinearLayoutBuilder from) {
        this.orientation = from.orientation;
        return super.load(from);
    }

    public V build(Context c, V v) {
        super.build(c, v);
        if(weightSum >= 0) {
            v.setWeightSum(weightSum);
        }
        v.setOrientation(this.orientation);
        return v;
    }

    public B weightSum(float sum) {
        this.weightSum = sum;
        return (B)this;
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