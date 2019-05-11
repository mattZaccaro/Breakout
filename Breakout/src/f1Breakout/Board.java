package f1Breakout;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import sf.Sound;
import sf.SoundFactory;


@SuppressWarnings("serial")
public class Board extends JPanel implements Commons {
	Image ii;
    Timer timer;
    String message;
    String playerInitials = "MMZ";
    Ball ball;
    Paddle paddle;
    Brick bricks[];
    
    int bricksHit;
    int livesLeft = 1;
    int playerScore;
    int recentScore = 100;
    int timerId;
    int gameState = 1;
    
    public final static String DIR = "src/res/";
    public final static String PADDLE_SOUND = DIR + "paddleSound.wav";
    public final static String WALL_SOUND   = DIR + "wallSound.wav";
    public final static String RESET_SOUND  = DIR + "ballReset.wav";
    public final static String BRICK_SOUND  = DIR + "brickSound.wav";
    public final static String GAME_OVER    = DIR + "gameOver.wav";
    
    Font font = new Font("Verdana", Font.BOLD, 18);
    FontMetrics metr = this.getFontMetrics(font);

    public Board() {

        addKeyListener(new TAdapter());
        setFocusable(true);

        bricks = new Brick[30];
        setDoubleBuffered(true);
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 1000, 10);
    }

        public void addNotify() {
            super.addNotify();
            gameInit();
        }

    public void gameInit() {

        ball = new Ball();
        paddle = new Paddle();      
 

        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
            	
            	switch (i) { 
            	case 0: 
                    bricks[k] = new Brick(j * 80 + 30, i * 20 + 50, Brick.redBrick);
                    break;
                    
            	case 1: 
                    bricks[k] = new Brick(j * 80 + 30, i * 20 + 50, Brick.greenBrick);
                    break;
                    
            	case 2: 
                    bricks[k] = new Brick(j * 80 + 30, i * 20 + 50, Brick.yellowBrick);
                    break;
                    
            	case 3:
                    bricks[k] = new Brick(j * 80 + 30, i * 20 + 50, Brick.purpleBrick);
                    break;
                    
            	case 4:
                    bricks[k] = new Brick(j * 80 + 30, i * 20 + 50, Brick.blueBrick);
                    break;        		
            	}            	
            	
                k++;
            }
        }        
    }


    public void paint(Graphics g) {
    	
        /*
         * gameState 1 = splashScreenk3
         * gameState 2 - inGame
         * gameState 3 = pause*
         * gameState 4 = gameOver
         * gameState 5 = Victory
         */
    	
        super.paint(g);
        
        if (gameState == 1) {
            g.setColor(Color.BLACK);
            g.setFont(font);
            
            message = "Welcome to Breakout!";
            g.drawString(message,
                    (Commons.WIDTH - metr.stringWidth(message)) / 2,
                    Commons.WIDTH / 3);
            
            message = "Use the Arrow Keys to Move";
            g.drawString(message,
                         (Commons.WIDTH - metr.stringWidth(message)) / 2,
                         Commons.WIDTH / 2 + 50);
            
            message = "Press Enter to Start!";
            g.drawString(message,
                         (Commons.WIDTH - metr.stringWidth(message)) / 2,
                         Commons.WIDTH / 2 + 100);
        }

        if (gameState == 2) {       	        	
            g.drawImage(ball.getImage(), ball.getX(), ball.getY(),
                        ball.getWidth(), ball.getHeight(), this);
            g.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(),
                        paddle.getWidth(), paddle.getHeight(), this);

            for (int i = 0; i < 30; i++) {
                if (!bricks[i].isDestroyed())
                    g.drawImage(bricks[i].getImage(), bricks[i].getX(),
                                bricks[i].getY(), bricks[i].getWidth(),
                                bricks[i].getHeight(), this);
                
                g.setColor(Color.BLACK);
                g.drawString("Recent Score: " + playerInitials + " " + recentScore, 5, 15);
                g.drawString("Lives Left: " + livesLeft, 470, 15);
                
                message = "Your Score: " + playerScore;
                g.drawString(message,
                             (Commons.WIDTH - metr.stringWidth(message)) / 2 + 25, 15);
                         
            }
        }
        
        if (gameState == 3) {
            g.setColor(Color.BLACK);
            g.setFont(font);
            
            message = "Game Paused!";
            g.drawString(message,
                    (Commons.WIDTH - metr.stringWidth(message)) / 2,
                    Commons.WIDTH / 3);
            
            message = "Press Enter to Continue!";
            g.drawString(message,
                         (Commons.WIDTH - metr.stringWidth(message)) / 2,
                         Commons.WIDTH / 2 + 50);
        	
        }
        
        if (gameState == 4) {
        	
//        	Sound gameOver = SoundFactory.getInstance(GAME_OVER);
//        	SoundFactory.play(gameOver);

            g.setColor(Color.BLACK);
            g.setFont(font);
            
            message = "Game Over!";
            g.drawString(message,
                         (Commons.WIDTH - metr.stringWidth(message)) / 2,
                         Commons.WIDTH / 3);
            
            message = "Your Score: " + playerScore;
            g.drawString(message,
                         (Commons.WIDTH - metr.stringWidth(message)) / 2,
                         Commons.WIDTH / 2 + 50);
            
            message = "Press Enter to Restart";
            g.drawString(message,
                         (Commons.WIDTH - metr.stringWidth(message)) / 2,
                         Commons.WIDTH / 2 + 100);           

            
        }
        
        if (gameState == 5) {
            g.setColor(Color.BLACK);
            g.setFont(font);
            
            message = "You Won!";
            g.drawString(message,
                         (Commons.WIDTH - metr.stringWidth(message)) / 2,
                         Commons.HEIGHT / 3);
            
            message = "Your Score: " + playerScore;
            g.drawString(message,
                         (Commons.WIDTH - metr.stringWidth(message)) / 2,
                         Commons.WIDTH / 2 + 50);
            
            message = "Press Enter to Restart";
            g.drawString(message,
                         (Commons.WIDTH - metr.stringWidth(message)) / 2,
                         Commons.WIDTH / 2 + 100);
        	
        }


        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    public void savePlayerScore(String message) {
    	
        JFrame f = new JFrame(); 	
       	String initials = JOptionPane.showInputDialog(f, message);
       	int input = JOptionPane.OK_OPTION;
		playerInitials = initials;
		recentScore  = playerScore;

		if (input == JOptionPane.OK_OPTION) {
			HighScores highscores = new HighScores("F:\\eclipse-workspace\\Breakout Plus\\src\\res\\highscores.txt");
			ArrayList<Record> records = highscores.load();
			
			records.clear();
			
			
			
			int scores[] = {100, 45, 105, 135, 50};
			String names[] = {"RGC", "JRR", "LRZ", "MMZ", "HAV"};
			
			for(int k = 0; k < names.length; k++) {
				records.add(new Record(names[k], scores[k]));
			}
			
			int count = highscores.save();
			
			System.out.println("Save");
			System.out.println(highscores);
			System.out.println(count + " records");
			
			String allSaved = count == records.size() ? "YES" : "no";
			System.out.println("all saved: " + allSaved);
			
		}
    }

    private class TAdapter extends KeyAdapter {   
    	
        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e); 
            
            int key = e.getExtendedKeyCode();
            
            if (gameState == 1) {
            	if (key == KeyEvent.VK_ENTER) {
            		gameState = 2;
            	}
                    
            }
            if (gameState == 2) {
            	if (key == KeyEvent.VK_ESCAPE) {
            		gameState = 3;
            	}
            }
            if (gameState == 3) {
            	if (key == KeyEvent.VK_ENTER) {
            		gameState = 2;
            	}
            }
            if (gameState == 4 | gameState == 5) {
            	if (key == KeyEvent.VK_ENTER) {   
            		gameState = 1;
                    livesLeft = 3;
                    gameInit();   
                    repaint();
                    savePlayerScore("Enter Your 3 Letter Initials");
                    System.out.println("restart");
                    
            	}

            }
                                           
        }  

        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);           
                        
        }
        
    }

    class ScheduleTask extends TimerTask {

        public void run() {
         	if (gameState == 2)
        		ball.move();
         	    paddle.move();
                checkCollision();
                repaint();     	
                
        }
    }

    public void checkCollision() {
    	

        if (ball.getRect().getMaxY() > Commons.BOTTOM) {
        	if (livesLeft > 1) {
        		
        		Sound ballReset = SoundFactory.getInstance(RESET_SOUND);
        		SoundFactory.play(ballReset);
        		
        		livesLeft -= 1;
            	paddle.resetState();
            	ball.resetState();   
        	}
        	else 
        		gameState = 4;        	        	             
        }
        

        for (int i = 0, j = 0, s = 0; i < 30; i++) {
            if (bricks[i].isDestroyed()) {                      	
                j++;
                s += 5;
                bricksHit = j;
                playerScore = s;

            }
            if (j == 30) {
                gameState = 5;

            }
        }

        if ((ball.getRect()).intersects(paddle.getRect())) {
        	
        	Sound sound = SoundFactory.getInstance(PADDLE_SOUND);
        	SoundFactory.play(sound);

            int paddleLPos = (int)paddle.getRect().getMinX();
            int ballLPos = (int)ball.getRect().getMinX();

            int first = paddleLPos + 8;
            int second = paddleLPos + 16;
            int third = paddleLPos + 24;
            int fourth = paddleLPos + 32;

            if (ballLPos < first) {
                ball.setXDir(-1);
                ball.setYDir(-1);
            }

            if (ballLPos >= first && ballLPos < second) {
                ball.setXDir(-1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos >= second && ballLPos < third) {
                ball.setXDir(0);
                ball.setYDir(-1);
            }

            if (ballLPos >= third && ballLPos < fourth) {
                ball.setXDir(1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos > fourth) {
                ball.setXDir(1);
                ball.setYDir(-1);
            }         

        }


        for (int i = 0; i < 30; i++) {
            if ((ball.getRect()).intersects(bricks[i].getRect())) {

                int ballLeft = (int)ball.getRect().getMinX();
                int ballHeight = (int)ball.getRect().getHeight();
                int ballWidth = (int)ball.getRect().getWidth();
                int ballTop = (int)ball.getRect().getMinY();

                Point pointRight =
                    new Point(ballLeft + ballWidth + 1, ballTop);
                Point pointLeft = new Point(ballLeft - 1, ballTop);
                Point pointTop = new Point(ballLeft, ballTop - 1);
                Point pointBottom =
                    new Point(ballLeft, ballTop + ballHeight + 1);
                	

                if (!bricks[i].isDestroyed()) {
                    if (bricks[i].getRect().contains(pointRight)) {
                        ball.setXDir(-1);
                    }

                    else if (bricks[i].getRect().contains(pointLeft)) {
                        ball.setXDir(1);
                    }

                    if (bricks[i].getRect().contains(pointTop)) {
                        ball.setYDir(1);
                    }

                    else if (bricks[i].getRect().contains(pointBottom)) {
                        ball.setYDir(-1);
                    }

                    bricks[i].setDestroyed(true);
                    
                    Sound brickSound = SoundFactory.getInstance(BRICK_SOUND);
                    SoundFactory.play(brickSound);
                }
            }
        }
    }
}

