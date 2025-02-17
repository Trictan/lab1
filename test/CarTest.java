package test;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color; import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import src.main.Car;
import src.main.CarCarrier;
import src.main.Saab95;
import src.main.Volvo240;
import src.main.Workshop;
import src.main.Scania;
import src.main.Truck;

class CarTest {

    @Test
    void volvo240_getters() {
        Volvo240 myVolvo = new Volvo240(new Color(255,0,0,0));
        assertEquals(4, myVolvo.getNrDoors());
        assertEquals(100, myVolvo.getEnginePower(),0.1);
        assertEquals(0, myVolvo.getCurrentSpeed(),0.1);
        assertEquals(new Color(255,0,0,0), myVolvo.getColor());
        assertEquals(new Point(0, 0), myVolvo.getPosition());
        assertEquals(90, myVolvo.getDirection());
    }

    @Test
    void saab95_getters() {
        Saab95 mySaab = new Saab95(new Color(0,255,0,0));
        assertEquals(2, mySaab.getNrDoors());
        assertEquals(100, mySaab.getEnginePower(),0.1);
        assertEquals(0, mySaab.getCurrentSpeed(),0.1);
        assertEquals(new Color(0,255,0,0), mySaab.getColor());
        assertEquals(new Point(0, 0), mySaab.getPosition());
        assertEquals(90, mySaab.getDirection());
    }

    @Test
    void volvo240_gas() {
        Volvo240 myVolvo = new Volvo240(new Color(255,0,0,0));
        // Volvo240 does not accept input outside [0,1].
        myVolvo.gas(1.1);
        assertEquals(0, myVolvo.getCurrentSpeed(),0.01);
        myVolvo.gas(-0.1);
        assertEquals(0, myVolvo.getCurrentSpeed(),0.01);
        // Volvo240 correctly increases speed using "trimFactor".
        myVolvo.gas(0.5);
        assertEquals(0.625, myVolvo.getCurrentSpeed(),0.01); // 0.5 ∈ [0,1]  -> spd = 0 + 0.5* 1.25 (trimFactor) = 0.725
    }

    @Test
    void volvo240_speed() {
        Volvo240 myVolvo = new Volvo240(new Color(255,0,0,0));
        // Speed can't be negative
        for (var i=0; i<12;i++) {myVolvo.brake(0.5);}
        assertEquals(0, myVolvo.getCurrentSpeed(),0.01);

        // Speed can't exceed Enginepower
        for (var i=0; i<120;i++) {myVolvo.gas(0.9);}
        assertEquals(myVolvo.getEnginePower(), myVolvo.getCurrentSpeed(),0.01);
    }

    @Test
    void volvo240_turn_LR() {
        Volvo240 myVolvo = new Volvo240(new Color(255,0,0,0));
        for (var i=0; i<9;i++) {myVolvo.turnRight();}
        assertEquals(45, myVolvo.getDirection());
        for (var i=0; i<18;i++) {myVolvo.turnRight();}
        assertEquals(-45, myVolvo.getDirection());
    }

    @Test
    void volvo240_move_forward() {
        Volvo240 myVolvo = new Volvo240(new Color(255,0,0,0));
        for (var i=0; i<120;i++) {myVolvo.gas(0.9);}
        // Observe, car is facing east.
        myVolvo.move();
        // Sucessfully moved forward at max speed.
        assertEquals(new Point(0,100), myVolvo.getPosition());
    }

    @Test
    void volvo240_move_diagonal() {
        Volvo240 myVolvo = new Volvo240(new Color(255,0,0,0));
        for (var i=0; i<9;i++) {myVolvo.turnRight();}
        for (var i=0; i<120;i++) {myVolvo.gas(0.9);}
        myVolvo.move();
        // Sucessfully moved diagonally at max speed.
        assertEquals(new Point(71,71), myVolvo.getPosition());
    }

    @Test
    void scania_getters() {
        Scania myScania = new Scania(new Color(255,0,0,0));
        assertEquals(2, myScania.getNrDoors());
        assertEquals(300, myScania.getEnginePower(), 0.01);
        assertEquals(0, myScania.getCurrentSpeed(), 0.01);
        assertEquals(new Color(255,0,0,0), myScania.getColor());
        assertEquals("Scania", myScania.getModelName());
        assertEquals(0, myScania.getIncline());
    }

    @Test
    void scania_incline() {
        Scania myScania = new Scania(new Color(255,0,0,0));
        for (var i = 0; i < 20; i++) {myScania.increaseIncline();}
        assertEquals(70, myScania.getIncline()); // max 70°
        for (var i = 0; i < 20; i++) {myScania.decreaseIncline();}
        assertEquals(0, myScania.getIncline());  // min 0°
    }

    @Test
    void scania_gas_while_incline() {
        Scania myScania = new Scania(new Color(255,0,0,0));
        for (var i = 0; i < 9; i++) {myScania.increaseIncline();}
        assertEquals(45, myScania.getIncline());  // inclined
        myScania.gas(0.5);
        assertEquals(0.0, myScania.getCurrentSpeed(), 0.01); // can't gas while incline
    }

    @Test
    void scania_incline_while_gas() {
        Scania myScania = new Scania(new Color(255,0,0,0));
        myScania.gas(0.5);
        assertEquals(1.5, myScania.getCurrentSpeed(), 0.01); // moving
        myScania.increaseIncline();
        assertEquals(0, myScania.getIncline());  // can't incline while moving
    }

    @Test
    void cc() {
        CarCarrier myCC = new CarCarrier(new Color(255,0,0,0), "MAN", 3, new Point(0,0));
        CarCarrier myCC2 = new CarCarrier(new Color(255,0,0,0), "MAN", 2, new Point(0,0));
        Saab95 mySaab1 = new Saab95(new Color(255,0,0,0));
        Saab95 mySaab2 = new Saab95(new Color(0,255,0,0));
        Saab95 mySaab3 = new Saab95(new Color(0,0,255,0));
        Volvo240 myVolvo = new Volvo240(new Color(0,0,0,0));
        myCC.decreaseIncline();
        myCC.loadCar(myCC2);
        myCC.getLoad();
        System.out.println("Load");
        myCC.loadCar(mySaab1);
        myCC.loadCar(myVolvo);
        myCC.loadCar(myVolvo); // already towed
        myCC.loadCar(mySaab2);
        myCC.loadCar(mySaab3); // no space
        myCC.getLoad();
        System.out.println("Unload");
        myCC.unloadCar();
        myCC.unloadCar();
        myCC.unloadCar();
        myCC.getLoad();
        //myCC.unloadCar();
        System.out.println("Reload");
        myCC.loadCar(mySaab3);
        myCC.getLoad();
        myCC.unloadCar();
        ArrayList<String> whitelist = new ArrayList<>(Arrays.asList("Volvo240"));
        Workshop<Car> myWorkshop = new Workshop<Car>(3, new Point(0,0), whitelist);
        myWorkshop.loadCar(myVolvo);
        myWorkshop.loadCar(myVolvo);
        myWorkshop.unloadCar(1);
    }
}

