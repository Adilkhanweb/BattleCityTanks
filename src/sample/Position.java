package sample;

public class Position {
    public static void setCoordinates(int[] P1coordinates) {
        Main.player.setTranslateX(P1coordinates[0]);
        Main.player.setTranslateY(P1coordinates[1]);
    }
}
