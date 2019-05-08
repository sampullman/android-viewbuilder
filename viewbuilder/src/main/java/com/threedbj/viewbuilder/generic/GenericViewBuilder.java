package com.threedbj.viewbuilder.generic;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.threedbj.viewbuilder.style.Style;
import com.threedbj.viewbuilder.util.Util;

import static android.view.Gravity.CENTER;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

@SuppressWarnings("unchecked")
public abstract class GenericViewBuilder<B extends GenericViewBuilder<B, V>, V extends View> {
    private static int ID_GENERATOR = 0;

    private enum ParentType {
        VIEWGROUP, LINEAR, FRAME, RELATIVE
    }

    private enum ClickableType {
        NONE, NO_CLICK, CLICK
    }

    private enum FocusType {
        NONE, NO_FOCUS, FOCUS
    }

    // Too bad Java doesn't have union types
    private static final class Background {
        @DrawableRes int resource = -1;
        Drawable drawable;
        @ColorRes int color = -1;
        boolean isSet = false;
        void set(@DrawableRes int resource, Drawable drawable, @ColorRes int color) {
            this.resource = resource;
            this.drawable = drawable;
            this.color = color;
            isSet = true;
        }
        protected Background clone() {
            Background b = new Background();
            b.set(this.resource, this.drawable, this.color);
            return b;
        }
        void setResource(@DrawableRes int resource) {
            set(resource, null, -1);
        }
        void setDrawable(Drawable drawable) {
            set(-1, drawable, -1);
        }
        void setColor(@ColorRes int color) {
            set(-1, null, color);
        }
    }

    // Special class to avoid some copies in load() during normal use
    private class RelativeLayoutParams {
        int above, alignBaseline, alignBottom, alignEnd, alignStart, alignTop, below, endOf, startOf;
        boolean parentBottom, parentEnd, parentStart, parentTop, centerHorizontal, centerVertical, center;

        RelativeLayoutParams() {}

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

        void addRules(RelativeLayout.LayoutParams params) {
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
        }
    }
    private static final int ID_GENERATE = -2;
    private static final int ID_NONE = -1;
    private int idType = ID_NONE;
    private Style style;
    private int visibility = View.VISIBLE;
    private float weight = 1f;
    private int layoutWidth = MATCH_PARENT, layoutHeight = MATCH_PARENT;
    private int layoutGravity = CENTER;
    private Background background;
    private Background backgroundFocused;
    private Background backgroundPressed;
    private int paddingLeft, paddingTop, paddingRight, paddingBottom;
    private int marginLeft, marginTop, marginRight, marginBottom;
    private ParentType parentType = ParentType.VIEWGROUP;
    private ClickableType clickable = ClickableType.NONE;
    private FocusType focusable = FocusType.NONE;
    private OnClickListener clickListener;
    private OnFocusChangeListener focusChangeListener;
    private RelativeLayoutParams relativeLayoutParams;

    public GenericViewBuilder() {}

    public GenericViewBuilder(GenericViewBuilder from) {
        load(from);
    }

    public B load(GenericViewBuilder from) {
        this.idType = from.idType;
        this.weight = from.weight;
        this.style = from.style;
        this.clickable = from.clickable;
        this.focusable = from.focusable;
        this.visibility = from.visibility;
        this.layoutWidth = from.layoutWidth;
        this.layoutHeight = from.layoutHeight;
        this.layoutGravity = from.layoutGravity;
        if(from.background != null) {
            this.background = from.background.clone();
        }
        if(from.backgroundFocused != null) {
            this.backgroundFocused = from.backgroundFocused.clone();
        }
        if(from.backgroundPressed != null) {
            this.backgroundPressed = from.backgroundPressed.clone();
        }
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
        this.focusChangeListener = from.focusChangeListener;
        if(from.relativeLayoutParams != null) {
            this.relativeLayoutParams = new RelativeLayoutParams(from.relativeLayoutParams);
        }
        return (B)this;
    }

