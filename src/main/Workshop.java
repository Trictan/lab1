package src.main;
import java.awt.*;
import java.util.ArrayList;

public class Workshop<T extends Car> {
    private Carrier parent;
    private ArrayList<String> whitelist;
    public Workshop(int capacity, Point position, Class<Car> whitelist) {
        this.parent = new Carrier(capacity, position);
        this.whitelist = new ArrayList<String>();
    }

    public boolean isClose(Car car) {
        return parent.isClose(car);
    }

    public void setPosition(double x, double y) {
        parent.setPosition(x, y);
    }

    public Point getPosition() {
        return parent.getPosition();
    }

    public void loadCar(Car car) {
        // kolla modell
        parent.loadCar(car);
    }

    public void unloadCar(int nr) {
        parent.unloadCar(nr-1);
    }

}
