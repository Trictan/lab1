package src.main;
import java.awt.*;

public class Saab95 extends Car{

    public boolean turboOn;

    public Saab95(int nrDoors, double enginePower, double currentSpeed, Color color, String modelName, Boolean turboOn){
        super(nrDoors, enginePower, currentSpeed, color, modelName);
        this.turboOn = turboOn;
    }

    public void setTurboOn(){
	    turboOn = true;
    }

    public void setTurboOff(){
	    turboOn = false;
    }
    
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }
}
