package org.academiadecodigo.twoballs;

import org.academiadecodigo.twoballs.gameobjects.Ball;
import org.academiadecodigo.twoballs.gameobjects.GameObject;

import java.util.HashSet;
import java.util.Set;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Game {

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

        while (true) {

            Thread.sleep(10);

            stage.run(0.0f);
        }
    }
}