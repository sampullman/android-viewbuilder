package com.threedbj.viewbuilder.generic;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ListView;

@SuppressWarnings("unchecked")
public abstract class GenericListViewBuilder<B extends GenericListViewBuilder<B, V>, V extends ListView> extends GenericAbsListViewBuilder<B, V> {
    private View header, footer;
    private Drawable divider;
    private int dividerHeight;

    public B load(GenericListViewBuilder from) {
        this.header = from.header;
        this.footer = from.footer;
        this.divider = from.divider;
        this.dividerHeight = from.dividerHeight;
        return super.load(from);
    }

    public V build(Context c, V v) {
        if(header != null) {
            v.addHeaderView(header);
        }
        if(footer != null) {
            v.addFooterView(footer);
        }
        if(divider != null) {
            v.setDivider(divider);
        }
        if(dividerHeight != 0) {
            v.setDividerHeight(dividerHeight);
        }
        return super.build(c, v);
    }

    public B header(View header) {
        this.header = header;
        return (B)this;
    }

    public B footer(View footer) {
        this.footer = footer;
        return (B)this;
    }

    public B divider(Drawable divider) {
        this.divider = divider;
        return (B)this;
    }

    public B dividerHeight(int dividerHeight) {
        this.dividerHeight = dividerHeight;
        return (B)this;
    }
}