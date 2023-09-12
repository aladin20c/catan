package Jade;





import renderer.ModelManager;
import scenes.*;

import javax.swing.*;

public class Window implements Runnable{

    public final int FPS_SET=10;
    public final int UPS_SET=10;
    private final boolean SHOW_FPS_UPS = false;
    private boolean isRunning;
    private Thread gameThread;

    private static Window window = null;
    private int width, height;
    private String title;
    private JFrame frame;
    private JPanel panel;


    private static Scene currentScene;


    private Window() {
        this.width = 1000;
        this.height = 700;
        this.title = "Game";
        this.isRunning=false;
        this.gameThread = new Thread(this);
        this.frame = new JFrame(this.title);
        this.panel =new JPanel();
    }

    public static Window get() {
        if (Window.window == null) {
            Window.window = new Window();
        }
        return Window.window;
    }

    public void startGame(){
        System.out.println("Hello LWJGL " /*+ Version.getVersion()*/ + "!");
        this.isRunning=true;
        ModelManager.init();
        this.init();
        //really important
        this.gameThread.start();
    }



    public void init() {
        // Setup an error callback
        // Initialize GLFW
        // Create the window
        System.out.println("[Window][init]: Creating window...");
        get().frame.add(panel);
        get().frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        get().frame.setBounds(100, 50, get().width, get().height);
        get().frame.setResizable(false);
        get().frame.setLocationRelativeTo(null);
        get().frame.setVisible(true);


        get().panel.setFocusable(true);
        get().panel.requestFocusInWindow();
        panel.addMouseMotionListener(Mouse.get());
        panel.addMouseListener(Mouse.get());
        panel.addKeyListener(Keyboard.get());

        Window.changeScene(0);
    }


    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (this.isRunning) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {

                currentScene.update();
                updates++;
                deltaU--;

            }
            if (deltaF >= 1) {
                currentScene.render(panel.getGraphics());
                frames++;
                deltaF--;

            }
            if (SHOW_FPS_UPS)
                if (System.currentTimeMillis() - lastCheck >= 1000) {

                    lastCheck = System.currentTimeMillis();
                    System.out.println("FPS: " + frames + " | UPS: " + updates);
                    frames = 0;
                    updates = 0;

                }
        }
    }




    public static void changeScene(int newScene) {
        switch (newScene) {
            case 0:
                currentScene = new MenuScene();
                currentScene.init();
                break;
            case 1:
                currentScene = new OptionScene();
                currentScene.init();
                break;
            case 2:
                currentScene = new PlayingScene();
                currentScene.init();
                break;
            case 3:
                currentScene = new StatsScene((PlayingScene) currentScene);
                currentScene.init();
                break;
            default:
                currentScene = new GameOverScene();
                currentScene.init();
                break;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static Scene getCurrentScene() {
        return currentScene;
    }
}
