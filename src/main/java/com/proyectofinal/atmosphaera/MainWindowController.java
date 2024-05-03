package com.proyectofinal.atmosphaera;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Pablo Rodriguez
 */
public class MainWindowController implements Initializable {

    //Declaración de instancia de la clase City
    City city;
    Connection connection;

    @FXML
    private Button buttonRequest;
    @FXML
    private Label labelHumidity;
    @FXML
    private Label labelWindSpeed;
    @FXML
    private Label labelWindDegree;
    @FXML
    private TextField textfieldSearch;
    @FXML
    private Label labelSunrise;
    @FXML
    private Label labelSunset;
    @FXML
    private ImageView imageDescription;
    @FXML
    private Label labelLatinName;
    @FXML
    private Label labelDate;
    @FXML
    private Label labelModernName;
    @FXML
    private Label labelDescription;
    @FXML
    private Label labelTempMin;
    @FXML
    private Label labelTemp;
    @FXML
    private Label labelTempMax;
    @FXML
    private Label labelTime;
    @FXML
    private Label labelRomanTime;
    @FXML
    private ImageView imageSunrise;
    @FXML
    private ImageView imageSunset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        city = new City();
        connection = new Connection();
    }

    @FXML
    private void buttonRequestClick(ActionEvent event) {

        // Recogemos el nombre introducido por el cuadro de texto y modificamos el atributo
        city.setModernName(textfieldSearch.getText().toLowerCase());

        // Le añadimos el código ISO del país 
        city.addCountryCode();

        // Recogemos las coordenadas X e Y de la ciudad
        connection.retrieveCoordinates(city);

        // Recogemos los datos meteorológicos 
        connection.retrieveData(city);

        // Los mostramos a través de la interfaz
        writeData();
    }

    private void writeData() {
        System.out.println(city.getDescriptionImage());
        imageDescription.setImage(city.getDescriptionImage());
        labelDescription.setText(city.getDescription());
        
        labelTemp.setText(city.getTemp());
        labelTempMin.setText(city.getTempMin());
        labelTempMax.setText(city.getTempMax());
        
        labelHumidity.setText(city.getHumidity());

        labelWindSpeed.setText(city.getWindSpeed());
        labelWindDegree.setText(city.getWindDegree());
        
        labelModernName.setText(city.getModernName());
        labelLatinName.setText(city.getLatinName());
        
        labelSunrise.setText(city.getSunrise());
        labelSunset.setText(city.getSunset());
        
        labelTime.setText(city.getTime());
        labelDate.setText(city.getDate());
    }

    @FXML
    private void textfieldSearchKeyReleased(KeyEvent event) throws IOException {

        String presentText = textfieldSearch.getText().toLowerCase();
        JSONObject json = city.getCityListJSON();

        JSONArray countriesArray = json.getJSONArray("countries");

        for (int i = 0; i < countriesArray.length(); i++) {

            JSONObject esCities = countriesArray.getJSONObject(i);
            String country = esCities.getString("country");
            city.setCountryCode(country);

            JSONArray cityArray = esCities.getJSONArray("cities");

            for (int j = 0; j < cityArray.length(); j++) {

                JSONObject city1 = cityArray.getJSONObject(j);
                String modernName = city1.getString("modernName");
                String latinName = city1.getString("latinName");

                if (presentText.equals(modernName)) {
                    buttonRequest.setDisable(false);
                    city.setModernName(presentText);
                    city.setLatinName(latinName);
                    return;
                }

                buttonRequest.setDisable(true);
            }

        }

    }

}
