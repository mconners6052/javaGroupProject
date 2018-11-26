public class Flight 
{
    private int availableSeats;
    private String flightNumber, departureCity, destinationCity, flightDate, departureTime, arrivalTime;
    
    Flight (int availableSeats, String flightNumber, String departureCity, 
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
    
      Flight ()
    {
        this.availableSeats = 0;
        this.flightNumber = "000000";
        this.departureCity = "Nowhere";
        this.destinationCity = "Nowhere";
        this.flightDate = "Jan 1, 1970";
        this.departureTime = "12:00 a.m.";
        this.arrivalTime = "12:00 a.m.";
    }
      
    
}
