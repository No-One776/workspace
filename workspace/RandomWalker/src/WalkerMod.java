import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: rohrj
 * Date: 2/20/13
 * Time: 9:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class WalkerMod extends WalkerPoint {

    public static Color[] color_array =
            { Color.black, Color.blue, Color.green, Color.orange, Color.cyan,
                    Color.magenta, Color.red, Color.yellow, Color.darkGray, Color.pink, Color.BLACK, Color.cyan, Color.pink, Color.magenta};
    private int c;


    public WalkerMod(int x, int y, Color c){
        super(x, y, c);
        this.c = 0;
    }
    @Override
    public boolean walk(JPanel window) {

        boolean on_screen = super.walk(window);
        if (!on_screen) {
            if (x <= 0) {
                x = window.getWidth();
            } else if (x >= window.getWidth()) {
                x = 0;
            }

            if (y <= 0) {
                y = window.getHeight();
            } else if (y >= window.getHeight()) {
                y = 0;
            }
            color = color_array[c];
            c++;
            if ( c == color_array.length)
                c = 0;
        }
        return true;
    }

    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillOval(x - (pointSize /2 ),
                y - (pointSize / 2),
                pointSize, pointSize);
        g.fillOval(x - (pointSize  ),
                y - (pointSize ),
                pointSize, pointSize);
    }
}
