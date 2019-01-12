package com.threedbj.viewbuilder.generic;

import android.content.Context;
import android.widget.RadioGroup;

public abstract class GenericRadioGroupBuilder<B extends GenericRadioGroupBuilder<B, V>, V extends RadioGroup> extends GenericLinearLayoutBuilder<B, V> {

    public B load(GenericRadioGroupBuilder from) {
        return super.load(from);
    }

    public V build(Context c, V v) {
        super.build(c, v);
        return v;
    }

}
