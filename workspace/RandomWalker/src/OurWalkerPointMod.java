import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: kurmasz
 * Date: 2/18/13
 * Time: 9:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class OurWalkerPointMod extends WalkerPoint {

    public OurWalkerPointMod(int x, int y, Color c) {
        super(x, y, c);
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

        }
        return true;
    }

}
