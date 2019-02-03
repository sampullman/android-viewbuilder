package com.threedbj.viewbuilder.generic;

import android.content.Context;
import android.view.SurfaceView;

public abstract class GenericSurfaceViewBuilder<B extends GenericSurfaceViewBuilder<B, V>, V extends SurfaceView> extends GenericViewBuilder<B, V> {

    public V build(Context c, V v) {
        super.build(c, v);
        return v;
    }
}
