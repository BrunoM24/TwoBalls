package org.academiadecodigo.twoballs.gameobjects.move;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */

public interface Movable {

    //TODO SAME REFERENCE FOR ALL OBJECTS
    Direction direction = new Direction();

    Speed speed = new Speed();

    void move();
}