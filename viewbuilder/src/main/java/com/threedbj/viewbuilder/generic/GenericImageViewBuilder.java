package com.threedbj.viewbuilder.generic;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

@SuppressWarnings("unchecked")
public abstract class GenericImageViewBuilder<B extends GenericImageViewBuilder<B, V>, V extends ImageView> extends GenericViewBuilder<B, V> {
    private int imageAlpha = 255;
    private ScaleType scaleType = ScaleType.FIT_CENTER;
    private Drawable drawable;
    private @DrawableRes int resource = 0;
    private CharSequence contentDescription;

    public B load(GenericImageViewBuilder from) {
        this.imageAlpha = from.imageAlpha;
        this.scaleType = from.scaleType;
        this.drawable = from.drawable;
        this.resource = from.resource;
        return super.load(from);
    }

    public V build(Context c, V v) {
        if(Build.VERSION.SDK_INT >= 16) {
            v.setImageAlpha(imageAlpha);
        }
        if(drawable != null) {
            v.setImageDrawable(drawable);
        } else if(resource != 0) {
            v.setImageResource(resource);
        }
        v.setScaleType(scaleType);
        v.setContentDescription(contentDescription);
        return super.build(c, v);
    }

    public B imageAlpha(int alpha) {
        this.imageAlpha = alpha;
        return (B)this;
    }

    public B scale(ScaleType type) {
        this.scaleType = type;
        return (B)this;
    }

    public B image(Drawable drawable) {
        this.drawable = drawable;
        return (B)this;
    }

    public B image(@DrawableRes int resource) {
        this.resource = resource;
        return (B)this;
    }

    public B description(CharSequence description) {
        this.contentDescription = description;
        return (B)this;
    }

}