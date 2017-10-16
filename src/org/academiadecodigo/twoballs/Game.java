package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.keyboard.VKeyboard;
import org.academiadecodigo.twoballs.gameobjects.GameObject;
import org.academiadecodigo.twoballs.gameobjects.Movable;

import java.awt.event.KeyEvent;
import java.util.Set;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */


public class Game implements KeyboardHandler {

    private Set<GameObject> gameObjects;

    Game() {
        init();
    }

    private void init() {

        VKeyboard keyboard = new VKeyboard(this);


        keyboard.listenToKey(KeyEvent.VK_ENTER); //Keys are here

        Canvas.getInstance().addKeyListener(keyboard);

    }

    public void start() {

        while (true) {
            run();
        }

    }

    private void run() {

        for (GameObject object : gameObjects) {

            if (object instanceof Movable) {
                ((Movable) object).move();
            }

            object.checkCollision();

        }

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    @Override
    public void keyTyped(KeyboardEvent keyboardEvent) {

    }
}
