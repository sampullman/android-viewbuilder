package com.threedbj.viewbuilder;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.ColorRes;

import com.threedbj.viewbuilder.generic.GenericViewBuilder;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class ViewBuilder extends GenericViewBuilder<ViewBuilder, View> {

    public static void HORIZONTAL_DIVIDER(LinearLayout parent, @ColorRes int color) {
        new ViewBuilder()
            .inLinear()
            .width(MATCH_PARENT)
            .height(1)
            .backgroundColor(color)
            .build(parent);
    }

    public static void VERTICAL_DIVIDER(LinearLayout parent, @ColorRes int color) {
        new ViewBuilder()
            .inLinear()
            .width(1)
            .weight(0)
            .height(MATCH_PARENT)
            .backgroundColor(color)
            .build(parent);
    }

    public View build(Context c) {
        return build(c, new View(c));
    }
}
