package com.threedbj.viewbuilder;

import android.content.Context;
import android.widget.EditText;

import com.threedbj.viewbuilder.generic.GenericEditTextBuilder;

public class EditTextBuilder extends GenericEditTextBuilder<EditTextBuilder, EditText> {

    public EditText build(Context c) {
        return build(c, new EditText(c));
    }
}