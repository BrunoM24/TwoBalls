package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by codecadet on 16/10/17.
 */
public class Stage {

    private Rectangle stage;
    public Stage (int PADDING, int width, int height) {
        this.stage = new Rectangle(PADDING, PADDING, width, height);
    }



}
