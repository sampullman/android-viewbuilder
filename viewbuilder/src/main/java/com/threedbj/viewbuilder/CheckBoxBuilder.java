package com.threedbj.viewbuilder;

import android.content.Context;
import android.widget.CheckBox;

import com.threedbj.viewbuilder.generic.GenericCheckBoxBuilder;

public class CheckBoxBuilder extends GenericCheckBoxBuilder<CheckBoxBuilder, CheckBox> {

    public CheckBox build(Context c) {
        return super.build(c, new CheckBox(c));
    }
}
