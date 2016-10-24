package window;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import game.*;
import game.Pause;
import java.io.IOException;

/**
 * This class will create the JFrame of the game.
 * Features of Version 1.0:
 * The JFrame of the game is created along.
 * The help and about dialogs are created (with the dialog (String title, String text) method) which can be accessed as menu items.
 * Also, there is a quit menu item which can be accessed through the menu bar.
 * An ActionListener is used to detect the user choosing menu bar options (actionPerformed (ActionEvent ae) is used).
 * Hours spent on this class: 4 hours
 * Features of Version 2.0:
 * The startGame method was created to start the actual game after the user is finished with the user input and the main menu.
 * Hours spent on this class (additive to previous version): 4 hours 15 min.
 * Features of Version 3.0:
 * A series of methods were added to control the pausing in the game.
 * A .chm help file was also added to the program.
 * Hours spent on this class (additive to previous version): 5 hours 15 min.
 * Features of Version 5.0: A few changes were made to the data fields stored, and the variables passed in and out in all methods with parameter passes.
 * @author Salar Hosseini & Sophia Weng
 * @version 5.0, June 12, 2014
 */ 
public class GameFrame extends JFrame implements ActionListener
{
  /**
   * This variable is an object reference variable to JDialog and it used to create the help & about dialogs.
   */ 
  JDialog d;
  /**
   * This object reference variable to Pause is used to store the instance of Pause that will be displayed the pause screen.
   */ 
  Pause p;
  /**
   * This object reference variable to Game is used to store the Game object that is being used during the game.
   */ 
  Game g2;
  /**
   * This object reference variable to GamePanel is used to store the GamePanel object that will be used for as the JPanel of all main menu elements.
   */ 
  GamePanel g;
  /**
   * This object reference variable to GameInfo is used to store the data class that stores all of the information about the settings that the user is playing with and their name.
   */ 
  GameInfo info;
  
