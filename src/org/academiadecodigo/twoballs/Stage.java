package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by codecadet on 16/10/17.
 */
public class Stage {

    private Rectangle bkgRectangle;

    public static final int PADDING = 10;

    public Stage(int width, int height) {

        this.bkgRectangle = new Rectangle(PADDING, PADDING, width, height);

        bkgRectangle.draw();
    }

    public Rectangle getBounds() {

        return bkgRectangle;
    }
}