package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

/**
 * Created by codecadet on 19/10/17.p
 */
public class PauseText {

    static Text fpsText;

    Text pauseText;

    String paused = " -PAUSED- ";
    String pressPToStart = " -Press P To Start- ";

    public PauseText() {

        pauseText = new Text(470, 272, pressPToStart);
        pauseText.setColor(Color.YELLOW);
        pauseText.grow(80, 25);

        fpsText = new Text(GameScreen.getX(), GameScreen.getHeight() - 10, "?");
        fpsText.setColor(Color.WHITE);
        fpsText.draw();
    }


    public void draw() {

        pauseText.draw();
        //TODO Implement a first touch
    }

    public void delete() {

        pauseText.delete();
    }
}