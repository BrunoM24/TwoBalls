package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.twoballs.manage.ObjectFactory;

import java.awt.*;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Brick extends GameObject {

    int durability;

    private Picture[] images = new Picture[3];

    int index = 0;

    public Brick(int x, int y, int durability) {

        this.durability = durability;

        shape = changeImage(x, y);
        bounds = new Rectangle(x, y, shape.getWidth(), shape.getHeight());
    }

    private Picture changeImage(int x, int y) {

        String color = "Gray";

        if (durability == 2) {

            color = "Blue";
        } else if (durability == 1) {

            color = "Green";
        }

        Picture p = new Picture(x, y,"assets/brick" + color + ".png");
        p.draw();
        return p;
    }

    public void damageBrick() {

        Canvas.getInstance().hide(shape);

        durability--;

        if (durability <= -1) {

            kill();
            int particles = (int)(Math.random() * 3) + 3;
            for(int i = 0; i <= particles; i++){
                ObjectFactory.spawnParticle(this.getX() + this.getWidth() / 2, this.getY() + this.getHeight() / 2);
            }
            return;
        }

        shape = changeImage(getX(), getY());
    }
}