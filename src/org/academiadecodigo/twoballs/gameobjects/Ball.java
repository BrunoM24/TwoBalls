package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.twoballs.Game;
import org.academiadecodigo.twoballs.GameScreen;
import org.academiadecodigo.twoballs.Stage;
import org.academiadecodigo.twoballs.gameobjects.move.Direction;
import org.academiadecodigo.twoballs.gameobjects.move.Movable;
import org.academiadecodigo.twoballs.gameobjects.move.Speed;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Ball extends GameObject implements Movable {

    private Speed speed = new Speed();

    private Direction direction = new Direction();

    private int dx, dy;

    public Ball(int x, int y) {

        shape = new Picture(x, y, "assets/ball.png");
        shape.draw();

        speed.x = calcSpeed();
        speed.y = calcSpeed();

        direction.x = Math.random() > 0.5f ? 1 : -1;
        direction.y = Math.random() > 0.5f ? 1 : -1;
    }

    @Override
    public void move(float delta) {

        dx = direction.x * speed.x;
        dy = direction.y * speed.y;

        checkBoundaries();

        ((Picture) shape).translate(dx * delta, dy * delta);
    }

    private void checkBoundaries() {

        if(getX() + dx < GameScreen.getX() || getX() + getWidth() + dx > GameScreen.getWidth() + GameScreen.getX()) {

            dx *= -1;
            changeX();
        }

        if(getY() + dy < GameScreen.getY() || getY() + getHeight() + dy > GameScreen.getHeight() + GameScreen.getY()) {

            dy *= -1;
            changeY();
        }
    }

    public void changeX() {

        direction.x *= -1;
        speed.x = calcSpeed();
    }

    public void changeY() {

        direction.y *= -1;
        speed.y = calcSpeed();
    }

    public int calcSpeed() {

        return (int) ((Math.random() * 5) + 1);
    }
}