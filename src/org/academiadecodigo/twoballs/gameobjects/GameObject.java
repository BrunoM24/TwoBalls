package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Shape;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public abstract class GameObject {

    protected Shape shape;

    public int getX() {

        return shape.getX();
    }

    public int getY() {

        return shape.getY();
    }

    public int getWidth() {

        return shape.getWidth();
    }

    public int getHeight() {

        return shape.getHeight();
    }

    public Shape getShape() {

        return shape;
    }
}