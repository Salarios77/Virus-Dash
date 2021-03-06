package highscores;
import java.io.*;
import java.util.ArrayList;

/**
 * This class is used to load all highscores, and add more highscores. 
 * Features of Version 1.0:
 * Highscores can be loaded (in constructor), checked to see if they can come on the list (canInsert (HighScore score) method), add to the list (insert (HighScore score) method), and the highscores file can be cleared - newFile () method.
 * Hours spent on this class: 2 hours
 * @author Salar Hosseini & Sophia Weng
 * @version 5.0, June 12, 2014
 */ 
public class HighScoreLoader
{
  /**
   * This object reference variable to ArrayList is used to create a list of HighScore objects - the highest scores achieved in the game.
   */ 
  private ArrayList <HighScore> highScores = new ArrayList <HighScore> ();
  /**
   * This final integer will store the maximum number of highscores that can be stored.
   */ 
  private final int MAX_HIGHSCORES = 10;
  /**
   * This integer store the number of scores that there are in the list.
   */ 
  private int numScores = 0;
  /**
   * This final String variable will store the header of the GameData.LT file.
   */ 
  private final String HEADER = "Hi";
  
  /**
   * This is the class constructor which will load all scores that were achieved before, or create a new file for the highscores if there is an error with the file.
   * The first try block is used to catch any IOExceptions or NullPointerExceptions when reading from the highscores file.
   * The first if statement (nested within try block) checks to see if the current header stored matches the correct header. If so, reading of scores continues, otherwise, the file is overriden by a new highscores file.
   * The next try block (nested within first if statement) is used to catch any NumberFormatExceptions that may occur when reading & parsing the second line of the file (the number of highscores).
   * The for loop (nested within first if statement) is used to read all of the highscores that have been stored in the highscores file. The loop variable starts at 0, increments by 1 while it is less than the total number of highscores.
   * The next try block (nested within for loop) is used to catch any NumberFormatExceptions or NullPointerExceptions that may occur when reading in the highscores stored.
   * @param in This object reference variable to BufferedReader will be used to read in the scores from a file.
   * @param e This object reference variable to NumberFormatException will be used to catch any of these Exceptions when parsing occurs.
   * @param x This integer is a for loop variable that starts at 0, and increments up to the number of highscores stored by 1.
   * @param e This object reference variable to NumberFormatException will be used to catch any of these Exceptions when parsing occurs.
   * @param e This object reference variable to NullPointerException will be used to catch any of these Exceptions when reading from the highscores file.
   * @param e This object reference variable to IOException will be used to catch any of these Exceptions when reading from the highscores file.
   * @param e This object reference variable to NullPointerException will be used to catch any of these Exceptions when reading from the highscores file.
   * @throws NumberFormatException when parsing occurs.
   * @throws IOException when reading from the highscores file.
   * @throws NullPointerException when reading from the highscores file.
   */ 
  public HighScoreLoader ()
  {
    try
    {
      BufferedReader in = new BufferedReader (new FileReader ("highscores/GameData.LT"));
      if (in.readLine (). equals (HEADER))
      {
        try
        {
          numScores = Integer.parseInt (in.readLine ());
        }
        catch (NumberFormatException e)
        {}
        for (int x = 0; x<numScores; x++)
        {
          try
          {
            highScores.add (new HighScore (in.readLine (), Integer.parseInt (in.readLine ()), in.readLine (), in.readLine ()));
          }
          catch (NumberFormatException e)
          {
            break;
          }
          catch (NullPointerException e)
          {
            break;
          }
        }
      }
      else
        newFile ();
      in.close ();
    }
    catch (IOException e)
    {
      e.printStackTrace ();
      newFile ();
    }
    catch (NullPointerException e)
    {
      newFile ();
    }
  }
  
  /**
   * This method is used to create (and possibly overwrite) a new highscores file which is empty except for the header and the number of scores.
   * The try block is used to catch any IOExceptions that may occur when writing to the file.
   * @param out This object reference variable to PrintWriter is used to write to the highscores file.
   * @param e This object reference variable to IOException is used to catch any IOExceptions when writing the file.
   * @throws IOException when writing to a file.
   */ 
  public void newFile ()
  {
    try
    {
      PrintWriter out = new PrintWriter (new FileWriter ("highscores/GameData.LT"));
      out.println (HEADER);
      out.println (0);
      out.close ();
    }
    catch (IOException e)
    {
    }
  }
  
