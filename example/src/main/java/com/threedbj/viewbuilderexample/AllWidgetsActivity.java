package com.threedbj.viewbuilderexample;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.threedbj.viewbuilder.ButtonBuilder;
import com.threedbj.viewbuilder.EditTextBuilder;
import com.threedbj.viewbuilder.FrameLayoutBuilder;
import com.threedbj.viewbuilder.ImageViewBuilder;
import com.threedbj.viewbuilder.LinearLayoutBuilder;
import com.threedbj.viewbuilder.TextViewBuilder;
import com.threedbj.viewbuilder.ViewBuilder;

import threedbj.com.viewbuilderexample.R;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class AllWidgetsActivity extends AppCompatActivity {
    TextView helloWorld;
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Vertical linear layout
        LinearLayout root = new LinearLayoutBuilder().vertical().build(this);

        // Base parameters for row
        ViewBuilder row = new ViewBuilder().inLinear().height(0);

        // Horizontal linear layout
        LinearLayout row1 = new LinearLayoutBuilder().load(row).build(root);

        // Base parameters for an item in a horizontal linear layout
        // It's not that useful here, this is just an example
        ViewBuilder rowItem = new ViewBuilder().inLinear().width(0);

        // TextView
        new TextViewBuilder().load(rowItem)
                .text("Howdy!").textSize(22f)
                .build(row1);

        // Button
        new ButtonBuilder().load(rowItem)
                .text("Useful Button")
                .listener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(view.getContext(), "Look at me!", Toast.LENGTH_SHORT).show();
                    }
                })
                .build(row1);

        // EditText
        new EditTextBuilder().load(rowItem)
                .height(WRAP_CONTENT)
                .paddingDp(6, 16, 16, 6)
                .hint("HINT!")
                .build(row1);

        // FrameLayout with TextView
        helloWorld = new TextViewBuilder()
                .text("Hello").textSize(24f)
                .wrap().inFrame().layoutGravity(Gravity.CENTER)
                .build(new FrameLayoutBuilder()
                        .load(row)
                        .build(root));

        LinearLayout row2 = new LinearLayoutBuilder().load(row).build(root);
        rowItem.build(row2); // Empty View

        // ImageView
        new ImageViewBuilder().load(rowItem)
                .image(R.drawable.fox)
                .scale(ImageView.ScaleType.CENTER_CROP)
                .build(row2);

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
}
