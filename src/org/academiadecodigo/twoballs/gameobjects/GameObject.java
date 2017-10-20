package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Shape;
import org.academiadecodigo.twoballs.manage.ObjectFactory;

import java.awt.*;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public abstract class GameObject {

    Rectangle bounds;

    Shape shape;

    private boolean dead;

    public int getX() {

        return (int) bounds.getX();
    }

    public int getY() {

        return (int) bounds.getY();
    }

    public int getWidth() {

        return (int) bounds.getWidth();
    }

    public int getHeight() {

        return (int) bounds.getHeight();
    }

    @Deprecated
    public Shape getShape() {

        return shape;
    }

    public Rectangle getBounds() {

        return bounds;
    }

    @Override
    public String toString() {

        return getClass().getSimpleName() + ": {x=" + getX() + ", y=" + getY() + ", w=" + getWidth() + ", h=" + getHeight() + "}";
    }

    public boolean isDead() {

        return dead;
    }

    public void kill() {

        this.dead = true;
    }
}