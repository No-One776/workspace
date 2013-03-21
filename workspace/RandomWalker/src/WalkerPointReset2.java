import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: kurmasz
 * Date: 10/14/11
 * Time: 2:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class WalkerPointReset2 extends WalkerPoint {

    protected int origX, origY;

    private final static Color[] COLORS = {Color.red, Color.yellow, Color.CYAN, Color.green, Color.blue, Color.orange};
    private static int counter = 0;

    public WalkerPointReset2(int pX, int pY) {
        super(pX, pY, COLORS[0]);
        origX = pX;
        origY = pY;
    }

    @Override
    public boolean walk(JPanel window) {
        boolean onScreen = super.walk(window);

        if (!onScreen) {
            x = origX;
            y = origY;
            counter = (counter + 1) % COLORS.length;
            color = COLORS[counter];
        }
        return true;
    }
}
