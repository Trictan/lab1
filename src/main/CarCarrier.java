package src.main;
import java.awt.*;
import java.math.*;
import java.util.Arrays;

public class CarCarrier extends Truck{
    private int capacity;
    private Car[] load;
    private int loadIndex;

    public CarCarrier(Color color, String modelname, int capacity) {
        super(2, 100, 0, color, modelname);
        this.capacity = capacity;
        this.load = new Car[capacity];
        this.loadIndex = -1;
        setIncline(getMaxIncline());
    }

    public void getLoad() {
        for (int i = 0; i < loadIndex+1; i++) { // OBS loopa endast till pekaren
            System.out.println(load[i].getModelName());
        }
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
        if (getIncline() == 0 && loadIndex < capacity-1) { // lowered and has space
            if (car.isTowable() && !car.isTowed()) { // car is ok to pickup
                if (isClose(car)) {
                    loadIndex += 1;
                    load[loadIndex] = car;
                    car.setTowed();
                }
            }
        }
    }

    public void unloadCar() {
        load[loadIndex].setNotTowed();
        loadIndex = Math.max(-1,loadIndex-1);  // flytta pekare
        // move car?
    }

    @Override
    public void move() {
        super.move();
        for (int i = 0; i < loadIndex+1; i++) { // OBS loopa endast till pekaren
            load[i].towedBy(this);
        }
    }

}
