package org.academiadecodigo.twoballs.manage;

import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.twoballs.GameScreen;

/**
 * TwoBalls Created by BrunoM24 on 17/10/2017.
 */
public class ScoreManager {

    int[] playerScore = new int[3];

    Text textScorePlayerOne;
    Text textScorePlayerTwo;

    public void draw() {

        textScorePlayerOne = new Text(70, 40, playerScore[1] + "");
        textScorePlayerTwo = new Text(GameScreen.getWidth() - 70, 40, playerScore[2] + "");

        textScorePlayerOne.grow(5, 10);
        textScorePlayerTwo.grow(5, 10);

        textScorePlayerOne.draw();
        textScorePlayerTwo.draw();
    }


    public void addPoints(int player, int points) {

        playerScore[player] += points;
        Text text = textScorePlayerTwo;

        if (player == 1) {

            text = textScorePlayerOne;
        }

        int textSize = text.getText().length();
        text.setText(Integer.toString(playerScore[player]));

        if (textSize != text.getText().length()) {

            text.grow(5, 0);
        }
    }
}