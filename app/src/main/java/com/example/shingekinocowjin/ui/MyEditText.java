package com.example.shingekinocowjin.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatEditText;

public class MyEditText extends androidx.appcompat.widget.AppCompatEditText {
    private Context contextm;
    private int left;
    private int top;
    private int right;
    private int bottom;

    public MyEditText(Context context, Canvas canvas, int left, int top, int right, int bottom) {
        super(context);
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        contextm = context;
    }

    public void drawText(Canvas canvas) {
        AppCompatEditText text1 = new AppCompatEditText(contextm);
        text1.setText("CHANGE NAME");
        text1.setTextColor(Color.BLACK);
        text1.setTextSize(3000);
        LinearLayout layout = new LinearLayout(contextm);
        TextView name = new TextView(contextm);
        name.setText("CHANGE NAME");
        name.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        name.setTextColor(Color.BLACK);
        name.setVisibility(View.VISIBLE);
        name.setTextSize(3000);
        EditText text = new EditText(contextm);
        text.setText("CHANGE NAME");
        text.setTextColor(Color.BLACK);
        text.setTextSize(3000);
        text.setBackgroundColor(Color.WHITE);
        layout.setBackgroundColor(Color.MAGENTA);
        text.setFocusable(true);
        text.setClickable(true);
        layout.setX(500);
        layout.setY(500);
        layout.setLeftTopRightBottom(200, 50, 200, 200);
        layout.setVisibility(View.VISIBLE);
        layout.addView(text);
        text.setLeftTopRightBottom(100, 100, 100, 100);
        layout.addView(name);
        layout.addView(text1);
        layout.draw(canvas);
    }
}
