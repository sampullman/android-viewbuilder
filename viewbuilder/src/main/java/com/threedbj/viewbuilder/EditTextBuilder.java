package com.threedbj.viewbuilder;

import android.content.Context;
import android.widget.EditText;

import com.threedbj.viewbuilder.generic.GenericEditTextBuilder;
import com.threedbj.viewbuilder.style.Style;

public class EditTextBuilder extends GenericEditTextBuilder<EditTextBuilder, EditText> {

    public EditTextBuilder(Style style) {
        style(style);
    }

    public EditText build(Context c) {
        return build(c, new EditText(c));
    }
}