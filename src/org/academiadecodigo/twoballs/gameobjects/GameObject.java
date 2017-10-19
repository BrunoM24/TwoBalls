package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Shape;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public abstract class GameObject {

    public abstract int getX();

    public abstract int getY();

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract Shape getShape();
}