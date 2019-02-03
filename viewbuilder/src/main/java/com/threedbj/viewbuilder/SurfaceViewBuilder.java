package com.threedbj.viewbuilder;

import android.content.Context;
import android.view.SurfaceView;

import com.threedbj.viewbuilder.generic.GenericSurfaceViewBuilder;

public class SurfaceViewBuilder extends GenericSurfaceViewBuilder<SurfaceViewBuilder, SurfaceView> {

    public SurfaceView build(Context c) {
        return  build(c, new SurfaceView(c));
    }
}
