package src.main;
import java.awt.*;

public class Truck extends Car {

    private int incline = 0;

    public Truck(int nrDoors, double enginePower, double currentSpeed, Color color, String modelName) {
        super(nrDoors, enginePower, currentSpeed, color, modelName);
    }

    @Override
    public void gas(double amount) {
        if (getIncline() == 0) {
            super.gas(amount);
        }
    }

    public void increaseIncline() {
        if (getCurrentSpeed() == 0) {
            incline = Math.min(incline+5,70);
        }
    }

    public void decreaseIncline() {
        incline = Math.max(incline-5,0);
    }

    public int getIncline() {
        return incline;
    }
}
