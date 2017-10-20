package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.twoballs.GameScreen;
import org.academiadecodigo.twoballs.gameobjects.move.Direction;
import org.academiadecodigo.twoballs.gameobjects.move.Movable;
import org.academiadecodigo.twoballs.gameobjects.move.Speed;
import org.academiadecodigo.twoballs.manage.ScoreManager;
import org.academiadecodigo.twoballs.sound.GameSound;
import org.academiadecodigo.twoballs.sound.SoundManager;

import java.awt.*;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Ball extends GameObject implements Movable {

    private final int MIN_SPEED = 3, MAX_SPEED = 3;

    private Speed speed = new Speed();

    private Direction direction = new Direction();

    private int dx, dy;

    private GameObject lastGameObjectTouched;

    private Paddle lastPaddleTouched;

    public Ball(int x, int y, int dirX, int dirY) {

        shape = new Picture(x, y, "assets/ball.png");
        shape.draw();
        bounds = new Rectangle(x, y, shape.getWidth(), shape.getHeight());

        speed.x = calcSpeed();
        speed.y = calcSpeed();

        direction.x = dirX;
        direction.y = dirY;
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

                flipX();
            }
        }

        if(getY() <= 0) {

            translate(0, getY() * -1);
            if(direction.y < 0) {

                flipY();
            }
        }

        if(getX() + getWidth() > GameScreen.getWidth()) {

            translate(-1, 0);
            if(direction.x > 0) {

                flipX();
            }
        }

        if(getY() + getHeight() > GameScreen.getHeight()) {

            translate(0, -1);
            if(direction.y > 0) {

                flipY();
            }
        }
    }

    public void translate(float x, float y) {

        ((Picture) shape).translate(x, y);
        bounds.setLocation(shape.getX(), shape.getY());
    }

    private void checkBoundaries(float delta) {

        if(getX() + dx * delta < GameScreen.getX()) {

            dx *= -1;
            flipX();
            ScoreManager.instance.addPoints(2, ScoreManager.OUT_OF_BOUNDS_POINTS);
            SoundManager.getInstance().playSound(GameSound.laugh());
            //ScoreManager.instance.checkScore();
        }


        if(getX() + getWidth() + dx * delta > GameScreen.getWidth()) {

            dx *= -1;
            flipX();
            ScoreManager.instance.addPoints(1, ScoreManager.OUT_OF_BOUNDS_POINTS);
            SoundManager.getInstance().playSound(GameSound.laugh());
            //ScoreManager.instance.checkScore();
        }

        if(getY() + dy * delta < GameScreen.getY() || getY() + getHeight() + dy * delta > GameScreen.getHeight()) {

            dy *= -1;
            flipY();
            SoundManager.getInstance().playSound(GameSound.BOUNCE);
            setLastObjectTouched(null);
        }
    }

    public void flipX() {

        direction.x *= -1;
        speed.x = calcSpeed();
    }

    public void flipY() {

        direction.y *= -1;
        speed.y = calcSpeed();
    }

    public int calcSpeed() {

        return (int) ((Math.random() * MAX_SPEED) + MIN_SPEED);
    }


    public Direction getDirection() {

        return direction;
    }

    public void setLastObjectTouched(GameObject gameObject) {

        lastGameObjectTouched = gameObject;

        if(gameObject instanceof Paddle) {
            lastPaddleTouched = (Paddle) gameObject;
        }
    }

    public GameObject getLastGameObjectTouched() {

        return lastGameObjectTouched;
    }

    public boolean canCollideWith(GameObject object) {

        return lastGameObjectTouched != object;
    }

    public Paddle getLastPaddleTouched() {

        return lastPaddleTouched;
    }
}