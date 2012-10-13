package Java;
import java.util.*;
import java.io.*;
public class Film
{
   private String title;
   private String genre;
   private int screenings;
   private int attendance;

   public Film(String title,String genre,int screenings,int attendance)
   {
      this.title = title;
      this.genre = genre;
      this.screenings = screenings;
      this.attendance = attendance;
   }
    public String toString()
    {
       return "title" + title + "genre" + screenings + "attendance" +attendance;
    }

    public String getTitle()
    {
       return title;
    }

    public String getGenre()
    {
       return genre;
    }

    public int getScreenings()
    {
       return screenings;
    }

    public int getAttendance()
    {
       return attendance;
    }

    public void setScreenings(int s)
    {
       this.screenings = s;
    }

    public void setAttendance(int a)
    {
       this.attendance = a;
    }

    public double calcaerage()
    {
       if(screenings == 0 || attendance == 0)
       {
          return -1;
       }
       else
       {
          double avr = attendance/screenings;
          return avr;
       }
    }
}
      
