package f1Breakout;

import javax.swing.ImageIcon;

import sf.Sound;
import sf.SoundFactory;


public class Ball extends Sprite implements Commons {

   private int xdir;
   private int ydir;
   
   public final static String DIR = "src/res/";
   public final static String WALL_SOUND = DIR + "wallSound.wav";

   protected String ball = "/res/ball.png";

   public Ball() {

     xdir = 1;
     ydir = -1;

     ImageIcon ii = new ImageIcon(this.getClass().getResource(ball));
     image = ii.getImage();

     width = image.getWidth(null);
     heigth = image.getHeight(null);

     resetState();
    }


    public void move()
    {
    	
     Sound sound = SoundFactory.getInstance(WALL_SOUND);
    	
      x += xdir;
      y += ydir;

      if (x == 0) {
        setXDir(1);
        SoundFactory.play(sound);
      }

      if (x == BALL_RIGHT) {
        setXDir(-1);
        SoundFactory.play(sound);
      }

      if (y == 0) {
        setYDir(1);
        SoundFactory.play(sound);
      }
    }
    
    public void stop() {
    	setXDir(0);
    	setYDir(0);
    }

    public void resetState() 
    {
      x = 260;
      y = 490;
    }

    public void setXDir(int x)
    {
      xdir = x;
    }

    public void setYDir(int y)
    {
      ydir = y;
    }

    public int getYDir()
    {
      return ydir;
    }
}