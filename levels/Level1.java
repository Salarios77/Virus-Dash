package levels;
import cell.*;
import game.GameInfo;

/**
 * This class is used to draw the first level that the user may choose to play on.
 * Features of Version 2.0:
 * This class was created, but it is only set up to be coded, it does not do anything yet.
 * Time spent on this class: 10 min.
 * Features of Version 3.0:
 * This class was re-modeled since the super class was changed. The addAntibody () method was added to override the super classes's method.
 * Time spent on this class: 20 min (additive to previous versions).
 * Features of Version 4.0: This class was again re-modeled but for the final time. The level now does what it is supposed to do. The update method is overriden to be specific to the Virus's abilities and a data field along with get and set methods was added to keep track of if the virus's immunity has increased or not.
 * Time spent on this class: 45 min (additive to previous versions).
 * @author Salar Hosseini & Sophia Weng
 * @version 5.0, June 12, 2014
 */ 
public class Level1 extends Level 
{
  /**
   * This boolean is used to store true or false depending on if the virus' immunity has increased or not.
   */ 
  private boolean hasIncreased;
  
  /**
   * This is the class constructor which will assign JPanel object to the instance variable.
   * @param panel This object reference variable to JPanel stores the JPanel that the level will be drawn on.
   * @param pathogen This object reference variable to pathogen is used to store the Pathogen Object controlled by the user.
   */ 
  public Level1( Pathogen pathogen, GameInfo info)
  {
    super ("Virus", pathogen, info);
  }
  
  /**
   * This overriden method is used to update the level just as the super class does and add the ability of the virus as well (its ability to increase immunity).
   * The if statement is used to check if the virus can increase its immunity or not. If it can, then the immunity is increased.
   */ 
  public void update ()
  {
    super.update ();
    if (!canInfectTarget () && ((Virus) (getPathogen ())).canIncreaseImmunity ())
    {
      getPathogen().setImmunity (getPathogen().getImmunity () - 0.1);
      setHasIncreased (true);
    }
  }
  
  /**
   * This mutator method is used to set whether or not the virus' immunity has increased or not.
   * @param hasIncreased This boolean stores the new value of the hasIncreased boolean which keeps track of whether or not the virus' immunity has increased.
   */ 
  public void setHasIncreased(boolean hasIncreased)
  {
    this.hasIncreased = hasIncreased;
  }
  
  /**
   * This accessor method is used to get true or false (if the virus' immunity has increased or not).
   * @return boolean True or false is returned signifying if the virus' immunity has increased or not. 
   */ 
  public boolean getHasIncreased()
  {
    return hasIncreased;
  }
}