package org.academiadecodigo.twoballs;

import com.sun.xml.internal.bind.v2.model.core.ID;
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

    private int frames;
    public void start() {


        long timer = System.currentTimeMillis();
        /*

        while(true) {

            if((delta = System.currentTimeMillis() - lastTime) > IDEAL_DELTA) {

                delta /= 10;

                run();
                lastTime = System.currentTimeMillis();
            }

            if(System.currentTimeMillis() - timer > 1000) {

                timer += 1000;
                PauseText.fpsText.setText("" + frames);
                System.out.println("FPS: " + frames + ", " + System.currentTimeMillis() + ", " + delta);
                frames = 0;
            }
        }
*/
        while(true) {

            long now = System.nanoTime();

            //divide the current delta by the IDEAL FPS (60)
            delta += (now - lastTime) / IDEAL_DELTA;

            run();

            //sets the new time
            lastTime = now;

            if(System.currentTimeMillis() - timer > 1000) {

                timer += 1000;
                PauseText.fpsText.setText("" + frames);
                //System.out.println("FPS: " + frames + ", " + System.currentTimeMillis() + ", " + delta);
                frames = 0;
            }
        }
    }

    private void run() {




        //Executes this 60FPS
        while(delta >= 1) {


            //run x 60FPS
            if(gamePaused) {

                delta = 0;
                //TODO SHOW PAUSED TEXT

                pauseText.draw();

            } else {
                pauseText.delete();
            }


            if(delta > 3) {

                delta = 3;
            }

            stage.run((float) delta);
            delta--;
            frames++;
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