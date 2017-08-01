package com.threedbj.viewbuilder.generic;

import android.content.Context;
import android.widget.TextView;

public abstract class GenericTextViewBuilder<B extends GenericTextViewBuilder<B, V>, V extends TextView> extends GenericViewBuilder<B, V> {
    int gravity = -1;
    String text = "";
    float textSize = 16f;

    public V build(Context c, V v) {
        v.setText(text);
        v.setTextSize(textSize);
        return super.build(c, v);
    }

    public B gravity(int gravity) {
        this.gravity = gravity;
        return (B)this;
    }

    public B text(String text) {
        this.text = text;
        return (B)this;
    }

    public B textSize(float textSize) {
        this.textSize = textSize;
        return (B)this;
    }
}