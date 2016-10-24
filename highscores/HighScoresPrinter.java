package highscores;
import java.awt.*;
import javax.swing.*;
import java.awt.print.*;
import java.util.ArrayList;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;

/**
 * This class is used to print the top 10 highscores.
 * New features of Version 2.0: 
 * This entire class was created, and it can print the top 10 highscores (in a formatted way [title bolded, highscores in table]). The printHighscores () method needs to be called to print the highscores, and the print method is defined (from the Printable class - it does the actual printing). The drawText method is used to make the code for printing some lines on the page (that will be printed) more KISS - helper method.
 * Time spent on this class: about 3 hours.
 * @author Salar Hosseini & Sophia Weng
 * @version 5.0, June 12, 2014
 */ 
public class HighScoresPrinter implements Printable
{
  /**
   * This method is used to print the top 10 highscores.
   * This first if structure is used to check if the pageIndex is too large or not. If it is, then the static variable NO_SUCH_PAGE is returned to signify that the request page does not exist.
   * The try block is used to catch any IOExceptions that may occur when storing the picture of the company logo.
   * The for loop is used to move through all top 10 highscores, and display each of them in the correct location on the page to be printed. The x for loop variable keeps track of which of the top ten scores is being printed (starts at 0, increments by 1 while less than the maximum number of highscores [10]), and the y for loop variable keeps track of the spacing on the page (starts at 160, increments by 15).
   * The next try block (nested within for loop) is used to catch any IndexOutOfBoundsExceptions that may occur when getting each index of the ArrayList which stores the top ten HighScores (HighScore class). If it does catch an exception, in the spot where a high score is supposed to be, "None" is written to signify that there are no more highscores.
   * @param g This object reference variable to Graphics is used to draw all of the text and image(s) that will be displayed on the page to be printed.
   * @param pf This object reference variable to PageFormat is used to store the format of the page that will be printed.
   * @param page This integer is used to store the index of the page.
   * @param g2d This object reference variable to Graphics2D is used to store a more advanced type of 2D Graphics that will be used to translate the x and y values of the imageable values since the user (who is at 0,0) is out of the screen.
   * @param x2 This integer is used to store the x position of the first highscore to be printed.
   * @param pic This object reference variable to BufferedImage is used to store the image of the logo that will be displayed on the pritned page.
   * @param e This object reference variable to IOException is used to catch any IOExceptions that may occur in the try block (when storing the BufferedImage of the logo).
   * @param current This object reference variable to HighScore is used to keep track of which HighScore is currently being printed in the for loop (stores that HighScore).
   * @param x This integer is a for loop variable that is used to keep track of the iterations of the for loop (and how many highscores are printed). It starts at 0, and increments by 1 while it is less than the maximum number of highscores.
   * @param y This integer is used to keep track of the spacing of the top 10 highscores. It is a for loop variable that starts at 160, and increments by 15 while the other for loop variable (x) is less than the maximum number of highscores.
   * @param e This object reference variable to IndexOutOfBoundsException is used to catch any of those Exceptions when accessing the ArrayList of Highscores that will be displayed in top 10. If an exception is caught, then "None" will be displayed to signify that there are no more highscores.
   * @param d This object reference variable to Dimension is used to store the dimensions of the page to be printed.
   * @param hs This object reference variable to HighScoreLoader is used to store all of the top 10 highscores, and prepare them all to be accessed.
   * @param scores This object reference variable to ArrayList (generic type <HighScore>) will store all of the HighScore objects (the top highscores).
   * @throws IOException when storing a picture that will be displayed on the page to be printed.
   * @throws IndexOutOfBoundsException when accessing the indexes within the ArrayList storing the individual highscores to be shown.
   * @return int An integer is returned to signify if whether or not the page request was fully rendered or not.
   */ 
  public int print(Graphics g, PageFormat pf, int page) throws PrinterException
  {
    if (page > 0) 
      return NO_SUCH_PAGE;
    
    Graphics2D g2d = (Graphics2D)g;
    g2d.translate(pf.getImageableX(), pf.getImageableY());
    Dimension d = new Dimension ((int) (pf.getImageableWidth()), (int) (pf.getImageableHeight()));
    
    drawText (g, Font.BOLD, 18, "Top 10 Highscores of Virus Dash", 75, d);
    drawText (g, Font.PLAIN, 15, "Created by Salar Hosseini & Sophia Weng", 93, d);
    drawText (g, Font.PLAIN, 15, "From file: GameData.LT", 111, d);
    int x2 = drawText (g, Font.BOLD, 13, "Place                Name                Difficulty                 Level                Score", 140, d);
    
    BufferedImage pic;
    try 
    {
      pic = ImageIO.read(new File("misc/translupus2.png"));
      g.drawImage (pic, 390, 15, null);
    }
    catch (IOException e)
    {}
    
    g.setFont (new Font("Agency FB",Font.PLAIN,12));
    
    HighScoreLoader hs = new HighScoreLoader ();
    ArrayList <HighScore> scores = hs.getHighScores ();
    HighScore current;
    
    for (int x = 0, y = 160; x<10; x++, y += 15)
    {
      try
      {
        current = scores.get (x);
        g.drawString ("" + (x+1), x2, y);
        g.drawString(current.getName (), 205, y);
        g.drawString(current.getDifficulty (), 277, y);
        g.drawString(current.getLevel (), 359, y);
        g.drawString("" + current.getScore (), 426, y);
      }
      catch (IndexOutOfBoundsException e)
      {
        g.drawString ("" + (x+1), x2, y);
        g.drawString("None", 205, y);
        g.drawString("None", 277, y);
        g.drawString("None", 359, y);
        g.drawString("None", 426, y);            
      }
    }
    return PAGE_EXISTS;
  }
  
