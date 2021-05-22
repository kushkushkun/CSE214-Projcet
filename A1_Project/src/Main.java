import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.*;

public class Main extends Hello {
    public static void main(String[] args) throws InterruptedException {

        JFrame k = new JFrame();
        k.setVisible(true);
        k.setSize(800, 1000);
        k.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton b = new JButton("Click to play");
        b.setBounds(50,100,95,30);
        k.add(b);
        k.setTitle("Space Invaders");
        b.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
                k.setVisible(false);
                Hello s = new Hello();
                JFrame f = new JFrame();
                f.add(s);
                f.setVisible(true);
                f.setSize(800, 1000);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setTitle("Space Invaders");
            }
        });
    }
}