package src.main;
import java.awt.*;
import java.math.*;
import java.util.Arrays;

public class CarCarrier extends Truck{
    private int capacity;
    private Car[] load;
    private int loadIndex;

    CarCarrier(Color color, String modelname, int capacity) {
        super(2, 100, 0, color, modelname);
            this.capacity = capacity;
            this.load = new Car[capacity];
            this.loadIndex = 0;
            setIncline(getMaxIncline());
    }

    @Override
    public void gas(double amount) {
        if (getIncline() == getMaxIncline()) {
            super.gas(amount);
        }
    }

    @Override
    public void increaseIncline() {
        setIncline(getMaxIncline());
    }

    @Override
    public void decreaseIncline() {
        if (getCurrentSpeed() == 0) {
            setIncline(0);
        }
    }


    public boolean isClose(Car car) {
        double x_dif = car.getPosition().getX() - this.getPosition().getX();
        double y_dif = car.getPosition().getY() - this.getPosition().getY();
        double r = Math.sqrt(Math.pow(x_dif, 2)+Math.pow(y_dif, 2));
        if (r<5) {return true;} else {
            return false;
        }
    }

    public void loadCar(Car car) {
        if ((getIncline() == 0) && (load.length < capacity)) { // lowered and has space
            if (car.getClass().getSuperclass().equals(Car.class)) { // is a car
                if (isClose(car)) { // in proximity
                    load[loadIndex] = car;
                }
            }
        }
    }

    public void unloadCar() {
        load[loadIndex] = null;
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
