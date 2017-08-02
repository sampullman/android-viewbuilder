package com.threedbj.viewbuilder.generic;

import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.threedbj.viewbuilder.util.Util;

import static android.view.Gravity.CENTER;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

@SuppressWarnings("unchecked")
public abstract class GenericViewBuilder<B extends GenericViewBuilder<B, V>, V extends View> {

    private enum ParentType {
        VIEWGROUP, LINEAR, FRAME, RELATIVE
    }

    // Special class to avoid some copies in load() during normal use
    private class RelativeLayoutParams {
        int above, alignBaseline, alignBottom, alignEnd, alignStart, alignTop, below, endOf, startOf;
        boolean parentBottom, parentEnd, parentStart, parentTop, centerHorizontal, centerVertical, center;

        RelativeLayoutParams(RelativeLayoutParams from) {
            this.above = from.above;
            this.alignBaseline = from.alignBaseline;
            this.alignBottom = from.alignBottom;
            this.alignEnd = from.alignEnd;
            this.alignStart = from.alignStart;
            this.alignTop = from.alignTop;
            this.below = from.below;
            this.endOf = from.endOf;
            this.startOf = from.startOf;
            this.parentBottom = from.parentBottom;
            this.parentEnd = from.parentEnd;
            this.parentStart = from.parentStart;
            this.parentTop = from.parentTop;
            this.centerHorizontal = from.centerHorizontal;
            this.centerVertical = from.centerVertical;
            this.center = from.center;
        }

        private void addRule(RelativeLayout.LayoutParams params, int verb, int id) {
            if(id != 0) {
                params.addRule(verb, id);
            }
        }

        private void addRule(RelativeLayout.LayoutParams params, int verb, boolean enable) {
            if(enable) {
                params.addRule(verb);
            }
        }

        RelativeLayout.LayoutParams addRules(RelativeLayout.LayoutParams params) {
            addRule(params, RelativeLayout.ABOVE, above);
            addRule(params, RelativeLayout.ALIGN_BASELINE, alignBaseline);
            addRule(params, RelativeLayout.ALIGN_BOTTOM, alignBottom);
            if(Build.VERSION.SDK_INT >= 17) {
                addRule(params, RelativeLayout.ALIGN_END, alignEnd);
                addRule(params, RelativeLayout.ALIGN_START, alignStart);
                addRule(params, RelativeLayout.END_OF, endOf);
                addRule(params, RelativeLayout.START_OF, startOf);
                addRule(params, RelativeLayout.ALIGN_PARENT_END, parentEnd);
                addRule(params, RelativeLayout.ALIGN_PARENT_START, parentStart);
            } else {
                addRule(params, RelativeLayout.ALIGN_RIGHT, alignEnd);
                addRule(params, RelativeLayout.ALIGN_LEFT, alignStart);
                addRule(params, RelativeLayout.RIGHT_OF, endOf);
                addRule(params, RelativeLayout.LEFT_OF, startOf);
                addRule(params, RelativeLayout.ALIGN_PARENT_LEFT, parentEnd);
                addRule(params, RelativeLayout.ALIGN_PARENT_RIGHT, parentStart);
            }
            addRule(params, RelativeLayout.ALIGN_TOP, alignTop);
            addRule(params, RelativeLayout.BELOW, below);
            addRule(params, RelativeLayout.ALIGN_PARENT_BOTTOM, parentBottom);
            addRule(params, RelativeLayout.ALIGN_PARENT_TOP, parentTop);
            addRule(params, RelativeLayout.CENTER_HORIZONTAL, centerHorizontal);
            addRule(params, RelativeLayout.CENTER_VERTICAL, centerVertical);
            addRule(params, RelativeLayout.CENTER_IN_PARENT, center);
            return params;
        }
    }

    private int id;
    private float weight = 1f;
    private int layoutWidth = MATCH_PARENT, layoutHeight = MATCH_PARENT;
    private int layoutGravity = CENTER;
    private @ColorRes int backgroundColor = -1;
    private int paddingLeft, paddingTop, paddingRight, paddingBottom;
    private int marginLeft, marginTop, marginRight, marginBottom;
    private ParentType parentType = ParentType.VIEWGROUP;
    private OnClickListener clickListener;
    private RelativeLayoutParams relativeLayoutParams;

