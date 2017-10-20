package org.academiadecodigo.twoballs.sound;

/**
 * Created by miro on 20/10/2017.
 */
public enum GameSound {

    BOUNCE("bounce"),
    POINTS("points"),
    BKG_LOOP("bkg_loop"),
    PUF("puf"),
    LAUGH_1("Gargalhada1"),
    LAUGH_2("Gargalhada2"),
    GARGALHADA3("Gargalhada3"),
    DRSH1("drsh1"),
    DRSH2("drsh2"),
    DRSH3("drsh3"),
    DRSH4("drsh4"),
    DRSH5("drsh5"),
    DRSH6("drsh6"),
    PAKIN("Pakin");


    private String path;

    GameSound(String sound) {

        path = "/assets/sound/" + sound + ".wav";
    }


    public static GameSound drsh() {

        int x = (int) (Math.random() * 5);

        switch (x) {
            case 0:
                return GameSound.DRSH6;
            case 1:
                return GameSound.DRSH5;
            case 2:
                return GameSound.DRSH4;
            case 3:
                return GameSound.DRSH3;
            case 4:
                return GameSound.DRSH2;
            default:
                return GameSound.DRSH1;
        }
    }

    public static GameSound laugh() {

        int x = (int) (Math.random() * 3);

        switch (x) {
            case 0:
                return GameSound.LAUGH_1;
            default:
                return GameSound.LAUGH_2;

        }

    }

    public String getPath() {

        return path;
    }
}