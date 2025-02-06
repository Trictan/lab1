package src.main;
import java.awt.*;

public class Saab95 extends Car{

    private boolean turboOn;

    public Saab95(Color color){
        super(2, 100, 0, color, "Saab95");
        this.turboOn = false;
    }

    public void setTurboOn(){
	    turboOn = true;
    }

    public void setTurboOff(){
	    turboOn = false;
    }

    public boolean getTurbo(){
        return turboOn;
    }
    
    public double speedFactor(){
        double turbo = 1;
        if(getTurbo()) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
}
