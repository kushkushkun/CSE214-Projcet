import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Hello extends JPanel implements ActionListener, KeyListener
{
    private final int BOARD_WIDTH = 800, BOARD_HEIGHT = 1000;
    Timer tm = new Timer(5, this); //timer refreshes screen 5mS
    private final Player player;
    Invader[] ayy = new Invader[15];
    private int ayyx = 10;
    private int ayyy = 10;
    private int count = 0;
    Projectile[] Shot = new Projectile[40]; // make number higher for more shots in game.

    public Hello(){
        tm.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        player = new Player(BOARD_WIDTH/2, BOARD_HEIGHT - 60, 5);

        //creating Bullets
        for(int k = 0; k < Shot.length; k++){
            Shot[k] = new Projectile(player.x, player.y - 20, 10,10, Color.red);
        }

        //creating ayys
        for(int i = 0; i < ayy.length; i++){
            ayy[i] = new Invader(ayyx, ayyy, 10);
            ayyx += 40;
            if(i == 4 || i == 9 || i == 14){
                ayyx = 10;
                ayyy +=40;
            }
        }
    }

    public void moveBullet(){
        for(int i = 0; i < Shot.length; i++) {
            if (Shot[i].isVisible) {
                Shot[i].y -= 4;
            }
        }
                //TOP OF SCREEN DETECTION
            for(int i = 0; i < Shot.length; i++){
                if (Shot[i].y < (20)){
                    Shot[i].isVisible = false;
                    Shot[i].moveUp = false;
                    Shot[i].y = player.y - 20;
                    Shot[i].x = player.x;
                    if( i >= 15){
                        count = 0;
                    }
                }
            }
        for(int i = 0; i < ayy.length; i++){ // Bullet and Ayy collision
            for(int j = 0; j < Shot.length; j++) {
                if(Shot[j].y <= (ayy[i].y + 10) && Shot[j].y >= (ayy[i].y - 10) && ayy[i].isVisible && (Shot[j].x >= ayy[i].x - 10) && Shot[j].x <= (ayy[i].x + 10)) { // shot has to be in y range of invader
                    Shot[j].isVisible = false;
                    Shot[j].moveUp = false;
                    Shot[j].y = player.y - 20;
                    Shot[j].x = player.x;
                    ayy[i].isVisible = false;
                    ayy[i].moveLeft = false;
                    ayy[i].moveRight = false;
                }
            }
        }
    }


    public void moveAyy(){
        for(int i = 0; i < ayy.length; i++) {
            if (ayy[i].moveLeft && ayy[i].isVisible) {
                ayy[i].x -= 2;
            }
            if (ayy[i].moveRight && ayy[i].isVisible) {
                ayy[i].x += 2;
            }
        }
            for(int k = 0; k < ayy.length; k++){
            if (ayy[k].x > (BOARD_WIDTH - 40) && ayy[k].isVisible){
                for(int j =0; j<ayy.length; j++){
                    ayy[j].moveLeft = true;
                    ayy[j].moveRight = false;
                    ayy[j].y += 10;
                }
            }
            if (ayy[k].x < 0 && ayy[k].isVisible){
                for(int j =0; j<ayy.length; j++){
                    if(ayy[j].isVisible) {
                        ayy[j].moveRight = true;
                        ayy[j].moveLeft = false;
                        ayy[j].y += 10;
                    }
                }
            }
        }
    }

    @Override
    public void paint(Graphics g){ //draws everything.
        super.paint(g);
       super.paintComponent(g);
        //player
        g.setColor(Color.red);
        g.fillRect(player.x,player.y,20, 20);
        if(player.moveRight){
            player.x += player.speed;
        }
        if(player.moveLeft){
            player.x -= player.speed;
        }
        moveAyy();
        moveBullet();
        for(int i = 0; i < ayy.length; i++) {
            if (!ayy[i].isVisible) {
                g.clearRect(ayy[i].x, ayy[i].y, 30, 30);
            } else {
                g.fillRect(ayy[i].x, ayy[i].y, 30, 30);
            }
        }

        for(int i = 0; i < Shot.length; i++) {
            if (Shot[i].isVisible) {
                g.fillRect(Shot[i].x, Shot[i].y, 10, 10);
            }
        }
    }


    @Override //timer checks this every 5mS
    public void actionPerformed(ActionEvent e){
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode(); //gets the code associated for used key and stores it in code.
        if(code == 37){
            player.moveLeft = true;
        }
        if(code == 39){
            player.moveRight = true;
        }

        if(code == 38){
               Shot[count].isVisible = true;
               Shot[count].moveUp = true;
               Shot[count].x = player.x;
               Shot[count].y = player.y - 20;
               count = count + 1;
           }
        }


    @Override
    public void keyTyped(KeyEvent e) {
        int code = e.getKeyCode(); //gets the code associated for used key and stores it in code.
        if(code == 37){
            player.moveLeft = true;
        }
        if(code == 39){
            player.moveRight = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 39 || code == 37){
        player.moveRight = false;
        player.moveLeft = false;
        }

    }
}
