package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.twoballs.gameobjects.move.Direction;
import org.academiadecodigo.twoballs.gameobjects.move.Movable;

import java.awt.*;

/**
 * Created by miro on 20/10/2017.
 */
public class Particle extends GameObject implements Movable {

    private Direction direction = new Direction();

    private float timeToLive = (float)((Math.random() * 16) + 15);

    public Particle(int x, int y) {

        this(x, y, Math.random() > 0.5f ? 1 : -1, Math.random() > 0.5f ? 1 : -1);
    }

    public Particle(int x, int y, int dirX, int dirY) {

        shape = new Picture(x-10, y-10, "assets/particleStar.png");
        shape.draw();
        bounds = new Rectangle(x, y, shape.getWidth(), shape.getHeight());

        direction.x = dirX;
        direction.y = dirY;
    }

    @Override
    public void move(float delta) {

        timeToLive -= delta;

        if(timeToLive <= 0) {

            kill();
        }

        ((Picture) shape).translate(direction.x, direction.y);
    }
}