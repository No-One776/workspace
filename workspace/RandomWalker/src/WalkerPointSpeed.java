import sun.java2d.pipe.SpanShapeRenderer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: alexajoh
 * Date: 2/21/12
 * Time: 2:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class WalkerPointSpeed extends WalkerPoint {
public int count =0;
    public static Color[] color_array =
            {Color.black, Color.blue, Color.green, Color.orange, Color.cyan,
                    Color.magenta, Color.red, Color.yellow, Color.darkGray, Color.pink};

    protected int original_x, original_y;

    protected int color_number;

    public WalkerPointSpeed(int pX, int pY, Color pColor) {

        super(pX, pY, pColor);
        initialize();
    }

    public void initialize() {
        original_x = x;
        original_y = y;

        color_number = 0;
        for (int loop = 0; loop < color_array.length; loop++) {
            if (color.equals(color_array[loop]))
                color_number = loop;
        }
    }

    public boolean walk(JPanel window) {
        boolean on_screen = super.walk(window);

        if (count == 0) {

            if (!on_screen) {

               x = original_x;
               y = original_y;
               super.speed = 3;
               super.pointSize = 5;
                color_number = ++color_number % color_array.length;
                color = color_array[color_number];
               count = 1;
            }
        }
        else if (count == 1) {
            if (!on_screen) {

                x= original_x;
                y = original_y;
                super.speed = 14;
                super.pointSize = 80;
                color_number = ++color_number % color_array.length;
                color = color_array[color_number];
                count = 0;
            }
        }
        return true;
    }

}