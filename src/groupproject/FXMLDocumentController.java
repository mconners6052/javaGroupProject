package groupproject;

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
        private Label reservationFeedbackLabel;

    
        
        
    @FXML
    private TitledPane seatMapPane;
        @FXML
        private ComboBox<String> flightComboBox;
    
    
    

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
    private TitledPane exitPane;
    
        @FXML
        private Button exitButton;
        
        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }

    public void exitProgram ()
    {
        System.exit(0);
    }
    
    public void addNewFlight () throws IOException
    {
      String flightNumber = flightNumberLabel.getText();
      String departureCity = departureCityLabel.getText();
      String destinationCity = destinationCityLabel.getText();
      String availableSeats = availableSeatsLabel.getText();
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
    
     public ArrayList<String> getFlightNumbers ()
     {
         ArrayList<String> flightNumbers = new ArrayList<String>();
         for (int index = 0; index < GroupProject.flights.size(); index++)
         {
             flightNumbers.add(GroupProject.flights.get(index).getFlightNumber());
         }
         return flightNumbers;
     }
     
     public void setSeatChartComboBox ()
     {
         var flightNumbers = getFlightNumbers();   
         for (int index = 0; index < flightNumbers.size(); index++)
         {
             flightComboBox.getItems().add(index, flightNumbers.get(index));
         }
         
     }
    
}
