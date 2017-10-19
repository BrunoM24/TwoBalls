package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.twoballs.GameScreen;
import org.academiadecodigo.twoballs.gameobjects.move.Movable;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Paddle extends GameObject implements Movable {

    private int direction = 0;

    private int speed = 4;

    private int deltaY;

    public Paddle(String paddleColor, int posX, int posY) {

        if(posX - 14 > GameScreen.getWidth() / 2) {

            posX -= 4;
        }

        shape = new Picture(posX, posY, "assets/" + paddleColor + "Paddle.png");
        shape.draw();
    }

    public void updateDirection(int newValue) {

        direction = newValue;
    }

    @Override
    public void move(float delta) {

        deltaY = direction * speed;

        checkBoundaries();

        ((Picture) shape).translate(0, deltaY * delta);
    }

    private void checkBoundaries() {

        if(getY() + deltaY < GameScreen.getY() || getY() + getHeight() + deltaY > GameScreen.getHeight() + GameScreen.getY()) {

            deltaY *= -1;
        }
    }
}