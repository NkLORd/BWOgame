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

        //TITLE STATE
        if(gp.gameState == gp.titleState){

            if(gp.ui.titleScreenState == 0){
                if (code == KeyEvent.VK_W){
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0){
                        gp.ui.commandNum = 2;
                    }
                }
                if (code == KeyEvent.VK_S){
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 2){
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER){
                    if(gp.ui.commandNum == 0){
                        gp.ui.titleScreenState = 1;
                    }
                    if(gp.ui.commandNum == 1){
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if(gp.ui.commandNum == 2){
                        System.exit(0);
                    }
                }
            }

            else if(gp.ui.titleScreenState == 1){
                if (code == KeyEvent.VK_W){
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0){
                        gp.ui.commandNum = 3;
                    }
                }
                if (code == KeyEvent.VK_S){
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 3){
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER){
                    if(gp.ui.commandNum == 0){
                        System.out.println("Do some Infant specific stuff!");
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if(gp.ui.commandNum == 1){
                        System.out.println("Do some Toddler specific stuff!");
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if(gp.ui.commandNum == 2){
                        System.out.println("Do some Kid specific stuff!");
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if(gp.ui.commandNum == 3){
                        gp.ui.titleScreenState = 0;
                    }
                }
            }
        }



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

