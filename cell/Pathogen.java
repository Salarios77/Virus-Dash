package cell;
import javax.swing.Timer;
import java.awt.event.*;

/**
 * This class is used to create the Pathogen that the user will control.
 * Features of Version 2.0: This entire class was created. The accessor and mutator methods were created for the pathogen.
 * Time spent on this class: 15 minutes.
 * Features of Version 4.0: The cooldown feature of the pathogen was added (the following methods: timer (), getTimeLeft (), resume (), pause (), actionPerformed (ActionEvent ae)). ActionListener is now implemented to be used for the java.swing.Timer;
 * Time spent on this class: 1 hour (additive to previous versions).
 * @author Salar Hosseini & Sophia Weng
 * @version 5.0, June 12, 2014
 */ 
public class Pathogen extends Cell  implements ActionListener
{
  /**
   * This variable will store the immunity of the pathogen as a double - how resistant it is (the damage the pathogen takes will be multiplyed by this real number). 
   */ 
  private double immunity;
  /**
   * This object reference variable to Timer is used to store a Timer which will be used to count down 5 seconds for ability cool downs in the game.
   */ 
  private Timer tm = new Timer (1000, this);
  /**
   * This integer will store the amount of time remaining in the ability cooldowns.
   */ 
  private int interval;
  /**
   * This integer stores the x velocity of the pathogen's movement.
   */ 
  private int xVel = 0;
  /**
   * This integer stores the y velocity of the pathogen's movement.
   */ 
  private int yVel = 0;
  
  /**
   * This is the class constructor which will create the Pathogen to be played with.
   * @param name2 This object reference variable to String is used to store the name of the pathogen.
   * @param color2 This object reference variable to Color will be used to store the color of the pathogen.
   * @param immunity2 This double will be used to store the immunity of the pathogen.
   */ 
  public Pathogen (double immunity2)
  {
    super (320, 400, 500);
    immunity = immunity2;
  }
  
  /**
   * This mutator method is used to set the x velocity of the pathogen to a new x velocity.
   * @param xVel This integer stores the new x velocity.
   */ 
  public void setXVel (int xVel)
  {
    this.xVel = xVel;
  }
  
  /**
   * This mutator method is used to set the y velocity of the pathogen to a new y velocity.
   * @param yVel This integer stores the new y velocity.
   */ 
  public void setYVel (int yVel)
  {
    this.yVel = yVel;
  }
  
  /**
   * This accessor method is used to get the x velocity of the pathogen.
   * @return int The integer returns stores the x velocity of the pathogen.
   */ 
  public int getXVel ()
  {
    return xVel;
  }
  
  /**
   * This accessor method is used to get the y velocity of the pathogen.
   * @return int The integer returns stores the y velocity of the pathogen.
   */ 
  public int getYVel ()
  {
    return yVel;
  }
  
  /**
   * This mutator method is used to change the immunity of the pathogen.
   * @param immunity2 This double stores the new immunity of the pathogen.
   */ 
  public void setImmunity (double immunity2)
  {
    immunity = immunity2;
  }
  
  /**
   * This accessor method is used to get the immunity of the pathogen.
   * @return double - The immunity of the pathogen is returned.
   */ 
  public double getImmunity ()
  {
    return immunity;
  }
  
  /**
   * This overriden method is used to set the health of the pathogen, while applying the immunity of the pathogen to it as well.
   * @param damage This integer stores the amount of damage that a antibody has conflicted to this pathogen.
   */ 
  public void setHealth (int damage)
  {
    super.setHealth (damage * getImmunity ());
  }
  
  /**
   * This method is used to start the cooldowns for the pathogen.
   */
  public void timer ()
  {
    interval = 5;
    tm.restart ();
  }
  
  /**
   * This method is used to start the cooldowns for the pathogen.
   */
  public void timer (int time)
  {
    interval = time;
    tm.restart ();
  }
  
  /**
   * This method is used to detect when to decrement the time left in the cool down (the Timer object calls this method every 1 second).
   * The if statement is used to check if the cool down is finished. If so, the timer is stopped.
   */ 
  public void actionPerformed (ActionEvent ae)
  {
    if (interval == 0)
    {
      tm.stop ();
      return;
    }
    --interval;
  }
  
  /**
   * This accessor method is used to get the time remaining in the cooldown of the pathogen.
   * @return int The time left for the cooldown is returned.
   */ 
  public int getTimeLeft ()
  {
    return interval;
  }
  
  /**
   * This method is used to pause the cooldown timer.
   */ 
  public void pause ()
  {
    tm.stop ();
  }
  
  /**
   * This method is used to resume the cooldown timer.
   */ 
  public void resume ()
  {
    tm.start ();
  }
}