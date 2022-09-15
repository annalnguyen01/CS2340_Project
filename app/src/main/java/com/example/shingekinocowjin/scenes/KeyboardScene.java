package com.example.shingekinocowjin.scenes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.shingekinocowjin.GameState;
import com.example.shingekinocowjin.ui.MyButton;

public class KeyboardScene implements SceneMethods {
    private MyButton[] buttons = new MyButton[26];
    private MyButton space;
    private MyButton done;
    private MyButton back;
    protected String userInput = "Change Name";

    public KeyboardScene() {
        initButtons();
    }

    private void initButtons() {
        space = new MyButton("SPACE", 650, 800, 1150, 950);
        back = new MyButton("BKSP", 1580, 500, 1850, 770);
        done = new MyButton("DONE", 1580, 100, 1850, 250);
        createKeyboardButtons();
    }

    public void drawConfig(Canvas canvas) {
        drawKeyboard(canvas);
        drawButtons(canvas);
    }

    private void drawButtons(Canvas canvas) {
        space.drawButton(canvas);
        back.drawButton(canvas);
        done.drawButton(canvas);
        drawKeyboardButtons(canvas);
        drawText(canvas);
    }

    private void createKeyboardButtons() {
        char letter = 'A';
        int top = 500;
        int left = 150;
        int right = 230;
        int bottom = 620;
        for (int i = 0; i < 26; i++) {
            buttons[i] = new MyButton(toString((char) (letter++)), left, top, right, bottom);

            if (i == 12) {
                top += 150;
                bottom += 150;
                left = 150;
                right = 230;
            } else {
                left += 110;
                right += 110;
            }
        }
    }

    private void drawKeyboardButtons(Canvas canvas) {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].drawButton(canvas);
        }
        Rect r = new Rect();
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        Paint paint1 = new Paint();
        paint1.setStrokeWidth(10.0f);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setColor(Color.BLACK);
        canvas.drawRect(150, 100, 1550, 250, paint1);
        r.set(150, 100, 1550, 250);
        canvas.drawRect(r, paint);
    }

    private void drawText(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setTextSize(100);
        canvas.drawText(getUserInputText(), 160, 200, p);

    }

    private void drawKeyboard(Canvas canvas) {
        Rect r = new Rect();
        r.set(0, 0, 3000, 1400);
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#FCF2F3"));
        canvas.drawRect(r, paint);
    }

    @Override
    public void touched(int x, int y, MotionEvent event) {
        if (done.getBounds().contains(x, y)) {
            GameState.setGameState(GameState.CONFIG);
        }
        if (back.getBounds().contains(x, y) && !userInput.equals("")) {
            setUserInputText(getUserInputText().substring(0, getUserInputText().length() - 1));
        }
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].getBounds().contains(x, y)) {
                setUserInputText(getUserInputText() + buttons[i].getText());
            }
        }
        if (space.getBounds().contains(x, y)) {
            setUserInputText(getUserInputText() + " ");
        }
    }

    private String toString(char c) {
        String str = c + "";
        return str;
    }

    public String getUserInputText() {
        return userInput;
    }

    public void setUserInputText(String userInput) {
        this.userInput = userInput;
    }
}
