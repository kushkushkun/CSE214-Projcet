import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Clicklistener extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println( e.getX() + " " + e.getY());
        super.mouseClicked(e);
    }
}
