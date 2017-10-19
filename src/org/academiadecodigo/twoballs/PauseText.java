package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

/**
 * Created by codecadet on 19/10/17.
 */
public class PauseText {

    Text pauseText;
    String textToDisplay = " -PAUSE- ";

    public PauseText() {
        pauseText = new Text (470,272,textToDisplay);
        pauseText.setColor(Color.YELLOW);
        pauseText.grow(80,25);
    }


    public void draw() {
        pauseText.draw();
    }

    public void delete() {
        pauseText.delete();
    }

}
