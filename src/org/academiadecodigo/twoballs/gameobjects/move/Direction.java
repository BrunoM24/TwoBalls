package org.academiadecodigo.twoballs.gameobjects.move;

/**
 * TwoBalls Created by BrunoM24 on 17/10/2017.
 */

public class Direction {

    public int x;

    public int y;

    public Direction() {

        this(0, 0);
    }

    public Direction(int x, int y) {

        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {

        return getClass().getSimpleName() + ": x=" + x + ", y=" + y;
    }
}
