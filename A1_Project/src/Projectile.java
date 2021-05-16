import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Projectile extends Rectangle {


    Color color;

      public Projectile(int x, int y, int width, int height, Color color){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
        }


}
