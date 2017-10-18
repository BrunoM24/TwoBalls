package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

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
}