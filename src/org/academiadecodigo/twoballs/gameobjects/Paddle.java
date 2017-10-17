package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.twoballs.Stage;
import org.academiadecodigo.twoballs.gameobjects.move.Movable;


/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Paddle extends GameObject implements Movable {

    private Picture paddle;

    private int direction = Math.random() > 0.5f ? 1 : -1;

    private int speed = 4;

    private Rectangle backgroundImage;

    public Paddle(Rectangle backgroundImage, String paddleColor, int posX, int posY) {

        if(posX - 14 > backgroundImage.getWidth() / 2) {

            posX -= 4;
        }

        paddle = new Picture(posX, posY, "assets/" + paddleColor + "Paddle.png");
        paddle.draw();

        this.backgroundImage = backgroundImage;
    }

    @Override
    public void move() {

        checkDirection();

        paddle.translate(0, direction * speed);
    }

    @Override
    public void move(float delta) {


    }

    public void checkDirection() {

        if(paddle.getY() <= Stage.PADDING || paddle.getY() + paddle.getHeight() >= backgroundImage.getHeight() + Stage.PADDING) {

            changeDirection();
        }
    }

    public void changeDirection() {

        direction = direction * (-1);
    }

    @Override
    public void checkCollision() {

    }

    public void setSpeed(int newSpeed) {

        speed = newSpeed;
    }
}