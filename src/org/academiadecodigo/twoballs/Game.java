package org.academiadecodigo.twoballs;

import org.academiadecodigo.twoballs.input.KeyboardManager;
import org.academiadecodigo.twoballs.sound.GameSound;
import org.academiadecodigo.twoballs.sound.SoundManager;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Game {

    private static final double IDEAL_DELTA = 1000000000.0 / 60.0;

    private long lastTime = System.nanoTime();

    private double delta = 0.0f;

    private boolean running = true;

    private boolean gamePaused = true;

    private Stage stage;

    private KeyboardManager keyboardManager;

    private SoundManager soundManager;

    private PauseText pauseText;

    int frames;


    Game() {

        init();
    }

    private void init() {

        //int width = 960;
        //int height = 544;

        stage = new Stage();

        stage.initializeObjects();

        keyboardManager = new KeyboardManager(this);

        pauseText = new PauseText();

        soundManager = new SoundManager().init();
    }

    void start() {

        long timer = System.currentTimeMillis();

        SoundManager.getInstance().playSound(GameSound.BKG_LOOP);

        while(running) {

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

        soundManager.dispose();

        System.exit(0);
    }

    private void run() {

        //Executes this 60FPS
        while(delta >= 1) {

            //run x 60FPS
            if(gamePaused) {

                delta = 0;
                pauseText.draw();
            }
            else {

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

        if(key == KeyboardManager.GAME_END) {

            running = false;
            return;
        }

        stage.keyPressed(key);
    }

    public void keyReleased(int key) {

        stage.keyReleased(key);
    }
}