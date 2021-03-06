package levels;
import cell.*;
import java.util.ArrayList;
import game.*;

/**
 * This class is used to draw the third level that the user may choose to play on.
 * Features of Version 2.0:
 * This class was created, but it is only set up to be coded, it does not do anything yet.
 * Time spent on this class: 10 min.
 * Features of Version 3.0:
 * This class was re-modeled since the super class was changed. The addAntibody () method was added to override the super classes's method.
 * Time spent on this class: 20 min (additive to previous versions).
 * Features of Version 4.0:
 * This class was re-modeled for the last time to make the guards of the parasite correctly display and function. The update () method was fixed.
 * Time spent on this class: 1 hour 15 min (additive to previous versions).
 * @author Salar Hosseini & Sophia Weng
 * @version 5.0, June 12, 2014
 */ 
public class Level3 extends Level
{
  
  /**
   * This is the class constructor which will assign JPanel object to the instance variable.
   * @param panel This object reference variable to JPanel stores the JPanel that the level will be drawn on.
   * @param pathogen This object reference variable to pathogen is used to store the Pathogen Object controlled by the user.
   */ 
  public Level3( Pathogen pathogen, GameInfo info)
  {
    super ("Parasite", pathogen, info);
  }
  
  /**
   * This method is used to update the level along with the pathogen's guards.
   * The first if statement is used to check if a guard can be spawned for the parasite. If so, then a guard is activated for it.
   * The first for loop is used to go through the list of the parasite's guards and update their positions and check for any collisions.
   * The next if statement (nested in first for loop) is used to check if the guard being checked in the for loop iteration is active (visible). If it is, then its position is updated.
   * The next if statement (nested in previous if statement) is used to check if the position of the x pathogen is in the x movement range. If it is, then the guard's x position is updated.
   * The next if statement (same nested level as previous if statement) is used to check if the y  position of the pathogen is in the y movement range. If it is, then the guard's y position is updated.
   * The next if statement (if (!canInfectTarget ()) - let this be called the can infect if statement) is used to check if the user is not against the boss. If so, then collisions are checked for between guards and antibodies. Else, collisions are checked for between the pathogen against the boss/ target.
   * The next for loop (within the can infect if statement) is used to move through the list of cells and check if there are any collisions with the current guard being examined.
   * The next if statement (within second for loop) is used to check if a collision has occured between a cell and a guard. If it has, then the cell and the guard both "die".
   * The next if statement within the else of the can infect if statement is used to check if there are any collisions between the guards and the boss. If so, the guard is removed and the boss is moved slightly away from the pathogen.
   * The second if statement within the else of the can infect if statement is used to check if there are any collisions between the target and the guards of the parasite. If so, the target is damaged, and the guard is removed.
   * @param cells This object reference variable to ArrayList is used to store the antibodies that are falling.
   * @param guards This object reference variable to ArrayList is used to store the guards that are protecting the pathogen the user is controlling.
   * @param guard This object reference variable to Entity is used to store the guard that is getting updated.
   * @param x This integer is used to go though all the guards of the pathogen (so that all their locations get updated)
   * @param c This integer is used to go through all the antibodies that are falling, to see if the guards of the pathogen have collided with any of them.
   */ 
  public void update ()
  {
    super.update ();
    if (((Parasite) (getPathogen ())).canDuplicate ())
      ((Parasite) (getPathogen())).insertBodyguard ();
    
    
    //checking parasites for collision
    ArrayList <Entity> cells = getCells ();
    ArrayList <Entity> guards = ((Parasite) getPathogen ()).getParasites ();
    Entity guard;
    for (int x = 0; x< guards.size (); x++)
    {
      guard = guards.get (x);
      //updating position of guards
      if (guard.getX () != -1)
      {
        if (getPathogen ().getX () < 775 - getPathogen ().getBounds().getWidth () && getPathogen ().getX () > 108)
          guard.setX (guard.getX () + getPathogen ().getXVel ());
        if (getPathogen ().getY () > 0 && getPathogen ().getY () < 500)
          guard.setY (guard.getY () + getPathogen ().getYVel ());
      }
      //updating collisions
      if (!canInfectTarget ())
      {
        for (int c = 0; c<cells.size (); c++)
        {
          if (CollisionDetection.hasCollided (cells.get (c).getBounds (), guard.getBounds ()))
          {
            cells.remove (c);
            guards.set (x, new Entity (-1, -1));
            break;
          }
        }
      }
      else
      {
        int[] directions = CollisionDetection.getMoveDirections (boss.getX (), guard.getBounds (). getCenterX (), boss.getY (), guard.getBounds (). getCenterY (), 2); 
        if (CollisionDetection.hasCollided (guard.getBounds (), getBoss ().getBounds ()))
        {
          guards.set (x, new Entity (-1, -1));
          setPosition (directions, 3, null);
        }
        
        if (CollisionDetection.hasCollided (cells.get (0).getBounds (), guard.getBounds ()))
        {
          damageTarget ();
          guards.set (x, new Entity (-1, -1));
        }
      }
    }
  } 
}