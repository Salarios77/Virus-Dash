package game;

/**
 * This class is used to store the information about the user and the level, difficulty, and settings that they are playing with.
 * Features of Version 5.0: This class was created to store the settings that the user is playing with.
 * Time spent on this class: 10 min.
 * @author Salar Hosseini & Sophia Weng
 * @version 5.0, June 12, 2014
 */ 
public class GameInfo
{
  /**
   * This object reference variable to String is used to store the name of the user playing.
   */
  private String name = "";
  /**
   * This integer is used to store the difficulty that the user selects.
   */
  private int difficulty;
  /**
   * This integer is used to store the level that the user selects.
   */
  private int level;
  /**
   * This boolean is used to store true if the program is on learning mode (the user is learning), and false if vice versa.
   */ 
  private boolean shouldTeach;
  /**
   * This boolean is used to store true if information about white blood cells should be taught, and false if vice versa.
   */ 
  private boolean shouldTeachWbc;
  
  /**
   * This is the class constructor which is used to assign values to only 2 of the data fields (the two booleans).
   * @param shouldTeach This boolean is used to store true if the program is on learning mode (the user is learning), and false if vice versa.
   * @param shouldTeachWbc This boolean is used to store true if information about white blood cells should be taught, and false if vice versa.
   */ 
  public GameInfo (boolean shouldTeach, boolean shouldTeachWbc)
  {
    this.shouldTeach = shouldTeach;
    this.shouldTeachWbc = shouldTeachWbc;
  }
  
  /**
   * This accessor method is used to get the name entered by the user while they are playing.
   * @return String The username entered by the user is returned.
   */ 
  public String getName ()
  {
    return name;
  }
  
  /**
   * This accessor method is used to get the difficulty that the user is playing on in the game.
   * @return int The difficulty of the game is returned as an integer representation (1 = easy, 2 = normal, 3 = hard).
   */ 
  public int getDifficulty ()
  {
    return difficulty;
  }
  
  /**
   * This accessor method is used to get the level that the user is playing on in the game.
   * @return int The level of the game is returned as an integer representation (1 = virus, 2 = bacteria, 3 = parasite).
   */ 
  public int getLevel ()
  {
    return level;
  }
  
  /**
   * This accessor method is used to get if the learning mode of the game is on or not.
   * @return boolean This boolean stores true if the learning mode of the game is on, and false if vice versa.
   */ 
  public boolean getShouldTeach ()
  {
    return shouldTeach;
  }
  
  /**
   * This accessor method is used to get if the white blood cell teaching of the game is on or not.
   * @return boolean This boolean stores true if the white blood cell teaching of the game is on, and false if vice versa.
   */ 
  public boolean getShouldTeachWbc ()
  {
    return shouldTeachWbc;
  }
  
  /**
   * This mutator method is used to set the username of the user to whatever they enter in the user input.
   * @param name This object reference variable to String is used to store the user name of the user.
   */ 
  public void setName (String name)
  {
    this.name = name;
  }
  
  /**
   * This mutator method is used to set the difficulty that the user plays on during the game. 
   * @param difficulty This integer is used to store the integer representation of the difficulty that the user is playing on during the game.
   */ 
  public void setDifficulty (int difficulty)
  {
    this.difficulty = difficulty;
  }
  
  /**
   * This mutator method is used to set the level that the user plays on during the game. 
   * @param level This integer is used to store the integer representation of the level that the user is playing on during the game.
   */ 
  public void setLevel (int level)
  {
    this.level = level;
  }
  
  /**
   * This mutator method is used to set if the game should be teaching the user at all.
   * @param shouldTeach This boolean stores true if the learning mode of the game is on, and false if vice versa.
   */ 
  public void setShouldTeach (boolean shouldTeach)
  {
    this.shouldTeach = shouldTeach;
  }
  
  /**
   * This mutator method is used to set if the game should be teaching the user about white blood cells.
   * @param shouldTeachWbc This boolean stores true if the white blood cell teaching of the game is on, and false if vice versa.
   */ 
  public void setShouldTeachWbc (boolean shouldTeachWbc)
  {
    this.shouldTeachWbc = shouldTeachWbc;
  }
}