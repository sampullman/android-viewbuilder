package com.threedbj.viewbuilderexample;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import static android.view.Gravity.CENTER;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import com.threedbj.viewbuilder.ButtonBuilder;
import com.threedbj.viewbuilder.FrameLayoutBuilder;
import com.threedbj.viewbuilder.LinearLayoutBuilder;
import com.threedbj.viewbuilder.RelativeLayoutBuilder;
import com.threedbj.viewbuilder.TextViewBuilder;
import com.threedbj.viewbuilder.ViewBuilder;
import com.threedbj.viewbuilder.style.Style;
import com.threedbj.viewbuilder.util.Util;

import androidx.appcompat.app.AppCompatActivity;

import threedbj.com.viewbuilderexample.R;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayoutBuilder().style(Style.MATCH).vertical().build(this);

        RelativeLayout topBar = new RelativeLayoutBuilder()
            .heightDp(120)
            .width(MATCH_PARENT)
            .inLinear()
            .build(root);
        new TextViewBuilder()
            .inRelative()
            .text("Demo Activities")
            .style(Style.WRAP)
            .center()
            .textSizeSp(18)
            .build(topBar);
        new ViewBuilder()
            .inRelative()
            .height(1)
            .parentBottom()
            .width(MATCH_PARENT)
            .backgroundColor(R.color.black)
            .build(topBar);

        FrameLayoutBuilder frameBuilder = new FrameLayoutBuilder()
            .inLinear().height(MATCH_PARENT);
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
