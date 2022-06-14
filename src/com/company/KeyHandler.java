package com.company;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyHandler implements KeyListener{
    gamepanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    //DEBUG
    boolean checkDrawTime = false;

    public KeyHandler(gamepanel gp){

        this.gp=gp;
    }

    public void keyTyped(KeyEvent e){
    }

    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();

        //PLAY STATE
        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W){
                upPressed  = true;
            }
            if (code == KeyEvent.VK_S){
                downPressed = true;
            }
            if (code == KeyEvent.VK_A){
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D){
                rightPressed = true;
            }
            if (code == KeyEvent.VK_ESCAPE){
                gp.gameState = gp.pauseState;
            }
            if (code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }
            //DEBUG
            if (code == KeyEvent.VK_T){
                if(checkDrawTime == false){
                    checkDrawTime = true;
                }
                else if (checkDrawTime == true){
                    checkDrawTime = false;
                }
            }

        }
        //pause state
        else if(gp.gameState == gp.pauseState){
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
            }
        }
        //dialogue state
        else if(gp.gameState == gp.dialogueState){
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }
        }
    }

        public void keyReleased(KeyEvent e){
            int code = e.getKeyCode();
            if (code == KeyEvent.VK_W){
                upPressed  = false;
            }
            if (code == KeyEvent.VK_S){
                downPressed = false;
            }
            if (code == KeyEvent.VK_A){
                leftPressed = false;
            }
            if (code == KeyEvent.VK_D){
                rightPressed = false;
            }
        }
}