  /**
   * This is the class constructor which will create the actual Frame of the game window along with the menu bar, menus and menu items.
   * The windowClosing method is used to detect when the JFrame window is closed.
   * @param quitItem This object reference variable to JMenuItem is used to store/create the Quit item in the File menu.
   * @param helpItem This object reference variable to JMenuItem is used to store/create the Help item in the Help menu.
   * @param aboutItem This object reference variable to JMenuItem is used to store/create  the About item in the Help menu.
   * @param fileMenu This object reference variable to JMenu is used to store/create the File menu.
   * @param helpMenu This object reference variable to JMenu is used to store/create the Help menu.
   * @param myMenus This object reference variable to JMenuBar is used to store/create the menu bar of the frame.
   * @param appIcon This object reference variable to ImageIcon is used to store the image that will be used as an icon.
   * @param windowEvent This Object reference variable to WindowEvent is used to detect if the JFrame window is closed.
   * @param helpFileItem This object reference variable to JMenuItem is used to create a chm file when the user presses it.
   */ 
  public GameFrame ()
  {
    super ("Virus Dash");
    info = new GameInfo (true, true);
    g = new GamePanel (this);
    JMenuItem quitItem = new JMenuItem ("Quit");
    JMenuItem helpItem = new JMenuItem ("Help");
    JMenuItem aboutItem = new JMenuItem ("About");
    JMenuItem helpFileItem = new JMenuItem ("Help File");
    JMenu fileMenu = new JMenu ("File");
    JMenu helpMenu = new JMenu ("Help");
    
    quitItem.addActionListener (this);
    helpItem.addActionListener (this);
    aboutItem.addActionListener (this);
    helpFileItem.addActionListener (this);
    
    fileMenu.add (quitItem);
    helpMenu.add (helpItem);
    helpMenu.add (aboutItem);
    helpMenu.add (helpFileItem);
    
    JMenuBar myMenus = new JMenuBar ();
    myMenus.add (fileMenu);
    myMenus.add (helpMenu);  
    setJMenuBar (myMenus);
    
    setSize (900, 650);
    setVisible (true);
    setResizable (false);
    setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
    
    ImageIcon appIcon = new ImageIcon("misc/translupus2.png");
    setIconImage(appIcon.getImage());
    
    add (g);
    revalidate ();
    
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent windowEvent) 
      {
        System.exit(0);
      }
    });
  }
  
  
  /**
   * This method is used to start the actual game by removing the panel with the main menu and a new panel.
   * @param type This boolean is used to store true if the JPanel of the actual game should be removed or not, and false if vice versa.
   */ 
  public void startGame (boolean shouldRemove)
  {  
    remove (g);
    if (shouldRemove)
      remove (g2);
    game ();
  }
  
  /**
   * This method is used to create a new Game about which will be added to the JFrame and start the game.
   */ 
  public void game ()
  {
    g2 = new Game (this);
    add (g2);
    g2.requestFocusInWindow ();
    revalidate ();
  }
  
  /**
   * This method is called when the user wishes to pause the game so that their game can be paused.
   */ 
  public void pause ()
  {
    p = new Pause (this);
    add (p);
    g2.setVisible (false);
    p.setVisible (true);
    revalidate ();
  }
  
  /**
   * This method is used to finish the paused state of the game.
   * The if statement is used to check if the game should be resumed or not. If so, the timer of the game is resumed (animations and processing begin again).
   * @param shouldStartTimer This boolean is used to store true if the timer of the game should be started, and false if not.
   */ 
  public void donePause (boolean shouldStartTimer)
  {
    remove (p);
    g2.setVisible (true);
    g2.requestFocusInWindow ();
    if (shouldStartTimer)
      g2.startTimer ();
  }
  
  /**
   * This method is used to restart the game that the user is playing by removing that panel, and creating a new instance of it.
   * The if statement is used to check if the game is on pause or not. If so, the pause JPanel is removed.
   * @param isOnPause This boolean is used to store true if the game is on pause mode, and false if not.
   */ 
  public void restart (boolean isOnPause)
  {
    if (isOnPause)
      remove (p);
    remove (g2);
    game ();
  }
  
  /**
   * This method is used to close the Game JPanel that the user was playing on, and return to the main menu.
   */ 
  public void doneGame ()
  {
    remove (g2);
    add (g);
    g.mainMenu ();
    revalidate ();
  }
  
  /**
   * This method is used to get the instance of the class that stores all of the game info.
   * @return GameInfo This class is returned because it contains all the game info.
   */ 
  public GameInfo getGameInfo ()
  {
    return info;
  }
  
  /**
   * This method is used to create the JDialog box that appears as the help or about dialog.
   * The try block is used to catch any BadLocationExceptions that may occur when inserting a String into the DefaultStyledDocument of the JTextPane that is created.
   * @param title This object reference variable to String is used to store the title of the JDialog box.
   * @param text This object reference variable to String is used to store the text that will be displayed in the JDialogBox/ JTextPane.
   * @param sc This object reference variable to StyleContext is used to set a style context for the styled document that will be used for the JTextPane.
   * @param logo This object reference variable to Icon is use to store the icon of the company logo.
   * @param doc This object reference variable to DefaultStyledDocument is used to create style of the JTextPane, and the components in it.
   * @param center This object reference variable to SimpleAttributeSet is used to center the text on the JTextPane.
   * @param pane This object reference variable JTextPane is used to create the text pane that will appear on the JDialog box.
   * @param s This object reference variable to style is used to create the style for the icon on the JTextPane.
   * @param e This object reference variable to BadLocationException is used to catch any of these Exceptions when inserting strings into the StyledDocument of the JTextPane.
   * @throws BadLocationException when inserting strings into a DefaultStyledDocument.
   */ 
  public void dialog (String title, String text)
  {
    d = new JDialog (this, title);                              
    d.setResizable (false);
    d.setLocationRelativeTo (this);
    d.setSize(400, 250);
    
    StyleContext sc = new StyleContext();
    Icon logo = new ImageIcon ("misc/translupus2.png");
    final DefaultStyledDocument doc = new DefaultStyledDocument (sc);
    SimpleAttributeSet center = new SimpleAttributeSet();
    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
    doc.setParagraphAttributes(0, doc.getLength(), center, false);
    JTextPane pane = new JTextPane (doc);
    pane.setEditable (false);    
    Style s = doc.addStyle("icon", doc.addStyle("regular", StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE)));
    StyleConstants.setIcon(s, logo);
    
    try 
    {
      doc.insertString(0, "Lupus Tech", null);
      doc.insertString(0, "\n" , null);   // Add the text to the document
      doc.insertString (0, " ", doc.getStyle ("icon"));
      doc.insertString(0, text + "\n" + "\n", null);   // Add the text to the document
    } 
    catch (BadLocationException e) 
    {}
    
    d.getContentPane().add(new JScrollPane(pane));
    d.setVisible(true);
  }
  
  /**
   * This actionPerformed method is overriden to detect actions within the JFrame.
   * The if statement is used to detect which action has been performed. If the about or help menu items are chosen, a dialog box opens, and if the quit menu item is chosen, the window closes.
   * The try block is used to catch any IOExceptions that may occur when reading the CHM file.
   * @param ae This object reference variable to ActionEvent is used to detect certain actions performed in the JFrame window.
   * @param e This object reference variable to IOException is used to catch any of those Exceptions when reading the chm file.
   * @throws IOException when reading the chm file.
   */ 
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getActionCommand (). equals ("Quit"))
      System.exit (0);
    else if (ae.getActionCommand().equals ("About"))
      dialog ("About", "Virus Dash \n This game was programmed by Lupus Tech in 1 month. \n All rights reserved 2014. \n Lupus Tech Members: Salar Hosseini & Sophia Weng");
    else if (ae.getActionCommand().equals ("Help"))
      dialog ("Help", "Virus Dash \n Objective of the Game: To reach the target cell with your virus and infect it by getting past all of the guarding antibodies");
    else
    {
      try 
      {
        Runtime.getRuntime().exec("hh.exe misc/VirusDash.chm");
      }
      catch (IOException e)
      {
        e.printStackTrace();
        System.out.println (e.getMessage());
      }      
    }
  }
}