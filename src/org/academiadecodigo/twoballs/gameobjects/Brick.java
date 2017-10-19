package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Brick extends GameObject {

    int durability = 1;

    public Brick(int x, int y) {

        shape = new Rectangle(x, y, 16, 64);
        ((Rectangle) shape).setColor(Color.GREEN);
        ((Rectangle) shape).fill();
    }
}