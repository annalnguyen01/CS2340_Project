package com.example.shingekinocowjin.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class MyButton {
    private int left;
    private int top;
    private int right;
    private int bottom;
    private String text;
    private Rect bounds;
    private int bodyColor = Color.parseColor("#FDA4BA");
    private int textSize = 70;
    private boolean pressed;
    private int cowID;
    private String[] keys = new String[26];

    public MyButton(String text, int left, int top, int right, int bottom) {
        this.text = text;
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        cowID = 0;
        initBounds();
    }

    private void initBounds() {
        this.bounds = new Rect(left, top, right, bottom);
    }

    public void drawButton(Canvas canvas) {
        // Body
        drawBody(canvas);
        // Border
        drawBorder(canvas);
        // Text
        drawButtonText(canvas);

    }

    private void drawButtonText(Canvas canvas) {
        Paint textPaint = new Paint();
        int textColor = Color.BLACK;
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        Rect textBounds = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), textBounds);
        int height = textBounds.height();
        int width = textBounds.width();
        canvas.drawText(text, ((right + left) - width) / 2,
                ((bottom + top) + height) / 2, textPaint);
    }

    private void drawBorder(Canvas canvas) {
        Paint borderPaint = new Paint();
        borderPaint.setStrokeWidth(9.0f);
        borderPaint.setColor(Color.BLACK);
        borderPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(left, top, right, bottom, borderPaint);
    }

    private void drawBody(Canvas canvas) {
        Paint bodyPaint = new Paint();
        if (pressed) {
            bodyPaint.setColor(bodyColor);
            canvas.drawRect(left, top, right, bottom, bodyPaint);
        } else {
            if (text == "QUIT") {
                bodyPaint.setColor(Color.LTGRAY);
                canvas.drawRect(left, top, right, bottom, bodyPaint);
            } else {
                bodyPaint.setColor(Color.parseColor("#FDA4BA"));
                canvas.drawRect(left, top, right, bottom, bodyPaint);
            }
        }
    }

    public Rect getBounds() {
        return bounds;
    }

    public void setBodyColor(int color) {
        bodyColor = color;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public void setTextSize(int newTextSize) {
        textSize = newTextSize;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCowID(int cowID) {
        this.cowID = cowID;
    }

    public int getCowID() {
        return cowID;
    }

    public String getText() {
        return text;
    }

}
