package com.threedbj.viewbuilder;

import android.content.Context;
import android.widget.RadioGroup;

import com.threedbj.viewbuilder.generic.GenericRadioGroupBuilder;

public class RadioGroupBuilder extends GenericRadioGroupBuilder<RadioGroupBuilder, RadioGroup> {

    public RadioGroup build(Context c) {
        return build(c, new RadioGroup(c));
    }
}
