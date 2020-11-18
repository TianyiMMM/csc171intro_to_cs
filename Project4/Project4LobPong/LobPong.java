import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/* tma8
 * project4
 * MW 200-315
 * I did not collaborate with anyone on this assignment.
 */
public class LobPong extends JFrame implements KeyListener, MouseListener{
	
	// variables that varied each turns
	protected int livesCount = 3;
	protected double turnCount = 0;
	protected int failCount = 0;
	protected int score = 0;
	protected String scoreC = "";
	protected double timeCount = 15;
	protected double timeCount2 = timeCount;
	protected String timeC = "";
	protected int count = 0;
	protected double timeElapsed = 0;
	protected int timeRate = 15;
	protected String win = "Lop Pong Game!";
	protected String finalScore = "";
	protected int xRect;
	protected int yRect;
	protected int velocityX;
	protected int velocityY;
	protected int v00 = 100;
	protected int v0X = v00;
	protected int v0Y = v00;
	protected int xOval;
	protected int yOval;
	protected int angle = 70;
	
	// Components
	protected JPanel upperBar = new JPanel();
	protected Canvas canvas = new Canvas();
	protected Timer timer;

	// Images: shapes
	protected Image imgBackGround;
	protected Image imgPaddle;
	protected Image imgDoodle;
	protected Image imgDoodleR;
	protected Image imgDoodleL;
	protected Image imgDoodleJR;
	protected Image imgDoodleJL;
	protected Image imgGreenMonster;
	protected Image imgPlate;
	
	// Images: buttons
	protected Image imgPlay;
	protected Image imgPlayOn;
	protected Image imgRestart;
	protected Image imgRestartOn;
	protected Image imgMusicOff;
	protected Image imgMusicOn;
	
