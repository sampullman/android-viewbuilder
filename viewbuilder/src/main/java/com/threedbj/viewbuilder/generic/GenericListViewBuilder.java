package com.threedbj.viewbuilder.generic;

import android.content.Context;
import android.widget.ListView;

public abstract class GenericListViewBuilder<B extends GenericListViewBuilder<B, V>, V extends ListView> extends GenericAbsListViewBuilder<B, V> {

    public V build(Context c, V v) {
        return super.build(c, v);
    }

}