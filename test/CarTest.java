package test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color; import java.awt.Point;
import org.junit.jupiter.api.Test;

import src.main.Saab95;
import src.main.Volvo240;

class CarTest {

    private final Volvo240 myVolvo = new Volvo240(3, 100, 0, new Color(255,0,0,0), "Volvo");
    private final Saab95 mySaab = new Saab95(3, 100, 0, new Color(255,0,0,0), "Saab", false);

    @Test
    void getters() {
        assertEquals(3, myVolvo.getNrDoors());
        assertEquals(100, myVolvo.getEnginePower());
        assertEquals(0, myVolvo.getCurrentSpeed());
        assertEquals(new Color(255,0,0,0), myVolvo.getColor());
        assertEquals(new Point(0, 0), myVolvo.getPosition());
        assertEquals(90, myVolvo.getDirection());
    }

    @Test
    void controls() {
        assertEquals(0, myVolvo.currentSpeed);
        myVolvo.startEngine(); 
        assertEquals(0.1, myVolvo.currentSpeed); // spd = 0.1

        myVolvo.gas(0.5); // spd = 0.1 + 0.5 * 1.25 (trimFactor) = 0.725
        for (var i=0; i<9;i++) {myVolvo.turnRight();} // 90°->45° 
        myVolvo.move();
        assertEquals(0.725, myVolvo.getCurrentSpeed());
        assertEquals(45, myVolvo.getDirection());
        //double v = myVolvo.getDirection() * (Math.PI/180);  // 45° -> diagonal movement
        //assertEquals(Math.cos(v), Math.sin(v));                 
        assertEquals(new Point(1,1), myVolvo.getPosition()); // rounded diagonal movement

        // Speed can't be negative
        for (var i=0; i<12;i++) {myVolvo.brake(0.5);}
        assertEquals(0, myVolvo.getCurrentSpeed());

        // Speed can't exceed Enginepower
        for (var i=0; i<120;i++) {myVolvo.gas(0.9);}
        assertEquals(myVolvo.getEnginePower(), myVolvo.getCurrentSpeed());
        
        for (var i=0; i<9;i++) {myVolvo.turnRight();} // 45° -> 0°
        myVolvo.move();
        assertEquals(new Point(101,1), myVolvo.getPosition()); // moved 100 in x-axis
        for (var i=0; i<36;i++) {myVolvo.turnLeft();} // 0° -> 180°
        myVolvo.move();
        myVolvo.move();
        assertEquals(new Point(-99,1), myVolvo.getPosition()); // moved -200 in x-axis

        for (var i=0; i<36;i++) {myVolvo.turnRight();} // 180° -> 0°
        myVolvo.move();
        assertEquals(new Point(1,1), myVolvo.getPosition()); // moved back to origo

        for (var i=0; i<45;i++) {myVolvo.turnLeft();} // 0° -> 225° (-45°)
        myVolvo.move();
        assertEquals(new Point(-70,-70), myVolvo.getPosition()); // moved -100 diagonally (rounded)
    }

    @Test
    void volvo240() {
        // Volvo240 does not accept input outside [0,1].
        myVolvo.gas(1.1);
        assertEquals(0, myVolvo.getCurrentSpeed());
        myVolvo.gas(-0.1);
        assertEquals(0, myVolvo.getCurrentSpeed());
        // Volvo240 correctly increases speed using "trimFactor".
        myVolvo.gas(0.5);
        assertEquals(0.5*1.25, myVolvo.getCurrentSpeed()); // 0.5 ∈ [0,1]  -> 0.5*trimFactor
    }

    void saab95() {
        // Saab95 does not accept input outside [0,1].
        mySaab.gas(1.1);
        assertEquals(0, mySaab.getCurrentSpeed());
        mySaab.gas(-0.1);
        assertEquals(0, mySaab.getCurrentSpeed());
        // Saab95 correctly increases speed using turbo.
        mySaab.gas(0.5);
        assertEquals(0.5, mySaab.getCurrentSpeed()); // 0.5 ∈ [0,1] -> 0.0 + 0.5 * 1 (no turbo)
        mySaab.setTurboOn(); // turbo on
        mySaab.gas(0.5);
        assertEquals(1.15, mySaab.getCurrentSpeed()); // 0.5 ∈ [0,1] -> 0.5 + 0.5 * 1.3 (turbo)
    }

}