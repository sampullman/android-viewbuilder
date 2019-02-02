package com.threedbj.viewbuilder;

import android.content.Context;
import android.widget.ListView;

import com.threedbj.viewbuilder.generic.GenericListViewBuilder;
import com.threedbj.viewbuilder.style.Style;

public class ListViewBuilder extends GenericListViewBuilder<ListViewBuilder, ListView> {

    public ListViewBuilder(Style style) {
        style(style);
    }

    public ListView build(Context c) { return super.build(c, new ListView(c)); }

}
