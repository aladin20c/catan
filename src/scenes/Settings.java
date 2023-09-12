package scenes;

import java.util.Random;

public class Settings {

    public static int map=1;
    public static int numberOfPlayers=4;
    public static int speed=1;


    public static int MAXROADS=15;
    public static int MAXSETTLEMENTS=5;
    public static int MAXCITIES=4;



    private Settings(){}

    public static void shuffle(Object[] array) {
        Random random = new Random();
        int count = array.length;
        for (int i = count; i > 1; i--) {
            //swap(array, i - 1, random.nextInt(i));
            int r = random.nextInt(i);
            Object tmp = array[i - 1];
            array[i - 1] = array[r];
            array[r] = tmp;
        }
    }
}
