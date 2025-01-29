package test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color; import java.awt.Point;
import org.junit.jupiter.api.Test;

import src.main.Saab95;
import src.main.Volvo240;

class CarTest {

    private final Volvo240 myVolvo = new Volvo240(3, 100, 0, new Color(255,0,0,0), "Volvo");
    private final Saab95 mySaab = new Saab95(4, 200, 0, new Color(0,255,0,0), "Saab", false);

    @Test
    void getters() {
        // Volvo240
        assertEquals(3, myVolvo.getNrDoors());
        assertEquals(100, myVolvo.getEnginePower());
        assertEquals(0, myVolvo.getCurrentSpeed());
        assertEquals(new Color(255,0,0,0), myVolvo.getColor());
        assertEquals(new Point(0, 0), myVolvo.getPosition());
        assertEquals(90, myVolvo.getDirection());
        // Saab95
        assertEquals(4, mySaab.getNrDoors());
        assertEquals(200, mySaab.getEnginePower());
        assertEquals(0, mySaab.getCurrentSpeed());
        assertEquals(new Color(0,255,0,0), mySaab.getColor());
        assertEquals(new Point(0, 0), mySaab.getPosition());
        assertEquals(90, mySaab.getDirection());
    }

    @Test
    void volvo240Controls() {
        assertEquals(0, myVolvo.currentSpeed);
        myVolvo.startEngine(); 
        assertEquals(0.1, myVolvo.currentSpeed); // spd = 0.1

        // Volvo240 does not accept input outside [0,1].
        myVolvo.gas(1.1);
        assertEquals(0.1, myVolvo.getCurrentSpeed());
        myVolvo.gas(-0.1);
        assertEquals(0.1, myVolvo.getCurrentSpeed());
        // Volvo240 correctly increases speed using "trimFactor".
        myVolvo.gas(0.5);
        assertEquals(0.725, myVolvo.getCurrentSpeed()); // 0.5 ∈ [0,1]  -> spd = 0.1 + 0.5* 1.25 (trimFactor) = 0.725

        // Correct (but rounded) movement in x-,y-axis.
        for (var i=0; i<9;i++) {myVolvo.turnRight();} // 90°->45°
        assertEquals(45, myVolvo.getDirection());
        myVolvo.move();
        //double v = myVolvo.getDirection() * (Math.PI/180);  // 45° -> diagonal movement
        //assertEquals(Math.cos(v), Math.sin(v));                 
        assertEquals(new Point(1,1), myVolvo.getPosition()); // rounded diagonal movement

        // Speed can't be negative
        for (var i=0; i<12;i++) {myVolvo.brake(0.5);}
        assertEquals(0, myVolvo.getCurrentSpeed());

        // Speed can't exceed Enginepower
        for (var i=0; i<120;i++) {myVolvo.gas(0.9);}
        assertEquals(myVolvo.getEnginePower(), myVolvo.getCurrentSpeed());
        
        // Additional tests, movement in several directions.
        for (var i=0; i<9;i++) {myVolvo.turnRight();} // 45° -> 0°
        assertEquals(0, myVolvo.getDirection()); // 0°
        myVolvo.move();
        assertEquals(new Point(101,1), myVolvo.getPosition()); // moved 100 in x-axis

        for (var i=0; i<36;i++) {myVolvo.turnLeft();} // 0° -> 180°
        myVolvo.move();
        myVolvo.move();
        assertEquals(new Point(-99,1), myVolvo.getPosition()); // moved -200 in x-axis

        for (var i=0; i<36;i++) {myVolvo.turnRight();} // 180° -> 0°
        myVolvo.move();
        assertEquals(new Point(1,1), myVolvo.getPosition()); // moved 100 in x-axis (return to 1,1)

        for (var i=0; i<45;i++) {myVolvo.turnLeft();} // 0° -> 225° (-45°)
        myVolvo.move();
        assertEquals(new Point(-70,-70), myVolvo.getPosition()); // moved -100 diagonally (rounded)
    }

    @Test
    void saab95Controls() {
        assertEquals(0, mySaab.currentSpeed);
        mySaab.startEngine(); 
        assertEquals(0.1, mySaab.currentSpeed); // spd = 0.1

        // Saab95 does not accept input outside [0,1].
        mySaab.gas(1.1);
        assertEquals(0.1, mySaab.getCurrentSpeed());
        mySaab.gas(-0.1);
        assertEquals(0.1, mySaab.getCurrentSpeed());
        // Saab95 correctly increases speed with and without turbo.
        mySaab.gas(0.5);
        assertEquals(1.1, mySaab.getCurrentSpeed()); // 0.5 ∈ [0,1] -> spd = 0.1 + 0.5 * 2 (no turbo)
        mySaab.setTurboOn(); // turbo on
        mySaab.gas(0.5);
        assertEquals(2.4, Math.round(mySaab.getCurrentSpeed()*10.0)/10.0); // 0.5 ∈ [0,1] -> spd = 1.1 + 0.5 * 2.6 (turbo) = 2.4

        // Correct (but rounded) movement in x-,y-axis.
        for (var i=0; i<9;i++) {mySaab.turnRight();} // 90°->45° 
        mySaab.move();
        assertEquals(2.4, Math.round(mySaab.getCurrentSpeed()*10.0)/10.0);
        assertEquals(45, mySaab.getDirection());            
        assertEquals(new Point(2,2), mySaab.getPosition()); // rounded diagonal movement

        // Speed can't be negative
        for (var i=0; i<12;i++) {mySaab.brake(0.5);}
        assertEquals(0, mySaab.getCurrentSpeed());

        // Speed can't exceed Enginepower
        for (var i=0; i<120;i++) {mySaab.gas(0.9);}
        assertEquals(mySaab.getEnginePower(), mySaab.getCurrentSpeed());  
        
        // Additional test, movement in several directions.
        for (var i=0; i<9;i++) {mySaab.turnRight();} // 45°->0°
        mySaab.move();
        assertEquals(new Point(202,2), mySaab.getPosition()); // moved 200 in x-axis

        for (var i=0; i<36;i++) {mySaab.turnLeft();} // 0°->180°
        mySaab.move();
        assertEquals(new Point(2,2), mySaab.getPosition()); // moved -200 in x-axis

        for (var i=0; i<9;i++) {mySaab.turnLeft();} // 180° -> 225° (-45°)
        mySaab.move();
        assertEquals(new Point(-139,-139), mySaab.getPosition()); // moved -200 diagonally (rounded)

    }

}

