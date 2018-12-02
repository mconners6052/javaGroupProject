package groupproject;

import static groupproject.GroupProject.reservations;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;



public class FXMLDocumentController implements Initializable 
{
    
    @FXML
    private TitledPane addFlightPane;
    
        @FXML
        private TextField flightNumberLabel;

        @FXML
        private TextField departureCityLabel;

        @FXML
        private TextField destinationCityLabel;

        @FXML
        private TextField availableSeatsLabel;

        @FXML
        private TextField departureTimeLabel;

        @FXML
        private TextField arrivalTimeLabel;
        
        @FXML
        private TextField flightDateLabel;
        
        @FXML
        private Label flightFeedbackLabel;
  
        
        
          

    @FXML
    private TitledPane newReservationPane;
    
        @FXML
        private Label passengerFeedbackLabel;
        
        @FXML
        private TextField nameField;
        
        @FXML
        private TextField flightField;
        
        @FXML
        private TextField IDField;
        
        @FXML
        private TextField rowField;
        
        @FXML
        private TextField columnField;
        
        @FXML
        private TextArea reservationSeatChartTextArea;
        

    
        
        
    @FXML
    private TitledPane seatMapPane;
        @FXML
        private TextField seatMapField;
        @FXML
        private TextArea seatMapTextArea;

    
    
    

    @FXML
    private TitledPane flightListPane;
    
        @FXML
        private TextArea allFlightTextArea;

   
        
        
    @FXML
    private TitledPane allReservationsPane;
       
        @FXML
        private TextArea allReservationsTextArea;
        
        

    @FXML
    private TitledPane specificFlightReservations;
        @FXML
        private TextArea specificReservationTextArea;
        @FXML
        private TextField reservationsField;
        @FXML
        private Button specificReservationButton;
    
    
    

    @FXML
    private TitledPane exitPane;
    
