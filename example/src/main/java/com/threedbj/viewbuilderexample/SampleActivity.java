package com.threedbj.viewbuilderexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.view.View.OnClickListener;
import static android.view.Gravity.CENTER;

import com.threedbj.viewbuilder.ButtonBuilder;
import com.threedbj.viewbuilder.FrameLayoutBuilder;
import com.threedbj.viewbuilder.LinearLayoutBuilder;
import com.threedbj.viewbuilder.ViewBuilder;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayoutBuilder().vertical().build(this);
        ViewBuilder emptyBuilder = new ViewBuilder().inLinear().height(0);

        FrameLayoutBuilder frameBuilder = new FrameLayoutBuilder()
                .inLinear().height(0);
        ButtonBuilder buttonBuilder = new ButtonBuilder()
                .wrap()
                .text("All Widgets")
                .gravity(CENTER)
                .inFrame().layoutGravity(CENTER)
                .paddingDp(14, 6, 14, 6)
                .listener(allWidgetsListener);

        FrameLayout vg1 = frameBuilder.build(root);
        buttonBuilder.build(vg1);

        FrameLayout vg2 = frameBuilder.build(root);
        buttonBuilder.text("Programmatic")
                .listener(programmaticListener)
                .build(vg2);

        emptyBuilder.build(root);
        emptyBuilder.build(root);
        setContentView(root);
    }

    OnClickListener allWidgetsListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(SampleActivity.this, AllWidgetsActivity.class));
        }
    };

    OnClickListener programmaticListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(SampleActivity.this, ProgrammaticActivity.class));
        }
    };
}
