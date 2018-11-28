package groupproject;



import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class GroupProject extends Application 
{
    public static ArrayList<Flight> flights = new ArrayList<Flight>();
    public static ArrayList<Passenger> reservations = new ArrayList<Passenger>();
    
    @Override
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) 
    {
        getFlights();
        getPassengers();
        launch(args);
    }
    
        public static void getPassengers ()
   {
        Charset charset = Charset.forName("US-ASCII");
        Path filePath = Paths.get("passengers.txt");
        String line = null;
    
        try (BufferedReader br = Files.newBufferedReader(filePath, charset))
        {
            while ((line = br.readLine()) != null)
            {
                if((!line.contains("Flight#")) && !line.contains(""))
               {
               String delims = "[\t]+";
               String[] parse = line.split(delims);
               Passenger p = new Passenger(parse[0], parse[1], parse[2], parse[3]);
               reservations.add(p);
               }
            }
        }
        catch (IOException x)
        {
         System.err.format("IOException: %s%n", x);
        }
   }
   
    public static void getFlights ()
   {
             Charset charset = Charset.forName("US-ASCII");
             Path filePath = Paths.get("flights.txt");
             String line;
    
        try (BufferedReader br = Files.newBufferedReader(filePath, charset))
        {
            while ((line = br.readLine()) != null)
            {
               if((!line.contains("Flight#")) && !line.contains(""))
               {
                String delims = "[\t]+";
                String[] parse = line.split(delims);
                Flight f = new Flight(Integer.parseInt(parse[6]), parse[0], 
                parse[5], parse[6], parse[1], parse[2], parse[3]);
                flights.add(f);
               }
            }
        }
        catch (IOException x)
        {
         System.err.format("IOException: %s%n", x);
        }   
   }
    
}
