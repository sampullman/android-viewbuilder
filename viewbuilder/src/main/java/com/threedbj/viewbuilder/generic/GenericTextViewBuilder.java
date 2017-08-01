package com.threedbj.viewbuilder.generic;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

@SuppressWarnings("unchecked")
public abstract class GenericTextViewBuilder<B extends GenericTextViewBuilder<B, V>, V extends TextView> extends GenericViewBuilder<B, V> {
    private int gravity = -1;
    private CharSequence text = "";
    private CharSequence hint;
    private @ColorRes int textColor = -1;
    private float textSize = 16f;
    private int style = Typeface.NORMAL;

    public B load(GenericTextViewBuilder from) {
        this.gravity = from.gravity;
        this.text = from.text;
        this.hint = from.hint;
        this.textColor = from.textColor;
        this.textSize = from.textSize;
        this.style = from.style;
        return super.load(from);
    }

    public V build(Context c, V v) {
        v.setText(text);
        v.setHint(hint);
        v.setTextSize(textSize);
        if(gravity != -1) {
            v.setGravity(gravity);
        }
        if(textColor != -1) {
            v.setTextColor(ContextCompat.getColor(c, textColor));
        }
        if(style != Typeface.NORMAL) {
            v.setTypeface(v.getTypeface(), style);
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

    public B color(@ColorRes int color) {
        this.textColor = color;
        return (B)this;
    }

    public B bold() {
        if(style == Typeface.NORMAL) {
            style = Typeface.BOLD;
        } else if(style == Typeface.ITALIC) {
            style = Typeface.BOLD_ITALIC;
        }
        return (B)this;
    }

    public B italic() {
        if(style == Typeface.NORMAL) {
            style = Typeface.ITALIC;
        } else if(style == Typeface.BOLD) {
            style = Typeface.BOLD_ITALIC;
        }
        return (B)this;
    }
}