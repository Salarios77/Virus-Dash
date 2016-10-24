package splashscreen;
import javax.swing.*;
import window.GamePanel;


/**
 * This class extends the Thread class and is used to run the splash screen of the game.
 * Features of version 2.0:
 * This entire class was created - the constructor assigns the JPanel that will be drawn on, and the run method is used to create and start the actual splash screen.
 * Hours spent on this class: for coding: 15 min, for drawing, changing file format, etc.: 7.5 hours.
 * @author Salar Hosseini & Sophia Weng
 * @version 5.0, June 12, 2014
 */ 
public class SplashScreen
{
  /**
   * This object reference variable to JPanel is used to store the JPanel that the splash screen will be displayed on.
   */ 
  JPanel panel;
  
  /**
   * This is the class constructor which will assign the proper JPanel value to the instance variable which is an object reference variable to JPanel.
   * @param panel2 This object reference variable to JPanel is used to store the JPanel that will have the splash screen displayed on.
   */ 
  public SplashScreen (JPanel panel2)
  {
    panel = panel2;
    display ();
  }
  
  
  /**
   * This method is used to display the splashscreen.
   * @param gif This object reference variabel to JLabel is used to store the gif that will be displayed a JLabel which will be added to the panel.
   */ 
  public void display ()
  {
    JLabel gif = GamePanel.imageLoader ("misc/SplashScreen.gif");
    GamePanel.layout.putConstraint (SpringLayout.NORTH, gif, -25, SpringLayout.NORTH, panel);
    panel.add (gif);
    panel.revalidate ();
    panel.repaint ();
  }
}