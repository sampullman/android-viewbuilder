package com.threedbj.viewbuilder.generic;

import android.content.Context;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

@SuppressWarnings("unchecked")
public abstract class GenericRadioGroupBuilder<B extends GenericRadioGroupBuilder<B, V>, V extends RadioGroup> extends GenericLinearLayoutBuilder<B, V> {
    private OnCheckedChangeListener checkedChangeListener;

    public B load(GenericRadioGroupBuilder from) {
        this.checkedChangeListener = from.checkedChangeListener;
        return super.load(from);
    }

    public V build(Context c, V v) {
        super.build(c, v);
        if(checkedChangeListener != null) {
            v.setOnCheckedChangeListener(checkedChangeListener);
        }
        return v;
    }

    public B checkedChange(OnCheckedChangeListener checkedChangeListener) {
        this.checkedChangeListener = checkedChangeListener;
        return (B)this;
    }

}
