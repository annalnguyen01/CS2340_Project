package com.example.shingekinocowjin;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class Game extends Thread {
    private final SurfaceHolder surfaceHolder;
    private GameScreen gameScreen;
    private boolean isRunning = false;

    private static final double MAX_UPS = 30.0;
    private static final double UPS_PERIOD = 1E3 / MAX_UPS;
    private double averageUPS;
    private double averageFPS;

    // ***Figure out how to connect monumentHealth to the monument health in
    // player!!.
    private static int monumentHealth;

    public Game(GameScreen gameScreen, SurfaceHolder surfaceHolder) {
        super();
        this.gameScreen = gameScreen;
        this.surfaceHolder = surfaceHolder;
    }

    public double getAverageUPS() {
        return averageUPS;
    }

    public double getAverageFPS() {
        return averageFPS;
    }

    public int getMonumentHealth() {
        return monumentHealth;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    // public GameState getGameState() {return gamestate;}

    // public GameState getGameState() {return GameState.PLAYING;}

    public void startLoop() {
        isRunning = true;
        start();
    }

    @Override
    public void run() {
        super.run();

        // Declare time and update cycle count variables
        int updateCount = 0;
        int frameCount = 0;
        long startTime;
        long elapsedTime;
        long sleepTime;

        // Game Loop
        Canvas canvas = null;
        startTime = System.currentTimeMillis();
        while (isRunning) {
            // Try to update and render game
            try {
                canvas = surfaceHolder.lockCanvas();
                // Prevents multiple threads from calling the update
                synchronized (surfaceHolder) {
                    gameScreen.update();
                    updateCount++;
                    gameScreen.draw(canvas);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }

                // Pause game Loop to not exceed target UPS
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (long) (updateCount * UPS_PERIOD) - elapsedTime;
                if (sleepTime > 0) {
                    try {
                        sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            // Skip frames to keep with target UPS
            while (sleepTime < 0 && updateCount < MAX_UPS - 1) {
                gameScreen.update();
                updateCount++;
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (long) (updateCount * UPS_PERIOD) - elapsedTime;
            }

            // Calculate avg UPS and FPS
            elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= 1000) {
                averageUPS = updateCount / (1E-3 * elapsedTime);
                averageFPS = frameCount / (1E-3 * elapsedTime);
                updateCount = 0;
                frameCount = 0;
                startTime = System.currentTimeMillis();
            }

            // Game over section when monumentHealth <= 0;

        }

    }
}
