package window;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.sound.sampled.*;
import java.util.*;
import highscores.*;
import java.io.IOException;

/**
 * This class will create the JPanel of the game.
 * Features of Version 1.0: <br>
 * The screens of the games will be controlled here via buttons from the main menu which appears after the splash screen (the instructions, goodBye, settings, play, highScore methods are called from actionPerformed which listens to the actions of the mainMenu method). <br>
 * Hours spent on this class: 4 hours, 30 minutes <br> <br>
 * Features of Version 2.0: <br>
 * Improved buttons, user input error trapping (difficultySelection, via the method inputIsInvalid).
 * Splash screen has been added, as well as improved graphics for main menu.
 * A new timer method has been addded so that the splash screen can be fully displayed before main menu is called.
 * Some new helper methods have been added, all of which are private, except for the image loader, which will also be used in the SplashScreen class. <br>
 * Hours spent on this version (addtive to previous version): 10 hours, 30 minutes <br> <br>
 * Features of Version 3.0: <br>
 * Settings and instructions have been updated, and music was added.
 * Hours spent on this version (additive to previous version): 2.5 hours
 * Features of Version 5.0 <br>
 * Some data fields were taken out for KISS purposes.
 * Hours spent on this version (additive to previous version): 3 hours
 * @author Salar Hosseini & Sophia Weng
 * @version 5.0, June 12, 2014
 */
public class GamePanel extends JPanel implements ActionListener
{
  /**
   * This object reference variable to SpringLayout is used to access the said layout type, which is used in the game's panel.
   */ 
  public static SpringLayout layout = new SpringLayout ();
  /**
   * This object reference variable to KeyAdapter is used to track input by the user via keyboard. 
   * It is used to temporarily pause the screens so that the user can see the messages displayed, and continue by pressing a key.
   */
  private KeyAdapter keyAdapter;
  /**
   * This object reference variable to MouseListener is used to access methods that are used to listen to mouse actions.
   */
  private MouseListener mouseListener;
  /**
   * This boolean is used to determine when a box should be drawn around the user's selections in the difficultySelection screen.
   */
  private boolean shouldDraw;
  /**
   * This object reference variable to JTextField is used as a place for the user to enter their name.
   */
  private JTextField nameField;
  /**
   * This object reference variable to Timer is used to set up a timer so that the mainmenu call is delayed, therefore allowing the user to see the splash screen.
   */
  private java.util.Timer timer;
  /**
   * This integer is used to cue when the main menu should be called (after the splash screen has finished).
   */
  private int interval = 12;
  /**
   * This object reference variable to GameFrame is used to store the frame that is used to contain the GamePanel.
   */
  private GameFrame frame;
  /**
   * This object reference variable to JButton is used to create the on and off button for music in settings.
   */
  private JButton musicOnOff = new JButton ("Turn Off Music");;
  /**
   * This object reference variable to AudioInputStreams is used to reference the music that is to be played.
   */
  private AudioInputStream ais;
  /**
   * This object reference variable to Clip is used to play the music.
   */
  private Clip clip;
  /**
   * This boolean variable is used to determine whether or not the music is playing, which is used to determine which button should display in settings.
   */
  private boolean musicOn;
  /**
   * This object reference variable to JButton is used to create the button that is used to turn the learning mode of the game on or off.
   */ 
  private JButton learningOnOff = new JButton ("Turn Off Learning");
  
  
  /*
   * This is the class constructor will display a splash screen, create the layout type and set it to the panel, as well as call display the main menu and start the music.
   * @param frame This object reference variable to GameFrame is used to hold the frame that is used to hold this panel. It will be used to remove this panel, and add the panel belonging to the Game class.
   */
  public GamePanel (GameFrame frame)
  {
    this.frame = frame;
    setLayout (layout);
    timer ();
    splashScreen(); //IF THIS IS COMMENTED OUT, MAIN MENU SHORT CUTS MAY NOT WORK FIRST TIME. EVERYTHING IS FINE IF NOT TAKEN OUT.
    music ();
  }
  
  /**
   * This method is used to start the actual game.
   */ 
  public void play ()
  {
    frame.startGame (false);
  }
  
  /**
   * This helper method is to be called in other classes, to create a title in a set font in white, centered on the screen.
   * @param title This object reference variable to JLabel  creates the title that is going to be centered.
   */
  private void title ()
  {
    removeAll ();
    JLabel title = new JLabel ("Virus Dash");
    title.setFont(new Font("Agency FB", Font.ITALIC, 85));
    title.setForeground (Color.WHITE);
    layout.putConstraint (SpringLayout.HORIZONTAL_CENTER, title, 0, SpringLayout.HORIZONTAL_CENTER, this);
    add (title);
  }
  
  /**
   * This method is used to display a splash screen.
   */
  public void splashScreen ()
  {
    new splashscreen.SplashScreen (this);
  }
  
