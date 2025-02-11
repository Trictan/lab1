package src.main;
import java.awt.*;
import java.util.ArrayList;

public class Carrier<T extends Car> {
    private int capacity;
    private ArrayList<T> load;
    private Point position;


    public Carrier(int capacity, Point position) {
        this.capacity = capacity;
        this.position = position;
        this.load = new ArrayList<T>();
    }


    public int getCurrentCapacity() {
        return load.size();
    }


    public void getLoad() {
        for (var i = 0; i < load.size(); i++) {
            System.out.println(load.get(i));
        }
    }


    public boolean isClose(T car) {
        double x_dif = car.getPosition().getX() - getPosition().getX();
        double y_dif = car.getPosition().getY() - getPosition().getY();
        double r = Math.sqrt(Math.pow(x_dif, 2)+Math.pow(y_dif, 2));
        if (r<5) {return true;} else {
            return false;
        }
    }

    public void setPosition(double x, double y) {
        position.setLocation(x, y);
        for (int i = 0; i < load.size(); i++) {
            load.get(i).towedBy(this);
        }
        
    }

    public Point getPosition() {
        return position;
    }

    public void loadCar(T car) {
        if (load.size() < capacity) { // has space
            if (car.isTowable() && !car.isTowed()) { // car is ok to pickup
                if (isClose(car)) {
                    load.add(car);
                    car.stopEngine();
                    car.setTowed();
                }
            }
        }
    }

    public T unloadCar(int index) {
        if (load.size() > 0) {
            if (index >= 0 && index < load.size()) {
                T outputCar = load.get(index);
                outputCar.setNotTowed();
                load.remove(index);
                return outputCar;
            } else {throw new java.lang.Error("Could not find car. Index out of range.");}
        } else {throw new java.lang.Error("There are no cars to unload.");}
    }

}