    public V build(Context c, V v) {
        if(visibility != View.VISIBLE) {
            v.setVisibility(visibility);
        }
        v.setLayoutParams(makeParams());
        if((backgroundFocused != null && backgroundFocused.isSet)
            || (backgroundPressed != null && backgroundPressed.isSet)) {

            StateListDrawable states = new StateListDrawable();
            addStateDrawable(c, states, android.R.attr.state_pressed, backgroundPressed);
            addStateDrawable(c, states, android.R.attr.state_pressed, backgroundFocused);
            addStateDrawable(c, states, 0, background);
            v.setBackground(states);
        } else if(background != null && background.isSet) {
            if(background.color != -1) {
                v.setBackgroundColor(ContextCompat.getColor(c, background.color));
            } else if(background.drawable != null) {
                v.setBackground(background.drawable);
            } else if(background.resource != -1) {
                v.setBackgroundResource(background.resource);
            }
        }
        v.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        if(clickListener != null) {
            v.setOnClickListener(clickListener);
        }
        if(focusChangeListener != null) {
            v.setOnFocusChangeListener(focusChangeListener);
        }
        if(clickable != ClickableType.NONE) {
            v.setClickable(clickable == ClickableType.CLICK);
        }
        if(focusable != FocusType.NONE) {
            v.setFocusable(focusable == FocusType.FOCUS);
            v.setFocusableInTouchMode(focusable == FocusType.FOCUS);
        }
        switch(idType) {
            case ID_NONE:
                // No view ID
                break;
            case ID_GENERATE:
                // Get a view ID from the SDK with pre-17 fallback
                if(Build.VERSION.SDK_INT < 17) {
                    v.setId(nextId());
                } else {
                    v.setId(View.generateViewId());
                }
                break;
            default:
                v.setId(idType);
                break;
        }
        return v;
    }

    public abstract V build(Context c);

    public V build(ViewGroup vg) {
        V me = build(vg.getContext());
        vg.addView(me);
        return me;
    }

    private static int nextId() {
        // Make sure to only return positive numbers, in case you need 2 billion View ids
        ID_GENERATOR = (ID_GENERATOR + 1) % (Integer.MAX_VALUE - 1);
        return ID_GENERATOR;
    }

    private LayoutParams makeParams() {
        MarginLayoutParams params;
        switch(parentType) {
            case LINEAR:
                LinearLayout.LayoutParams llp;
                llp = new LinearLayout.LayoutParams(layoutWidth, layoutHeight, weight);
                llp.gravity = layoutGravity;
                params = llp;
                break;
            case RELATIVE:
                params = new RelativeLayout.LayoutParams(layoutWidth, layoutHeight);
                relativeParams().addRules((RelativeLayout.LayoutParams)params);
                break;
            case FRAME:
                params = new FrameLayout.LayoutParams(layoutWidth, layoutHeight, layoutGravity);
                break;
            default:
                return new LayoutParams(layoutWidth, layoutHeight);
        }
        params.setMargins(marginLeft, marginTop, marginRight, marginBottom);
        return params;
    }

    private void addStateDrawable(Context c, StateListDrawable states, int state, Background background) {
        if(background == null) {
            return;
        }
        int[] stateSet;
        if(state == 0) {
            stateSet = new int[] {};
        } else {
            stateSet = new int[] { state };
        }
        Drawable drawable = background.drawable;
        if(background.color != -1) {
            drawable = new ColorDrawable(ContextCompat.getColor(c, background.color));
        } else if(background.resource != -1) {
            drawable = ContextCompat.getDrawable(c, background.resource);
        }
        states.addState(stateSet, drawable);
    }

    public B style(Style style) {
        this.style = style;
        style.apply(this);
        return (B)this;
    }

    public B backgroundColor(@ColorRes int res) {
        if(this.background == null) {
            this.background = new Background();
        }
        this.background.setColor(res);
        return (B)this;
    }

    public B background(@DrawableRes int res) {
        if(this.background == null) {
            this.background = new Background();
        }
        this.background.setResource(res);
        return (B)this;
    }

    public B background(Drawable background) {
        if(this.background == null) {
            this.background = new Background();
        }
        this.background.setDrawable(background);
        return (B)this;
    }

    public B backgroundFocusedColor(@ColorRes int res) {
        if(this.backgroundFocused == null) {
            this.backgroundFocused = new Background();
        }
        this.backgroundFocused.setColor(res);
        return (B)this;
    }

    public B backgroundFocused(@DrawableRes int res) {
        if(this.backgroundFocused == null) {
            this.backgroundFocused = new Background();
        }
        this.backgroundFocused.setResource(res);
        return (B)this;
    }

    public B backgroundFocused(Drawable background) {
        if(this.backgroundFocused == null) {
            this.backgroundFocused = new Background();
        }
        this.backgroundFocused.setDrawable(background);
        return (B)this;
    }

    public B backgroundPressedColor(@ColorRes int res) {
        if(this.backgroundPressed == null) {
            this.backgroundPressed = new Background();
        }
        this.backgroundPressed.setColor(res);
        return (B)this;
    }

    public B backgroundPressed(@DrawableRes int res) {
        if(this.backgroundPressed == null) {
            this.backgroundPressed = new Background();
        }
        this.backgroundPressed.setResource(res);
        return (B)this;
    }

    public B backgroundPressed(Drawable background) {
        if(this.backgroundPressed == null) {
            this.backgroundPressed = new Background();
        }
        this.backgroundPressed.setDrawable(background);
        return (B)this;
    }

    public B width(int width) {
        this.layoutWidth = width;
        return (B)this;
    }

