package com.threedbj.viewbuilder;

import android.content.Context;
import android.widget.RadioButton;

import com.threedbj.viewbuilder.generic.GenericRadioButtonBuilder;

public class RadioButtonBuilder extends GenericRadioButtonBuilder<RadioButtonBuilder, RadioButton> {

    public RadioButton build(Context c) {
        return build(c, new RadioButton(c));
    }
}
