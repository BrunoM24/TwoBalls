package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.twoballs.GameScreen;
import org.academiadecodigo.twoballs.Stage;
import org.academiadecodigo.twoballs.manage.Spawn;
import org.academiadecodigo.twoballs.manage.ScoreManager;
import org.academiadecodigo.twoballs.sound.GameSound;
import org.academiadecodigo.twoballs.sound.SoundManager;

import java.awt.*;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Brick extends GameObject {

    int durability;

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

        Picture p = new Picture(x, y, "brick" + color + ".png");
        p.draw();
        return p;
    }

    public void damageBrick(int paddleId) {

        if (paddleId == 0) {

            return;
        }

        Canvas.getInstance().hide(shape);
        durability--;


        if (durability <= -1) {

            SoundManager.playSound(GameSound.PUF);

            if (paddleId == 1 || paddleId == 2) {

                ScoreManager.addPoints(paddleId, ScoreManager.BRICK_POINTS);
            }

            kill();
            int particles = (int) (Math.random() * 3) + 3;
            for (int i = 0; i <= particles; i++) {

                Spawn.spawnParticle(this.getX() + this.getWidth() / 2, this.getY() + this.getHeight() / 2);
            }

            if ((int) (Math.random() * 10) <= 2) {

                Spawn.newPowerUp(this.getX(), this.getY() + this.getHeight() / 2, paddleId == 1 ? -1 : 1);

            }

            return;
        }

        SoundManager.playSound(GameSound.PAKIN);
        shape = changeImage(getX(), getY());
    }

}