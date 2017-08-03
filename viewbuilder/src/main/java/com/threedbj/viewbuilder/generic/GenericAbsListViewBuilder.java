package com.threedbj.viewbuilder.generic;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.AbsListView;
import android.widget.ListAdapter;

@SuppressWarnings("unchecked")
public abstract class GenericAbsListViewBuilder<B extends GenericAbsListViewBuilder<B, V>, V extends AbsListView> extends GenericAdapterViewBuilder<B, V, ListAdapter> {
    private float friction;
    @DrawableRes private int selector;

    public B load(GenericAbsListViewBuilder from) {
        this.friction = from.friction;
        this.selector = from.selector;
        return super.load(from);
    }

    public V build(Context c, V v) {
        v.setFriction(friction);
        v.setSelector(selector);
        return super.build(c, v);
    }

    public B friction(float friction) {
        this.friction = friction;
        return (B)this;
    }

    public B selector(@DrawableRes int selector) {
        this.selector = selector;
        return (B)this;
    }

}