package com.company;
import tile.TileManager;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


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
    public KeyHandler keyh = new KeyHandler(this);


    Sound se = new Sound();
    Sound music = new Sound();

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);

    public UI ui = new UI(this);

    public EventHandler eHandler = new EventHandler(this);
    //to update the screen n number of times
    //to run the program in thread until we stop it
    Thread gameThread;


    //entity and object
    public entity obj[] = new entity[10];
    public player p = new player(this, keyh);
    public entity npc[] = new entity[10];
    public entity monster[] = new entity[20];

    ArrayList<entity> entityList = new ArrayList<>();
    ArrayList<entity> projectileList = new ArrayList<>();

    // game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

    public final int characterState = 4;

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
        aSetter.setNPC();
        aSetter.setMonster();
        //playMusic(0);
        gameState = titleState;

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
        if (gameState == playState){
            p.update();  //PLAYER

            for(int i = 0; i < npc.length; i++){    //NPC
                if(npc[i] != null){
                    npc[i].update();
                }
            }
            for( int i=0; i<monster.length; i++){
                if(monster[i] != null){
                    if(monster[i].alive == true && monster[i].dying == false){
                        monster[i].update();
                    }
                    if(monster[i].alive == false){
                        monster[i] = null;
                    }
                }
            }

            for(int i = 0; i < projectileList.size(); i++){
                if(projectileList.get(i) != null){
                    if(projectileList.get(i).alive == true){
                        projectileList.get(i).update();
                    }
                    if(projectileList.get(i).alive == false){
                        projectileList.remove(i);
                    }
                }
            }
        }
       if (gameState == pauseState){
           //nothing to update
       }

    }
    public void paintComponent(Graphics g){
            super.paintComponent(g);  //updating on jpanel
            Graphics2D g2 = (Graphics2D) g;

            //DEBUG
            long drawStart = 0;
            if(keyh.checkDrawTime == true) {
                drawStart = System.nanoTime();
            }

            //Title Screen
            if(gameState == titleState){

                ui.draw(g2);

            }
            else {


                //title
                tileM.draw(g2);

                //add entities to list
                entityList.add(p);
                for(int i = 0; i<npc.length; i++){
                    if(npc[i]!=null){
                        entityList.add(npc[i]);
                    }
                }
                for(int i = 0; i<obj.length; i++){
                    if(obj[i]!=null){
                        entityList.add(obj[i]);
                    }
                }
                for(int i = 0; i<monster.length; i++){
                    if(monster[i]!=null){
                        entityList.add(monster[i]);
                    }
                }
                for(int i = 0; i<projectileList.size(); i++){
                    if(projectileList.get(i) != null){
                        entityList.add(projectileList.get(i));
                    }
                }

                //sort
                Collections.sort(entityList, new Comparator<entity>() {
                    @Override
                    public int compare(entity e1, entity e2) {
                        int result  = Integer.compare(e1.worldY, e2.worldY);
                        return result;
                    }
                });

                //draw entities
                for(int i = 0; i< entityList.size(); i++){
                    entityList.get(i).draw(g2);
                }

                //empty entity list
                entityList.clear();

                //UI
                ui.draw(g2);

            }


            //DEBUG
            if(keyh.checkDrawTime == true){
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;
                g2.setColor(Color.white);
                g2.drawString("Draw Time: "+ passed, 10, 400);
                System.out.println("Draw Time: "+passed);
            }

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