  /**
   * This private method is a helper method that will be used to draw some of the text that will be printed and centered.
   * @param g This object reference variable to Graphics is used to draw the Strings on the page.
   * @param fontStyle This integer is used to store what style (PLAIN, BOLD, or ITALIC) that text to be printed will be printed in.
   * @param fontSize This integer is used to store what font size will be used to print the text.
   * @param printString This object reference variable to String will be used to store the string that will be printed.
   * @param yLoc This integer is used to store what the y position of the text to be printed is.
   * @param d This integer is used to store the Dimensionss of the page that will be printed.
   * @param x2 This integer is used to store the x position of the Strings that will be printed.
   * @return integer The integer that will be returned is the x position of the text that is drawn.
   */ 
  private int drawText (Graphics g, int fontStyle, int fontSize, String printString, int yLoc, Dimension d)
  {
    g.setFont (new Font ("Agency FB", fontStyle, fontSize));
    int x2 = d.width/2 - g.getFontMetrics().stringWidth (printString)/2;
    g.drawString (printString, x2, yLoc);
    return x2;
  }
  
  /**
   * This method is used to start the printing of the highscores.
   * The if statement is used to check if it is ok to start printing the page by calling the printDialog () method which will open the actual print dialog. If it is ok to start, then the page-to-be-printed is printed.
   * The try block is used to catch any PrinerExceptions that may occur when printing the page.
   * @param job This object reference variable to PrinterJob is used to store a new PrinterJob that will allow the user to print the top 10 highscores.
   * @param e This object reference variable to PrinterException is used to store a PrinterException, and catch any of that Exception that may occur when printing the page.
   */ 
  public void printHighscores () 
  {
    PrinterJob job = PrinterJob.getPrinterJob();
    job.setPrintable(this);
    if (job.printDialog()) //if ok to start printing
    {
      try 
      {
        job.print();
      } 
      catch (PrinterException e) 
      {
        JOptionPane.showMessageDialog (null, "An Error has occured. Please make sure that you are connected to your printer, and it is ready to print.");
      }
    }
  }
}