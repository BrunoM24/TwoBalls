package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Brick extends GameObject {

    int durability;

    private Picture[] images = new Picture[3];

    public Brick(int x, int y, int durability) {

        this.durability = durability;
        initializeImgs(x, y);

        shape = images[durability];
        shape.draw();
        bounds = new Rectangle(x, y, shape.getWidth(), shape.getHeight());
    }

    private void initializeImgs(int x, int y) {

        for(int n = 0; n < images.length; n++) {

            String color = "Gray";

            if(durability == 2) {

                color = "Blue";
            }
            else if(durability == 1) {

                color = "Green";
            }

            images[n] = new Picture(x, y, "assets/brick" + color + ".png");
        }
    }

    public void deleteBrick() {

        durability--;

        if(durability <= -1) {

            kill();
            return;
        }

        Canvas.getInstance().hide(shape);
        shape = images[durability];
        shape.draw();
    }
}