  /**
   * This helper method is used to delay when main menu is called, so that splash screen can be fully displayed.
   * The nested method is used to start the timer.
   */
  private void timer ()
  {
    timer = new java.util.Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      public void run() {
        setInterval ();
      }
    }, 1000, 1000);
  }
  
  /**
   * This helper method is used to decrease interval until it is equal to 0, in which case the timer will stop and main menu will be called.
   * The if statement is used to check if the time left is only 1 second. If so, the timer is stopped, and the main menu is displayed.
   * @return int The timer of the splashscreen minus one is returned.
   */
  private final int setInterval() 
  {
    if (interval == 1)
    {
      timer.cancel();
      mainMenu ();
      revalidate ();
    }
    return --interval;
  }
  
  /**
   * This method is used to create the main menu and all it's buttons. It has a blood vessel background.
   * ActionListeners are added to all the buttons so that they respond accordingly when they are pressed.
   * All buttons are organizd on the screen using SpringLayout, and added to the panel. The buttons' appearances are also changed via the setButtonCool method.
   * For nested class:
   * The largest if structure is used to check if any of the ctrl keys of the menu where pressed. If so, they are detected, and a specific action is performed for each.
   * The first nested if structure is used to check which ctrl short cut was pressed to be exact.
   * The most nested if structure is used to again check which ctrl short cut was pressed to be exact.
   * @param e This object reference variable to KeyEvent is used to detect the keys that the user presses.
   * @param highScores This object reference variable to JButton is used to create a button that will take the user to the highscores screen.
   * @param play This object reference variable to JButton is used to create a button that will take the user to select a level and difficulty, and then play. 
   * @param instructions This object reference variable to JButton is used to create a button that will take the user to the instructions screen.
   * @param settings This object reference variable to JButton is used to create a button that will take the user to the settings screen.
   * @param quit This object reference variable to JButton is used to create a button that will take the user to the goodbye screen, which will then close the program automatically when any key is pressed.
   * @param ctrlPlay This object reference variable to JLabel is used to create a JLabel which will display the short cut key for play in the main menu.
   * @param ctrlScores This object reference variable to JLabel is used to create a JLabel which will display the short cut key for high scores in the main menu.
   * @param ctrlInstructions This object reference variable to JLabel is used to create a JLabel which will display the short cut key for instructions in the main menu.
   * @param ctrlSettings This object reference variable to JLabel is used to create a JLabel which will display the short cut key for settings in the main menu.
   * @param ctrlQuit This object reference variable to JLabel is used to create a JLabel which will display the short cut key for quit in the main menu.
   */
  public void mainMenu ()
  {
    frame.getGameInfo ().setDifficulty (0);
    frame.getGameInfo ().setLevel (0);
    frame.getGameInfo ().setName ("");
    frame.getGameInfo ().setShouldTeachWbc (true);
    
    title ();
    JButton highScores = new JButton ("High Scores");
    JButton play = new JButton ("Play");
    JButton instructions = new JButton ("Instructions");
    JButton settings = new JButton ("Settings");
    JButton quit = new JButton ("Quit");
    setButtonCool (highScores, 18);
    setButtonCool (play, 18);
    setButtonCool (instructions, 18);
    setButtonCool (settings, 18);
    setButtonCool (quit, 18);
    layout.putConstraint (SpringLayout.WEST, play, 95, SpringLayout.WEST,this);
    layout.putConstraint (SpringLayout.NORTH, play, 160, SpringLayout.NORTH, this);
    layout.putConstraint (SpringLayout.WEST, instructions, 75, SpringLayout.WEST, this);
    layout.putConstraint (SpringLayout.NORTH, instructions, 65, SpringLayout.NORTH, play);
    layout.putConstraint (SpringLayout.WEST, settings, 85, SpringLayout.WEST, this);
    layout.putConstraint (SpringLayout.NORTH, settings, 65, SpringLayout.NORTH, instructions);
    layout.putConstraint (SpringLayout.WEST, highScores, 75, SpringLayout.WEST, this);
    layout.putConstraint (SpringLayout.NORTH, highScores, 65, SpringLayout.NORTH, settings);
    layout.putConstraint (SpringLayout.WEST, quit,  95, SpringLayout.WEST, this);
    layout.putConstraint (SpringLayout.NORTH, quit, 65, SpringLayout.NORTH, highScores);
    add (highScores);
    add (play);
    add (instructions);
    add (settings);
    add (quit);
    
    JLabel ctrlPlay = new JLabel ("Ctrl-P");
    JLabel ctrlScores = new JLabel ("Ctrl-H");
    JLabel ctrlInstructions = new JLabel ("Ctrl-I");
    JLabel ctrlSettings  = new JLabel ("Ctrl-S");
    JLabel ctrlQuit = new JLabel ("Ctrl-Q");
    
    setLabelCool (ctrlPlay, 17, 160, this, true);
    setLabelCool (ctrlScores, 17, 365, this, true);
    setLabelCool (ctrlInstructions, 20, 225, this, true);
    setLabelCool (ctrlSettings, 17, 300, this, true);
    setLabelCool (ctrlQuit, 17, 430, this, true);
    
    layout.putConstraint (SpringLayout.WEST, ctrlPlay, 30, SpringLayout.EAST, play);
    layout.putConstraint (SpringLayout.WEST, ctrlInstructions, 30, SpringLayout.EAST, instructions);
    layout.putConstraint (SpringLayout.WEST, ctrlSettings, 30, SpringLayout.EAST, settings);
    layout.putConstraint (SpringLayout.WEST, ctrlQuit, 30, SpringLayout.EAST, quit);
    layout.putConstraint (SpringLayout.WEST, ctrlScores, 30, SpringLayout.EAST, highScores);
    
    highScores.addActionListener (this);
    play.addActionListener (this);
    instructions.addActionListener (this);
    settings.addActionListener (this);
    quit.addActionListener (this);
    
    requestFocusInWindow ();
    keyAdapter = new KeyAdapter (){
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyChar () == 16 || e.getKeyChar () == 8 || e.getKeyChar () == 9 || e.getKeyChar () == 19 || e.getKeyChar () == 17)
        {
          // removeKeyListener (keyAdapter);
          if (e.getKeyChar () == 9 || e.getKeyChar () == 19 || e.getKeyChar () == 8)
          { 
            if (e.getKeyChar () == 19)
              settings ();
            else if (e.getKeyChar () == 8)
              highScore ();
            else
              instructions ();
            pauseProgram (1);
          }
          else if (e.getKeyChar () == 17)
            goodBye ();
          else
            difficultySelection ();
          revalidate ();
          repaint ();
        }
      }
    };
    addKeyListener (keyAdapter);
    
    add (imageLoader ("misc/mainmenu3.2.png"));
  }
  
  /**
   * This helper method is used to create aesthetically pleasing buttons. 
   * @param button This object reference variable to JButton is used to set the visuals of the button, as well as it's text.
   * @param fontSize This integer is used to store the font size of the button that will be modified.
   */
  public static void setButtonCool (JButton button, int fontSize)
  {
    button.setFont (new Font ("Agency FB", Font.PLAIN, fontSize));
    button.setForeground (Color.WHITE);
    button.setContentAreaFilled(false);
  }
  
  /**
   * This method is used to create the goodbye screen, where credits are shown and the user is thanked for playing the game.
   * The user is then prompted to press any key to continue, which will then close the program.
   * @param text This object reference variable to JLabel is used to show the text (credits and thanks). It will be positioned using SpringLayout.
   */
  public void goodBye ()
  {
    title ();
    JLabel text = new JLabel ("Thank you for playing 'Virus Dash', which was programmed by Lupus Tech. Hope you had fun!");
    setLabelCool (text, 25, 140, this, false);
    pauseProgramWriting();
    add (imageLoader ("misc/goodbyebacktemp.png"));
    pauseProgram (0);
  }
  
  /**
   * This helper method is used to stop the program until the user it ready to continue (a prompt is displayed for the user to press any key to continue the program, mainly to read the text on the screen.
   * This nested method is used to specify what should be done when the key listener picks up input from the user.
   * The if statement (nested method) is used check what it should do until a key is pressed. 
   * The nested if statement (nested method) is to check when a key is pressed, and whether the user was on the goodbye screen (TYPE = 0 in that case).
   * When TYPE = 0, the program will close. Otherwise, the program will go back to main menu, and then the key listener is removed since the nested method has run.   
   * @param e This object reference variable to KeyEvent will store the key that is pressed.
   * @param TYPE This final int is used to store whether the method should go back to main menu or if it should close the program.
   */
  private void pauseProgram (final int TYPE)
  {
    requestFocusInWindow ();
    keyAdapter = new KeyAdapter (){
      public void keyPressed(KeyEvent e)
      {
        removeKeyListener (keyAdapter);
        if (e.getKeyChar () != 0)
        {
          if (TYPE == 0)
            System.exit (0);
          else
          {
            mainMenu ();
            revalidate ();
            repaint ();
          }
        }
      }
    };
    addKeyListener (keyAdapter);
  }
  
  /**
   * This helper method is used to show the user a prompt to press any key to continue the program. It is centered, and written in a special font. 
   * @param temp This object reference variable to JLabel is used to create the prompt to the user to press a key to continue.
   */
  private void pauseProgramWriting ()
  {
    JLabel temp = new JLabel ("Press any key to continue");
    temp.setForeground (Color.WHITE);
    setLabelCool (temp, 25, 525, this, false);
  }
  
  /**
   * This method is used to create the instructions screen, where there is a special background is set, and there is a prompt to press a key.
   * @param text This object reference variable to JLabel is used to display the instructions.
   */
  public void instructions ()
  {
    title ();
    pauseProgramWriting ();
    
    JLabel text = new JLabel ("In Virus Dash, you will learn about different types of pathogens that can affect the body through the blood stream!");
    setLabelCool (text, 21, 120, this, false);
    text = new JLabel ("You will then be able to control a pathogen, to try and infect a specified cell.");
    setLabelCool (text, 21, 150, this, false);
    text = new JLabel ("You will use the W A S D keys to control the pathogen: W to go up, A to go left, S to go down, and D to go right.");
    setLabelCool (text, 21, 210, this, false);
    text = new JLabel ("In this game, you are to dodge or attack (only with the bacteria) the antibodies going through the blood stream. You can attack with");
    setLabelCool (text, 21, 240, this, false);
    text = new JLabel ("bacteria by clicking the mouse in the direction you want to shoot. This will send out a clone of yourself (the bacteria) to");
    setLabelCool (text, 21, 270, this, false);
    text = new JLabel ("harm the antibodies. However, this ability has a cool down, and therefore cannot be spammed.");
    setLabelCool (text, 21, 300, this, false);
    text = new JLabel ("You can take multiple hits from antibodies but you a have a specific amount of health and immunity/ resistence which");
    setLabelCool (text, 21, 360, this, false);
    text = new JLabel ("depends on your pathogen type. At the end of each level you must infect the targetted cell by getting past the boss");
    setLabelCool (text, 21, 390, this, false);
    text = new JLabel ("which cannot be harmed. Some side bars will display your health, and progress towards the targetted cell.");
    setLabelCool (text, 21, 420, this, false);
    
    text = new JLabel ("Enjoy the game!");
    setLabelCool (text, 25, 480, this, false);
    JLabel wasd = imageLoader ("misc/twasdcontrols.png");
    JLabel mouse = imageLoader ("misc/Mouse.png");
    layout.putConstraint (SpringLayout.WEST, wasd, 27, SpringLayout.WEST, this);
    layout.putConstraint (SpringLayout.SOUTH, wasd, -20, SpringLayout.SOUTH, this);
    layout.putConstraint (SpringLayout.EAST, mouse, -27, SpringLayout.EAST, this);
    layout.putConstraint (SpringLayout.SOUTH, mouse, -20, SpringLayout.SOUTH, this);
    add (wasd);
    add (mouse);
    add (imageLoader ("misc/InstructionsBack2.jpg")); 
  }
  
  /**
   * This method is used to create the settings screen, where a special backgound is set and there is a prompt to press a key.
   */
  public void settings ()
  {
    title ();
    setButtonCool (musicOnOff, 30);
    setButtonCool (learningOnOff, 30);
    layout.putConstraint (SpringLayout.WEST, musicOnOff, 50, SpringLayout.WEST, this);
    layout.putConstraint (SpringLayout.VERTICAL_CENTER, musicOnOff, 0, SpringLayout.VERTICAL_CENTER, this);
    layout.putConstraint (SpringLayout.EAST, learningOnOff, -50, SpringLayout.EAST, this);
    layout.putConstraint (SpringLayout.VERTICAL_CENTER, learningOnOff, 0, SpringLayout.VERTICAL_CENTER, this);
    add (learningOnOff);
    add (musicOnOff);
    musicOnOff.addActionListener (this);
    learningOnOff.addActionListener (this);
    pauseProgramWriting ();
    add (imageLoader ("misc/SettingsBack.jpg"));
  }
  
  /**
   * This method is used to return the picture specified by the method that calls it.
   * @param name This String reference variable is used to specify the file name of the picture to be added.
   * @param icon This object reference variable to ImageIcon is used to store the Image that will be used as the window icon.
   * @param thumb This object reference variable to JLabel is used to hold the picture, that will later be added by the method that calls it
   * @return JLabel - returns the picture in the specified file name.
   */
  public static JLabel imageLoader(String name)
  {
    ImageIcon icon = new ImageIcon (name); 
    JLabel thumb = new JLabel();
    thumb.setIcon(icon);
    return thumb;
  }
  
  /**
   * This helper method is used to set font and size to text in JLabels. 
   * It also adds the JLabel to the panel, at the specified location.
   * @param label This object reference variable to JLabel is used to store the label that is being manipulated.
   * @param fontSize This integer is used to store the desired font size. 
   * @param y This integer is used to store where the labels hould be positioned.
   */
  public static void setLabelCool (JLabel label, int fontSize, int y, JPanel panel, boolean isOnMainMenu)
  {
    label.setFont (new Font ("Agency FB", Font.PLAIN, fontSize));
    label.setForeground (Color.WHITE);
    if (!isOnMainMenu)
    {
      layout.putConstraint (SpringLayout.HORIZONTAL_CENTER, label, 0, SpringLayout.HORIZONTAL_CENTER, panel);
    }
    layout.putConstraint (SpringLayout.NORTH, label, y, SpringLayout.NORTH, panel);
    panel.add (label);
  }
  
  /**
   * This method is used to display the top 10 highscores to the player.
   * The for loop is used to navigate through the highscores stored, and display them (if they are present), or display "No score." if they are not present. The for loop variable x starts at 0, and increments by 1 while it is less than 10, and the other loop variable (y) is used to keep track of the spacing between the writing (starts at 100, increments by 35).
   * The try block is used to catch any IndexOutOfBoundsExceptions that may occur when displaying the highscores. If any are caught, then "No score." is displayed.
   * @param temp1 This object reference variable to JLabel is used to create the writing that will be displayed on the highscores page (the position of the score).
   * @param temp2 This object reference variable to JLabel is used to create the writing that will be displayed on the highscores page (the name).
   * @param temp3 This object reference variable to JLabel is used to create the writing that will be displayed on the highscores page (the difficulty).
   * @param temp4 This object reference variable to JLabel is used to create the writing that will be displayed on the highscores page (the level).
   * @param temp5 This object reference variable to JLabel is used to create the writing that will be displayed on the highscores page (the score).
   * @param header1 This object reference variable to JLabel is used to create the writing that will be displayed on the highscores page (Place header).
   * @param header2 This object reference variable to JLabel is used to create the writing that will be displayed on the highscores page (Name header).
   * @param header3 This object reference variable to JLabel is used to create the writing that will be displayed on the highscores page (Difficulty header).
   * @param header4 This object reference variable to JLabel is used to create the writing that will be displayed on the highscores page (Level header).
   * @param header5 This object reference variable to JLabel is used to create the writing that will be displayed on the highscores page (Score header).
   * @param scores This object reference variable to the HighScoreLoader class is used to load all of the highscores currently existing.
   * @param scores2 This object reference variable to ArrayList is used to store the highscores achieved.
   * @param current This object reference variable to HighScore will keep track of which HighScore is currently being analyzed, and displayed.
   * @param x This integer is a for loop variable that starts at 0, and increments by 1 while it is less than 10 to display all highscores.
   * @param y This integer is a for loop variable that starts at 100, and increments by 35 while the other for loop variable (x) is less than 10. y controls the spacing between the JLabels.
   * @param e This object reference variable to IndexOufOfBoundsException is used to catch any of those Exceptions that may occur when displaying the highscores.
   * @throws IndexOutOfBoundsException This exception is thrown when reading from the list of highscores, and if the for loop variable goes out of its size limit.
   */ 
  public void highScore ()
  {
    title ();
    JLabel temp1, temp2, temp3, temp4, temp5;
    HighScoreLoader scores = new HighScoreLoader ();
    ArrayList <HighScore> scores2 = scores.getHighScores ();
    HighScore current;
    
    JButton print = new JButton ("Press Me To Print.");
    layout.putConstraint (SpringLayout.NORTH, print, 25, SpringLayout.NORTH, this);
    layout.putConstraint (SpringLayout.EAST, print, -25, SpringLayout.EAST, this);
    print.addActionListener (this);
    add (print); 
    
    JLabel header1 = new JLabel ("Place"), header2 = new JLabel ("Name"), header3 = new JLabel ("Difficulty"), header4 = new JLabel ("Level"), header5 = new JLabel ("Score");
    
    layout.putConstraint (SpringLayout.WEST, header1, 40, SpringLayout.WEST, this);
    layout.putConstraint (SpringLayout.WEST, header2, 160, SpringLayout.WEST, this);
    layout.putConstraint (SpringLayout.WEST, header3, 280, SpringLayout.WEST, this);
    layout.putConstraint (SpringLayout.WEST, header4, 400, SpringLayout.WEST, this);
    layout.putConstraint (SpringLayout.WEST, header5, 520, SpringLayout.WEST, this);
    
    setLabelCool (header1, 25, 110, this, true);   
    setLabelCool (header2, 25, 110, this, true);
    setLabelCool (header3, 25, 110, this, true);
    setLabelCool (header4, 25, 110, this, true);
    setLabelCool (header5, 25, 110, this, true);
    
    for (int x = 0, y = 150; x<10; x++, y +=35)
    {
      try
      {
        temp1 = new JLabel ("" + (x+1));
        layout.putConstraint (SpringLayout.WEST, temp1, 40, SpringLayout.WEST, this);
        setLabelCool (temp1, 20, y, this, true);
        
        current = scores2.get (x);
        temp2 = new JLabel (current.getName ());
        temp3 = new JLabel (current.getDifficulty ());
        temp4 = new JLabel (current.getLevel ());
        temp5 = new JLabel ("" + current.getScore ());
      }
      catch (IndexOutOfBoundsException e)
      {
        temp2 = new JLabel ("None");
        temp3 = new JLabel ("None");
        temp4 = new JLabel ("None");
        temp5 = new JLabel ("None");
      }
      layout.putConstraint (SpringLayout.WEST, temp2, 160, SpringLayout.WEST, this);
      layout.putConstraint (SpringLayout.WEST, temp3, 280, SpringLayout.WEST, this);
      layout.putConstraint (SpringLayout.WEST, temp4, 400, SpringLayout.WEST, this);
      layout.putConstraint (SpringLayout.WEST, temp5, 520, SpringLayout.WEST, this);
      
      setLabelCool (temp2, 20, y, this, true);
      setLabelCool (temp3, 20, y, this, true);
      setLabelCool (temp4, 20, y, this, true);
      setLabelCool (temp5, 20, y, this, true);
    }
    pauseProgramWriting ();
    add (imageLoader("misc/highscoresback.png"));
  }
  
  /**
   * This method is used to start playing the music of the game.
   * The try block is used to catch any UnsupportedAudioFileExceptions, IOExceptions, or LineUnavailableExceptions that may occur when getting the URL of the music and playing it.
   * @param clip This object reference variable to Clip is used to store the Clip that will be used to play the audio file.
   * @param url This object reference variable to URL is used to store the url of the Music file.
   * @param ais This object reference variable to AudioInputStream is used to store the audio input stream of the url stored.
   * @param e This object reference variable to UnsupportedAudioFileException is used to catch any of those Exceptions when playing the music.
   * @param e This object reference variable to IOException is used to catch any of those Exceptions when getting the url of the music file.
   * @param e This object reference variable to LineUnavailableException is used to catch any of those Exceptions when playing the music.
   * @throws UnsupportedAudioFileException - when playing the music.
   * @throws IOException - when storing the url of the music file.
   * @throws LineUnavailableException  - when playing the music.
   */ 
  public void music() 
  {       
    try
    {
      ais = AudioSystem.getAudioInputStream(GamePanel.class.getResource("/misc/Music.wav"));
      clip = AudioSystem.getClip();
      clip.open(ais);
      FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      gainControl.setValue(-25.0f); 
      clip.loop(Clip.LOOP_CONTINUOUSLY);
      musicOn = true;
    }
    catch (UnsupportedAudioFileException e)
    {} 
    catch (IOException e) 
    {}
    catch (LineUnavailableException e)
    {} 
  }
  
  /**
   * This method is used to select the difficulty and level that the user will play on, as well as input their name. <br>
   * For the nested method mousePressed, the first if else statement is to isolate if they clicked on a difficulty selection or a level selection.
   * The next nested if statement is used to check that the user has actually clicked on one of the words, rather than just in that row.
   * The nested if else if else statement is used to determine where to place a box encircling the users choice, depending on which they clicked. <br>
   * The nested method in the else of the first if else is used to determine if the user has actually clicked on one of the words representing the level options.
   * The nested if else if else statement is then used to determine where to place a box encircling the users choice, depending on which word they clicked.
   * @param e (For the mouseClicked nested method)This object reference variable is used to get the mouse event.
   * @param e (For the mouseEntered nested method)This object reference variable is used to get the mouse event.
   * @param e (For the mouseExited nested method)This object reference variable is used to get the mouse event. 
   * @param e (For the mousePressed nested method)This object reference variable is used to get the mouse event. 
   * @param e (For the mouseReleased nested method)This object reference variable is used to get the mouse event. 
   * @param welcome This object reference variable to JLabel is used to create and display instructions to the user.
   * @param enterName This object reference variable to JLabel is used to prompt the user to enter their name.
   * @param selectDifficulty This object reference variable to JLabel is used to prompt the user to select a difficulty.
   * @param difficulties This object reference variable to JLabel is used to create the difficulties that the user can select.
   * @param selectLevel This object reference variable to JLabel is used to prompt the user to select a level.
   * @param levels This object reference variable to JLabel is used to create the levels that the user can select.
   * @param done This object reference variable to JButton is to be pressed by the user when they have entered their name, the difficulty and the level they want to play.
   * @param menu This object reference variable to JButton is used to direct the user back to main menu, if they don't want to play after all.
   */ 
  public void difficultySelection ()
  {
    title ();
    this.setBackground(new Color (0, 128, 128));
    
    JLabel welcome = new JLabel ("Welcome to the level selection! You can select the options and press the finished button");
    setWriting (welcome);
    layout.putConstraint (SpringLayout.HORIZONTAL_CENTER, welcome, 0, SpringLayout.HORIZONTAL_CENTER, this);
    layout.putConstraint (SpringLayout.NORTH, welcome, 100, SpringLayout.NORTH, this);
    add (welcome);
    
    welcome = new JLabel ("to proceed to the game, or press the main menu button to return to the main menu.");
    setWriting (welcome);
    layout.putConstraint (SpringLayout.HORIZONTAL_CENTER, welcome, 0, SpringLayout.HORIZONTAL_CENTER, this);
    layout.putConstraint (SpringLayout.NORTH, welcome, 125, SpringLayout.NORTH, this);
    
    JLabel enterName = new JLabel ("Please enter your name: ");
    setWriting (enterName);
    layout.putConstraint (SpringLayout.WEST, enterName, 200, SpringLayout.WEST, this);
    layout.putConstraint (SpringLayout.NORTH, enterName, 45, SpringLayout.NORTH, welcome);
    
    nameField = new JTextField (20);
    layout.putConstraint (SpringLayout.WEST, nameField, 25, SpringLayout.EAST, enterName);
    layout.putConstraint (SpringLayout.NORTH, nameField, 49, SpringLayout.NORTH, welcome);
    nameField.requestFocus ();
    
    JLabel selectDifficulty = new JLabel ("Please select a difficulty:");
    setWriting (selectDifficulty);
    layout.putConstraint (SpringLayout.HORIZONTAL_CENTER, selectDifficulty, 0, SpringLayout.HORIZONTAL_CENTER, this);
    layout.putConstraint (SpringLayout.NORTH, selectDifficulty, 75, SpringLayout.NORTH, nameField);
    
    JLabel difficulties = new JLabel ("Easy                          Medium                          Hard");
    setWriting (difficulties);
    layout.putConstraint (SpringLayout.HORIZONTAL_CENTER, difficulties, 0, SpringLayout.HORIZONTAL_CENTER, this);
    layout.putConstraint (SpringLayout.NORTH, difficulties, 40, SpringLayout.NORTH, selectDifficulty);
    
    JLabel selectLevel = new JLabel ("Please select a level:");
    setWriting (selectLevel);
    layout.putConstraint (SpringLayout.HORIZONTAL_CENTER, selectLevel, 0, SpringLayout.HORIZONTAL_CENTER, this);
    layout.putConstraint (SpringLayout.NORTH, selectLevel, 75, SpringLayout.NORTH, difficulties);
    
    JLabel levels = new JLabel ("Virus                          Bacteria                       Parasite");
    setWriting (levels);
    layout.putConstraint (SpringLayout.HORIZONTAL_CENTER, levels, 0, SpringLayout.HORIZONTAL_CENTER, this);
    layout.putConstraint (SpringLayout.NORTH, levels, 40, SpringLayout.NORTH, selectLevel);
    
    JButton done = new JButton ("Finished");
    layout.putConstraint (SpringLayout.WEST, done, 325, SpringLayout.WEST, this);
    layout.putConstraint (SpringLayout.SOUTH, done, -50, SpringLayout.SOUTH, this);
    done.addActionListener (this);
    
    JButton menu = new JButton ("Main Menu");
    layout.putConstraint (SpringLayout.WEST, menu, 70, SpringLayout.EAST, done);
    layout.putConstraint (SpringLayout.SOUTH, menu, -50, SpringLayout.SOUTH, this);
    menu.addActionListener (this);
    
    setButtonCool (menu, 18);
    setButtonCool (done, 18);
    
    add (done);
    frame.getRootPane (). setDefaultButton (done);
    add (menu);
    add (selectLevel);
    add (levels);
    add (difficulties);
    add (enterName);
    add (nameField);
    add (welcome);
    add (selectDifficulty);
    nameField.requestFocus ();    
    
    shouldDraw = true;
    mouseListener = new MouseListener () {
      public void mouseClicked (MouseEvent e)
      {
      }
      public void mouseEntered (MouseEvent e)
      {
      }
      public void mouseExited (MouseEvent e)
      {
      }
      public void mousePressed (MouseEvent e)
      {
        if (e.getY () >= 295 && e.getY () <= 314) //difficulty labels
        {
          if ((e.getX () >= 208 && e.getX () <= 249 && frame.getGameInfo ().getDifficulty () != 1) || (e.getX () >= 406 && e.getX () <= 483 && frame.getGameInfo ().getDifficulty () != 2) || (e.getX () >= 641 && e.getX () <= 686 && frame.getGameInfo ().getDifficulty () != 3))
          {
            if (e.getX () >= 208 && e.getX () <= 249 && frame.getGameInfo ().getDifficulty () != 1) //easy 
              frame.getGameInfo (). setDifficulty (1);
            else if (e.getX () >= 406 && e.getX () <= 483 && frame.getGameInfo ().getDifficulty () != 2)//medium
              frame.getGameInfo (). setDifficulty (2);
            else
              frame.getGameInfo (). setDifficulty (3);
          }
        }
        else
        {
          if (e.getY () >= 410 && e.getY () < 425) //level labels
          {
            if ((e.getX () >= 206 && e.getX () <= 248 && frame.getGameInfo ().getLevel () != 1) || (e.getX () >= 406 && e.getX () <= 485 && frame.getGameInfo ().getLevel () != 2) || (e.getX () >= 619 && e.getX () <= 694 && frame.getGameInfo ().getLevel () != 3)) 
            {
              if (e.getX () >= 206 && e.getX () <= 248 && frame.getGameInfo ().getLevel () != 1) // virus level
                frame.getGameInfo (). setLevel (1);
              else if (e.getX () >= 406 && e.getX () <= 485 && frame.getGameInfo ().getLevel () != 2)//bacteria level
                frame.getGameInfo (). setLevel (2);
              else
                frame.getGameInfo (). setLevel (3);
            }
          }
        }
        repaint ();
      }
      public void mouseReleased (MouseEvent e)
      {
      }
    };
    addMouseListener (mouseListener);
  }
  
  /**
   * This method is used to override the method called by revalidate. It will still do what the original method does, but the if statement will determine if additional actions should be performed.
   * The first nested if structure is used to determine where the rectangle to encircle the difficulty selections should be placed, depending on the users choice.
   * The next nested if structure is used to determine where the rectangle to encircle the level selections should be placed, depending on the users choice.
   * @param g This object reference variable to Graphics is used to control graphics (e.g. drawing shapes, changing colours, etc).
   */
  public void paintComponent (Graphics g)
  {
    super.paintComponent(g);
    if (shouldDraw)
    {
      g.setColor (Color.ORANGE);
      if (frame.getGameInfo (). getDifficulty () == 1)
      {
        g.drawRect(203, 290, 51, 29);
      }
      else if (frame.getGameInfo (). getDifficulty () == 2)
      {
        g.drawRect(401, 290, 87, 29);
      }
      else
      {
        if (frame.getGameInfo (). getDifficulty () == 3)
          g.drawRect(636, 290, 55, 29);
      }
      if (frame.getGameInfo (). getLevel () == 1)
      {
        g.drawRect(196, 405, 52, 25);
      }
      else if (frame.getGameInfo (). getLevel () == 2)
      {
        g.drawRect(396, 405, 89, 25);
      }
      else
      {
        if (frame.getGameInfo (). getLevel () == 3)
          g.drawRect(614, 405, 85, 25);
      }
    }
  }
  
  /**
   * This helper method is used to determine if the user has entered a legitimate name, selected a difficulty and selected a level. If not, it returns a number depending on what they did not enter correctly.
   * The if statements are used to determine if the input by the user is invalid or not (if the name is more than 10 letters or 0, and has non-letter and non-number characters or if a level/diffiulty is not selected). Depending on the type of input error, a number is returned.
   * @return int - returns the error number.
   */
  private int inputIsValid ()
  {
    String name = nameField.getText ();
    if (name.indexOf ('"') != -1 || name.indexOf ("!") != -1 || name.indexOf ("#") != -1 || name.indexOf ("$") != -1 || name.indexOf ("%") != -1 || name.indexOf ("&") != -1 || name.indexOf ("'") != -1 || name.indexOf ("(") != -1 || name.indexOf (")") != -1 || name.indexOf ("*") != -1 || name.indexOf ("+") != -1 || name.indexOf (",") != -1 || name.indexOf ("-") != -1 || name.indexOf (".") != -1 || name.indexOf ("/") != -1 || name.indexOf (":") != -1 || name.indexOf (";") != -1 || name.indexOf ("<") != -1 || name.indexOf ("=") != -1 || name.indexOf (">") != -1 || name.indexOf ("?") != -1 || name.indexOf ("@") != -1 || name.indexOf ("[") != -1 || name.indexOf ("]") != -1 || name.indexOf ("^") != -1 || name.indexOf ("_") != -1 || name.indexOf ("`") != -1 || name.indexOf ("{") != -1 || name.indexOf ("}") != -1 || name.indexOf ("|") != -1 || name.indexOf ("~") != -1 || name.length () > 10 || name.equals (""))
      return 1; //signifies that there is a problem with the name input
    else if (frame.getGameInfo ().getDifficulty () == 0 || frame.getGameInfo ().getLevel () == 0)
      return 2; //signifies that there is a problem with the level/ difficulty selection
    return 3;
  }
  
  /**
   * This helper method sets the font to a specified label to Century Gothic, size 20 font.
   * @param label This object reference variable to JLabel is used to store the label that is having it's font set.
   */
  private void setWriting (JLabel label)
  {
    label.setForeground (Color.WHITE);
    label.setFont (new Font("Century Gothic", Font.PLAIN, 20)); 
  }
  
  /**
   * This overriden method is used to detect any actions that the user performs on the JPanel.
   * The if structure is used to detect which particular action was performed by the user (detects which menu item was pressed, and calls a specific method depending on which one was pressed).
   * The first nested if structure is used to check which option in the settings or highscores screen was selected. Based on the selection of the button, an action is performed and the focus is back on the JPanel.
   * The second nested if structure is used to which menu option (settings, highscores, or instructions) was selected, and perform a specific action for each action detected. After settings, highscores, or instructions are displayed, then the program is paused.
   * The third nested if structure is used to check if the input entered for the user input screen was valid. If not, error messages are displayed, otherwise, the game is started.
   * The most nested if structure (within the third nested if structure) is used to check how the user input didn't enter information properly (name input or level/difficulty input). A specific message is outputted for each.
   * @param ae This object reference variable to ActionEvent is used to detect which particular action was performed by the user in the JPanel.
   */ 
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getActionCommand (). equals ("Quit"))
      goodBye ();
    else if (ae.getActionCommand (). equals ("Turn On Learning Mode") || ae.getActionCommand (). equals ("Turn Off Learning") || ae.getActionCommand (). equals ("Turn On Music") || ae.getActionCommand (). equals ("Turn Off Music") || ae.getActionCommand (). equals ("Press Me To Print."))
    {
      if (ae.getActionCommand (). equals ("Turn On Music"))
      {
        musicOnOff.setText ("Turn Off Music");
        if (!musicOn)
          music ();
      }
      else if (ae.getActionCommand (). equals ("Turn Off Music"))
      {
        musicOnOff.setText ("Turn On Music");
        clip.close ();
        musicOn = false;
      }
      else if (ae.getActionCommand (). equals ("Turn On Learning Mode"))
      {
        learningOnOff.setText ("Turn Off Learning");
        frame.getGameInfo ().setShouldTeach (true);
      }
      else if (ae.getActionCommand (). equals ("Turn Off Learning"))
      {
        learningOnOff.setText ("Turn On Learning Mode");
        frame.getGameInfo ().setShouldTeach (false);
      }
      else
      {
        new HighScoresPrinter ().printHighscores ();
      }
      requestFocusInWindow ();
    }
    else if (ae.getActionCommand (). equals ("Instructions") || ae.getActionCommand().equals ("Settings") || ae.getActionCommand().equals ("High Scores"))
    { 
      if (ae.getActionCommand().equals ("Settings"))
        settings ();
      else if (ae.getActionCommand().equals ("High Scores"))
        highScore ();
      else
        instructions ();
      pauseProgram (1);
    }
    else if (ae.getActionCommand (). equals ("Finished"))
    {
      if (inputIsValid () == 3)
      {
        frame.getGameInfo ().setName (nameField.getText());
        shouldDraw = false;
        play ();
        removeMouseListener (mouseListener);
      }
      else
      {
        if (inputIsValid () == 2)
          JOptionPane.showMessageDialog (null, "Please make sure that you have chosen a difficulty and a level.", "Input Error", JOptionPane.ERROR_MESSAGE);
        else
        {
          JOptionPane.showMessageDialog (null, "Please enter a name that only contains numbers and letters and is up 10 characters long.", "Input Error", JOptionPane.ERROR_MESSAGE);
          nameField.setText ("");
          nameField.requestFocus ();
        }
      }
    }
    else if (ae.getActionCommand (). equals ("Main Menu"))
    {
      mainMenu ();
      removeMouseListener (mouseListener);
    }
    else 
      difficultySelection ();
    revalidate ();
    repaint ();
  }
}