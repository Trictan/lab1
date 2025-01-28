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
    private double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    private Point getPosition(){
        return position;
    }

    private int getDirection() {
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
    
    private double speedFactor(){
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
        setPosition(getPosition().getX() + (getCurrentSpeed() * Math.cos(getDirection())), 
                    getPosition().getY() + (getCurrentSpeed() * Math.sin(getDirection())));
    }

    public void turnLeft() {
        setDirection(getDirection() + 10);
    }

    public void turnRight() {
        setDirection(getDirection() - 10);
    }

    // Increase currentspeed
    public void gas(double amount){
        if (amount <= 0 || amount >= 1) {
            if (getCurrentSpeed()+amount <= getEnginePower()) {
                incrementSpeed(amount);
            } else {
                this.currentSpeed = getEnginePower();
            }
        } else {
            System.out.println("Invalid input: gas only accepts values in the interval [0,1].");
        }
    }

    // Decrease currentspeed
    public void brake(double amount){
        if (amount <= 0 || amount >= 1) {
            if (getCurrentSpeed()-amount >= 0) {
                decrementSpeed(amount);
            } else {
                this.currentSpeed = 0;
            }
        } else {
            System.out.println("Invalid input: brake only accepts values in the interval [0,1].");
        }
    }

}

