package groupproject;

public class Flight 
{
    private int availableSeats;
    private String flightNumber, departureCity, destinationCity, flightDate, departureTime, arrivalTime;
    char[][] seats;
    
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
      
      public static void createSeatChartFile (Flight flight)
      {
      
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
          int index, jndex;
          int counter = 0;
          
          for (index = 0; index < 10; index++)
          {
              for (jndex = 0; index < 8; jndex++)
              {
                  if (counter < availableSeats)
                      seats[index][jndex] = 'X';
                  else
                    seats[index][jndex] = (char) ('A' + jndex);
                  counter++;
              }
          }
      }
      
      public char[][] getSeats ()
      {
          return seats;
      }
      
      public boolean seatIsTaken (int row, int column, char[][] seats)
      {
          if (seats[row][column] == 'X') 
              return true;
          return false;
      }
      
      public void generateSeatChart(char[][] seats, String flightNumber) 
      {
          String fileName = flightNumber + ".txt";
          String seatChart;
          
          
      }
      
    
}
