package src.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import src.entity.Player;
import src.object.SuperObject;
import src.tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
 
  // SCREEN SETTINGS
  final int originalTileSize = 16;  // 16 x 16 tile
  final int scale = 3;
  public final int tileSize = originalTileSize * scale;  // per tile = 48 pixels
  
  public final int maxScreenCol = 20;
  public final int maxScreenRow = 15;    // 4 : 3 ratio
  public final int screenWidth = tileSize * maxScreenCol;  // 48 * 20 =  960 pixels
  public final int screenHeight = tileSize * maxScreenRow;  // 48 * 15 = 720 pixels

  // WORLD SETTINGS
  public final int maxWorldCol = 90;
  public final int maxWorldRow = 90;
  public final int worldWidth = maxWorldCol * tileSize;
  public final int worldHeight = maxWorldRow * tileSize;


  // FOR CHARACTER ANIMATION WE NEED TO INTRODUCE TIME IN OUR GAME

  // FPS
  int FPS = 60; 

  // SYSTEM
  TileManager tileM = new TileManager(this);
  KeyHandler keyH = new KeyHandler(this);
  Sound music = new Sound();
  Sound se = new Sound();
  public Camera camera = new Camera(this);
  public CollisionChecker cChecker = new CollisionChecker(this);
  public AssetSetter aSetter = new AssetSetter(this);
  public UI ui = new UI(this); 
  Thread gameThread;

  // ENTITY AND OBJECT
  public Player player = new Player(this, keyH);
  public SuperObject object[] = new SuperObject[20];

  //  GAME STATES
  public int gameState;
  public final int titleState = 0;
  public final int playState = 1;
  public final int pauseState = 2;



  
  public GamePanel() {

    this.setPreferredSize(new Dimension( screenWidth, screenHeight));
    this.setBackground(Color.green);
    this.setDoubleBuffered(true);  // enabling this will improve game rendering
    this.addKeyListener(keyH);
    this.setFocusable(true);   // with this game panel can be focusable to recieve key inputs 
  }

  public void setUpGame(){

    // playMusic(0);
    aSetter.setObject();
    gameState = titleState;
  }


  public void startGameThread() {

    gameThread = new Thread(this);
    gameThread.start();

  }


  @Override

    //   public void run(){

    //   double drawInterval = 1000000000/FPS;   //0.016667 seconds
    //   double nextDrawTime = System.nanoTime() + drawInterval;

    //    while( gameThread != null){
        
    //     //Update the information such as character pos
    //     update();
    //     //draw the screen with updated info
    //     repaint();

    //     try {
    //       double remainingTime = nextDrawTime - System.nanoTime();
    //       remainingTime /= 1000000;

    //       if( remainingTime < 0 )
    //       remainingTime = 0;

    //       Thread.sleep( (long) remainingTime);
    //       nextDrawTime += drawInterval;

    //     } catch (InterruptedException e) {
    //       e.printStackTrace();
    //     }

    //    }
    // }


  public void run(){
      
    double drawInterval = 1000000000/FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    long timer = 0;
    int drawcount = 0; 

    while(gameThread != null){

      currentTime = System.nanoTime();
      delta += ( currentTime - lastTime ) / drawInterval;
      timer += ( currentTime - lastTime );
      lastTime = currentTime;


      if( delta >= 1){
        update();
        repaint();
        delta--;
        drawcount ++;
      }

      if(timer >= 1000000000){
        System.err.println("FPS : " + drawcount);
        drawcount = 0;
        timer = 0;
      }
    }
  }

        // public void update() {
        //   player.update();          
        // }

        // public void paintComponent( Graphics g){

        // super.paintComponent(g);
        // Graphics2D g2 = (Graphics2D) g;

        // tileM.draw(g2);
        // player.draw(g2);

        // g2.dispose(); // dispose the graphics context to release some memories
        // }


      public void update() {

        if(gameState == playState){

          player.update();
          camera.update(player.worldX, player.worldY, player.screenX, player.screenY);  // Update camera position
        }
        if(gameState == playState){

        }

      }
      
      @Override
      public void paintComponent(Graphics g ) {

          super.paintComponent(g);
          Graphics2D g2 = (Graphics2D) g;


          // TITLE STATE
          if(gameState == titleState){

            ui.draw(g2);
          }
          else{

            tileM.draw(g2);  // Draw tiles based on camera

            for(int i= 0 ; i < object.length;i++){
              if(object[i] != null){
                object[i].draw(g2, this);
              }

            }

            player.draw(g2);  // Draw player based on camera
            ui.draw(g2);     // Draw ui  
          }


          g2.dispose();
      }
          


  public void playMusic(int i){

    music.setFile(i);
    music.play();
    music.loop();
  }

  public void stopMusic(){

    music.stop();
  }

  public void playerSE(int i){

    se.setFile(i);
    se.play();
  }
}
