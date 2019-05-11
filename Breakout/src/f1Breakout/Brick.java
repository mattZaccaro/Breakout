package f1Breakout;

import javax.swing.ImageIcon;

public class Brick extends Sprite {
   
    static String redBrick    = "/res/redBrick.png";
    static String blueBrick   = "/res/blueBrick.png";
    static String greenBrick  = "/res/greenBrick.png";
    static String yellowBrick = "/res/yellowBrick.png";
    static String purpleBrick = "/res/purpleBrick.png";

    
    boolean destroyed;


    public Brick(int x, int y,  String brickColor) {
      this.x = x;
      this.y = y;

      ImageIcon ii = new ImageIcon(this.getClass().getResource(brickColor));
      image = ii.getImage();

      width = image.getWidth(null);
      heigth = image.getHeight(null);

      destroyed = false;
    }

    public boolean isDestroyed()
    {
      return destroyed;
    }

    public void setDestroyed(boolean destroyed)
    {
      this.destroyed = destroyed;
    }

}