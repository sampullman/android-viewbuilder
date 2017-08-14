package com.threedbj.viewbuilderexample;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.threedbj.viewbuilder.ButtonBuilder;
import com.threedbj.viewbuilder.EditTextBuilder;
import com.threedbj.viewbuilder.FrameLayoutBuilder;
import com.threedbj.viewbuilder.ImageViewBuilder;
import com.threedbj.viewbuilder.LinearLayoutBuilder;
import com.threedbj.viewbuilder.ListViewBuilder;
import com.threedbj.viewbuilder.ProgressBarBuilder;
import com.threedbj.viewbuilder.RelativeLayoutBuilder;
import com.threedbj.viewbuilder.ScrollViewBuilder;
import com.threedbj.viewbuilder.TextViewBuilder;
import com.threedbj.viewbuilder.ViewBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import threedbj.com.viewbuilderexample.R;

import static android.view.Gravity.CENTER;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class AllWidgetsActivity extends AppCompatActivity {
    private final int GH_FONT = 1;
    TextView helloWorld;
    ProgressBar usefulBar;
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextViewBuilder.addFont(this, "font/GloriaHallelujah.ttf", GH_FONT);

        // Base parameters for an item in a horizontal linear layout
        // It's not that useful here, this is just an example
        ViewBuilder rowItem = new ViewBuilder().inLinear().width(0);
        ViewBuilder colItem = new ViewBuilder().inLinear().height(0);

        // Vertical linear layout
        LinearLayout root = new LinearLayoutBuilder().vertical().build(this);

        // Base parameters for row
        ViewBuilder row = new ViewBuilder().load(colItem);

        // Horizontal linear layout
        LinearLayout row1 = new LinearLayoutBuilder().load(row).build(root);

        LinearLayout row1Column1 = new LinearLayoutBuilder().load(rowItem).vertical().build(row1);
        // TextView
        new TextViewBuilder().load(colItem)
                .text("Howdy!").textSize(22f)
                .marginDp(10, 20, 0, 0)
                .color(R.color.blueish)
                .build(row1Column1);
        new TextViewBuilder().load(colItem)
                .text("Pad left")
                .color(R.color.redish)
                .paddingLeft(16)
                .build(row1Column1);

        // Button
        new ButtonBuilder().load(rowItem)
                .text("Useful Button")
                .listener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toast("Look at me!");
                        usefulBar.setProgress((usefulBar.getProgress()+1) % (usefulBar.getMax() + 1));
                    }
                })
                .build(row1);

        LinearLayout row1Column3 = new LinearLayoutBuilder().load(rowItem).vertical().build(row1);
        // EditText
        new EditTextBuilder().load(colItem)
                .height(WRAP_CONTENT)
                .paddingDp(6, 16, 16, 0)
                .hint("HINT!")
                .noFirstFocus()
                .build(row1Column3);
        // TextView with custom font
        new TextViewBuilder().load(colItem).weight(3)
                .paddingLeft(6).paddingRight(6)
                .marginTop(10)
                .font(GH_FONT).textSize(14)
                .gravity(CENTER)
                .text("A custom font. It is good.")
                .build(row1Column3);

        LinearLayout row2 = new LinearLayoutBuilder().load(row).build(root);
        // ListView
        List<String> list = Arrays.asList("Item1", "Item2", "Item3", "Item4", "Item5", "Item6", "Item7", "Item7");

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>(list));
        new ListViewBuilder()
                .adapter(itemsAdapter)
                .backgroundColor(R.color.light_gray)
                .build(new FrameLayoutBuilder().load(rowItem)
                        .build(row2));

        // FrameLayout with TextView
        helloWorld = new TextViewBuilder()
                .text("Hello").textSize(24f)
                .bold().italic()
                .wrap().inFrame().layoutGravity(CENTER)
                .build(new FrameLayoutBuilder().load(rowItem)
                        .build(row2));

        LinearLayout row3 = new LinearLayoutBuilder().load(row).build(root);

        // A whole mess of RelativeLayout stuff
        RelativeLayout row3Item1 = new RelativeLayoutBuilder().load(rowItem).build(row3);

        View row3Center = new ButtonBuilder().wrap().inRelative().center().text("CENTER").build(row3Item1);

        new ButtonBuilder().wrap().inRelative().parentTop().parentStart().text("TOP LEFT").build(row3Item1);
        new ButtonBuilder().wrap().inRelative().parentTop().parentEnd().text("TOP RIGHT").build(row3Item1);

        View row3Below = new ButtonBuilder().wrap().inRelative().below(row3Center).centerHorizontal().text("B").build(row3Item1);

        new ButtonBuilder().wrap().inRelative().below(row3Center).startOf(row3Below).text("L").build(row3Item1);
        new ButtonBuilder().wrap().inRelative().below(row3Center).endOf(row3Below).text("R").build(row3Item1);

        // ImageView, ProgressBar in ScrollView
        LinearLayout scroll = new LinearLayoutBuilder()
                .height(WRAP_CONTENT)
                .vertical()
                .build(new ScrollViewBuilder()
                        .load(rowItem)
                        .overScrollMode(View.OVER_SCROLL_NEVER)
                        .build(row3));
        new ProgressBarBuilder().height(WRAP_CONTENT).inLinear().build(scroll);
        usefulBar = new ProgressBarBuilder().height(WRAP_CONTENT).determinate().max(10).progress(5).inLinear().build(scroll);


        ImageViewBuilder fox = new ImageViewBuilder().height(WRAP_CONTENT).inLinear()
                .image(R.drawable.fox)
                .scale(ScaleType.CENTER_INSIDE);
        fox.build(scroll);
        fox.scale(ScaleType.FIT_CENTER).build(scroll);

        setContentView(root);

        handler.postDelayed(helloWorldRunnable, 1500);
    }

    Runnable helloWorldRunnable = new Runnable() {
        @Override
        public void run() {
            String text = helloWorld.getText().equals("Hello") ? "world!" : "Hello";
            helloWorld.setText(text);
            handler.postDelayed(helloWorldRunnable, 1500);
        }
    };

    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