    public B widthDp(int widthDp) {
        this.layoutWidth = Util.dpToPx(widthDp);
        return (B)this;
    }

    public B height(int height) {
        this.layoutHeight = height;
        return (B)this;
    }

    public B heightDp(int heightDp) {
        this.layoutHeight = Util.dpToPx(heightDp);
        return (B)this;
    }

    public B paddingDp(int left, int top, int right, int bottom) {
        return paddingPx(Util.dpToPx(left), Util.dpToPx(top),
                Util.dpToPx(right), Util.dpToPx(bottom));
    }

    public B paddingLeft(int left) {
        return paddingPx(Util.dpToPx(left), paddingTop, paddingRight, paddingBottom);
    }

    public B paddingTop(int top) {
        return paddingPx(paddingLeft, Util.dpToPx(top), paddingRight, paddingBottom);
    }

    public B paddingRight(int right) {
        return paddingPx(paddingLeft, paddingTop, Util.dpToPx(right), paddingBottom);
    }

    public B paddingBottom(int bottom) {
        return paddingPx(paddingLeft, paddingTop, paddingRight, Util.dpToPx(bottom));
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

    public B marginLeft(int left) {
        return marginPx(Util.dpToPx(left), marginTop, marginRight, marginBottom);
    }

    public B marginTop(int top) {
        return marginPx(marginLeft, Util.dpToPx(top), marginRight, marginBottom);
    }

    public B marginRight(int right) {
        return marginPx(marginLeft, marginTop, Util.dpToPx(right), marginBottom);
    }

    public B marginBottom(int bottom) {
        return marginPx(marginLeft, marginTop, marginRight, Util.dpToPx(bottom));
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

    public B visibility(int visibility) {
        this.visibility = visibility;
        return (B)this;
    }

    public B clickable(boolean clickable) {
        this.clickable = clickable ? ClickableType.CLICK : ClickableType.NO_CLICK;
        return (B)this;
    }

    public B focusable(boolean focusable) {
        this.focusable = focusable ? FocusType.FOCUS : FocusType.NO_FOCUS;
        return (B)this;
    }

    public B click(OnClickListener listener) {
        this.clickListener = listener;
        return (B)this;
    }

    public B focus(OnFocusChangeListener listener) {
        this.focusChangeListener = listener;
        return (B)this;
    }

    public B id() {
        this.idType = ID_GENERATE;
        return (B)this;
    }

    public B id(int id) {
        this.idType = id;
        return (B)this;
    }

    private RelativeLayoutParams relativeParams() {
        if(relativeLayoutParams == null) {
            relativeLayoutParams = new RelativeLayoutParams();
            this.id();
        }
        return relativeLayoutParams;
    }

    public B above(@IdRes int id) {
        relativeParams().above = id;
        return (B)this;
    }

    public B above(View v) {
        return above(v.getId());
    }

    public B alignBaseline(@IdRes int id){
        relativeParams().alignBaseline = id;
        return (B)this;
    }

    public B alignBaseline(View v) {
        return alignBaseline(v.getId());
    }

    public B alignBottom(@IdRes int id){
        relativeParams().alignBottom = id;
        return (B)this;
    }

    public B alignBottom(View v) {
        return alignBottom(v.getId());
    }

    public B alignEnd(@IdRes int id){
        relativeParams().alignEnd = id;
        return (B)this;
    }

    public B alignStart(@IdRes int id){
        relativeParams().alignStart = id;
        return (B)this;
    }

    public B alignStart(View v) {
        return alignStart(v.getId());
    }

    public B alignTop(@IdRes int id){
        relativeParams().alignTop = id;
        return (B)this;
    }

    public B alignTop(View v) {
        return alignTop(v.getId());
    }

    public B below(@IdRes int id){
        relativeParams().below = id;
        return (B)this;
    }

    public B below(View v) {
        return below(v.getId());
    }

    public B endOf(@IdRes int id){
        relativeParams().endOf = id;
        return (B)this;
    }

    public B endOf(View v) {
        return endOf(v.getId());
    }

    public B startOf(@IdRes int id){
        relativeParams().startOf = id;
        return (B)this;
    }

    public B startOf(View v) {
        return startOf(v.getId());
    }

    public B parentBottom() {
        relativeParams().parentBottom = true;
        return (B)this;
    }

    public B parentEnd() {
        relativeParams().parentEnd = true;
        return (B)this;
    }

    public B parentStart() {
        relativeParams().parentStart = true;
        return (B)this;
    }

    public B parentTop() {
        relativeParams().parentTop = true;
        return (B)this;
    }

    public B centerHorizontal() {
        relativeParams().centerHorizontal = true;
        return (B)this;
    }

    public B centerVertical() {
        relativeParams().centerVertical = true;
        return (B)this;
    }

    public B center() {
        relativeParams().center = true;
        return (B)this;
    }
}