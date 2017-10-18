package org.academiadecodigo.twoballs;

import org.academiadecodigo.twoballs.input.KeyboardManager;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Game {

    private static final double IDEAL_DELTA = 1000000000.0 / 60.0;

    long lastTime = System.nanoTime();

    double delta = 0.0f;

    private boolean gamePaused = true;

    private Stage stage;

    private KeyboardManager keyboardManager;

    Game() {

        init();
    }

    private void init() {

        int width = 960;
        int height = 544;

        stage = new Stage(width, height);

        stage.initializeObjects();

        keyboardManager = new KeyboardManager(this);
    }

    public void start() {

        while(true) {

            long now = System.nanoTime();

            //divide the current delta by the IDEAL FPS (60)
            delta += (now - lastTime) / IDEAL_DELTA;

            //sets the new time
            lastTime = now;

            //Executes this 60FPS
            while(delta >= 1) {

                //run x 60FPS
                if(gamePaused) {

                    delta = 0;
                    //TODO SHOW PAUSED TEXT
                }

                stage.run((float) delta);
                delta--;
                //TODO move up?
            }
        }
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