package cell;
import java.awt.Rectangle;

/**
 * This data class is used to store the attributes of the boss of the game.
 * Features of Version 4.0: This class was created to have a class construtor to create the Object, and a getBounds method to check what the perimeter of the Boss is.
 * Time spent on this class: 5 min.
 * @author Salar Hosseini & Sophia Weng
 * @version 5.0, June 12, 2014
 */ 
public class Boss extends Projectile
{
  /**
   * This is the class constructor is used to create this Object and call the super class' construtor to define the data fields that need to be defined in the super class with them. In other words, the x and y position are set for the boss.
   */
  public Boss ()
  {
    super (350, -250);
  }
  
  /**
   * This method is used to return the perimeter of the boss.
   * @return Rectangle The rectangle returned is a representation of an imaginary rectangle around the boss which is used to check for collision.
   */ 
  public Rectangle getBounds ()
  {
    return new Rectangle (getX (), getY (), 179, 172);
  }
}