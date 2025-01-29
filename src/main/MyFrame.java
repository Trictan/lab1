
package src.main;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class MyFrame extends JFrame implements KeyListener{

	Volvo240 myVolvo;
	JLabel label;
	ImageIcon icon;
	
	MyFrame(){		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.setLayout(null);
		this.addKeyListener(this);
		this.myVolvo = new Volvo240(3, 100, 0, new Color(255,0,0,0), "Volvo");
		
		icon = new ImageIcon("icon.png");
		
		label = new JLabel();
		label.setBounds(0, 0, 50, 50);
		label.setIcon(icon);
		//label.setBackground(Color.red);
		//label.setOpaque(true);
		this.getContentPane().setBackground(Color.black);
		this.add(label);
		this.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//keyTyped = Invoked when a key is typed. Uses KeyChar, char output
		switch(e.getKeyChar()) {
			case 'a': 
			for (var i=0;i<5;i++){myVolvo.turnLeft();};
			myVolvo.move();
				break;
			case 'w': 
			myVolvo.gas(0.5);
			myVolvo.move();
				break;
			case 's': 
			myVolvo.brake(0.5);
			myVolvo.move();
				break;
			case 'd': 
			for (var i=0;i<5;i++){myVolvo.turnRight();};
			myVolvo.move();
				break;
		}
		update();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//keyPressed = Invoked when a physical key is pressed down. Uses KeyCode, int output
		switch(e.getKeyCode()) {
		case 37: 
			for (var i=0;i<3;i++){myVolvo.turnLeft();};
			myVolvo.move();
			update();
			break;
		case 38: 
			myVolvo.gas(0.5);
			myVolvo.move();
			update();
			break;
		case 39: 
			myVolvo.brake(0.5);
			myVolvo.move();
			update();
			break;
		case 40: 
			for (var i=0;i<3;i++){myVolvo.turnRight();};
			myVolvo.move();
			update();
			break;

	}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//keyReleased = called whenever a button is released
		System.out.println("You released key char: " + e.getKeyChar());
		System.out.println("You released key code: " + e.getKeyCode());
	}

	public void update() {
		var x = (int) myVolvo.getPosition().getX();
		var y = (int) myVolvo.getPosition().getY();
		label.setLocation(x,y);
	}
}