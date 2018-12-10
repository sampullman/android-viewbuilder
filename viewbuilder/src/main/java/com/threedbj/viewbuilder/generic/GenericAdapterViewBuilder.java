package com.threedbj.viewbuilder.generic;

import android.content.Context;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

@SuppressWarnings("unchecked")
public abstract class GenericAdapterViewBuilder<B extends GenericAdapterViewBuilder<B, V, A>, V extends AdapterView, A extends Adapter> extends GenericViewGroupBuilder<B, V> {
    private A adapter;
    private OnItemClickListener itemClickListener;
    private OnItemLongClickListener longItemClickListener;

    public V build(Context c, V v) {
        super.build(c, v);
        v.setAdapter(adapter);
        if(itemClickListener != null) {
            v.setOnItemClickListener(itemClickListener);
        }
        if(longItemClickListener != null) {
            v.setOnItemLongClickListener(longItemClickListener);
        }
        return v;
    }

    public B adapter(A adapter) {
        this.adapter = adapter;
        return (B)this;
    }

    public B itemClick(OnItemClickListener listener) {
        this.itemClickListener = listener;
        return (B)this;
    }

    public B itemLongClick(OnItemLongClickListener listener) {
        this.longItemClickListener = listener;
        return (B)this;
    }

}