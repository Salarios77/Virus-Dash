package game;
import java.awt.Rectangle;

/**
 * This class is used to detect any collisions that occur in the game.
 * Features of Version 3.0:
 * This class was created (the hasCollided method) and it is used to detect collisions.
 * Time spent: 10 min.
 * Features of Version 5.0: 
 * Another method was added to do calculations for movement directions after collisions.
 * Time spent on this class: 35 min.
 * @author Salar Hosseini & Sophia Weng
 * @version 5.0, June 12, 2014
 */ 
public class CollisionDetection
{
  /**
   * This static method is used to return true or false depending on whether or not two rectangles (boundaries of objects) collide.
   * The if statement is used to check if the two rectangles instersect. If so, true is returned. If not false will eventually be returned.
   * @param a This object reference variable stores the first Rectangle object that will be checked to see if it collides with the other.
   * @param b This object reference variable stores the secoond Rectangle object that will be checked to see if it collides with the other.
   * @return boolean (True if rectangles collide, false if they do not).
   */ 
  public static boolean hasCollided (Rectangle a, Rectangle b)
  {
    if (a.intersects (b))
      return true;
    return false;
  }
  
  /**
   * This method is used to get the increment of the x position and y position of any moving object.
   * The first if statement is used to check if the change in x values (the rise) is not 0. If so, then the appropriate direction for the movement is calculated.
   * The second if statement is used to check if the increment in the x variable calculated and stored has the apropriate value of negative or positive. If it does not, that value is multiplyed by -1; 
   * The third if statement is used to check if the increment in the y variable calculated and stored has the apropriate value of negative or positive. If it does not, that value is multiplyed by -1;    
   * @param x1 This double stores the first x position in the calculation of the slope.
   * @param x2 This double stores the second x position in the calculation of the slope.
   * @param y1 This double stores the first y position in the calculation of the slope.
   * @param y2 This double stores the second y position in the calculation of the slope.
   * @param speed This integer stores the speed of the movement that will be calculated.
   * @param moveX This integer stores the run of the two x positions (target and location).
   * @param moveY This integer stores the rise of the two x positions (target and location).
   * @param direction This double stores the direction that the movement being calculated will take place in.
   * @param moveX2 This integer stores the calculated increment in the x position which is used during the movement of whatever Entity uses it.
   * @param moveY2 This integer stores the calculated increment in the y position which is used during the movement of whatever Entity uses it.
   * @param directions This array of integers will store the increment in the x position and the y position which have been calculated.
   * @return int [] The array of integers storing the increment in the x position and the y position which have been calculated, is returned.
   */
  public static int [] getMoveDirections (double x1, double x2, double y1, double y2, int speed)
  {
    int moveX = (int) (x2 - x1); //center 2 minus center 1
    int moveY = (int) (y2 - y1); //center 2 minus center 1
    double direction2 = 0;
    if (moveX != 0)
      direction2 = Math.atan(moveY / moveX);
    int moveX2 = (int) (speed * Math.cos (direction2));
    if ((moveX < 0 && moveX2 >0) || (moveX > 0 && moveX2 < 0))
      moveX2 *= -1;
    int moveY2 = (int) (speed * Math.sin (direction2));
    if ((moveY < 0 && moveY2 >0) || (moveY > 0 && moveY2 < 0))
      moveY2 *= -1;
    int [] directions = {moveX2, moveY2};
    return directions;
  }
}