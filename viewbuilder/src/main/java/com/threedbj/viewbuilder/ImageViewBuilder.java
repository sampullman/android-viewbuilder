package com.threedbj.viewbuilder;

import android.content.Context;
import android.widget.ImageView;

import com.threedbj.viewbuilder.generic.GenericImageViewBuilder;

public class ImageViewBuilder extends GenericImageViewBuilder<ImageViewBuilder, ImageView> {

    public ImageView build(Context c) {
        return build(c, new ImageView(c));
    }
}