package src.main;
import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.*;
import java.util.*;

public class DrawCar extends Component{    
    Volvo240 myVolvo;

    public DrawCar() {
        this.myVolvo = new Volvo240(3, 100, 0, new Color(255,0,0,0), "Volvo");
    }
    
    @Override
    public void paint(Graphics g) {
        myVolvo.startEngine();
        myVolvo.gas(0.5);

    }//paint

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        DrawCar tracks = new DrawCar();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(30,30,300,300);
        frame.getContentPane().add(tracks);
        frame.setVisible(true);
    }//main


}//DrawPolygons

