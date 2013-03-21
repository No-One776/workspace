/**
 * Created by IntelliJ IDEA.
 * User: kurmasz
 * Date: 2/15/12
 * Time: 2:47 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Shape {

    private int objetNumber;

    public int getObjetNumber() {
        return objetNumber;
    }

    public void setObjetNumber(int objetNumber) {
        this.objetNumber = objetNumber;
    }

    public abstract void draw();

    public abstract int size();

}
