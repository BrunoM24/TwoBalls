package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Ball extends GameObject implements Movable{

    private Ellipse ball;
    private Rectangle background;
    private int xDirection = 1;
    private int xSpeed = 2;
    private int yDirection = 1;
    private int ySpeed = 1;
    private int radius = 15;


    public Ball (Rectangle backgroundImage, int x, int y) {
        ball = new Ellipse(x, y, radius, radius);
        ball.setColor(Color.BLUE);
        ball.fill();
        background = backgroundImage;

    }


    @Override
    public void move() {

            checkDirection();
            int x = xDirection*xSpeed;
            int y = yDirection*ySpeed;
            ball.translate(x, y);
    }

        public void checkDirection() {

            //bottom
            if (ball.getY() + ball.getHeight() > background.getHeight()+background.getY()) {
                yDirection = -1;
                ySpeed = (int)(Math.random() *3)+1;
                return;
            }

            // top

            if (ball.getY() < background.getY()) {
                yDirection = 1;
                ySpeed = (int)(Math.random() *3)+1;
                return;
            }

            //right

            if (ball.getX() + ball.getWidth() > background.getWidth()+background.getX()) {
                xDirection = -1;
                xSpeed = (int)(Math.random() * 3)+1;
                return;
            }

            //left? for now I'll set the four sides

            if (ball.getX() < background.getX()) {
                xDirection = 1;
                xSpeed = (int)(Math.random() * 3)+1;
                return;
            }
        }
        public void changeX () {
            xDirection = xDirection*-1;
            xSpeed = (int)(Math.random() * 3)+1;
            System.out.println(xDirection + " x " + xSpeed);
        }

        public void changeY () {
            yDirection = yDirection*-1;
            ySpeed = (int)(Math.random() *3)+1;
            System.out.println(yDirection + " y " + ySpeed);
        }



        @Override
    public void checkCollision() {

    }
}