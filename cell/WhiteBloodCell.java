package cell;
import java.awt.Rectangle;

/**
 * This class is used to create the white blood cell.
 * Features of Version 2.0: This entire class was created.
 * Time spent on this class: 10 minutes.
 * Features of Version 4.0: This class was re-modeled to have only a constructor and an override of the getBounds () method.
 * Time spent on this class: 20 minutes (additive to previous versions).
 * Features of Version 5.0: This class was changed to extend Entity.
 * Time spent on this class: 30 minutes (additive to previous versions).
 * @author Salar Hosseini & Sophia Weng
 * @version 5.0, June 12, 2014
 */ 
public class WhiteBloodCell extends Entity
{
  /**
   * This is the class constructor which will set the x and y positions of the white blood cell.
   */ 
  public WhiteBloodCell ()
  {
    super ((int) (Math.random ()*600)+ 108, -50);
  }
  
  /**
   * This method is used to return the imaginary perimeter of the white blood cell.
   * @return Rectangle The rectangle returned is a representation of an imaginary rectangle around the white blood cell which is used to check for collision.
   */ 
  public Rectangle getBounds ()
  {
    return new Rectangle (getX ()+2, getY ()+2, 52, 52);
  }
}