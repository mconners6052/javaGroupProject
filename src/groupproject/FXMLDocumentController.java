package groupproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

public class FXMLDocumentController implements Initializable {
    
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
    private TitledPane newReservationPane;

    @FXML
    private TitledPane seatMapPane;

    @FXML
    private TitledPane flightListPane;

    @FXML
    private TitledPane allReservationsPane;

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
    
    public void addNewFlight ()
    {
        
    }
    
}
