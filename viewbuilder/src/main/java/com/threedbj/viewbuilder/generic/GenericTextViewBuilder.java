package com.threedbj.viewbuilder.generic;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;

import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;

@SuppressWarnings("unchecked")
public abstract class GenericTextViewBuilder<B extends GenericTextViewBuilder<B, V>, V extends TextView> extends GenericViewBuilder<B, V> {

    // Cache for Typefaces keyed on font path
    private static final HashMap<String, Typeface> FONT_CACHE = new HashMap<>();

    // Convenience for accessing fonts via integer IDs
    private static final SparseArray<Typeface> FONT_IDS = new SparseArray<>();

    private static Typeface DEFAULT_FONT = null;

    protected static Typeface addFont(Context c, String path, boolean setDefault) {
        Typeface font = FONT_CACHE.get(path);
        if(font == null) {
            font = Typeface.createFromAsset(c.getAssets(), path);
            FONT_CACHE.put(path, font);
        }
        if(setDefault) {
            DEFAULT_FONT = font;
        }
        return font;
    }

    protected static Typeface addFont(Context c, String path, int id, boolean setDefault) {
        Typeface font = FONT_IDS.get(id);
        if(font == null) {
            font = addFont(c, path, setDefault);
            FONT_IDS.put(id, font);
        }
        if(setDefault) {
            DEFAULT_FONT = font;
        }
        return font;
    }

    private int gravity = -1;
    private CharSequence text = "";
    private CharSequence hint;
    private @ColorRes int textColor = -1;
    private float textSize = 16f;
    private int textSelectable = -1;
    private Typeface typeface = DEFAULT_FONT;
    private String fontPath;
    private int style = Typeface.NORMAL;
    private boolean firstFocus = true;

    public B load(GenericTextViewBuilder from) {
        this.gravity = from.gravity;
        this.text = from.text;
        this.hint = from.hint;
        this.textColor = from.textColor;
        this.textSize = from.textSize;
        this.textSelectable = from.textSelectable;
        this.typeface = from.typeface;
        this.fontPath = from.fontPath;
        this.style = from.style;
        this.firstFocus = from.firstFocus;
        return super.load(from);
    }

    public V build(ViewGroup vg) {
        if(firstFocus) {
            vg.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
            vg.setFocusableInTouchMode(true);
        }
        return super.build(vg);
    }

    public V build(Context c, V v) {
        super.build(c, v);

        v.setText(text);
        v.setHint(hint);
        v.setTextSize(textSize);
        if(textSelectable != -1) {
            v.setTextIsSelectable(textSelectable == 1);
        }

        if(gravity != -1) {
            v.setGravity(gravity);
        }

        if(textColor != -1) {
            v.setTextColor(ContextCompat.getColor(c, textColor));
        }

        if(typeface != null) {
            v.setTypeface(typeface, style);

        } else if(fontPath != null) {

            this.typeface = addFont(c, fontPath, false);
            v.setTypeface(typeface, style);
            this.fontPath = null;

        } else if(style != Typeface.NORMAL) {
            v.setTypeface(v.getTypeface(), style);
        }

        return v;
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

    public B textSizeSp(Activity context, float textSizeSp) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return textSize(textSizeSp * dm.scaledDensity);
    }

    public B textSelectable(boolean selectable) {
        this.textSelectable = selectable ? 1 : 0;
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

    public B font(String path) {
       this.typeface = FONT_CACHE.get(path);
        if(this.typeface == null) {
            this.fontPath = path;
        }
        return (B)this;
    }

    public B font(int id) {
        this.typeface = FONT_IDS.get(id);
        return (B)this;
    }

    // Only works when build(ViewGroup) is called
    public B noFirstFocus() {
        this.firstFocus = false;
        return (B)this;
    }
}