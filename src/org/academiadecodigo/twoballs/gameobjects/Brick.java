package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Brick extends GameObject {

    int durability = 1;

    public Brick(int x, int y) {

        shape = new Picture(x, y, "assets/brickGray.png");
        shape.draw();
    }
}