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
    void cc_full() {
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

