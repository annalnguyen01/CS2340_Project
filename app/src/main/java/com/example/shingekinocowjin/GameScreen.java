package com.example.shingekinocowjin;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowMetrics;

import androidx.core.content.ContextCompat;

import com.example.shingekinocowjin.scenes.ConfigScene;
import com.example.shingekinocowjin.scenes.GameOverScene;
import com.example.shingekinocowjin.scenes.KeyboardScene;
import com.example.shingekinocowjin.scenes.PlayScene;
import com.example.shingekinocowjin.scenes.WelcomeScene;

/*
* Game manages all objects in the game and is responsible for updating all states and render
* all objects in the screen
*/
public class GameScreen extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private Game game;
    private WelcomeScene welcomeScene;
    private ConfigScene configScene;
    private KeyboardScene keyboardScene;
    private PlayScene playScene;
    private GameOverScene gameOverScene;
    private Rect display;
    private Context contextm;
    private Bitmap[] bit = new Bitmap[4];

    public GameScreen(Context context) {
        super(context);
        contextm = context;

        // Window Metrics
        WindowMetrics windowMetrics = ((Activity) getContext()).getWindowManager()
                .getCurrentWindowMetrics();
        display = windowMetrics.getBounds();

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        getHolder().addCallback(this);

        // Initialize Game State
        GameState.setGameState(GameState.WELCOME);
        game = new Game(this, surfaceHolder);

        // Initialize player
        player = new Player(100, 5, "");

        setFocusable(true);
    }

    // Inputs from player
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Handle touch events based on game state
        switch (GameState.getGamestate()) {
        case WELCOME:
            welcomeScene.touched((int) event.getX(), (int) event.getY(), event);
            break;
        case CONFIG:
            configScene.touched((int) event.getX(), (int) event.getY(), event);
            break;
        case KEYBOARD:
            keyboardScene.touched((int) event.getX(), (int) event.getY(), event);
            configScene.setUserInput(keyboardScene.getUserInputText());
            break;
        case PLAYING:
            playScene.touched((int) event.getX(), (int) event.getY(), event);
            break;
        case GAMEOVER:
            gameOverScene.touched((int) event.getX(), (int) event.getY(), event);
            break;
        default:
            break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        welcomeScene = new WelcomeScene(BitmapFactory.decodeResource(getResources(),
                R.drawable.welcome_screen));
        configScene = new ConfigScene(BitmapFactory.decodeResource(getResources(),
                R.drawable.cow_background));
        keyboardScene = new KeyboardScene();
        bit[0] = BitmapFactory.decodeResource(getResources(), R.drawable.basic_cow);
        bit[1] = BitmapFactory.decodeResource(getResources(), R.drawable.cannon_cow);
        bit[2] = BitmapFactory.decodeResource(getResources(), R.drawable.cow_mage);
        bit[3] = BitmapFactory.decodeResource(getResources(), R.drawable.cc_cow);
        playScene = new PlayScene(BitmapFactory.decodeResource(getResources(), R.drawable.map),
                bit);
        gameOverScene = new GameOverScene(BitmapFactory.decodeResource(getResources(),
                R.drawable.game_over));
        game.startLoop();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    // Rendering of things onto the screen
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            switch (GameState.getGamestate()) {
            case WELCOME:

                welcomeScene.setWelcomeDisplay(display);
                welcomeScene.drawWelcome(canvas);
                break;
            case CONFIG:
                configScene.setUserInput(keyboardScene.getUserInputText());
                configScene.setChangeName(keyboardScene.getUserInputText());
                configScene.setConfigDisplay(display);
                configScene.drawConfig(canvas);
                break;
            case KEYBOARD:
                keyboardScene.drawConfig(canvas);
                configScene.setUserInput(keyboardScene.getUserInputText());
                break;
            case PLAYING:
                playScene.setPlayingDisplay(display);
                playScene.drawPlay(canvas);
                drawUPS(canvas);
                drawFPS(canvas);
                drawBarn(canvas);
                drawMonumentHealth(canvas);
                drawMoney(canvas);
                drawCowPrice(canvas);
                break;
            case GAMEOVER:
                gameOverScene.setGameOverDisplay(display);
                gameOverScene.drawGameOver(canvas);
                break;
            default:
                break;
            }
        }
    }

    // Updates Per Second = Object changing property and states.
    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(game.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.red);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS " + averageUPS, 100, 100, paint);
    }

    // Frames Per Second = Rendering portion of the game. Visual updates in the game
    // that are drawn.
    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(game.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.red);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS " + averageFPS, 100, 200, paint);
    }

    public void drawMonumentHealth(Canvas canvas) {
        String theMonumentHealth = Double.toString(player.getMonumentHealth());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.red);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("Health: " + theMonumentHealth, 1650, 100, paint);
    }

    public void drawMoney(Canvas canvas) {
        String theMoney = Double.toString(player.getMoney());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.green);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("Money: " + theMoney, 1250, 100, paint);
    }

    public void drawCowPrice(Canvas canvas) {
        String theCowPrice = Double.toString(configScene.getCowPrice());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.green);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("Cow Price: " + theCowPrice, 1650, 900, paint);
    }

    public void drawBarn(Canvas canvas) {
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.red_barn);
        canvas.drawBitmap(b, 1910, 335, null);
    }

    public void update() {
        switch (GameState.getGamestate()) {
        case WELCOME:
            break;
        case CONFIG:
            break;
        case PLAYING:
            playScene.update();
            break;
        default:
            break;
        }
    }

    public WelcomeScene getWelcomeScreen() {
        return welcomeScene;
    }

    public ConfigScene getConfigScreen() {
        return configScene;
    }

}
