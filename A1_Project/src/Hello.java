import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class Hello extends JPanel implements ActionListener, KeyListener
{
    int BOARD_WIDTH = 800, BOARD_HEIGHT = 1000;
    Timer tm = new Timer(5, this); //timer
    Player player;
    Invader[] ayy = new Invader[10];
    int ayyx = 10;
    int ayyy = 10;

    public Hello(){
        tm.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        player = new Player(BOARD_WIDTH/2, BOARD_HEIGHT - 60, 5);
        //creating ayys
        for(int i = 0; i < ayy.length; i++){
            ayy[i] = new Invader(ayyx, ayyy, 10);
            ayyx += 40;
            if(i == 4 ){
                ayyx = 10;
                ayyy +=40;
            }
        }
    }

    public void moveAyy(){
        for(int i = 0; i < ayy.length; i++) {
            if (ayy[i].moveLeft == true) {
                ayy[i].x -= 2;//ayy[i].speed;
            }
            if (ayy[i].moveRight == true) {
                ayy[i].x += 2;//ayy[i].speed;
            }
        }
            for(int k = 0; k < ayy.length; k++){
            if (ayy[k].x > BOARD_WIDTH){
                for(int j =0; j<ayy.length; j++){
                    ayy[k].moveLeft = true;
                    ayy[k].moveRight = false;
                }
            }
            if (ayy[k].x < 0){
                for(int j =0; j<ayy.length; j++){
                    ayy[k].moveRight = true;
                    ayy[k].moveLeft = false;
                }
            }
        }
    }

    @Override //draws everything.
    public void paint(Graphics g){
        super.paint(g);
       super.paintComponent(g);
    /*
        Ellipse2D circle = new Ellipse2D.Double(Posx, 300,40, 40);
        g2.fill(circle);
        */
        //playerf
        g.setColor(Color.red);
        g.fillRect(player.x,player.y,20, 20);
        if(player.moveRight){
            player.x += player.speed;
        }
        if(player.moveLeft){
            player.x -= player.speed;
        }
        moveAyy();
        for(int i = 0; i < ayy.length; i++){
            g.fillRect(ayy[i].x,ayy[i].y,30, 30);
        }
      /*  if(fire == 1) {
            Projectile yes = new Projectile(Posx + 17,(Posy - 35),10,10, Color.blue, Vely); //creates projectile instance
            g2.setColor(yes.color);
            g2.fill(yes);
            //g2.drawString(); use to draw text

       */
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

    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();
        int animationDelay = 5;
        long time =
                System.currentTimeMillis();
        while (true) {//infinite loop
            // spriteManager.update();
            repaint();
            try {
                time += animationDelay;
                Thread.sleep(Math.max(0,time -
                        System.currentTimeMillis()));
            }catch (InterruptedException e) {
                System.out.println(e);
            }//end catch
        }//end while loop


    }//end of run

}
