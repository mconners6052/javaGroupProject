package groupproject;

import java.io.IOException;
import java.io.PrintWriter;

public class Flight 
{
    private int availableSeats;
    private String flightNumber, departureCity, destinationCity, flightDate, departureTime, arrivalTime;
    char[][] seats = new char[10][8];
    
    public Flight (int availableSeats, String flightNumber, String departureCity, 
        String destinationCity, String flightDate, String departureTime, String arrivalTime)
    {
        this.availableSeats = availableSeats;
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.flightDate = flightDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
    
      public Flight ()
    {
        this.availableSeats = 0;
        this.flightNumber = "000000";
        this.departureCity = "Nowhere";
        this.destinationCity = "Nowhere";
        this.flightDate = "Jan 1, 1970";
        this.departureTime = "12:00 a.m.";
        this.arrivalTime = "12:00 a.m.";
    }
      
      public void createSeatChartFile () throws IOException
      {
          String fileName = flightNumber + ".txt";
        try (PrintWriter writer = new PrintWriter(fileName, "US-ASCII")) 
        {
           for (int index = 0; index < 10; index++)
           {
               writer.print((index + 1) + "\t");
               for (int jndex = 0; jndex < 8; jndex++)
               {
                   writer.print(seats[index][jndex]);
               }
               writer.println();
           }
        }
        catch (IOException x)
        {
            System.err.println("IO Error.");
        }
      }
      
      public int getAvailableSeats ()
      {
          return availableSeats;
      }
      
      public void setAvailableSeats (int availableSeats)
      {
          this.availableSeats = availableSeats;
      }
      
      public void setSeats (int availableSeats)
      {
       int takenSeats = 80 - availableSeats;
       
       for (int index = 0; index <= 9; index++)
       {
           for ( int jndex = 0; jndex <= 7; jndex++)
           {
               if (takenSeats > 0)
                   seats[index][jndex] = 'X';
               else
                   seats[index][jndex] = (char) ('A' + jndex);
               takenSeats--;
           }
       }
      }
      
      public char[][] getSeats ()
      {
          return seats;
      }
      
      public boolean seatIsTaken (int row, int column)
      {
          if (seats[row][column] == 'X') 
              return true;
          return false;
      }
      
      String getFlightNumber ()
      {
          return flightNumber;
      }
}
