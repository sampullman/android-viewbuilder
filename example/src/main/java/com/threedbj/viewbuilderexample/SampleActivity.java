package com.threedbj.viewbuilderexample;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.view.View.OnClickListener;
import static android.view.Gravity.CENTER;

import com.threedbj.viewbuilder.ButtonBuilder;
import com.threedbj.viewbuilder.FrameLayoutBuilder;
import com.threedbj.viewbuilder.LinearLayoutBuilder;
import com.threedbj.viewbuilder.ViewBuilder;
import com.threedbj.viewbuilder.style.Style;

import androidx.appcompat.app.AppCompatActivity;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayoutBuilder().style(Style.MATCH).vertical().build(this);

        FrameLayoutBuilder frameBuilder = new FrameLayoutBuilder()
            .inLinear().height(0);
        ButtonBuilder buttonBuilder = new ButtonBuilder()
            .style(Style.WRAP)
            .text("All Widgets")
            .gravity(CENTER)
            .inFrame().layoutGravity(CENTER)
            .paddingDp(14, 6, 14, 6)
            .click(allWidgetsListener);

        FrameLayout vg1 = frameBuilder.build(root);
        buttonBuilder.build(vg1);

        setContentView(root);
    }

    OnClickListener allWidgetsListener = v ->
        startActivity(new Intent(SampleActivity.this, AllWidgetsActivity.class));
}
