package com.threedbj.viewbuilder.style;

import android.util.Log;

import com.threedbj.viewbuilder.generic.GenericViewBuilder;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public abstract class Style {

    public static Style MATCH = new Match();
    public static Style WRAP = new Wrap();
    public static Style TALL = new Tall();
    public static Style WIDE = new Wide();

    public static class Tall extends Style {
        public void apply(GenericViewBuilder builder) {
            builder.width(0);
            builder.height(MATCH_PARENT);
            builder.weight(1);
        }
    }

    public static class Wide extends Style {
        public void apply(GenericViewBuilder builder) {
            builder.width(MATCH_PARENT);
            builder.height(0);
            builder.weight(1);
        }
    }

    public static class Match extends Style {
        public void apply(GenericViewBuilder builder) {
            builder.width(MATCH_PARENT);
            builder.height(MATCH_PARENT);
        }
    }

    public static class Wrap extends Style {
        public void apply(GenericViewBuilder builder) {
            builder.width(WRAP_CONTENT);
            builder.height(WRAP_CONTENT);
        }
    }

    public abstract void apply(GenericViewBuilder builder);

}
