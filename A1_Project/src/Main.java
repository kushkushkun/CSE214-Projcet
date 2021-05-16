import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Main {
    public static void main(String[] args) {

        Hello s = new Hello();
        JFrame f = new JFrame();
        f.add(s);
        f.setVisible(true);
        f.setSize(800, 1000);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("moving ball");


    }
}

