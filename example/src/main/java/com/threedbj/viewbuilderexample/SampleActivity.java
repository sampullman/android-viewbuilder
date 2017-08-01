package com.threedbj.viewbuilderexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import static android.view.Gravity.CENTER;

import com.threedbj.viewbuilder.ButtonBuilder;
import com.threedbj.viewbuilder.FrameLayoutBuilder;
import com.threedbj.viewbuilder.LinearLayoutBuilder;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayoutBuilder().vertical().build(this);

        FrameLayoutBuilder frameBuilder = new FrameLayoutBuilder().height(0);
        ButtonBuilder buttonBuilder = new ButtonBuilder()
                .text("Simple Sample")
                .gravity(CENTER).wrap()
                .inFrame().layoutGravity(CENTER)
                .paddingDp(10, 6, 10, 6);

        FrameLayout vg1 = frameBuilder.build(this);
        Button simple = buttonBuilder.build(this);
        vg1.addView(simple);

        FrameLayout vg2 = frameBuilder.build(this);
        Button example2 = buttonBuilder.text("Example 2").build(this);
        vg2.addView(example2);

        FrameLayout vg3 = frameBuilder.build(this);
        Button example3 = buttonBuilder.text("Example 3").build(this);
        vg3.addView(example3);

        root.addView(vg1);
        root.addView(vg2);
        root.addView(vg3);
        setContentView(root);
    }
}
