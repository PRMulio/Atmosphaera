package com.proyectofinal.atmosphaera;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class City {

    // Atributos
    private String Lat;
    private String Lon;
    private String completeName;
    private String modernName;
    private String latinName;
    private String countryCode;

    private String description;
    private Image descriptionImage;
    private String temp;
    private String tempMin;
    private String tempMax;
    private String humidity;
    private String windSpeed;
    private String windDegree;
    private String rain; // Cantidad de precipitaciones en mm. Opcional, en caso de lluvia
    private String clouds; // Porcentaje
    private String time;
    private String romanTime;
    private String sunrise;
    private String sunset;
    private String timezone;
    private String moonPhase;
    private String date;

    public City() {

    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String Lat) {
        this.Lat = Lat;
    }

    public String getLon() {
        return Lon;
    }

    public void setLon(String Lon) {
        this.Lon = Lon;
    }

    public String getCompleteName() {
        return completeName;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }

    public String getModernName() {
        return modernName;
    }

    public void setModernName(String modernName) {
        this.modernName = modernName;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getDescriptionImage() {
        return descriptionImage;
    }

    public void setDescriptionImage(Image descriptionImage) {
        this.descriptionImage = descriptionImage;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(String windDegree) {
        this.windDegree = windDegree;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRomanTime() {
        return romanTime;
    }

    public void setRomanTime(String romanTime) {
        this.romanTime = romanTime;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(String moonPhase) {
        this.moonPhase = moonPhase;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "City{" + "Lat=" + Lat + ", Lon=" + Lon + ", completeName=" + completeName + ", modernName=" + modernName + ", latinName=" + latinName + ", countryCode=" + countryCode + '}';
    }

    public void addCountryCode() {

        setCompleteName(getModernName() + "," + getCountryCode());
        System.out.println("El nombre completo es: " + getCompleteName());
    }

    public String retrieveDate(long epoch) {

        String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date(epoch * 1000));
        System.out.println(formattedDate);
        String day = formattedDate.substring(0, 2);
        String month = formattedDate.substring(3, 5);
        String year = formattedDate.substring(6, 10);
        System.out.println(day);
        System.out.println(month);
        System.out.println(year);

        System.out.println("Fecha: " + formattedDate);

        return formattedDate;
    }

    public String retrieveTime(int timezone) {
        String time;

        ZoneId zid = ZoneId.of("+" + timezone / 3600);
        ZonedDateTime zdt = ZonedDateTime.now(zid);

        time = DateTimeFormatter.ofPattern("h:mm a").format(zdt);
        System.out.println(time);

        return time;
    }

    public JSONObject getCityListJSON() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/com/proyectofinal/atmosphaera/json/cityList.json")));
        JSONObject json = (JSONObject) new JSONTokener(content).nextValue();
        return json;
    }

    // En base al JSON que contiene las coordenadas de la ciudad, modificamos los atributos
    // Lat y Lon
    public void setCoordinates(StringBuilder json) {

        String json_string = json.toString();
        System.out.println(json_string);

        JSONObject jsonObjectResults = new JSONObject(json_string);
        JSONArray jsonArrayResults = jsonObjectResults.getJSONArray("results");

        for (int i = 0; i < jsonArrayResults.length(); i++) {
            
            JSONObject result = jsonArrayResults.getJSONObject(i);
            String countryCode = String.valueOf(result.getString("country_code")).toLowerCase();
            
            System.out.println(countryCode);
            System.out.println(getCountryCode());
           
            if (countryCode.equals(getCountryCode())) {
                
                setLat("latitude=" + String.valueOf(result.getFloat("latitude")));
                setLon("longitude=" + String.valueOf(result.getFloat("longitude")));

                System.out.println("Lat: " + getLat());
                System.out.println("Lon: " + getLon());
                
                return;
            }
        }

    }

}
