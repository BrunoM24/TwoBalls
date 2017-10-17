package org.academiadecodigo.twoballs;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Game {

    private static final double IDEAL_DELTA = 1000000000.0 / 60.0;

    long lastTime = System.nanoTime();

    double delta = 0.0f;

    private Stage stage;

    Game() {

        init();
    }

    private void init() {

        int width = 960;
        int height = 544;

        stage = new Stage(width, height);

        stage.initializeObjects();
    }

    public void start() throws InterruptedException {

        while(true) {

            long now = System.nanoTime();

            //divide the current delta by the IDEAL FPS (60)
            delta += (now - lastTime) / IDEAL_DELTA;

            //sets the new time
            lastTime = now;

            //Executes this 60FPS
            while(delta >= 1) {

                //run x 60FPS
                stage.run((float) delta);
                delta--;
            }
        }
    }
}