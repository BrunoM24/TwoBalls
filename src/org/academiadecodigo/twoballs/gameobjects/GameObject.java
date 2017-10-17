package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */


public abstract class GameObject {

    Rectangle rectangle;
    
    public int getX(){
        return rectangle.getX();
    }
    
    public int getY(){
        return rectangle.getY();
    }
    
    public int getWidth(){
        return rectangle.getWidth();
    }
    
    public int getHeight(){
        return rectangle.getHeight();
    }

    public abstract void checkCollision();
}