  /**
   * This method returns all of the highscores that have been recorded.
   * @return ArrayList <HighScore> which is the highscores stored.
   */ 
  public ArrayList <HighScore> getHighScores ()
  {
    return highScores;
  }
  
  /**
   * This method is used to insert a score into the list of highscores and update the highscores file.
   * The first for loop is used to navigate through the list of highscores, and find the first highscore that is less than the score to be stored. The loop variable starts at 0, and increments by 1 while it is less than the total number of highscores.
   * The first if statement (nested within for loop) is used to check if the current position (stored by the for loop variable) in the list has a lower score than the score to be inserted. If so, then the newest score is inserted at that position, and the for loop is stopped.
   * The second if statement (nested within first if statement) is used to check if the total number of scores is equal to the max number of scores allowed, if so then the last score is removed.
   * The third if statement (not nested within anything) is used to check if the variable used to keep track of the for loop position is equal to the number of highscores stored (a.k.a if the score to be inserted was not added yet). If so, then the score to be added is added to the last position of the list.
   * The fourth if statement is used to check if the number of scores stored are not equal to the max number of scores permitted. If so, the number of scores is increased.
   * The try block is used to catch any IOExceptions that may occur when writing to the highscores file.
   * The second for loop (within the try block) is used to navigate through the highscores stored, and store them all into the highscores file. The loop variable starts at 0, and increments while it is less than the total number of scores.
   * @param score This object reference variable to HighScore stores the score to be inserted.
   * @param x This integer is used to keep track of the position on the for loop. It starts at 0, and the loop iterates while it is less than the number of highscores already stored. It is not a for loop variable because if it increments to be equal to the number of highscores, then that means that no highscore was added to the for loop, and this is used after the for loop.
   * @param out This object reference variable to PrintWriter is used to write to a file.
   * @param y This integer is used to navigate through the list of highscores. It starts at 0, and increments by 1 until it is no longer less than the number of highscores already stored.
   * @param e This object reference variable to IOException will be used to catch any of these Exceptions when writing to the highscores file.
   * @throws IOException when writing to a file.
   */ 
  public void insert (HighScore score)
  {
    int x;
    for (x = 0; x<numScores; x++)
    {
      if (highScores.get (x).getScore () <= score.getScore ())
      {
        if (numScores == MAX_HIGHSCORES)
          highScores.remove (MAX_HIGHSCORES-1);
        highScores.add (x, score);
        break;
      }
    }   
    if (x == numScores)
      highScores.add (score);
    if (numScores != MAX_HIGHSCORES)
      numScores ++;
    try
    {
      PrintWriter out = new PrintWriter (new FileWriter ("highscores/GameData.LT"));
      out.println (HEADER);
      out.println (numScores);
      for (int y = 0; y< numScores; y++)
      {
        out.println (highScores.get (y).getName ());
        out.println (highScores.get (y).getScore ());
        out.println (highScores.get (y).getDifficulty ());
        out.println (highScores.get (y).getLevel ());
      }
      out.close ();
    }
    catch (IOException e)
    {
      e.printStackTrace ();
    }
  }
  
  /**
   * This method is used to check if another highscore can be added to the list of highscores.
   * The if statement is used to check if there are less than the maximum number of highscores permitted, of if there are exactly the maximum number permitted but the last highscore stored is less than the highscore to be added. If so, then true is returned. Otherwise, false is returned immediately after.
   * @param score This object reference variable to HighScore stores the HighScore object that may be added to the list of highscores.
   * @return boolean - returns true or false depending on whether or not another score can be added.
   */ 
  public boolean canInsert (HighScore score)
  {
    if ((numScores == MAX_HIGHSCORES && score.getScore () > highScores.get (highScores.size ()-1).getScore ()) || numScores < MAX_HIGHSCORES)
      return true;
    return false;
  }
}