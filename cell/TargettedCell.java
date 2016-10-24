package cell;
import java.awt.Rectangle;

/**
 * This class is used to create the cell that will be targetted by the pathogen.
 * Features of Version 3.0: This class was created (just the constructor).
 * Time spent: 5 min.
 * @author Salar Hosseini & Sophia Weng
 * @version 5.0, June 12, 2014
 */ 
public class TargettedCell extends Cell
{
  /**
   * This is the constructor which will call the super classes' constructor to assign the appropriate values to its parameters.
   */ 
  public TargettedCell ()
  {
    super (91, 400, -350);
  }
  
  /**
   * This method is used to return the imaginary perimeter of the target.
   * @return Rectangle The Rectangle object returned stores the boundaries of the target.
   */ 
  public Rectangle getBounds ()
  {
    return new Rectangle (getX (), getY (), 91, 85);
  }
}