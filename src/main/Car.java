package src.main;
import java.awt.*;

public abstract class Car implements Movable{
    public int nrDoors; // Number of doors on the car
    public double enginePower; // Engine power of the car
    public double currentSpeed; // The current speed of the car
    private int dirAngle; // The current direction of the car
    private Point position;
    public Color color; // Color of the car
    public String modelName; // The car model name

    public Car(int nrDoors, double enginePower, double currentSpeed, Color color, String modelName) {
        this.nrDoors=nrDoors;
        this.enginePower=enginePower;
        this.currentSpeed=currentSpeed;
        this.dirAngle = 90;
        this.position = new Point(0,0);
        this.color=color;
        this.modelName=modelName;
        stopEngine();
    }

    
    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Point getPosition(){
        return position;
    }

    public int getDirection() {
        return dirAngle;
    }

    public Color getColor(){
        return color;
    }

    private void setColor(Color clr){
	    color = clr;
    }

    public void startEngine(){
	    currentSpeed = 0.1;
    }

    public void stopEngine(){
	    currentSpeed = 0;
    }
    
    public double speedFactor(){
        return enginePower * 0.01;
    }

    private void incrementSpeed(double amount){
	    currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    private void setPosition(double x, double y) {
        position.setLocation(x,y);
    }

    private void setDirection(int dir) {
        dirAngle = dir;
    }

    public void move() {
        setPosition(getPosition().getX() + (getCurrentSpeed() * Math.cos(getDirection() * (Math.PI/180))), 
                    getPosition().getY() + (getCurrentSpeed() * Math.sin(getDirection() * (Math.PI/180))));
    }

    public void turnLeft() {
        setDirection(getDirection() + 5);
    }

    public void turnRight() {
        setDirection(getDirection() - 5);
    }

    // Increase currentspeed
    public void gas(double amount){
        if (amount < 0 || amount > 1) {
            System.out.println("Invalid input: gas only accepts values in the interval [0,1].");
        } else {
            incrementSpeed(amount);
        }
    }

    // Decrease currentspeed
    public void brake(double amount){
        if (amount < 0 || amount > 1) {
            System.out.println("Invalid input: brake only accepts values in the interval [0,1].");
        } else {
            decrementSpeed(amount);
        }
    }

}

