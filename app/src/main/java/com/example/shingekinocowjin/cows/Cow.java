package com.example.shingekinocowjin.cows;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Cow {
    private int x;
    private int y;
    private int left;
    private int top;
    private int bottom;
    private int right;
    private int cowBodyColor = Color.BLACK;
    private int towerDamage;
    private int towerRange;
    private int id;

    private Bitmap image;
    private Bitmap mageCow;
    private Bitmap cCCow;
    private Bitmap basicCow;
    private Bitmap cannonCow;
    private int towerType = 0; // 0 = basic, 1 = mage, 2 = cannon, 3 = cc

    public Cow(int x, int y, int id, int towerType, Bitmap[] bit) {
        this.id = id;
        this.towerType = towerType;
        this.basicCow = bit[0];
        this.cannonCow = bit[1];
        this.mageCow = bit[2];
        this.cCCow = bit[3];
        this.x = x;
        this.y = y;
        left = x - 50;
        top = y - 50;
        bottom = y + 50;
        right = x + 50;
    }

    public void drawCow(Canvas canvas) {
        drawCowBody(canvas);
        //drawCowOutline(canvas);
        drawCowRange(canvas);
    }

    public void drawCowBody(Canvas canvas) {
        Paint cowBody = new Paint();
        switch (towerType) {
        case 0:
            //cowBodyColor = Color.BLUE;
            image = basicCow;
            towerDamage = 3;
            towerRange = 120;
            break;
        case 1:
            //cowBodyColor = Color.GREEN;
            image = mageCow;
            towerDamage = 3;
            towerRange = 150;
            break;
        case 2:
            //cowBodyColor = Color.RED;
            towerDamage = 1;
            towerRange = 175;
            image = cannonCow;
            break;
        case 3:
            //cowBodyColor = Color.WHITE;
            towerDamage = 2;
            towerRange = 200;
            image = cCCow;
            break;
        default:
            break;
        }
        // cowBody.setColor(cowBodyColor);
        // canvas.drawRect(left, top, right, bottom, cowBody);
        canvas.drawBitmap(image, left - 50, top - 50, null);
    }

    public void drawCowOutline(Canvas canvas) {
        Paint cowOutline = new Paint();
        cowOutline.setStrokeWidth(1);
        cowOutline.setColor(Color.BLACK);
        cowOutline.setStyle(Paint.Style.STROKE);
        canvas.drawRect(left, top, right, bottom, cowOutline);
    }

    public void drawCowRange(Canvas canvas) {
        Paint cowOutline = new Paint();
        cowOutline.setStrokeWidth(1);
        cowOutline.setColor(Color.WHITE);
        cowOutline.setStyle(Paint.Style.STROKE);
        canvas.drawOval(left - (towerRange), top - (towerRange),
                right + (towerRange), bottom + (towerRange), cowOutline);
    }

    public int getTowerType() {
        return towerType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCowRange() {
        return towerRange;
    }

    public void setCowRange(int range) { towerRange = range; }

    public int getTowerDamage() {
        return towerDamage;
    }

    public void setTowerDamage(int damage) { towerDamage = damage; }

}
