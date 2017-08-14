package com.threedbj.viewbuilder;

import android.content.Context;
import android.widget.ProgressBar;

import com.threedbj.viewbuilder.generic.GenericProgressBarBuilder;

public class ProgressBarBuilder extends GenericProgressBarBuilder<ProgressBarBuilder, ProgressBar> {

    public ProgressBar build(Context c) {
        ProgressBar bar;
        int style = getStyle();
        if(style == -1) {
            bar = new ProgressBar(c);
        } else {
            bar = new ProgressBar(c, null, style);
        }
        return super.build(c, bar);
    }
}
