/* tma8
 * project3
 * MW 200-315
 * I did not collaborate with anyone on this assignment.
 */
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.lang.Math;
import java.util.Random;

public class FireWork extends JFrame implements ChangeListener, ActionListener{
	
	// Panels and Canvas
	protected JPanel panel = new JPanel();
	protected JPanel panel2 = new JPanel();
	protected JPanel bigPanel = new JPanel();
	protected Canvas canvas = new Canvas();
	
	// Components
	protected JButton add = new JButton("Add");
	protected JButton delete = new JButton("Delete");
	protected JButton go = new JButton("Go");
	// Components for the first projectile
	protected JSlider angle = new JSlider(JSlider.HORIZONTAL, 90);
	protected JSlider speed = new JSlider(JSlider.HORIZONTAL, 40, 100, 70);
	protected JSlider time = new JSlider(JSlider.HORIZONTAL, 20);
	protected String[] typeData = {"Circle", "TripleCircle", "Ferguson", "SmallCircles", "Flower"};
	protected String[] colorData = {"Blue", "Orange", "Pink", "Cyan", "Magenta"};
	protected Color[] colorData2 = {Color.BLUE, Color.ORANGE, Color.PINK, Color.CYAN, Color.MAGENTA};
	protected JComboBox type = new JComboBox(typeData);
	protected JComboBox color = new JComboBox(colorData);
	protected JLabel labelAngle = new JLabel("  [0-90] Angle: 45");
	protected JLabel labelSpeed = new JLabel("  [40-100] Speed: 70");
	protected JLabel labelTime = new JLabel("  [0-20] Time: 10");
	protected JLabel labelType = new JLabel("  Type of Firework: ");
	protected JLabel labelColor = new JLabel("  Color: ");
	// Components for the second projectile
	protected JSlider angle2P = new JSlider(JSlider.HORIZONTAL, 90, 180, 135);
	protected JSlider speed2 = new JSlider(JSlider.HORIZONTAL, 40, 100, 70);
	protected JSlider time2 = new JSlider(JSlider.HORIZONTAL, 20);
	protected JComboBox type2 = new JComboBox(typeData);
	protected JComboBox color2 = new JComboBox(colorData);
	protected JLabel labelAngle2 = new JLabel("  [0-90] Angle: 45");
	protected JLabel labelSpeed2 = new JLabel("  [40-100] Speed: 70");
	protected JLabel labelTime2 = new JLabel("  [0-20] Time: 10");
	protected JLabel labelType2 = new JLabel("  Type of Firework: ");
	protected JLabel labelColor2 = new JLabel("  Color: ");
	
	// Parameters(int, color, String, x and y)
	protected int angleL = 0;
	protected int speedL = 0;
	protected int timeL = 0;
	protected int angleL2 = 0;
	protected int speedL2 = 0;
	protected int timeL2 = 0;
	protected Color colorSelected;
	protected Color colorSelected2;
	protected String typeL = "null";
	protected String colorL = "null";
	protected String typeL2 = "null";
	protected String colorL2 = "null";
	protected int x = 0;
	protected int y = 0;
	protected int x2 = 0;
	protected int y2 = 0;
	protected int height2 = 1100;//x and y for the launching point of the second projectile
	protected int width2 = 1100;
	
	protected Random random = new Random();
	
