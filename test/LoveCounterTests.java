import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * {NAME}
 * Author: Phillip Boksz
 * Date: 2/15/12
 * Time: 12:07 PM
 */
public class LoveCounterTests
{
   @Test
   public void testCounterSameTime()
   {
      //given
      GregorianCalendar timeCal = new GregorianCalendar(2012, Calendar.JANUARY, 1);
      //when
      LoveCounter lc = new LoveCounter(timeCal);
      String time = lc.timeDating(timeCal);
      //then
      System.out.println(time);
      assertEquals(time, "0 years, 0 months, 0 days, 0 hours, 0 minutes, 0 seconds");
   }

   @Test
   public void testCounterSingleSecond()
   {
      //given
      GregorianCalendar startCal = new GregorianCalendar(2012, Calendar.JANUARY, 1, 0, 0, 0);
      GregorianCalendar endCal = new GregorianCalendar(2012, Calendar.JANUARY, 1, 0, 0, 1);
      //when
      LoveCounter lc = new LoveCounter(startCal);
      String time = lc.timeDating(endCal);
      //then
      System.out.println(time);
      assertEquals(time, "0 years, 0 months, 0 days, 0 hours, 0 minutes, 1 seconds");
   }

   @Test
   public void testCounterSingleMinute()
   {
      //given
      GregorianCalendar startCal = new GregorianCalendar(2012, Calendar.JANUARY, 1, 0, 0, 0);
      GregorianCalendar endCal = new GregorianCalendar(2012, Calendar.JANUARY, 1, 0, 1, 0);
      //when
      LoveCounter lc = new LoveCounter(startCal);
      String time = lc.timeDating(endCal);
      //then
      System.out.println(time);
      assertEquals(time, "0 years, 0 months, 0 days, 0 hours, 1 minutes, 0 seconds");
   }

   @Test
   public void testCounterSingleHour()
   {
      //given
      GregorianCalendar startCal = new GregorianCalendar(2012, Calendar.JANUARY, 1, 0, 0, 0);
      GregorianCalendar endCal = new GregorianCalendar(2012, Calendar.JANUARY, 1, 1, 0, 0);
      //when
      LoveCounter lc = new LoveCounter(startCal);
      String time = lc.timeDating(endCal);
      //then
      System.out.println(time);
      assertEquals(time, "0 years, 0 months, 0 days, 1 hours, 0 minutes, 0 seconds");
   }

   @Test
   public void testCounterSingleDay()
   {
      //given
      GregorianCalendar startCal = new GregorianCalendar(2012, Calendar.JANUARY, 1);
      GregorianCalendar endCal = new GregorianCalendar(2012, Calendar.JANUARY, 2);
      //when
      LoveCounter lc = new LoveCounter(startCal);
      String time = lc.timeDating(endCal);
      //then
      System.out.println(time);
      assertEquals(time,"0 years, 0 months, 1 days, 0 hours, 0 minutes, 0 seconds");
   }

   @Test
   public void testCounterSingleMonth()
   {
      //given
      GregorianCalendar startCal = new GregorianCalendar(2012, Calendar.JANUARY, 1);
      GregorianCalendar endCal = new GregorianCalendar(2012, Calendar.JANUARY, 31);
      //when
      LoveCounter lc = new LoveCounter(startCal);
      String time = lc.timeDating(endCal);
      //then
      System.out.println(time);
      assertEquals(time, "0 years, 0 months, 30 days, 0 hours, 0 minutes, 0 seconds");
   }

   @Test
   public void testCounterSingleMonth1stTo1st()
   {
      //given
      GregorianCalendar startCal = new GregorianCalendar(2012, Calendar.JANUARY, 1);
      GregorianCalendar endCal = new GregorianCalendar(2012, Calendar.FEBRUARY, 1);
      //when
      LoveCounter lc = new LoveCounter(startCal);
      String time = lc.timeDating(endCal);
      //then
      System.out.println(time);
      assertEquals(time, "0 years, 1 months, 0 days, 0 hours, 0 minutes, 0 seconds");
   }

   @Test
   public void testCounterSingleYear()
   {
      //given
      GregorianCalendar startCal = new GregorianCalendar(2012, Calendar.JANUARY, 1);
      GregorianCalendar endCal = new GregorianCalendar(2013, Calendar.JANUARY, 1);
      //when
      LoveCounter lc = new LoveCounter(startCal);
      String time = lc.timeDating(endCal);
      //then
      System.out.println(time);
      //Leap year not accounted for!
      assertEquals(time, "1 years, 0 months, 1 days, 0 hours, 0 minutes, 0 seconds");
   }
}
