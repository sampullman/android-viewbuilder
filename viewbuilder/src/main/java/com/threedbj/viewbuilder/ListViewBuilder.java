package com.threedbj.viewbuilder;

import android.content.Context;
import android.widget.ListView;

import com.threedbj.viewbuilder.generic.GenericListViewBuilder;

public class ListViewBuilder extends GenericListViewBuilder<ListViewBuilder, ListView> {

    public ListView build(Context c) { return super.build(c, new ListView(c)); }

}
