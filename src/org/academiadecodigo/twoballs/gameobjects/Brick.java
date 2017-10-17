package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Brick extends GameObject{

    Rectangle rectangle;
    int durability = 1;

    public Brick(int x, int y){
        rectangle = new Rectangle(x, y, 16, 64);
        rectangle.setColor(Color.GREEN);
        rectangle.fill();
    }

    @Override
    public void checkCollision() {

    }
}