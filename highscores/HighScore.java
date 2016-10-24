package highscores;
/**
 * This class is used to store an individual highscore.
 * Features of Version 1.0:
 * The constructor with multiple parameters is created to take in all information about the person who achieved the highscore. 
 * The accessor methods are all created and no mutator methods are required.
 * Hours spent on this class: 30 minutes.
 * @author Salar Hosseini & Sophia Weng
 * @version 5.0, June 12, 2014
 */ 
public class HighScore
{
  /**
   * This private String object reference will store the name the person who achieved this score.
   */ 
  private String name;
  /**
   * This private integer will store the score that was achieved.
   */
  private int score;
  /**
   * This private String object reference will store the difficulty that the score was achieved with.
   */
  private String difficulty;
  /**
   * This private String object reference will store the level that the score was achieved with.
   */
  private String level;
  
  /**
   * This is the class constructor (with four parameters).
   * @param name2 This String object reference will store the name that achieved a particular score.
   * @param score2 This integer will store the score that was achieved.
   * @param difficulty2 This String object reference will store the difficulty that the score was achieved on.
   * @param level2 This String object reference will store the level that the score was achieved on.
   */ 
  public HighScore (String name2, int score2, String difficulty2, String level2)
  {
    name = name2;
    score = score2;
    difficulty = difficulty2;
    level = level2;
  }
  
  /**
   * This accessor method will return the name that achieved this highscore.
   * @return String - The name of the person with this highscore.
   */ 
  public String getName ()
  {
    return name;
  }
  
  /**
   * This accessor method will return the score that achieved.
   * @return int - the score achieved.
   */ 
  public int getScore ()
  {
    return score;
  }
  
  /**
   * This accessor method will return the difficulty that the score was achieved on.
   * @return String - The difficulty that the score was achieved on.
   */ 
  public String getDifficulty ()
  {
    return difficulty;
  }
  
  /**
   * This accessor method will return the level that the score was achieved on.
   * @return String - The level that the score was achieved on.
   */ 
  public String getLevel ()
  {
    return level;
  }
}