	public LobPong(){
		// set size and title, add event listeners and set layout
		setTitle("Lob Pong Game!");
		setSize(530, 850);
		addKeyListener(this);
		addMouseListener(this);
		add(canvas, BorderLayout.CENTER);
		// set images
		try {
			imgBackGround = ImageIO.read(new File("pictures/background.png"));
			imgPaddle = ImageIO.read(new File("pictures/yellow_platform.png"));
			imgPlate = ImageIO.read(new File("pictures/green_platform.png"));
			imgDoodleR = ImageIO.read(new File("pictures/doodle_crop.png"));
			imgDoodleL = ImageIO.read(new File("pictures/doodle_left.png"));
			imgDoodleJR = ImageIO.read(new File("pictures/doodle_right_crouch.png"));
			imgDoodleJL = ImageIO.read(new File("pictures/doodle_left_crouch.png"));
			imgGreenMonster = ImageIO.read(new File("pictures/blue_monster_left.png"));
			imgGreenMonster1 = imgGreenMonster;
			imgPlay = ImageIO.read(new File("pictures/play2.png"));
			imgPlay1 = imgPlay;
			imgPlayOn = ImageIO.read(new File("pictures/play-on2.png"));
			imgRestart = ImageIO.read(new File("pictures/restart.png"));
			imgRestartOn = ImageIO.read(new File("pictures/restart-on.png"));
			imgMusicOff = ImageIO.read(new File("pictures/music-off.png"));
			imgMusicOn = ImageIO.read(new File("pictures/music-on.png"));
			imgMusic1 = imgMusicOff;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// images that store the state(hidden/appear) of each images
	protected Image imgDoodle1;
	protected Image imgDoodle2;
	protected Image imgDoodle3;
	protected Image imgPlay1;
	protected Image imgPaddle1;
	protected Image imgMusic1;
	protected Image imgGreenMonster1;
	protected Image imgPlate1;
	protected Image imgPlate2;
	
	// x's and y's, height's and width's, texts
	protected int rectHeight = 15;
	protected int rectWidth = 125;
	protected int ovalRadius = 25;
	protected Color color;
	protected int playX;
	protected int playY;
	protected int monsterX = 75;
	protected int monsterY = 500;
	protected int monsterWidth = 95;
	protected int monsterHeight = 110;
	protected int plate1X = 380;
	protected int plate1Y = 250;
	protected int plate2X = 200;
	protected int plate2Y = 350;
	protected int musicY;
	protected int imgWidth = 150;
    protected int imgHeight = 55;
    protected String lives = "";
    protected String times = "";
    protected String scores = "";
    protected String finalScores = "";
    protected String fullScore = "";
	
	public class Canvas extends JPanel{
		@Override
	    public void paintComponent(Graphics g) {
			//  get the width and height of the canvas
			int height = getHeight();
			int width = getWidth();
			// draw the background and several blocks
			super.paintComponent(g);
			g.drawImage(imgBackGround, 0, 0, width, height, this);
			g.drawImage(imgGreenMonster1, monsterX, monsterY, monsterWidth, monsterHeight, this);
			g.drawImage(imgPlate1, plate1X, plate1Y, rectWidth-10, rectHeight, this);
			g.drawImage(imgPlate2, plate2X, plate2Y, rectWidth-10, rectHeight, this);
			
			// draw the paddle and the doodle
	        g.drawLine(0, 0, width, 0);
	        g.setColor(Color.BLACK);
	        int x = width/2-rectWidth/2;
	        int y = height-50-rectHeight/2;
	        if (count==0){
	        	xRect = x;
	        	yRect = y;
	        	xOval = x-ovalRadius+rectWidth/2;
	        	yOval = y-2*ovalRadius+7;
	        }
	        playX = (width-imgWidth)/2;
	        playY = (height-imgHeight)/2;
	        musicY = playY+imgHeight+30;
	        g.drawImage(imgPaddle1, xRect, yRect, rectWidth, rectHeight, this);
	        g.drawImage(imgDoodle, xOval, yOval, 2*ovalRadius, 2*ovalRadius, this);
	        // draw the buttons
	        g.drawImage(imgPlay1, playX, playY, imgWidth, imgHeight, this);
	        g.drawImage(imgMusic1, playX, musicY, imgWidth, imgHeight, this);
	        // draw the title (welcome/game over/congratulations)
	        g.setFont(new Font("Off Kilter", Font.PLAIN, 32));
	        int widthWin = g.getFontMetrics().stringWidth(win);
	        g.drawString(win, (width-widthWin)/2, height/2-140);
	        // draw the lives count, time count and score count
	        g.setFont(new Font("Off Kilter", Font.PLAIN, 18));
	        int widthLives = g.getFontMetrics().stringWidth("Lives: ");
	        g.drawString(lives, 5, 25);
	        g.drawImage(imgDoodle1, 5+widthLives, 5, 30, 30, this);
	        g.drawImage(imgDoodle2, 5+widthLives+27, 5, 30, 30, this);
	        g.drawImage(imgDoodle3, 5+widthLives+27+27, 5, 30, 30, this);
	        g.drawString(scores + scoreC, 5+widthLives+27+27+27+60, 25);
	        g.drawString(times + timeC, 400, 25);
	        // draw the final score
	        int widthScore = g.getFontMetrics().stringWidth(finalScores + finalScore + fullScore);
	        g.drawString(finalScores + finalScore + fullScore, (width-widthScore)/2, height/2-75);
	    }
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// if it is the beginning of a turn, start a timer
		if (count == 0){
			timer = new Timer(timeRate, new TimerCallback()); // 100 ms = 0.1 sec
			timer.start();
		} 
		// if the user type the key that moves the paddle, move the paddle towards the corresponding direction
		switch(e.getKeyCode()){
		case 37: //left
			if (xRect+ovalRadius >= 0){
				xRect -= 40;
			}
		break;
		case 39: //right
			if (xRect+ovalRadius+rectWidth <= getWidth()){
				xRect += 40;
			}
		break;
		}
		repaint();
	}
	protected int keyCode = 0;
	@Override
	public void keyPressed(KeyEvent e) {
		// if it is the beginning of a turn, start a timer
		if (count == 0){
			timer = new Timer(timeRate, new TimerCallback()); // 100 ms = 0.1 sec
			timer.start();
		}
		// if the user type the key that moves the paddle, move the paddle towards the corresponding direction
		switch(e.getKeyCode()){
		case 37: //left
			if (xRect-ovalRadius >= 0){
				xRect -= 40;
			}
		break;
		case 39: //right
			if (xRect+ovalRadius+rectWidth <= getWidth()){
				xRect += 40;
			}
		break;
		}
		repaint();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	// check if the doodle is travel right or left
	protected String xCheck = "TravelRight";
	protected String xCheck1 = xCheck;
protected class TimerCallback implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			// start the timer
			count++;
			timeCount -= (double)timeRate/1000; // count the time remained
			double timeUnit = 0.12; 
			timeElapsed += timeUnit; // calculate the time elapsed
			timeC = new DecimalFormat("##.#").format(timeCount); // change the time display on the screen
			if (timeCount >= -0.01 && timeCount <= 0.01){ // check if the time is out
				int height = getHeight(); // if the time is out, start another turn, initialize variables
				int width = getWidth();
				int x = width/2-rectWidth/2;
		        int y = height-50-rectHeight/2;
		        count = 0;
		        velocityX=0;
		        velocityY=0;
		        timeElapsed = 0;
				timeCount2 += 5;
				timeCount = timeCount2;
				timeC = new DecimalFormat("##.#").format(timeCount);
				score += 3;
				scoreC = Integer.toString(score);
				turnCount++;
				imgDoodle = imgDoodleR;
				if (turnCount == 1){ // if it is the second turn, change the v0 and angle
					v00 += 10;
		        	v0X = v00;
		        	v0Y = v00;
		        	angle = 80;
		        	xCheck = "TravelLeft";
		        	xCheck1 = xCheck;
		        	imgPlate1 = imgPlate;
					imgPlate2 = imgPlate;
		        } else if (turnCount == 2){ // if it is the third turn, change the v0 and angle
		        	v00 += 15;
		        	v0X = v00;
		        	v0Y = v00;
		        	angle = 72;
		        	xCheck = "TravelRight";
		        	plate1X = 75;
		        	plate1Y = 225;
		        	plate2X = 260;
		        	plate2Y = 400;
		        	xCheck1 = xCheck;
		        } else if (turnCount >= 3){ // if the user finished all three turns, end the game and prompt the user
					count = 1;
					xRect = height+100000;
				    yRect = height+100000;
				    xOval = x+100000;
				    yOval = y+100000;
				    timeC = "0.0";
					win = "Congradulation! You win!";
					finalScores = "Final Score: ";
					finalScore = scoreC;
					fullScore = "/9";
					imgPlay1 = imgRestart;
					imgPlate1 = null;
					imgPlate2 = null;
					repaint();
		        }
				repaint();
				timer.stop();
			} else {
			// check if doodle lands on the paddle
			if (xOval >= xRect-ovalRadius-3 && xOval <= xRect+rectWidth+ovalRadius && yOval+2*ovalRadius >= yRect+10 && yOval+2*ovalRadius <= yRect+rectHeight+15){
				// if it lands on the paddle
				playSound("sounds/jump_sound.wav"); // play the corresponding sound effects
				if (xCheck.equals("TravelLeft")){ // make the doodle crouch
					imgDoodle = imgDoodleJL; 
				} else {
					imgDoodle = imgDoodleJR;
				}
				timeElapsed = 0; // change the direction of the jumping doodle
				v0Y = -velocityY;
			// if it doesn't land on the paddle, check if it hits any block
			} else if (velocityY<=0 && xOval >= monsterX && xOval <= monsterX+monsterWidth && yOval+2*ovalRadius >= monsterY && yOval+2*ovalRadius <= monsterY+10){
				playSound("sounds/jump2.wav"); // if it hit on the monster, play corresponding sound effects
				if (xCheck.equals("TravelLeft")){ // make the doodle crouch
					imgDoodle = imgDoodleJL;
				} else {
					imgDoodle = imgDoodleJR;
				}
				timeElapsed = 0; // change the direction of the jumping doodle
				v0Y = -velocityY;
			} else if ((turnCount >= 1 && velocityY<=0 && xOval >= plate1X-ovalRadius && xOval <= plate1X+rectWidth-ovalRadius && yOval+2*ovalRadius >= plate1Y+5 && yOval+2*ovalRadius <= plate1Y+rectHeight)
					|| (turnCount >= 1 && velocityY<=0 && xOval >= plate2X-ovalRadius && xOval <= plate2X+rectWidth-ovalRadius && yOval+2*ovalRadius >= plate2Y+5 && yOval+2*ovalRadius <= plate2Y+rectHeight)){
				playSound("sounds/jump_sound.wav"); // if it hits on other plates, play corresponding sound effects
				if (xCheck.equals("TravelLeft")){ // make the doodle crouch
					imgDoodle = imgDoodleJL;
					v0X += 10;
				} else {
					imgDoodle = imgDoodleJR;
					v0X -= 10;
				}
				timeElapsed = 0; // reverse the vertical jumping direction
				v0Y = -velocityY-10;
			} else {
			// if not, check if it hits the side or the top or the bottom
			if (xOval+ovalRadius >= getWidth()){ // if it hits the wall, reverse the horizontal jumping direction
					xCheck = "TravelLeft";
				} else if (xOval <= 0){
					xCheck = "TravelRight";
				}
				// if it hit the bottom, the user will lose one live and start over again
				if (yOval-3*ovalRadius >= canvas.getHeight()){
					// initialize variables
					int height = getHeight();
					int width = getWidth();
					int x = width/2-rectWidth/2;
			        int y = height-50-rectHeight/2;
			        count = 0;
			        livesCount--;
			        score -= 1;
			        scoreC = Integer.toString(score);
			        imgDoodle = imgDoodleR;
			        if (livesCount == 0){
						count = 1;
						xRect = height+100000;
					    yRect = height+100000;
					    xOval = x+100000;
					    yOval = y+100000;
					    timeCount = 0;
					    imgDoodle1 = null;
						win = "Game Over";
						finalScores = "Final Score: ";
						finalScore = scoreC;
						fullScore = "/9";
						imgPlay1 = imgRestart;
						repaint();
						timer.stop();
					} else if (livesCount == 1){
						imgDoodle2 = null;
					} else if (livesCount == 2){ // delete a live
						imgDoodle3 = null;
					} 
			        timeCount=timeCount2;
			        timeC = new DecimalFormat("##.#").format(timeCount);
			        v0X = v00;
		        	v0Y = v00;
			        velocityX=0;
			        velocityY=0;
			        timeElapsed = 0;
			        xCheck = xCheck1;
			        repaint();
			        timer.stop();
				} else if (yOval <= 0){
					v0Y = velocityY;
				}
			}
			// calculate the unit vertical/horizontal distance the doodle traveled
			if (xCheck.equals("TravelLeft")){
				velocityX = -(int) (v0X*Math.cos(Math.toRadians(angle)));
				int xTraveled = (int)(velocityX*timeUnit);
				xOval = xOval + xTraveled;
				imgDoodle = imgDoodleL;
			} else {
				velocityX = (int) (v0X*Math.cos(Math.toRadians(angle)));
				int xTraveled = (int)(velocityX*timeUnit);
				xOval = xOval + xTraveled;
				imgDoodle = imgDoodleR;
			}
			velocityY = (int) (v0Y*Math.sin(Math.toRadians(angle))-10*timeElapsed);
			int yTraveled = (int)(velocityY*timeUnit);
			yOval = yOval - yTraveled;
			repaint();
		}
		}
	}
	// playSound method
	protected Clip clip;
	public void playSound(String fileName){
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(fileName).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(Exception e) {
			System.out.println("Error.");
			e.printStackTrace();
    	}
	}
	// stopSound method
	public void stopSound(){
		try {
			clip.stop();
		} catch(Exception e) {
			System.out.println("Error.");
			e.printStackTrace();
    	}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	// when the user clicks/presses and releases the button, executes corresponding functions
	@Override
	public void mousePressed(MouseEvent e) {
		// display the pressed(darkened) version of the button when pressed
		if (e.getX() >= playX && e.getX() <= playX+imgWidth && e.getY() >= playY && e.getY() <= playY+imgHeight){
			if (imgPlay1 == imgPlay){ // play button
				imgPlay1 = imgPlayOn;
			} else if (imgPlay1 == imgRestart){ // restart button
				imgPlay1 = imgRestartOn;
			}
			repaint();
		}
		if (e.getX() >= playX && e.getX() <= playX+imgWidth && e.getY() >= musicY && e.getY() <= musicY+imgHeight){
			if (imgMusic1 == imgMusicOff){ // music: on button
				imgMusic1 = imgMusicOn;
				playSound("sounds/OMFG-Hello.wav");
			} else if (imgMusic1 == imgMusicOn){ // music: off button
				imgMusic1 = imgMusicOff;
				stopSound();
			}
			repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getX() >= playX && e.getX() <= playX+imgWidth && e.getY() >= playY && e.getY() <= playY+imgHeight){
			// when the user clicks/pressed and releases the "play"/"restart" button, initialize all values
			imgPlay1 = null;
			imgMusic1 = null;
			imgDoodle1 = imgDoodleR;
			imgDoodle2 = imgDoodleR;
			imgDoodle3 = imgDoodleR;
			imgPaddle1 = imgPaddle;
			imgDoodle = imgDoodleR;
			timeC = "15.0";
			score = 0;
			scoreC = Integer.toString(score);
			lives = "Lives: ";
			times = "Time: ";
			scores = "Score: ";
			win = "";
			finalScores = "";
			finalScore = "";
		    fullScore = "";
			int height = getHeight();
			int width = getWidth();
			int x = width/2-rectWidth/2;
	        int y = height-50-rectHeight/2;
			xRect = x;
        	yRect = y;
        	xOval = x-ovalRadius+rectWidth/2;
        	yOval = y-2*ovalRadius+7;
        	livesCount = 3;
        	v00 = 100;
        	v0X = v00;
        	v0Y = v00;
        	timeCount = 15;
        	timeCount2 = timeCount;
        	timeC = "15.0";
        	timeElapsed = 0;
        	count = 0;
        	turnCount = 0;
			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	// main method
	public static void main(String[] args){
		LobPong lobPong = new LobPong();
		lobPong.setVisible(true);
		lobPong.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

