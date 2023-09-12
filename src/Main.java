import Jade.Window;

import java.awt.*;

public class Main {

    private Main(){}

    public static void main(String[] args) {
        System.out.println("[Main]: Starting...");
        Window window = Window.get();
        window.startGame();
    }
}
