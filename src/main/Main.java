package src.main;

import javax.swing.JFrame;

public class Main {

  public static void main(String[] args) {
    
    JFrame window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    window.setTitle("Adventure Rush");

    GamePanel gamepanel = new GamePanel();
    window.add(gamepanel);

    window.pack();    //causes the window to fit the preffered size and layput of it's subcomponent (= GamePanel)

    window.setLocationRelativeTo(null);
    window.setVisible(true);

    gamepanel.setUpGame();
    gamepanel.startGameThread();

  }
}

