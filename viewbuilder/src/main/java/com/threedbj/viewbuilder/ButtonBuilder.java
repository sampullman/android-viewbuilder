package com.threedbj.viewbuilder;

import android.content.Context;
import android.widget.Button;

import com.threedbj.viewbuilder.generic.GenericButtonBuilder;

public class ButtonBuilder extends GenericButtonBuilder<ButtonBuilder, Button> {

    public Button build(Context c) {
        return build(c, new Button(c));
    }
}