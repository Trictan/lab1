package src.main;
import java.awt.*;

public class Workshop {
    private Carrier parent;
    private Class<Car> whitelist;

    public Workshop(int capacity, Point position, Class<Car> whitelist) {
        this.parent = new Carrier(capacity, position);
        this.whitelist = whitelist;
    }

    public int getLoadIndex() {
        return parent.getLoadIndex();
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

    public void unloadCar() {
        parent.unloadCar();
    }

}
