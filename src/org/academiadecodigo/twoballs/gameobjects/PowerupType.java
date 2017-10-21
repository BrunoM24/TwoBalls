package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.twoballs.manage.ScoreManager;

/**
 * Created by miro on 21/10/2017.
 */
public enum PowerupType {

    POINTS_50(true),
    POINTS_M50(false),
    FREEZE(false),
    FREEZE_OTHER(true);

    boolean good;

    PowerupType(boolean good) {

        this.good = good;
    }

    public static PowerupType randomGood() {

        return randomOfType(false);
    }

    public static PowerupType randomBad() {

        return randomOfType(true);
    }

    public static PowerupType random() {

        return values()[(int) (Math.random() * values().length)];
    }

    private static PowerupType randomOfType(boolean bad) {

        PowerupType pwType = random();
        while((pwType.good && bad) || (!pwType.good && !bad)) {

            pwType = random();
        }
        return pwType;
    }

    public String image() {

        return "powerup_" + name().toLowerCase() + ".png";
    }

    public boolean isGood() {

        return good;
    }

    public void collided(int paddleId) {

        switch(this) {

            case POINTS_50:
                ScoreManager.addPoints(paddleId, 50);
                break;
            case POINTS_M50:
                ScoreManager.addPoints(paddleId, -50);
                break;
        }
    }
}
