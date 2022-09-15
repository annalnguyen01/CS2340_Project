package com.example.shingekinocowjin.scenes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.shingekinocowjin.GameState;
import com.example.shingekinocowjin.ui.MyButton;

public class WelcomeScene implements SceneMethods {
    private Bitmap image;
    private MyButton quit;
    private Rect display;

    public WelcomeScene(Bitmap bmp) {
        image = bmp;
        initButtons();
    }

    public void drawWelcome(Canvas canvas) {

        canvas.drawBitmap(image, null, display, null);
        drawButtons(canvas);
    }

    private void initButtons() {
        quit = new MyButton("QUIT", 50, 50, 300, 200);
    }

    private void drawButtons(Canvas canvas) {
        quit.drawButton(canvas);
    }

    @Override
    public void touched(int x, int y, MotionEvent event) {
        if (quit.getBounds().contains(x, y)) {
            System.exit(0);
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN && !(quit.getBounds().contains(x, y))) {
            GameState.setGameState(GameState.CONFIG);
        }
    }

    public void setWelcomeDisplay(Rect rectangle) {
        display = rectangle;
    }
}
