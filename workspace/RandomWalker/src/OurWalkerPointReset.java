import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: kurmasz
 * Date: 2/20/13
 * Time: 8:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class OurWalkerPointReset extends WalkerPoint {

    private int orig_x;
    private int orig_y;

     public OurWalkerPointReset(int x, int y, Color c) {
         super(x, y, c);
         orig_x = x;
         orig_y = y;
     }

    @Override
    public boolean walk(JPanel window) {
        boolean on_screen =  super.walk(window);
        if (!on_screen) {
            this.x = orig_x;
            this.y = orig_y;
        }

        return true;
    }
}
