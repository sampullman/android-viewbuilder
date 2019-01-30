package com.threedbj.viewbuilder;

import android.content.Context;
import android.widget.Button;

import com.threedbj.viewbuilder.generic.GenericButtonBuilder;
import com.threedbj.viewbuilder.style.Style;

public class ButtonBuilder extends GenericButtonBuilder<ButtonBuilder, Button> {

    public ButtonBuilder() {}

    public ButtonBuilder(Style style) {
        style(style);
    }

    public Button build(Context c) {
        return build(c, new Button(c));
    }
}