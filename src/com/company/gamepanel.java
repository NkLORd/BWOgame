package com.company;
import objects.SuperObject;
import tile.TileManager;
import java.awt.*;
import javax.swing.*;

public class gamepanel extends JPanel implements Runnable{
    final int ogtilesize = 16;
    final int scale = 3;
    public final int tilesize = ogtilesize * scale;
    public final int maxScreencol = 16;
    public final int maxScreenRow = 12;
    public final int width = tilesize * maxScreencol;
    public final int height = tilesize * maxScreenRow;

    //world map parameters
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    //fps
    int fps = 60;

    //system
    TileManager tileM = new TileManager(this);

    //to get key inputs
    KeyHandler keyh = new KeyHandler();


    Sound se = new Sound();
    Sound music = new Sound();

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);

    public UI ui = new UI(this);

    //to update the screen n number of times
    //to run the program in thread until we stop it
    Thread gameThread;


    //entity and object
    public SuperObject obj[] = new SuperObject[10];


    public player p = new player(this, keyh);

    // methods of jpanel
    public gamepanel(){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyh);
        this.setFocusable(true);

    }
    //applying sound
    public void setupGame(){

        aSetter.setObject();
        playMusic(0);

    }
    // a method to execute gameloop
    public void startGameThread(){
        gameThread = new Thread(this);      //passing the gamepanel
        gameThread.start();         //calls run()

    }

    //method to run the program in threads due to runnable
    @Override
    public void run(){
        double drawInterval = 1000000000/fps;  //draw screen every 0.016667s
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        int drawcount = 0;
        long timer = 0;
        while (gameThread!= null){
            currentTime = System.nanoTime();
            delta +=(currentTime - lastTime) / drawInterval;    //variable to assign when to update frames
            timer +=(currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1){
                update();
                repaint();
                delta--;
                drawcount++;    //fps
                System.out.println(drawcount);
            }
            if (timer >= 1000000000){ //1 sec passes
                drawcount = 0;
                timer = 0;
            }
        }
    }
    public void update(){
        p.update();

    }
    public void paintComponent(Graphics g){
            super.paintComponent(g);  //updating on jpanel
            Graphics2D g2 = (Graphics2D) g;
            tileM.draw(g2);
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    obj[i].draw(g2, this);
                }
            }

            //player
            p.draw(g2);

            //UI
            ui.draw(g2);

            //dispose
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
    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
}