        @FXML
        private Button exitButton;
        
        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }

    
    public void getReservationsForFlight () throws IOException
    {
      String flightNumber = reservationsField.getText();
      if (flightNumber.equals(""))
      {
          specificReservationTextArea.setText("Please enter a flight number.");
          return;
      }
      if (!flightExists(flightNumber))
      {
         specificReservationTextArea.setText("Sorry, we don't recognize this flight."); 
      }
       Charset charset = Charset.forName("US-ASCII");
       Path filePath = Paths.get("passengers.txt");
       String line = null;
       String results = "";
    
       BufferedReader br = Files.newBufferedReader(filePath, charset);
   
       while ((line = br.readLine()) != null)
            {
                if(line.contains(flightNumber))
               {
                  results += "\n" + line;
               }
            }
       if (results.equals(""))
        {
        specificReservationTextArea.setText("No reservations currently.");
        }
       else
       {  
       specificReservationTextArea.setText(results);
       }
    }
    
    public void exitProgram ()
    {
        System.exit(0);
    }
    
    public void addNewPassengers () throws IOException
     {
         String name = nameField.getText();
         String ID = IDField.getText();
         int row = Integer.parseInt(rowField.getText());
         char column = columnField.getText().charAt(0);
         String flightNumber = flightField.getText();
         
                  if ((name == "") || (ID == "") || (row == 0) || (column == '\0') || (flightNumber == ""))
         {
             passengerFeedbackLabel.setText("You left one or more areas empty.");
             return;
         }
         
         else if (!flightExists(flightNumber))
         {
            passengerFeedbackLabel.setText("Sorry, we couldn't find that flight. Please try again.");
            return;
         }
         else
         {
             Flight flight = GroupProject.getFlight(flightNumber);
             if (GroupProject.getFlight(flightNumber).seatIsTaken(row - 1, column - 'A' - 1))
             {
                passengerFeedbackLabel.setText("Sorry, that seat is taken. Try another seat. ");
                return;
             }
             else
             {
                 flight.seats[row - 1][column - 'A'] = 'X';
                 flight.createSeatChartFile();
                 flight.setAvailableSeats(flight.getAvailableSeats() - 1);
                 passengerFeedbackLabel.setText("Seat reserved.");
                 displaySeatChart(flight, reservationSeatChartTextArea);
                 
                 String formattedString = "\n" + ID + "\t" + name + "\t" + rowField.getText() + columnField.getText() + "\t\t\t" + flightNumber;
                 printToPassengers(formattedString);
             }
         }
     }
    
    public void addNewFlight () throws IOException
    {
      String flightNumber = flightNumberLabel.getText();
      String departureCity = departureCityLabel.getText();
      String destinationCity = destinationCityLabel.getText();
      String availableSeats = (availableSeatsLabel.getText().equals("")) ? "0" : availableSeatsLabel.getText();
      String departureTime = departureTimeLabel.getText();
      String arrivalTime = arrivalTimeLabel.getText();
      String flightDate = flightDateLabel.getText();
      String formattedString = String.format("%n%s\t%s\t\t%s\t\t\t%s\t\t\t%s\t\t\t%s\t%s", 
      flightNumber, flightDate, departureTime, arrivalTime, departureCity, destinationCity, availableSeats);
      
      printToFlights(formattedString);
      Flight newFlight = new Flight(Integer.parseInt(availableSeats), flightNumber, departureCity, 
      destinationCity, flightDate, departureTime, arrivalTime);
      GroupProject.flights.add(newFlight);
      newFlight.setSeats(newFlight.getAvailableSeats());
      newFlight.createSeatChartFile();
    }
    
    public void printToFlights (String formattedString) throws IOException
   {
    FileWriter fr = new FileWriter("flights.txt", true);
    BufferedWriter br = new BufferedWriter(fr);
    PrintWriter pr = new PrintWriter(br);
    pr.println(formattedString);
    pr.close();
    br.close();
    fr.close();  
   }
    
    public void printToPassengers (String formattedString) throws IOException
   {
    FileWriter fr = new FileWriter("passengers.txt", true);
    BufferedWriter br = new BufferedWriter(fr);
    PrintWriter pr = new PrintWriter(br);
    pr.println(formattedString);
    pr.close();
    br.close();
    fr.close();  
   }
    
    public void displaySeatChartForFlight () throws IOException
    {
        String flightNumber = seatMapField.getText();
        displaySeatChart(GroupProject.getFlight(flightNumber), seatMapTextArea);
        
    }
    
    public void displayAllFlights () throws IOException
    {
        Path filePath = Paths.get("flights.txt");
        Charset charset = Charset.forName("US-ASCII");
        String line = null;
        String flights = "";
        try (BufferedReader br = Files.newBufferedReader(filePath, charset))
        {
            while ((line = br.readLine()) != null)
            {
                flights += "\n" + line;
            }
        }
        catch (IOException x)
        {
         System.err.format("IOException: %s%n", x);
        }
       
        
        allFlightTextArea.setText(flights);
    }
    
        public void displayAllPassengers () throws IOException
    {
        Path filePath = Paths.get("passengers.txt");
        Charset charset = Charset.forName("US-ASCII");
        String line = null;
        String passengers = "";
        try (BufferedReader br = Files.newBufferedReader(filePath, charset))
        {
            while ((line = br.readLine()) != null)
            {
                passengers += "\n" + line;
            }
        }
        catch (IOException x)
        {
         System.err.format("IOException: %s%n", x);
        }
       
        
        allReservationsTextArea.setText(passengers);
        
    }
        
    public void displaySeatChart (Flight flight, TextArea ta) throws IOException
    {
        
        String chartPath = flight.getFlightNumber() + ".txt";
        Path filePath = Paths.get(chartPath);
        Charset charset = Charset.forName("US-ASCII");
        String line = null;
        String seatChart = "";
        try (BufferedReader br = Files.newBufferedReader(filePath, charset))
        {
            while ((line = br.readLine()) != null)
            {
                seatChart += "\n" + line;
            }
        }
        catch (IOException x)
        {
         System.err.format("IOException: %s%n", x);
        }
       
        
        ta.setText(seatChart);
    }
    
     public ArrayList<String> getFlightNumbers ()
     {
         ArrayList<String> flightNumbers = new ArrayList<String>();
         for (int index = 0; index < GroupProject.flights.size(); index++)
         {
             flightNumbers.add(GroupProject.flights.get(index).getFlightNumber());
         }
         return flightNumbers;
     }
     
     public boolean flightExists (String flightNumber)
     {
         ArrayList<Flight> flights = GroupProject.flights;
         
         for (int index = 0; index < flights.size(); index++)
         {
             if (flightNumber.equals(flights.get(index).getFlightNumber()))
             {
                 return true;
             }
             else
             {
                return false;
             }
         }
         return false;
     }
}