    public B load(GenericViewBuilder from) {
        this.weight = from.weight;
        this.layoutWidth = from.layoutWidth;
        this.layoutHeight = from.layoutHeight;
        this.layoutGravity = from.layoutGravity;
        this.backgroundColor = from.backgroundColor;
        this.paddingLeft = from.paddingLeft;
        this.paddingTop = from.paddingTop;
        this.paddingRight = from.paddingRight;
        this.paddingBottom = from.paddingBottom;
        this.marginLeft = from.marginLeft;
        this.marginTop = from.marginTop;
        this.marginRight = from.marginRight;
        this.marginBottom = from.marginBottom;
        this.parentType = from.parentType;
        this.clickListener = from.clickListener;
        if(from.relativeLayoutParams != null) {
            this.relativeLayoutParams = new RelativeLayoutParams(from.relativeLayoutParams);
        }
        return (B)this;
    }

    public V build(Context c, V v) {
        v.setLayoutParams(makeParams());
        if(backgroundColor != -1) {
            v.setBackgroundColor(ContextCompat.getColor(c, backgroundColor));
        }
        Log.d("ViewBuilder", String.format("Building: %d %d : %d", layoutWidth, layoutHeight, parentType.ordinal()));
        v.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        v.setOnClickListener(clickListener);
        return v;
    }

    public abstract V build(Context c);

    public V build(ViewGroup vg) {
        V me = build(vg.getContext());
        vg.addView(me);
        return me;
    }

    private LayoutParams makeParams() {
        MarginLayoutParams params;
        switch(parentType) {
            case LINEAR:
                params = new LinearLayout.LayoutParams(layoutWidth, layoutHeight, weight);
                params.setMargins(marginLeft, marginTop, marginRight, marginBottom);
                break;
            case RELATIVE:
                params = new RelativeLayout.LayoutParams(layoutWidth, layoutHeight);
                params.setMargins(marginLeft, marginTop, marginRight, marginBottom);
                break;
            case FRAME:
                params = new FrameLayout.LayoutParams(layoutWidth, layoutHeight, layoutGravity);
                params.setMargins(marginLeft, marginTop, marginRight, marginBottom);
                break;
            default:
                return new LayoutParams(layoutWidth, layoutHeight);
        }
        return params;
    }

    public B backgroundColor(@ColorRes int res) {
        this.backgroundColor = res;
        return (B)this;
    }

    public B width(int width) {
        this.layoutWidth = width;
        return (B)this;
    }

    public B height(int height) {
        this.layoutHeight = height;
        return (B)this;
    }

    public B wrap() {
        this.layoutHeight = WRAP_CONTENT;
        this.layoutWidth = WRAP_CONTENT;
        return (B)this;
    }

    public B paddingDp(int left, int top, int right, int bottom) {
        return paddingPx(Util.dpToPx(left), Util.dpToPx(top),
                Util.dpToPx(right), Util.dpToPx(bottom));
    }

    public B paddingPx(int left, int top, int right, int bottom) {
        this.paddingLeft = left;
        this.paddingTop = top;
        this.paddingRight = right;
        this.paddingBottom = bottom;
        return (B)this;
    }

    public B marginDp(int left, int top, int right, int bottom) {
        return marginPx(Util.dpToPx(left), Util.dpToPx(top),
                Util.dpToPx(right), Util.dpToPx(bottom));
    }

    public B marginPx(int left, int top, int right, int bottom) {
        this.marginLeft = left;
        this.marginTop = top;
        this.marginRight = right;
        this.marginBottom = bottom;
        return (B)this;
    }

    public B inFrame() {
        this.parentType = ParentType.FRAME;
        return (B)this;
    }

    public B inRelative() {
        this.parentType = ParentType.RELATIVE;
        return (B)this;
    }

    public B inLinear() {
        this.parentType = ParentType.LINEAR;
        return (B)this;
    }

    public B layoutGravity(int gravity) {
        this.layoutGravity = gravity;
        return (B)this;
    }

    public B weight(float weight) {
        this.weight = weight;
        return (B)this;
    }

    public B listener(OnClickListener listener) {
        this.clickListener = listener;
        return (B)this;
    }
}