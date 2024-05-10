package com.proyectofinal.atmosphaera;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONObject;

import static com.proyectofinal.atmosphaera.Utilities.*;

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

            InputStream input = new URI(url).toURL().openStream();
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

        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    // Método que devuelve los datos meteorológicos a partir de las coordenadas
    public void retrieveData(City city) {

        String json_string;

        try {
            String url;
            url = URL_DATA.concat(city.getLatitude()).concat("&").concat(city.getLongitude());
            System.out.println(url);

            InputStream input = new URI(url).toURL().openStream();
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
            JSONArray weather_code = daily.getJSONArray("weather_code");
            JSONArray temp_2m_max = daily.getJSONArray("temperature_2m_max");
            JSONArray temp_2m_min = daily.getJSONArray("temperature_2m_min");
            JSONArray sunrise = daily.getJSONArray("sunrise");
            JSONArray sunset = daily.getJSONArray("sunset");
            JSONArray precipitation_probability_max = daily.getJSONArray("precipitation_probability_max");

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
            float windSpeed = current.getFloat("wind_speed_10m");
            float windDegree = current.getFloat("wind_direction_10m");
            String sunrise1 = sunrise.getString(0);
            String sunset1 = sunset.getString(0);

            // Recogemos los datos de la predicción de 5 días
            Object[] weatherCodeArray = weather_code.toList().toArray();
            System.out.println("Weather code de mañana " + weatherCodeArray[1]);
            Object[] tempMaxArray = temp_2m_max.toList().toArray();
            System.out.println("temp max de mañana " + tempMaxArray[1]);
            Object[] tempMinArray = temp_2m_min.toList().toArray();
            System.out.println("temp min de mañana " + tempMinArray[1]);
            Object[] rainProbabilityArray = precipitation_probability_max.toList().toArray();
            System.out.println("probabilidad máxima de lluvia mañana " + rainProbabilityArray[1]);


            sunrise1 = sunrise1.substring(11, 16);
            System.out.println(sunrise1);
            sunset1 = sunset1.substring(11, 16);
            System.out.println(sunset1);

            //String description = current.getString("description");
            //long epoch = current.getLong("dt");

            System.out.println("Current temperature: " + temp + "ºC");
            System.out.println("Current humidity: " + humidity + "%");
            System.out.println("Current pressure: " + pressure + "mbar");
            //System.out.println("Current weather: " + description);
            //System.out.println("Unix time: " + epoch);
            System.out.println("WeatherID es " + weatherCode);
            System.out.println("Time is " +  time);
            System.out.println("Date is " + calculateRomanDate(date));

            //city.setTime(city.retrieveTime(timezone));
            //city.setDate(city.retrieveDate(epoch));
            //city.setDescription(description);

            /*
            city.setDayOfWeek1(calculateDayOfWeek(date));
            city.setDayOfWeek2();
            city.setDayOfWeek3();
            city.setDayOfWeek4();
            city.setDayOfWeek5();*/

            city.setDescriptionImage1(new Image(new File(setDescriptionImage(city, (Integer) weatherCodeArray[1])).toURI().toString()));
            city.setDescriptionImage2(new Image(new File(setDescriptionImage(city, (Integer) weatherCodeArray[2])).toURI().toString()));
            city.setDescriptionImage3(new Image(new File(setDescriptionImage(city, (Integer) weatherCodeArray[3])).toURI().toString()));
            city.setDescriptionImage4(new Image(new File(setDescriptionImage(city, (Integer) weatherCodeArray[4])).toURI().toString()));
            city.setDescriptionImage5(new Image(new File(setDescriptionImage(city, (Integer) weatherCodeArray[5])).toURI().toString()));

            city.setTempMax1(tempMaxArray[1].toString());
            city.setTempMax2(tempMaxArray[2].toString());
            city.setTempMax3(tempMaxArray[3].toString());
            city.setTempMax4(tempMaxArray[4].toString());
            city.setTempMax5(tempMaxArray[5].toString());

            city.setTempMin1(tempMinArray[1].toString());
            city.setTempMin2(tempMinArray[2].toString());
            city.setTempMin3(tempMinArray[3].toString());
            city.setTempMin4(tempMinArray[4].toString());
            city.setTempMin5(tempMinArray[5].toString());

            city.setRainProbability1(rainProbabilityArray[1].toString());
            city.setRainProbability2(rainProbabilityArray[2].toString());
            city.setRainProbability3(rainProbabilityArray[3].toString());
            city.setRainProbability4(rainProbabilityArray[4].toString());
            city.setRainProbability5(rainProbabilityArray[5].toString());

            city.setTemp(temp + " C");
            city.setTempMax(tempMax + " C");
            city.setTempMin(tempMin + " C");

            city.setHumidity(humidity + "%");

            city.setWindSpeed(windSpeed + " km/h");
            city.setWindDegree(windDegree + " grados");

            city.setModernName(capitalizeString(city.getModernName()));
            city.setLatinName("(" + capitalizeString(city.getLatinName()) + ")");

            city.setSunrise(sunrise1);
            city.setSunset(sunset1);

            String[] dateArray = calculateRomanDate(date);
            city.setDayOfWeek(dateArray[0]);
            city.setDay(dateArray[1]);
            city.setMonth(dateArray[2]);
            city.setYear(dateArray[3]);
            System.out.println(Arrays.toString(dateArray));

            city.setTime(time);

        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public String setDescriptionImage(City city, int weatherCode) throws IOException {

            String content = new String(Files.readAllBytes(Paths.get("src/main/resources/com/proyectofinal/atmosphaera/json/descriptions.json")));
            JSONObject jsonDescription = new JSONObject(content);
            JSONObject jsonDescriptionObject = jsonDescription.getJSONObject(String.valueOf(weatherCode));
            JSONObject jsonDescriptionObjectN = jsonDescriptionObject.getJSONObject("day");
            String desc = jsonDescriptionObjectN.getString("descriptionEs");
            String descImage = jsonDescriptionObjectN.getString("image");
            city.setDescription(desc);
            city.setDescriptionImage(new Image(new File(descImage).toURI().toString()));
            return descImage;
    }
}
