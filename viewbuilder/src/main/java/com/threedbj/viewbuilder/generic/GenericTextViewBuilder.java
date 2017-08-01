package com.threedbj.viewbuilder.generic;

import android.content.Context;
import android.widget.TextView;

public abstract class GenericTextViewBuilder<B extends GenericTextViewBuilder<B, V>, V extends TextView> extends GenericViewBuilder<B, V> {
    private int gravity = -1;
    private CharSequence text = "";
    private CharSequence hint;
    private float textSize = 16f;

    public V build(Context c, V v) {
        v.setText(text);
        v.setHint(hint);
        v.setTextSize(textSize);
        if(gravity != -1) {
            v.setGravity(gravity);
        }
        return super.build(c, v);
    }

    public B gravity(int gravity) {
        this.gravity = gravity;
        return (B)this;
    }

    public B text(CharSequence text) {
        this.text = text;
        return (B)this;
    }

    public B hint(CharSequence hint) {
        this.hint = hint;
        return (B)this;
    }

    public B textSize(float textSize) {
        this.textSize = textSize;
        return (B)this;
    }
}