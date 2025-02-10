package src.main;
import java.awt.*;

public class Carrier {
    private int capacity;
    private Car[] load;
    private int loadIndex;
    private Point position;

    public Carrier(int capacity, Point position) {
        this.capacity = capacity;
        this.loadIndex = -1;
        this.position = position;
    }

    public int getLoadIndex() {
        return loadIndex;
    }

    public boolean isClose(Car car) {
        double x_dif = car.getPosition().getX() - getPosition().getX();
        double y_dif = car.getPosition().getY() - getPosition().getY();
        double r = Math.sqrt(Math.pow(x_dif, 2)+Math.pow(y_dif, 2));
        if (r<5) {return true;} else {
            return false;
        }
    }

    public void setPosition(double x, double y) {
        position.setLocation(x, y);
        for (int i = 0; i < getLoadIndex()+1; i++) {
            load[i].towedBy(this);
        }
    }

    public Point getPosition() {
        return position;
    }

    public void loadCar(Car car) {
        if (loadIndex < capacity-1) { // has space
            if (car.isTowable() && !car.isTowed()) { // car is ok to pickup
                if (isClose(car)) {
                    loadIndex += 1;
                    load[loadIndex] = car;
                    car.stopEngine();
                    car.setTowed();
                }
            }
        }
    }

    public void unloadCar() {
        if (loadIndex >= 0) {
            load[loadIndex].setNotTowed();
            loadIndex = Math.max(-1,loadIndex-1);  // flytta pekare
        }
    }

}
