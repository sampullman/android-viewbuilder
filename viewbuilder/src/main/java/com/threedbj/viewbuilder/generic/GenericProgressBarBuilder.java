package com.threedbj.viewbuilder.generic;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.AttrRes;
import android.widget.ProgressBar;

@SuppressWarnings("unchecked")
public abstract class GenericProgressBarBuilder <B extends GenericProgressBarBuilder<B, V>, V extends ProgressBar> extends GenericViewBuilder<B, V> {
    @AttrRes private int progressStyle = -1;
    private int progress = -1, min=-1, max=-1;

    public B load(GenericProgressBarBuilder from) {
        this.progressStyle = from.progressStyle;
        this.progress = from.progress;
        this.min = from.min;
        this.max = from.max;
        return super.load(from);
    }

    public V build(Context c, V v) {
        if(progress != -1) {
            v.setProgress(progress);
        }
        if(VERSION.SDK_INT >= 26 && min != -1) {
            v.setMin(min);
        }
        if(max != -1) {
            v.setMax(max);
        }
        return super.build(c, v);
    }

    public int getStyle() {
        return this.progressStyle;
    }

    public B style(@AttrRes int style) {
        this.progressStyle = style;
        return (B)this;
    }

    public B determinate() {
        this.progressStyle = android.R.attr.progressBarStyleHorizontal;
        return (B)this;
    }

    public B progress(int progress) {
        this.progress = progress;
        return (B)this;
    }

    public B min(int min) {
        this.min = min;
        return (B)this;
    }

    public B max(int max) {
        this.max = max;
        return (B)this;
    }

}
