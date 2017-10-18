package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Shape;

import java.util.ArrayList;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Brick extends GameObject {

    Rectangle rectangle;

    int durability = 1;

    public Brick(int x, int y) {

        rectangle = new Rectangle(x, y, 16, 64);
        rectangle.setColor(Color.GREEN);
        rectangle.fill();
    }


    @Override
    public int getX() {
        return rectangle.getX();
    }

    @Override
    public int getY() {
        return rectangle.getY();
    }

    @Override
    public int getWidth() {
        return rectangle.getWidth();
    }

    @Override
    public int getHeight() {
        return rectangle.getHeight();
    }

    @Override
    public Shape getShape() {

        return rectangle;
    }
}