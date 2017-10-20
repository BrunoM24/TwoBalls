package org.academiadecodigo.twoballs.sound;

/**
 * Created by miro on 20/10/2017.
 */
public enum GameSound {

    BOUNCE("bounce"),
    POINTS("points"),
    BKG_LOOP("bkg_loop"),
    PUF("puf"),
    GARGALHADA1("gargalhada1"),
    GARGALHADA2("gargalhada2"),
    DRSH1("drsh1"),
    DRSH2("drsh2"),
    DRSH3("drsh3"),
    DRSH4("drsh4"),
    DRSH5("drsh5"),
    DRSH6("drsh6"),
    PAKIN("pakin");


    private String path;

    GameSound(String sound) {

        path = "/assets/sound/" + sound + ".wav";
    }


    public String getPath() {

        return path;
    }
}