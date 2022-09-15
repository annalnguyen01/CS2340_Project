package com.example.shingekinocowjin.ui;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.shingekinocowjin.cows.Cow;
import com.example.shingekinocowjin.scenes.PlayScene;
import com.example.shingekinocowjin.scenes.SceneMethods;

public class Shop implements SceneMethods {
    private final PlayScene playScene;
    private Rect display;
    private MyButton[] cowTower;
    private String[] buttonNames = {"Basic Cow", "Mage Cow", "Cannon Cow", "CC Cow"};
    private Cow selectedCow;
    private Bitmap basicCow;
    private Bitmap cannonCow;
    private Bitmap mageCow;
    private Bitmap cCCow;
    private MyButton clickedButton;
    private Bitmap[] bit = new Bitmap[4];

    public Shop(PlayScene playScene, Bitmap[] bit) {
        this.playScene = playScene;
        this.basicCow = bit[0];
        this.cannonCow = bit[1];
        this.mageCow = bit[2];
        this.cCCow = bit[3];
        initButtons();
    }

    public void drawShop(Canvas canvas) {
        Paint barBody = new Paint();
        barBody.setColor(Color.parseColor("#fae2e3"));
        Paint borderPaint = new Paint();
        borderPaint.setStrokeWidth(20.5f);
        borderPaint.setColor(Color.BLACK);
        borderPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0, display.height() / (float) 1.15, display.width(),
                display.height(), borderPaint);
        canvas.drawRect(0, display.height() / (float) 1.15, display.width(),
                display.height(), barBody);
        drawTowersInShop(canvas);
    }

    public void drawTowersInShop(Canvas canvas) {
        for (int i = 0; i < cowTower.length; i++) {
            cowTower[i].setTextSize(50);
            cowTower[i].drawButton(canvas);
        }
    }

    // Helper
    public void setShopDisplay(Rect rectangle) {
        display = rectangle;
    }

    public void initButtons() {

        cowTower = new MyButton[4];
        int left = 100;
        int top = 972;
        int right = 475;
        int bottom = 1055;

        for (int i = 0; i < cowTower.length; i++) {
            cowTower[i] = new MyButton((buttonNames[i]), left + (int) (5.5 * i * left), top,
                    right + (int) (5.5 * i * left), bottom);
            cowTower[i].setCowID(i);
            cowTower[i].setBodyColor(Color.parseColor("#e87c83"));
        }

    }

    @Override
    public void touched(int x, int y, MotionEvent event) {
        for (MyButton b : cowTower) {
            if (b.getBounds().contains(x, y)) {
                b.setBodyColor(Color.GREEN);
                b.setPressed(true);
                clickedButton = b;
                selectedCow = new Cow(x, y, -1, b.getCowID(), bit);
                playScene.setSelectedTower(selectedCow);
            } else {
                b.setPressed(false);
            }
        }
        return;
    }

    public MyButton getClickedButton() {
        return clickedButton;
    }

}
