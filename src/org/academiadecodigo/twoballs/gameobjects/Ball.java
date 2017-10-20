package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.twoballs.GameScreen;
import org.academiadecodigo.twoballs.gameobjects.move.Direction;
import org.academiadecodigo.twoballs.gameobjects.move.Movable;
import org.academiadecodigo.twoballs.gameobjects.move.Speed;
import org.academiadecodigo.twoballs.sound.GameSound;
import org.academiadecodigo.twoballs.sound.SoundManager;

import java.awt.*;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Ball extends GameObject implements Movable {

    private final int MIN_SPEED = 3, MAX_SPEED = 5;

    private Speed speed = new Speed();

    private Direction direction = new Direction();

    private int dx, dy;

    private long lastChanged;

    private GameObject lastTouched;

    //TODO IF RUBBERBANDING HAPPENS, CHANGE IT HERE
    private int timeToBounce = 300;

    public Ball(int x, int y) {

        this(x, y, Math.random() > 0.5f ? 1 : -1, Math.random() > 0.5f ? 1 : -1);
    }

    public Ball(int x, int y, int dirX, int dirY) {

        shape = new Picture(x, y, "assets/ball.png");
        shape.draw();
        bounds = new Rectangle(x, y, shape.getWidth(), shape.getHeight());

        speed.x = calcSpeed();
        speed.y = calcSpeed();

        direction.x = dirX;
        direction.y = dirY;
    }

    public void touchedLeft(GameObject gObject) {

        if(lastTouched == gObject) {

            return;
        }
        //inverte X
        flipX(false);
    }

    public void touchedRight() {

        //inverte X
    }

    public void touchedDown() {

        //inverte Y
    }

    public void touchedUp() {

        //inverte Y
    }

    @Override
    public void move(float delta) {

        dx = direction.x * speed.x;
        dy = direction.y * speed.y;

        checkBoundaries(delta);

        translate(dx * delta, dy * delta);

        keepInScreen();
    }

    private void keepInScreen() {

        if(getX() <= 0) {

            translate(getX() * -1, 0);
            if(direction.x < 0) {

                flipX(true);
            }
        }

        if(getY() <= 0) {

            translate(0, getY() * -1);
            if(direction.y < 0) {

                flipY(true);
            }
        }

        if(getX() + getWidth() > GameScreen.getWidth()) {

            translate(-1, 0);
            if(direction.x > 0) {

                flipX(true);
            }
        }

        if(getY() + getHeight() > GameScreen.getHeight()) {

            translate(0, -1);
            if(direction.y > 0) {

                flipY(true);
            }
        }
    }

    public void translate(float x, float y) {

        ((Picture) shape).translate(x, y);
        bounds.setLocation(shape.getX(), shape.getY());
    }

    private void checkBoundaries(float delta) {

        if(getX() + dx * delta < GameScreen.getX() || getX() + getWidth() + dx * delta > GameScreen.getWidth()) {

            //DECREASE POINTS
            dx *= -1;
            flipX(true);
            SoundManager.getInstance().playSound(GameSound.BOUNCE);
        }

        if(getY() + dy * delta < GameScreen.getY() || getY() + getHeight() + dy * delta > GameScreen.getHeight()) {

            dy *= -1;
            flipY(true);
            SoundManager.getInstance().playSound(GameSound.BOUNCE);
        }
    }

    public void flipX(boolean force) {

        if(!canBounce() && !force) {

            return;
        }

        direction.x *= -1;
        speed.x = calcSpeed();
    }

    public void flipY(boolean force) {

        if(!canBounce() && !force) {

            return;
        }

        direction.y *= -1;
        speed.y = calcSpeed();
    }

    public int calcSpeed() {

        return (int) ((Math.random() * MAX_SPEED) + MIN_SPEED);
    }


    private boolean canBounce() {

        if(System.currentTimeMillis() - lastChanged < timeToBounce) {

            return false;
        }

        lastChanged = System.currentTimeMillis();
        return true;
    }


    public Direction getDirection() {

        return direction;
    }

    public int getDirectionX() {

        return direction.x;
    }

    public int getDirectionY() {

        return direction.y;
    }
}