package org.academiadecodigo.twoballs;

import org.academiadecodigo.twoballs.input.KeyboardManager;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Game {

    private static final float IDEAL_TIMED_DELTA = 15;

    long lastTime = System.currentTimeMillis();

    double delta = 0.0f;

    int frames = 0;

    private boolean gamePaused = true;

    private Stage stage;

    private KeyboardManager keyboardManager;

    private PauseText pauseText;


    Game() {

        init();
    }

    private void init() {

        int width = 960;
        int height = 544;

        stage = new Stage(width, height);

        stage.initializeObjects();

        keyboardManager = new KeyboardManager(this);

        pauseText = new PauseText();

    }

    public void start() {

        long timer = System.currentTimeMillis();

        while(true) {

            if((delta = System.currentTimeMillis() - lastTime) > IDEAL_TIMED_DELTA) {

                delta /= 10;

                run();
                lastTime = System.currentTimeMillis();
            }

            if(System.currentTimeMillis() - timer > 1000) {

                timer += 1000;
                PauseText.fpsText.setText("" + frames);
                //System.out.println("FPS: " + frames + ", " + System.currentTimeMillis() + ", " + delta);
                frames = 0;
            }
        }
    }

    private void run() {

        //oldRun x 60FPS
        if(gamePaused) {

            delta = 0;
            //TODO SHOW PAUSED TEXT

            pauseText.draw();
        }
        else {

            pauseText.delete();
        }



        stage.run((float) delta);

        frames++;
    }

    public void keyPressed(int key) {

        if(key == KeyboardManager.GAME_PAUSE) {

            gamePaused = !gamePaused;

            return;
        }

        stage.keyPressed(key);
    }

    public void keyReleased(int key) {

        stage.keyReleased(key);
    }
}