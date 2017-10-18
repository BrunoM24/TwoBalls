package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Shape;

import java.util.ArrayList;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */


public abstract class GameObject {

    Rectangle objectBoundaries;

    public int getX() {

        return objectBoundaries.getX();
    }

    public int getY() {

        return objectBoundaries.getY();
    }

    public int getWidth() {

        return objectBoundaries.getWidth();
    }

    public int getHeight() {

        return objectBoundaries.getHeight();
    }

    public abstract void checkCollision();

    public abstract void checkCollision(ArrayList<Shape> onTopArray);

    public abstract Shape getShape();
}