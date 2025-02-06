package test;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color; import java.awt.Point;
import org.junit.jupiter.api.Test;

import src.main.Saab95;
import src.main.Volvo240;
import src.main.Scania;

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
}

