package com.threedbj.viewbuilder.generic;

import android.content.Context;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

@SuppressWarnings("unchecked")
public abstract class GenericCompoundButtonBuilder<B extends GenericCompoundButtonBuilder<B, V>, V extends CompoundButton> extends GenericButtonBuilder<B, V> {
    private OnCheckedChangeListener checkedChangeListener;
    private boolean checked = false;

    public B load(GenericCompoundButtonBuilder from) {
        this.checkedChangeListener = from.checkedChangeListener;
        this.checked = checked;
        return super.load(from);
    }

    public V build(Context c, V v) {
        if(checkedChangeListener != null) {
            v.setOnCheckedChangeListener(checkedChangeListener);
        }
        if(checked) {
            v.setChecked(checked);
        }
        return super.build(c, v);
    }

    public B checkedChangeListener(OnCheckedChangeListener checkedChangeListener) {
        this.checkedChangeListener = checkedChangeListener;
        return (B)this;
    }

    public B checked(boolean checked) {
        this.checked = checked;
        return (B)this;
    }

}
