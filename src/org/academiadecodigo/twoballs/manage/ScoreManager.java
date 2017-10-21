package org.academiadecodigo.twoballs.manage;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.twoballs.GameScreen;

/**
 * TwoBalls Created by BrunoM24 on 17/10/2017.
 */
public class ScoreManager {

    public static final int OUT_OF_BOUNDS_POINTS = 50;

    public static final int BRICK_POINTS = 5;

    private static ScoreManager instance;

    private int[] playerScore = new int[3];

    private Text textScorePlayerOne;

    private Text textScorePlayerTwo;

    public ScoreManager() {

        instance = this;
    }

    public static void addPoints(int player, int points) {

        instance.incrementScore(player, points);
    }

    public void draw() {

        textScorePlayerOne = new Text(70, 40, playerScore[1] + "");
        textScorePlayerTwo = new Text(GameScreen.getWidth() - 70, 40, playerScore[2] + "");

        textScorePlayerOne.setColor(Color.RED);
        textScorePlayerTwo.setColor(Color.RED);

        textScorePlayerOne.grow(5, 10);
        textScorePlayerTwo.grow(5, 10);

        textScorePlayerOne.draw();
        textScorePlayerTwo.draw();
    }

    private void incrementScore(int player, int points) {

        playerScore[player] += points;
        Text text = textScorePlayerTwo;

        if(player == 1) {

            text = textScorePlayerOne;
        }

        int textSize = text.getText().length();
        text.setText(Integer.toString(playerScore[player]));

        if(textSize != text.getText().length()) {

            text.grow(5, 0);
        }
    }

    public int getWinner(){
        return playerScore[1] > playerScore[2] ? 1 : 2;
    }
}