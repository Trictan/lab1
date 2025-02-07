package src.main;
import java.awt.*;
import java.math.*;
import java.util.Arrays;

public class CarCarrier extends Truck{
    private int capacity;
    private Car[] load;
    private int load_index;

    CarCarrier(Color color, String modelname, int capacity) {
        super(2, 100, 0, color, modelname);
            this.capacity = capacity;
            this.load = new Car[capacity];
            this.load_index = 0;
    }

    public boolean isClose(Car car) {
        double x_dif = car.getPosition().getX() - this.getPosition().getX();
        double y_dif = car.getPosition().getY() - this.getPosition().getY();
        double r = Math.sqrt(Math.pow(x_dif, 2)+Math.pow(y_dif, 2));
        if (r<5) {return true;} else {
            return false;
        }
    }

    public void load_car(Car car) {
        if (isClose(car)) {
            if (load.length < capacity) {
                load[load_index] = car;
            }
        }
    }

    public void unload_car() {
        load[load_index] = null;
        // move car?
    }

    @Override
    public void move() {
        super.move();
        for (int i = 0; i < load.length; i++) {
            load[i].towedBy(this);
        }
    }

}
