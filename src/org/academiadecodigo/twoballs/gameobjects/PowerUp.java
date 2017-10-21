package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.twoballs.GameScreen;
import org.academiadecodigo.twoballs.Stage;
import org.academiadecodigo.twoballs.gameobjects.move.Direction;
import org.academiadecodigo.twoballs.gameobjects.move.Movable;
import org.academiadecodigo.twoballs.gameobjects.move.Speed;
import org.academiadecodigo.twoballs.manage.Spawn;

import java.awt.*;

/**
 * Created by codecadet on 20/10/2017.
 */
public class PowerUp extends GameObject implements Movable {

    public static final int FREEZE_TIME = 3;

    private Speed speed = new Speed();

    private Direction direction = new Direction();

    private float dx, dy;

    private float dYY;

    private int count = 0;

    private boolean isNearBorder;

    private PowerupType powerupType;


    public PowerUp(int x, int y, int dirX) {

        this(x, y, PowerupType.random(), dirX);
        //this(x, y, PowerupType.FREEZE_OTHER, dirX);
    }

    public PowerUp(int x, int y, PowerupType pwType, int dirX) {

        init(x, y, pwType, dirX);
    }

    private void init(int x, int y, PowerupType pwType, int dirX) {

        shape = new Picture(x, y, "powerups/" + pwType.image());
        shape.draw();
        bounds = new Rectangle(x, y, shape.getWidth(), shape.getHeight());

        speed.x = 3;
        speed.y = 1;

        direction.x = dirX;
        direction.y = 1;

        this.powerupType = pwType;

        if (y <= Stage.PADDING + 20 || y >= GameScreen.getHeight() - 20) {
            isNearBorder = true;

        } else {
            isNearBorder = false;
        }

    }

    public void move(float delta) {

        if (!isNearBorder){
            dYY = (float) Math.sin(count * 3.14 / 20)*6;
        }

        dx = direction.x * speed.x;
        dy = dYY * speed.y;

        count++;

        translate(dx * delta, dy * delta);

        if (getX() <= Stage.PADDING || getX() + getWidth() >= GameScreen.getWidth() + Stage.PADDING) {

            Spawn.removeObject(this);

        }
    }


    public PowerupType getPowerUp() {

        return powerupType;
    }
}