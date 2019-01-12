package com.threedbj.viewbuilder.generic;

import android.content.Context;
import android.widget.RadioButton;

public abstract class GenericRadioButtonBuilder<B extends GenericRadioButtonBuilder<B, V>, V extends RadioButton> extends GenericCompoundButtonBuilder<B, V> {

    public B load(GenericRadioButtonBuilder from) {
        return super.load(from);
    }

    public V build(Context c, V v) {
        super.build(c, v);
        return v;
    }
}
