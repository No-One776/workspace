import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.util.zip.ZipEntry;

/**
 * Created by IntelliJ IDEA.
 * User: arendsea
 * Date: 2/21/12
 * Time: 2:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class WalkerPointArendsen extends WalkerPoint
{
     boolean vertical = false;
    int maxSize;

    /**
     * (Constructor) Sets up an instance of {@code WalkerPointArendsen}
     * @param y - initial vertical position of point
     * @param size - initial size of point
     * @param speed - initial speed of point
     */
    public WalkerPointArendsen(int y, int size, int speed)
    {
        super(-32, y, Color.ORANGE, size, speed);
        maxSize = size;
    }

    @Override
    public boolean walk(JPanel window)
    {

        int clientWidth = window.getWidth();
        int clientHeight = window.getHeight();

        if(vertical){super.translate(0, speed);}
        if(!vertical){super.translate(speed, 0);}

        try
        {
            pointSize = (int)((85*maxSize)/distanceTo(clientWidth/2,clientHeight/2));
        }
        catch(ArithmeticException e)
        {
            pointSize = 100;
        }


        if(x>window.getWidth()||y>window.getHeight())
        {
            if(vertical)
            {
                x=-32;
                y = (int)(Math.random()*clientHeight);
            }

            if(!vertical)
            {
                x = (int)(Math.random()*clientWidth);
                y=-32;
            }

            vertical=!vertical;
            return false;
        }
        return true;
    }

    @Override
    public void draw(Graphics g)
    {
        super.draw(g);
    }

}
