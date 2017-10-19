package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Canvas;

import javax.swing.*;

/**
 * Created by miro on 19/10/2017.
 */
public class GameScreen {

    public static int getX() {

        return component().getX();
    }

    public static int getY() {

        return component().getY();
    }

    public static int getWidth() {

        return component().getWidth() - Stage.PADDING * 2;
    }

    public static int getHeight() {

        return component().getHeight() - Stage.PADDING * 2;
    }

    private static JComponent component() {

        return Canvas.getInstance().getComponent();
    }
}