package com.threedbj.viewbuilder;

import android.content.Context;
import android.widget.RadioGroup;

import com.threedbj.viewbuilder.generic.GenericRadioGroupBuilder;
import com.threedbj.viewbuilder.style.Style;

public class RadioGroupBuilder extends GenericRadioGroupBuilder<RadioGroupBuilder, RadioGroup> {

    public RadioGroupBuilder() {}

    public RadioGroupBuilder(Style style) {
        style(style);
    }

    public RadioGroup build(Context c) {
        return build(c, new RadioGroup(c));
    }
}
