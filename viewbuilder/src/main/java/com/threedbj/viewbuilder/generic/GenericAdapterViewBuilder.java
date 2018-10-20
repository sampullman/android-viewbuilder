package com.threedbj.viewbuilder.generic;

import android.content.Context;
import android.widget.Adapter;
import android.widget.AdapterView;

@SuppressWarnings("unchecked")
public abstract class GenericAdapterViewBuilder<B extends GenericAdapterViewBuilder<B, V, A>, V extends AdapterView, A extends Adapter> extends GenericViewGroupBuilder<B, V> {
    private A adapter;

    public V build(Context c, V v) {
        super.build(c, v);
        v.setAdapter(adapter);
        return v;
    }

    public B adapter(A adapter) {
        this.adapter = adapter;
        return (B)this;
    }

}