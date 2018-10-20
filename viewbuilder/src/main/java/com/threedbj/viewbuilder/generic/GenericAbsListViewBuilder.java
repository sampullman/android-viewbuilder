package com.threedbj.viewbuilder.generic;

import android.content.Context;
import androidx.annotation.DrawableRes;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;

@SuppressWarnings("unchecked")
public abstract class GenericAbsListViewBuilder<B extends GenericAbsListViewBuilder<B, V>, V extends AbsListView> extends GenericAdapterViewBuilder<B, V, ListAdapter> {
    private float friction = -1f;
    private OnScrollListener scrollListener;
    @DrawableRes private int selector;

    public B load(GenericAbsListViewBuilder from) {
        this.friction = from.friction;
        this.selector = from.selector;
        this.scrollListener = from.scrollListener;
        return super.load(from);
    }

    public V build(Context c, V v) {
        super.build(c, v);
        if(friction >= 0) {
            v.setFriction(friction);
        }
        if(selector != 0) {
            v.setSelector(selector);
        }
        v.setOnScrollListener(scrollListener);
        return v;
    }

    public B friction(float friction) {
        this.friction = friction;
        return (B)this;
    }

    public B selector(@DrawableRes int selector) {
        this.selector = selector;
        return (B)this;
    }

    public B scrollListener(OnScrollListener scrollListener) {
        this.scrollListener = scrollListener;
        return (B)this;
    }

}