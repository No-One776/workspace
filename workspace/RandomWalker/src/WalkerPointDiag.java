import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: kurmasz
 * Date: 6/18/12
 * Time: 4:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class WalkerPointDiag extends WalkerPoint {

    @Override
    public boolean walk(JPanel panel) {
       translate(1,1);
        return true;
        }
}
