package com.threedbj.viewbuilder;

import android.content.Context;
import android.widget.RadioButton;

import com.threedbj.viewbuilder.generic.GenericRadioButtonBuilder;
import com.threedbj.viewbuilder.style.Style;

public class RadioButtonBuilder extends GenericRadioButtonBuilder<RadioButtonBuilder, RadioButton> {

    public RadioButtonBuilder(Style style) {
        style(style);
    }

    public RadioButton build(Context c) {
        return build(c, new RadioButton(c));
    }
}
