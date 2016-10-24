package cell;
import java.awt.Rectangle;

/**
 * This class is used to create the virus pathogen which has the ability to change its immunity level.
 * Features of Version 2.0: This entire class was created. The accessor and mutator methods were created for the virus.
 * Time spent on this class: 20 minutes.
 * Features of Version 4.0: The canIncreaseImmunity () method was changed to incorporate the cool down.
 * Time spent on this class: 40 minutes (additive to previous versions).
 * @author Salar Hosseini & Sophia Weng
 * @version 5.0, June 12, 2014
 */ 
public class Virus extends Pathogen
{
  /**
   * This double is used to store the chance of the virus being able to increase its immunity.
   */ 
  private final double IMMUNITY_CHANCE = .5;
  
  /**
   * This is the class constructor which assigns calls the constructor of the super class to set an immunity for the virus. It also starts the first cooldown.
   */ 
  public Virus ()
  {
    super (0.9);
    timer ();
  }
  
  /**
   * This helper method is used to determine whether or not the virus can increase its immunity by returning either true or false.
   * The first if statement is used to check if the cooldown is finished or not. If so, true may be returned with further conditions. 
   * The nested if statement is used to check if a random decimal number is less than the chance of the virus increasing its immunity. If so, true is returned to signify that the immunity can be increased.
   * @return boolean - true if can increase immunity, false if cannot.
   */ 
  public boolean canIncreaseImmunity ()
  {
    if (getTimeLeft () == 0)
    {
      timer ();
      if (Math.random () < IMMUNITY_CHANCE)
        return true;
    }
    return false;
  }
  
  /**
   * This method returns the boundaries of the Virus.
   * @return Rectangle (The rectangle surrounding the Virus).
   */ 
  public Rectangle getBounds ()
  {
    return new Rectangle (getX ()+5, getY ()+5, 86, 82);
  }
}