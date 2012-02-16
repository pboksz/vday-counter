import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * {NAME}
 * Author: Phillip Boksz
 * Date: 2/14/12
 * Time: 12:33 PM
 */
public class LoveCounter
{

   private GregorianCalendar startCal;
   private JLabel howMuchTime;

   /**
    * Default constructer
    */
   public LoveCounter()
   {
      TimeZone est = TimeZone.getTimeZone("US/Eastern");
      GregorianCalendar startCal = new GregorianCalendar(est);
      startCal.set(2010, Calendar.JULY, 2, 2, 0, 0);
      this.startCal = startCal;
   }

   /**
    * Constructor for testing
    * @param startCal starting calendar to use
    */
   public LoveCounter(GregorianCalendar startCal){
      this.startCal = startCal;
   }

   /**
    * Method that returns a string of how long between the date in the constructor and current date
    * @param currentCal the calendar of the current datetime
    * @return returns string of years, months, days, hours, minutes, and seconds between two dates
    */
   public String timeDating(GregorianCalendar currentCal)
   {
      //initialize a bunch of numbers used
      long secondsPerYear = 60 * 60 * 24 * 365;
      long secondsPerDay = 60 * 60 * 24;
      long secondsPerHour = 60 * 60;
      long secondsPerMinute = 60;

      //get the current date
      Date currentDate = currentCal.getTime();
      Date startDate = startCal.getTime();

      //difference between the dates in seconds
      long difference = (currentDate.getTime() - startDate.getTime()) / 1000;

      //get the years from the seconds
      long years = difference / secondsPerYear;
      long remYears = difference % secondsPerYear;

      //get the total amount of days from years remainder
      long totalDays = remYears / secondsPerDay;
      long remDays = remYears % secondsPerDay;

      //convert the total days into months and days
      MonthDayObject mdo = getMonths(totalDays, startCal.get(Calendar.MONTH));
      long months = mdo.getMonth();
      long days = mdo.getDays();

      //get the hours from days remainder
      long hours = remDays / secondsPerHour;
      long remHours = remDays % secondsPerHour;

      //get the minutes and seconds from hours remainder
      long minutes = remHours / secondsPerMinute;
      long seconds = remHours % secondsPerMinute;

      return years + " years, " + months + " months, " + days + " days, " + hours + " hours, " + minutes + " minutes, " + seconds + " seconds";
   }

   /**
    * Gets month and days from total days.
    * Accounts for specific month totals.
    * Accounts for leap years.
    * @param totalDays the total days from year remainder
    * @param startMonth the start month
    * @return a MonthDayObject that holds the month and day
    */
   private MonthDayObject getMonths(long totalDays, int startMonth)
   {
      long[] daysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

      long monthDays = 0;
      long tempDays = 0;
      MonthDayObject mdo = new MonthDayObject();

      for (int i = 0; i < 12; i++)
      {
         tempDays += daysPerMonth[startMonth];
         if (tempDays > totalDays)
         {
            mdo.setMonth(i);
            mdo.setDays(totalDays-monthDays);
            break;
         }
         else
         {
            monthDays += daysPerMonth[startMonth];
         }
         if(startMonth < 11)
         {
            startMonth++;
         }
         else
         {
            startMonth = 0;
         }
      }
      return mdo;
   }

   /**
    * Adds content to the main swing frame and updates the label every second so its counting up
    * @param container frame container
    */
   private void addContentsToFrame(Container container)
   {
      JPanel panel = new JPanel();
      panel.setPreferredSize(new Dimension(620, 100));
      container.add(panel);

      howMuchTime = new JLabel();
      howMuchTime.setPreferredSize(new Dimension(600, 86));
      howMuchTime.setHorizontalAlignment(JLabel.CENTER);
      Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
      TitledBorder titledBorder = BorderFactory.createTitledBorder(border, "How Long Has Our Love Has Bloomed?");
      titledBorder.setTitleJustification(TitledBorder.CENTER);
      howMuchTime.setBorder(titledBorder);

      panel.add(howMuchTime);

      ActionListener actionListener = new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            GregorianCalendar currentCal = new GregorianCalendar();
            howMuchTime.setText(timeDating(currentCal));
         }
      };

      Timer timer = new Timer(1000, actionListener);
      timer.setRepeats(true);
      timer.start();
   }

   public static void main(String[] args)
   {
      LoveCounter lc = new LoveCounter();
      JFrame frame = new JFrame("LoveCounter");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      lc.addContentsToFrame(frame.getContentPane());
      frame.pack();
      frame.setVisible(true);
   }
}
