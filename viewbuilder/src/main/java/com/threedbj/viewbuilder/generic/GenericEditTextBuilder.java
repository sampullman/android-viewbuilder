package com.threedbj.viewbuilder.generic;

import android.content.Context;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.widget.EditText;

@SuppressWarnings("unchecked")
public abstract class GenericEditTextBuilder<B extends GenericEditTextBuilder<B, V>, V extends EditText> extends GenericTextViewBuilder<B, V> {
    private int inputType = -1;
    String digits;
    InputFilter[] filters;

    public B load(GenericEditTextBuilder from) {
        this.inputType = from.inputType;
        this.digits = from.digits;
        this.filters = from.filters;
        return super.load(from);
    }

    public V build(Context c, V v) {
        super.build(c, v);
        if(inputType != -1) {
            v.setInputType(inputType);
        }
        if(digits != null) {
            v.setKeyListener(DigitsKeyListener.getInstance(digits));
        }
        v.setFilters(filters);
        return v;
    }

    public B inputType(int inputType) {
        this.inputType = inputType;
        return (B)this;
    }

    public B digits(String digits) {
        this.digits = digits;
        return (B)this;
    }

    public B filters(InputFilter[] filters) {
        this.filters = filters;
        return (B)this;
    }
}