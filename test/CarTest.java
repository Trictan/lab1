package test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

//import example.util.Calculator;

import org.junit.jupiter.api.Test;

import src.main.Volvo240;

class CarTest {

    private final Volvo240 myCar = new Volvo240(3, 100, 0, new Color(255,0,0,0), "gojo_mobile", 1.25);

    @Test
    void getters() {
        assertEquals(3, myCar.getNrDoors());
        assertEquals(0, myCar.getCurrentSpeed());
        assertEquals(new Color(255,0,0,0), myCar.getColor());
        
    }

}