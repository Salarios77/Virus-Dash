package cell;
import java.awt.Rectangle;

/**
 * This is a data class for any entity in the game. 
 * Features of Version 4.0:
 * This entire class was created to store the x and y locations of any entity in the game.
 * Time spent on this class: 10 min.
 * @author Salar Hosseini & Sophia Weng
 * @version 5.0, June 12, 2014
 */ 
public class Entity 
{
  /**
   * This integer stores the x position of the entity.
   */ 
  private int x;
  
  /**
   * This integer stores the y position of the entity.
   */ 
  private int y;
  
  /**
   * This is the class constructor which assigns values to the data fields in Entity (the x and y positions of the Entity).
   * @param x This integer is used to store the x position of the Entity.
   * @param y This integer is used to store the y position of the Entity.
   */ 
  public Entity (int x, int y)
  {
    this.x = x;
    this.y = y;
  }
  
  /**
   * This accessor method returns the x position of the entity.
   * @return int (The x position).
   */ 
  public int getX ()
  {
    return x;
  }
  
  /**
   * This accessor method returns the y position of the entity.
   * @return int (The y position).
   */ 
  public int getY ()
  {
    return y;
  }
  
  /**
   * This mutator method sets the x position of the entity.
   * @param x2 This integer stores the new x position of the entity.
   */ 
  public void setX (int x2)
  {
    x = x2;
  }
  
  /**
   * This mutator method sets the y position of the entity.
   * @param y2 This integer stores the new y position of the entity.
   */ 
  public void setY (int y2)
  {
    y = y2;
  }
  
  /**
   * This method returns the boundaries of the entity.
   * @return Rectangle (The rectangle surrounding the cell).
   */ 
  public Rectangle getBounds ()
  {
    return new Rectangle (x, y, 47, 43);
  }
}