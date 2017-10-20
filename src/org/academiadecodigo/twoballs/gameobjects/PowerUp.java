package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.twoballs.gameobjects.move.Direction;
import org.academiadecodigo.twoballs.gameobjects.move.Speed;
import org.academiadecodigo.twoballs.gameobjects.move.Movable;



/**
 * Created by codecadet on 20/10/2017.
 */
public class PowerUp extends GameObject implements Movable {


    private Speed speed = new Speed();

    private Direction direction = new Direction();

    private float dx, dy;

    private float dYY;

    private int count=0;


    public PowerUp() {

        shape = new Picture(100, 100, "assets/ball.png");
        shape.draw();
        bounds = new java.awt.Rectangle(100, 100, shape.getWidth(), shape.getHeight());

        speed.x = 1;
        speed.y = 1;

        direction.x = 1;
        //direction.y = 0;
    }


    public void move(float delta) {

        System.out.println((float) Math.sin(count*3.14/6));

        dYY= (float) Math.sin(count*3.14/20);

        dx = direction.x * speed.x;
        dy = 6*dYY * speed.y;

        count++;


        translate(dx * delta, dy * delta);
        bounds.setLocation(shape.getX(), shape.getY());


        // keepInScreen();
    }


    public void translate(float x, float y) {

        ((Picture) shape).translate(x, y);
        bounds.setLocation(shape.getX(), shape.getY());
    }


//TODO PLAYER CATCHES POWERUPS AND FEELS IT; CHECK BOUNDERIES AND DISAPPEARS


}