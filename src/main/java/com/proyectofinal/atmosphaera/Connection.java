package com.proyectofinal.atmosphaera;

import static com.proyectofinal.atmosphaera.Utilities.capitalizeString;
import static com.proyectofinal.atmosphaera.Utilities.calculateRomanDate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONObject;

public class Connection {

    // Constantes
    // Primera petición (coordenadas)
    final String URL_COORDINATES = "https://geocoding-api.open-meteo.com/v1/search?language=es&count=5&name=";

    // Segunda petición (datos)
    final String URL_DATA = "https://api.open-meteo.com/v1/forecast?"
            + "current=temperature_2m,relative_humidity_2m,is_day,precipitation,rain,showers,snowfall,weather_code,cloud_cover,pressure_msl,surface_pressure,wind_speed_10m,wind_direction_10m&"
            + "daily=weather_code,temperature_2m_max,temperature_2m_min,sunrise,sunset,daylight_duration,precipitation_sum,rain_sum,showers_sum,snowfall_sum,precipitation_hours,precipitation_probability_max,wind_speed_10m_max,wind_direction_10m_dominant&"
            + "temperature_unit=celsius&"
            + "wind_speed_unit=kmh&"
            + "precipitation_unit=mm&"
            + "timeformat=iso8601&"
            + "timezone=auto&"
            + "forecast_days=7&";

    public Connection() {

    }

    // Método que devuelve las coordenadas a partir del nombre de la ciudad
    public void retrieveCoordinates(City city) {

        try {
            String url = URL_COORDINATES;

            url = url.concat(city.getModernName());
            System.out.println(url);

            InputStream input = new URL(url).openStream();
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);

            StringBuilder json = new StringBuilder();
            int c;

            while ((c = reader.read()) != -1) {
                json.append((char) c);
            }

            // Cerramos el BufferedReader
            reader.close();

            city.setCoordinates(json);

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Método que devuelve los datos meteorológicos a partir de las coordenadas 
    public void retrieveData(City city) {

        String json_string;

        try {
            String url;
            url = URL_DATA.concat(city.getLat()).concat("&").concat(city.getLon());
            System.out.println(url);

            InputStream input = new URL(url).openStream();
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);

            StringBuilder json = new StringBuilder();
            int c;

            while ((c = reader.read()) != -1) {
                json.append((char) c);
            }

            // Cerramos el BufferedReader
            reader.close();

            //Mostramos el json en formato String
            json_string = json.toString();
            System.out.println(json_string);

            // Navegamos manualmente a través del JSON (lo mejoraremos)
            JSONObject root = new JSONObject(json_string);
            JSONObject current = root.getJSONObject("current");
            System.out.println(current);

            JSONObject daily = root.getJSONObject("daily");
            JSONArray temp_2m_max = daily.getJSONArray("temperature_2m_max");
            JSONArray temp_2m_min = daily.getJSONArray("temperature_2m_min");
            JSONArray sunrise = daily.getJSONArray("sunrise");
            JSONArray sunset = daily.getJSONArray("sunset");

            //JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
            //System.out.println(jsonObjectWeather);
            String dateAndTime = current.getString("time");           
            String date = dateAndTime.substring(0, 10);
            String time = dateAndTime.substring(11, 16);

            float temp = current.getFloat("temperature_2m");
            float tempMax = temp_2m_max.getFloat(0);
            float tempMin = temp_2m_min.getFloat(0);
            float humidity = current.getFloat("relative_humidity_2m");
            float pressure = current.getFloat("pressure_msl");
            int weatherCode = current.getInt("weather_code");
            String sunrise1 = sunrise.getString(0);
            String sunset1 = sunset.getString(0);

            sunrise1 = sunrise1.substring(11,16);
            System.out.println(sunrise1);
            sunset1 = sunset1.substring(11,16);
            System.out.println(sunset1);
            
            //String description = current.getString("description");
            //long epoch = current.getLong("dt");

            System.out.println("Current temperature: " + temp + "ºC");
            System.out.println("Current humidity: " + humidity + "%");
            System.out.println("Current pressure: " + pressure + "mbar");
            //System.out.println("Current weather: " + description);
            //System.out.println("Unix time: " + epoch);
            System.out.println("WeatherID es " + weatherCode);
            System.out.println("Time is " + time);
            System.out.println("Date is " + date);
            
            System.out.println(calculateRomanDate(date));

            //city.setTime(city.retrieveTime(timezone));
            //city.setDate(city.retrieveDate(epoch));
            //city.setDescription(description);
            city.setDescriptionImage("src/main/resources/com/proyectofinal/atmosphaera/images/sun.png");
            city.setTemp(temp + " C");
            city.setTempMax(tempMax + " C");
            city.setTempMin(tempMin + " C");
            city.setHumidity(humidity + "%");
            city.setPressure(pressure + "mbar");
            city.setModernName(capitalizeString(city.getModernName()));
            city.setLatinName("(" + capitalizeString(city.getLatinName()) + ")");
            city.setSunrise(sunrise1);
            city.setSunset(sunset1);
            city.setDate(date);
            city.setTime(time);

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
