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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
    private Text textDayOfWeek;
    @FXML
    private Text textDay;
    @FXML
    private Text textMonth;
    @FXML
    private Text textYear;
    @FXML
    private Label labelDay1;
    @FXML
    private Label labelDay2;
    @FXML
    private Label labelDay3;
    @FXML
    private Label labelDay4;
    @FXML
    private Label labelDay5;
    @FXML
    private ImageView imagePrediction1;
    @FXML
    private ImageView imagePrediction2;
    @FXML
    private ImageView imagePrediction3;
    @FXML
    private ImageView imagePrediction4;
    @FXML
    private ImageView imagePrediction5;
    @FXML
    private Label labelTempMax1;
    @FXML
    private Label labelTempMax2;
    @FXML
    private Label labelTempMax3;
    @FXML
    private Label labelTempMax4;
    @FXML
    private Label labelTempMax5;
    @FXML
    private Label labelTempMin1;
    @FXML
    private Label labelTempMin2;
    @FXML
    private Label labelTempMin3;
    @FXML
    private Label labelTempMin4;
    @FXML
    private Label labelTempMin5;
    @FXML
    private Label labelRainProbability;
    @FXML
    private Label labelRainProbability1;
    @FXML
    private Label labelRainProbability2;
    @FXML
    private Label labelRainProbability3;
    @FXML
    private Label labelRainProbability4;
    @FXML
    private Label labelRainProbability5;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        city = new City();
        connection = new Connection();
        requestRomeData();
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

    private void requestRomeData() {
        // Al iniciar la aplicación, por defecto se visualizará la información de Roma,
        // al ser esta ciudad el centro espiritual de la Cristiandad
        city.setModernName("roma");
        city.setLatinName("roma");
        city.setCountryCode("it");

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

        labelRainProbability.setText(city.getRainProbability() + " %");

        labelModernName.setText(city.getModernName());
        labelLatinName.setText(city.getLatinName());

        labelSunrise.setText(city.getSunrise());
        labelSunset.setText(city.getSunset());

        labelTime.setText(city.getTime());

        textDayOfWeek.setText(city.getDayOfWeek() + " ");

        // Setting the color of the day red if it is Sunday
        if (city.getDayOfWeek().equals("Dominicus")) {
            textDayOfWeek.setFill(Color.RED);
        }

        textDay.setText(city.getDay() + " ");
        textMonth.setText(city.getMonth() + " ");
        textYear.setText(city.getYear());

        labelDay1.setText(city.getDayOfWeek1());
        labelDay2.setText(city.getDayOfWeek2());
        labelDay3.setText(city.getDayOfWeek3());
        labelDay4.setText(city.getDayOfWeek4());
        labelDay5.setText(city.getDayOfWeek5());

        imagePrediction1.setImage(city.getDescriptionImage1());
        imagePrediction2.setImage(city.getDescriptionImage2());
        imagePrediction3.setImage(city.getDescriptionImage3());
        imagePrediction4.setImage(city.getDescriptionImage4());
        imagePrediction5.setImage(city.getDescriptionImage5());

        labelTempMax1.setText(city.getTempMax1());
        labelTempMax2.setText(city.getTempMax2());
        labelTempMax3.setText(city.getTempMax3());
        labelTempMax4.setText(city.getTempMax4());
        labelTempMax5.setText(city.getTempMax5());

        labelTempMin1.setText(city.getTempMin1());
        labelTempMin2.setText(city.getTempMin2());
        labelTempMin3.setText(city.getTempMin3());
        labelTempMin4.setText(city.getTempMin4());
        labelTempMin5.setText(city.getTempMin5());

        labelRainProbability1.setText(city.getRainProbability1() + " %");
        labelRainProbability2.setText(city.getRainProbability2() + " %");
        labelRainProbability3.setText(city.getRainProbability3() + " %");
        labelRainProbability4.setText(city.getRainProbability4() + " %");
        labelRainProbability5.setText(city.getRainProbability5() + " %");
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
