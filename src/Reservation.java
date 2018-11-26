public class Reservation 

{
   private String passengerId, passengerName, seatNumber, flightNumber;
   
   Reservation (String passengerId, String passengerName, String seatNumber, String flightNumber)
   {
       this.passengerId = passengerId;
       this.passengerName = passengerName;
       this.seatNumber = seatNumber;
       this.flightNumber = flightNumber;
   }
   
    Reservation ()
   {
       this.passengerId = "0000000000";
       this.passengerName = "Titania McGee";
       this.seatNumber = "A1";
       this.flightNumber = "000000";
   }
    
    
}
