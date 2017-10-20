package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

/**
 * Created by codecadet on 19/10/17.p
 */
public class PauseText {

    Text pauseText;
    String textToDisplay = " -PAUSE- ";
    public static Text fpsText;

    public PauseText() {
        pauseText = new Text (470,272,textToDisplay);
        pauseText.setColor(Color.YELLOW);
        pauseText.grow(80,25);

        fpsText = new Text(GameScreen.getX(), GameScreen.getHeight() - 10, "?");
        fpsText.setColor(Color.WHITE);
        fpsText.draw();
    }


    public void draw() {
        pauseText.draw();
    }

    public void delete() {
        pauseText.delete();
    }

}