	public FireWork(){
		setTitle("FireWork!");
		setSize(1000, 600);
		// set Layout
		add(canvas, BorderLayout.CENTER);
		add(panel, BorderLayout.WEST);// set parameters to the west
		add(panel2, BorderLayout.NORTH);// set buttons to the north
		panel.setLayout(new GridLayout(22, 1));
		// add labels, sliders and comboBoxes to the west panel
		panel.add(labelAngle);
		panel.add(angle);
		panel.add(labelSpeed);
		panel.add(speed);
		panel.add(labelTime);
		panel.add(time);
		panel.add(labelType);
		panel.add(type);
		panel.add(labelColor);
		panel.add(color);
		// add buttons to the north panel
		panel2.add(go);
		panel2.add(add);
		// add Listeners for the Components
		go.addChangeListener(this);
		angle.addChangeListener(this);
		speed.addChangeListener(this);
		time.addChangeListener(this);
		type.addActionListener(this);
		color.addActionListener(this);
		add.addChangeListener(this);
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		// updates the values of the first projectle's parameters to the user
		if (e.getSource()==angle){
			angleL = angle.getValue();
			labelAngle.setText("  [0-90] Angle: " + angleL);
		} 
		if (e.getSource()==speed){
			speedL = speed.getValue();
			labelSpeed.setText("  [40-100] Speed: " + speedL);
		}
		if (e.getSource()==time){
			timeL = time.getValue();
			labelTime.setText("  [0-20] Time: " + timeL);
		}
		//repaint when "go" button is clicked
		if (e.getSource()==go){
			repaint();
		}
		//add the second projectile's Components(sliders, labels and comboBoxes)
		if (e.getSource()==add){
			panel.add(labelAngle2);
			panel.add(angle2P);
			panel.add(labelSpeed2);
			panel.add(speed2);
			panel.add(labelTime2);
			panel.add(time2);
			panel.add(labelType2);
			panel.add(type2);
			panel.add(labelColor2);
			panel.add(color2);
			panel.add(delete);
			panel.revalidate();
			panel.repaint();
			
			// add the second projectile's Listeners
			angle2P.addChangeListener(this);
			speed2.addChangeListener(this);
			time2.addChangeListener(this);
			delete.addChangeListener(this);
			type2.addActionListener(this);
			color2.addActionListener(this);
			
			// add the launching point of the second projectile
			int r = 10;
			height2 = canvas.getHeight()-100-r/2;
			width2 = canvas.getWidth()-r;
			repaint();
		}
		// updates the values of the second projectle's parameters to the user
		if (e.getSource()==angle2P){
			angleL2 = angle2P.getValue()-90;
			labelAngle2.setText("  [0-90] Angle: " + angleL2);
		} 
		if (e.getSource()==speed2){
			speedL2 = speed2.getValue();
			labelSpeed2.setText("  [40-100] Speed: " + speedL2);
		}
		if (e.getSource()==time2){
			timeL2 = time2.getValue();
			labelTime2.setText("  [0-20] Time: " + timeL2);
		}
		//delete the Components for the second projectile when "delete" button is clicked
		if (e.getSource()==delete){
			panel.remove(labelAngle2);
			panel.remove(angle2P);
			panel.remove(labelSpeed2);
			panel.remove(speed2);
			panel.remove(labelTime2);
			panel.remove(time2);
			panel.remove(labelType2);
			panel.remove(type2);
			panel.remove(labelColor2);
			panel.remove(color2);
			panel.remove(delete);
			panel.revalidate();
			panel.repaint();
			//set all the parameters for the second projectile to default in order to remove the graphics for the second projectile
			height2 = 1100;
			width2 = 1100;
			angleL2 = 0;
			speedL2 = 0;
			typeL2 = "null";
			colorL2 = "null";
			timeL2 = 0;
			repaint();
		}
	}
	// updates the values of the projectiles' parameters to the user
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==type){
			typeL = (String)type.getSelectedItem();
			labelType.setText("  Type of Firework: " + typeL);
		} 
		if (e.getSource()==color){
			colorL = (String) color.getSelectedItem();
			int x = color.getSelectedIndex();
			colorSelected = colorData2[x];
			labelColor.setText("  Color: " + colorL);
		}
		if (e.getSource()==type2){
			typeL2 = (String)type2.getSelectedItem();
			labelType2.setText("  Type of Firework: " + typeL2);
		} 
		if (e.getSource()==color2){
			colorL2 = (String) color2.getSelectedItem();
			int x = color2.getSelectedIndex();
			colorSelected2 = colorData2[x];
			labelColor2.setText("  Color: " + colorL2);
		}
	}	
	
	protected int locationX = 0;
	protected int locationX2 = 0;
	protected int locationY = 0;
	protected int locationY2 = 0;
	protected int angle2 = 0;
	protected int OvalLocationX = 0;
	protected int OvalLocationX2 = 0;
	protected int OvalLocationY = 0;
	protected int OvalLocationY2 = 0;
	
	public class Canvas extends JComponent{
		@Override
		public void paintComponent(Graphics g){
			int r = 10;
			int r2 = 4; //the trajectory of the projectile
			int height = getHeight();
			int width = getWidth();
			g.drawOval(0, height-100-r/2, r, r); // launching circle of the first projectile
			g.drawOval(width2, height2, r, r); // launching circle of the second projectile
			g.drawLine(0, height-100, width, height-100);//ground line
			locationX = (x-r2/2); //the location when the first projectile explodes
			locationY = height-100-r/2 - (y-r2/2);
			locationX2 = width-(x2-r2/2);//the location when the second projectile explodes
			locationY2 = height-100-r/2 - (y2-r2/2);
			// if the two projectiles hit each other(explode within 90 from each other), 
			// the firework will turn red and explode much more wildly
			if (locationX2-locationX <= 90 && locationY2-locationY <= 90){
				g.setColor(Color.RED);
				int r3 = 70;
			    int r4 = 90;
				int r5 = 110;
				int rOval = 6;
				int locationXM = (locationX+locationX2)/2;
				int locationYM = (locationY+locationY2)/2;
				for (angle2 = 0; angle2 <= 360; angle2 += 20){
					OvalLocationX = (int)(locationXM + Math.sin(Math.toRadians(angle2))*r3);
					OvalLocationY = (int)(locationYM + Math.cos(Math.toRadians(angle2))*r3);
					g.drawLine(locationXM, locationYM, OvalLocationX, OvalLocationY);
					g.fillOval(OvalLocationX-rOval/2, OvalLocationY-rOval/2, rOval, rOval);
				}
				for (angle2 = 0; angle2 <= 360; angle2 += 20){
					OvalLocationX = (int)(locationXM + Math.sin(Math.toRadians(angle2))*r4);
					OvalLocationY = (int)(locationYM+20 + Math.cos(Math.toRadians(angle2))*r4);
					g.drawLine(locationXM, locationYM+20, OvalLocationX, OvalLocationY);
					g.fillOval(OvalLocationX-rOval/2, OvalLocationY-rOval/2, rOval, rOval);
				}
				for (angle2 = 0; angle2 <= 360; angle2 += 20){
					OvalLocationX = (int)(locationXM + Math.sin(Math.toRadians(angle2))*r5);
					OvalLocationY = (int)(locationYM+40 + Math.cos(Math.toRadians(angle2))*r5);
					g.drawLine(locationXM, locationYM+40, OvalLocationX, OvalLocationY);
					g.fillOval(OvalLocationX-rOval/2, OvalLocationY-rOval/2, rOval, rOval);
				}
			} else { // if the two projectiles don't hit each other, they will explode separately and normally
			if (typeL.equals("Circle")){ // Firework, type "Circle"
				g.setColor(colorSelected);
				int r3 = 50;
				int r4 = 70;
				int rOval = 6;
				for (angle2 = 0; angle2 <= 360; angle2 += 20){
					OvalLocationX = (int)(locationX + Math.sin(Math.toRadians(angle2))*r3);
					OvalLocationY = (int)(locationY + Math.cos(Math.toRadians(angle2))*r3);
					g.fillOval(OvalLocationX-rOval/2, OvalLocationY-rOval/2, rOval, rOval);
				}
				for (angle2 = 0; angle2 <= 360; angle2 += 20){
					OvalLocationX = (int)(locationX + Math.sin(Math.toRadians(angle2))*r4);
					OvalLocationY = (int)(locationY + Math.cos(Math.toRadians(angle2))*r4);
					g.fillOval(OvalLocationX-rOval/2, OvalLocationY-rOval/2, rOval, rOval);
				}
			} 
			if (typeL2.equals("Circle")){ // Firework, type "Circle"
				g.setColor(colorSelected2);
				int r3 = 50;
				int r4 = 70;
				int rOval = 6;
				for (angle2 = 0; angle2 <= 360; angle2 += 20){
					OvalLocationX2 = (int)(locationX2 + Math.sin(Math.toRadians(angle2))*r3);
					OvalLocationY2 = (int)(locationY2 + Math.cos(Math.toRadians(angle2))*r3);
					g.fillOval(OvalLocationX2-rOval/2, OvalLocationY2-rOval/2, rOval, rOval);
				}
				for (angle2 = 0; angle2 <= 360; angle2 += 20){
					OvalLocationX2 = (int)(locationX2 + Math.sin(Math.toRadians(angle2))*r4);
					OvalLocationY2 = (int)(locationY2 + Math.cos(Math.toRadians(angle2))*r4);
					g.fillOval(OvalLocationX2-rOval/2, OvalLocationY2-rOval/2, rOval, rOval);
				}
			} 
			if (typeL.equals("TripleCircle")){ // Firework, type "TripleCircle"
				g.setColor(colorSelected);
				int r3 = 50;
				int r4 = 60;
				int r5 = 70;
				int rOval = 6;
				for (angle2 = 0; angle2 <= 360; angle2 += 20){
					OvalLocationX = (int)(locationX + Math.sin(Math.toRadians(angle2))*r5);
					OvalLocationY = (int)(locationY+40 + Math.cos(Math.toRadians(angle2))*r5);
					g.fillOval(OvalLocationX-rOval/2, OvalLocationY-rOval/2, rOval, rOval);
				}
				for (angle2 = 0; angle2 <= 360; angle2 += 20){
					OvalLocationX = (int)(locationX + Math.sin(Math.toRadians(angle2))*r4);
					OvalLocationY = (int)((locationY+20) + Math.cos(Math.toRadians(angle2))*r4);
					g.fillOval(OvalLocationX-rOval/2, OvalLocationY-rOval/2, rOval, rOval);
				}
				for (angle2 = 0; angle2 <= 360; angle2 += 20){
					OvalLocationX = (int)(locationX + Math.sin(Math.toRadians(angle2))*r3);
					OvalLocationY = (int)(locationY + Math.cos(Math.toRadians(angle2))*r3);
					g.drawLine(locationX, locationY, OvalLocationX, OvalLocationY);
					g.fillOval(OvalLocationX-rOval/2, OvalLocationY-rOval/2, rOval, rOval);
				}
			}
			if (typeL2.equals("TripleCircle")){ // Firework, type "TripleCircle"
				g.setColor(colorSelected2);
				int r3 = 50;
				int r4 = 60;
				int r5 = 70;
				int rOval = 6;
				for (angle2 = 0; angle2 <= 360; angle2 += 20){
					OvalLocationX2 = (int)(locationX2 + Math.sin(Math.toRadians(angle2))*r5);
					OvalLocationY2 = (int)(locationY2+40 + Math.cos(Math.toRadians(angle2))*r5);
					g.fillOval(OvalLocationX2-rOval/2, OvalLocationY2-rOval/2, rOval, rOval);
				}
				for (angle2 = 0; angle2 <= 360; angle2 += 20){
					OvalLocationX2 = (int)(locationX2 + Math.sin(Math.toRadians(angle2))*r4);
					OvalLocationY2 = (int)((locationY2+20) + Math.cos(Math.toRadians(angle2))*r4);
					g.fillOval(OvalLocationX2-rOval/2, OvalLocationY2-rOval/2, rOval, rOval);
				}
				for (angle2 = 0; angle2 <= 360; angle2 += 20){
					OvalLocationX2 = (int)(locationX2 + Math.sin(Math.toRadians(angle2))*r3);
					OvalLocationY2 = (int)(locationY2 + Math.cos(Math.toRadians(angle2))*r3);
					g.drawLine(locationX2, locationY2, OvalLocationX2, OvalLocationY2);
					g.fillOval(OvalLocationX2-rOval/2, OvalLocationY2-rOval/2, rOval, rOval);
				}
			}
			if (typeL.equals("Ferguson")){ // Firework, type "Ferguson"
				g.setColor(colorSelected);
				for (int i = 0; i <= 3; i++){
					int x = random.nextInt(70);
					int y = random.nextInt(70);
					g.drawString("Ferguson!", locationX+x, locationY+y);
				}
				for (int i = 0; i <= 3; i++){
					int x = random.nextInt(70);
					int y = random.nextInt(70);
					g.drawString("Ferguson!", locationX-x, locationY+y);
				}
				for (int i = 0; i <= 3; i++){
					int x = random.nextInt(70);
					int y = random.nextInt(70);
					g.drawString("Ferguson!", locationX+x, locationY-y);
				}
				for (int i = 0; i <= 3; i++){
					int x = random.nextInt(70);
					int y = random.nextInt(70);
					g.drawString("Ferguson!", locationX-x, locationY-y);
				}
			}
			if (typeL2.equals("Ferguson")){ // Firework, type "Ferguson"
				g.setColor(colorSelected2);
				for (int i = 0; i <= 3; i++){
					int x = random.nextInt(70);
					int y = random.nextInt(70);
					g.drawString("Ferguson!", locationX2+x, locationY2+y);
				}
				for (int i = 0; i <= 3; i++){
					int x = random.nextInt(70);
					int y = random.nextInt(70);
					g.drawString("Ferguson!", locationX2-x, locationY2+y);
				}
				for (int i = 0; i <= 3; i++){
					int x = random.nextInt(70);
					int y = random.nextInt(70);
					g.drawString("Ferguson!", locationX2+x, locationY2-y);
				}
				for (int i = 0; i <= 3; i++){
					int x = random.nextInt(70);
					int y = random.nextInt(70);
					g.drawString("Ferguson!", locationX2-x, locationY2-y);
				}
			}
			if (typeL.equals("SmallCircles")){ // Firework, type "SmallCircles"
				g.setColor(colorSelected);
				for (int i = 0; i <= 4; i++){
					int x = random.nextInt(70);
					int y = random.nextInt(70);
					for (int n = 0; n < 3; n++){
						g.drawOval(locationX-(n+1)*3-x, locationY-(n+1)*3-y, (n+1)*6, (n+1)*6);
					}
					g.drawLine(locationX, locationY, locationX-x, locationY-y);
				}
				for (int i = 0; i <= 4; i++){
					int x = random.nextInt(70);
					int y = random.nextInt(70);
					for (int n = 0; n < 3; n++){
						g.drawOval(locationX-(n+1)*3-x, locationY-(n+1)*3+y, (n+1)*6, (n+1)*6);
					}
					g.drawLine(locationX, locationY, locationX-x, locationY+y);
				}
				for (int i = 0; i <= 4; i++){
					int x = random.nextInt(70);
					int y = random.nextInt(70);
					for (int n = 0; n < 3; n++){
						g.drawOval(locationX-(n+1)*3+x, locationY-(n+1)*3-y, (n+1)*6, (n+1)*6);
					}
					g.drawLine(locationX, locationY, locationX+x, locationY-y);
				}
				for (int i = 0; i <= 4; i++){
					int x = random.nextInt(70);
					int y = random.nextInt(70);
					for (int n = 0; n < 3; n++){
						g.drawOval(locationX-(n+1)*3+x, locationY-(n+1)*3+y, (n+1)*6, (n+1)*6);
					}
					g.drawLine(locationX, locationY, locationX+x, locationY+y);
				}
			}
			if (typeL2.equals("SmallCircles")){ // Firework, type "SmallCircles"
				g.setColor(colorSelected2);
				for (int i = 0; i <= 4; i++){
					int x = random.nextInt(70);
					int y = random.nextInt(70);
					for (int n = 0; n < 3; n++){
						g.drawOval(locationX2-(n+1)*3-x, locationY2-(n+1)*3-y, (n+1)*6, (n+1)*6);
					}
					g.drawLine(locationX2, locationY2, locationX2-x, locationY2-y);
				}
				for (int i = 0; i <= 4; i++){
					int x = random.nextInt(70);
					int y = random.nextInt(70);
					for (int n = 0; n < 3; n++){
						g.drawOval(locationX2-(n+1)*3-x, locationY2-(n+1)*3+y, (n+1)*6, (n+1)*6);
					}
					g.drawLine(locationX2, locationY2, locationX2-x, locationY2+y);
				}
				for (int i = 0; i <= 4; i++){
					int x = random.nextInt(70);
					int y = random.nextInt(70);
					for (int n = 0; n < 3; n++){
						g.drawOval(locationX2-(n+1)*3+x, locationY2-(n+1)*3-y, (n+1)*6, (n+1)*6);
					}
					g.drawLine(locationX2, locationY2, locationX2+x, locationY2-y);
				}
				for (int i = 0; i <= 4; i++){
					int x = random.nextInt(70);
					int y = random.nextInt(70);
					for (int n = 0; n < 3; n++){
						g.drawOval(locationX2-(n+1)*3+x, locationY2-(n+1)*3+y, (n+1)*6, (n+1)*6);
					}
					g.drawLine(locationX2, locationY2, locationX2+x, locationY2+y);
				}
			}
			if (typeL.equals("Flower")){ // Firework, type "Flower"
				g.setColor(colorSelected);
				for (angle2 = 0; angle2 <= 360; angle2 += 15){
					int r3 = 100;
					OvalLocationX = (int)(locationX + Math.sin(Math.toRadians(angle2))*r3);
					OvalLocationY = (int)(locationY + Math.cos(Math.toRadians(angle2))*r3+50);
					g.drawLine(locationX, locationY, OvalLocationX, OvalLocationY);
					for (int n = 0; n < 3; n++){
						g.drawOval(OvalLocationX-(n+1)*6, OvalLocationY-(n+1)*6, (n+1)*12, (n+1)*12);
					}
				}
			}
			if (typeL2.equals("Flower")){ // Firework, type "Flower"
				g.setColor(colorSelected2);
				for (angle2 = 0; angle2 <= 360; angle2 += 15){
					int r3 = 100;
					OvalLocationX2 = (int)(locationX2 + Math.sin(Math.toRadians(angle2))*r3);
					OvalLocationY2 = (int)(locationY2 + Math.cos(Math.toRadians(angle2))*r3+50);
					g.drawLine(locationX2, locationY2, OvalLocationX2, OvalLocationY2);
					for (int n = 0; n < 3; n++){
						g.drawOval(OvalLocationX2-(n+1)*6, OvalLocationY2-(n+1)*6, (n+1)*12, (n+1)*12);
					}
				}
			}
			}
			g.setColor(Color.BLACK);
			for (int i = 0; i <= timeL; i++){ // draw the trajectory of the first projectile
				x = (int) (speedL*width/600 * Math.cos(Math.toRadians(angleL))*i);
				y = (int) (speedL*height/600 * Math.sin(Math.toRadians(angleL))*i - 10*Math.pow(i, 2)/2);
				g.fillOval((x-r2/2), height-100-r/2 - (y-r2/2), r2, r2);
			}
			for (int i = 0; i <= timeL2; i++){ // draw the trajectory of the second projectile
				x2 = (int) (speedL2*width/600 * Math.cos(Math.toRadians(angleL2))*i);
				y2 = (int) (speedL2*height/600 * Math.sin(Math.toRadians(angleL2))*i - 10*Math.pow(i, 2)/2);
				g.fillOval(width-(x2-r2/2), height-100-r/2 - (y2-r2/2), r2, r2);
			}
		}
	}
	
	public static void main(String[] args){
		FireWork firework = new FireWork();
		firework.setVisible(true);
		firework.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
