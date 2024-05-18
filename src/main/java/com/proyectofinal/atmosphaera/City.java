package com.proyectofinal.atmosphaera;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class City {

    // Atributos
    private String latitude;
    private String longitude;

    private String completeName;
    private String modernName;
    private String latinName;

    private String countryCode;

    private String description;
    private Image descriptionImage;

    private Image descriptionImage1;
    private Image descriptionImage2;
    private Image descriptionImage3;
    private Image descriptionImage4;
    private Image descriptionImage5;

    private String temp;
    private String tempMin;
    private String tempMax;

    private String tempMin1;
    private String tempMax1;
    private String tempMin2;
    private String tempMax2;
    private String tempMin3;
    private String tempMax3;
    private String tempMin4;
    private String tempMax4;
    private String tempMin5;
    private String tempMax5;

    private String humidity;

    private String windSpeed;
    private String windDegree;

    private String rainProbability;

    private String rainProbability1;
    private String rainProbability2;
    private String rainProbability3;
    private String rainProbability4;
    private String rainProbability5;

    private String time;
    private String sunrise;
    private String sunset;

    private String dayOfWeek;
    private String day;
    private String month;
    private String year;

    private String dayOfWeek1;
    private String dayOfWeek2;
    private String dayOfWeek3;
    private String dayOfWeek4;
    private String dayOfWeek5;

    public City() {

    }

    public Image getDescriptionImage1() {
        return descriptionImage1;
    }

    public void setDescriptionImage1(Image descriptionImage1) {
        this.descriptionImage1 = descriptionImage1;
    }

    public Image getDescriptionImage2() {
        return descriptionImage2;
    }

    public void setDescriptionImage2(Image descriptionImage2) {
        this.descriptionImage2 = descriptionImage2;
    }

    public Image getDescriptionImage3() {
        return descriptionImage3;
    }

    public void setDescriptionImage3(Image descriptionImage3) {
        this.descriptionImage3 = descriptionImage3;
    }

    public Image getDescriptionImage4() {
        return descriptionImage4;
    }

    public void setDescriptionImage4(Image descriptionImage4) {
        this.descriptionImage4 = descriptionImage4;
    }

    public Image getDescriptionImage5() {
        return descriptionImage5;
    }

    public void setDescriptionImage5(Image descriptionImage5) {
        this.descriptionImage5 = descriptionImage5;
    }

    public String getTempMin1() {
        return tempMin1;
    }

    public void setTempMin1(String tempMin1) {
        this.tempMin1 = tempMin1;
    }

    public String getTempMax1() {
        return tempMax1;
    }

    public void setTempMax1(String tempMax1) {
        this.tempMax1 = tempMax1;
    }

    public String getTempMin2() {
        return tempMin2;
    }

    public void setTempMin2(String tempMin2) {
        this.tempMin2 = tempMin2;
    }

    public String getTempMax2() {
        return tempMax2;
    }

    public void setTempMax2(String tempMax2) {
        this.tempMax2 = tempMax2;
    }

    public String getTempMin3() {
        return tempMin3;
    }

    public void setTempMin3(String tempMin3) {
        this.tempMin3 = tempMin3;
    }

    public String getTempMax3() {
        return tempMax3;
    }

    public void setTempMax3(String tempMax3) {
        this.tempMax3 = tempMax3;
    }

    public String getTempMin4() {
        return tempMin4;
    }

    public void setTempMin4(String tempMin4) {
        this.tempMin4 = tempMin4;
    }

    public String getTempMax4() {
        return tempMax4;
    }

    public void setTempMax4(String tempMax4) {
        this.tempMax4 = tempMax4;
    }

    public String getTempMin5() {
        return tempMin5;
    }

    public void setTempMin5(String tempMin5) {
        this.tempMin5 = tempMin5;
    }

    public String getTempMax5() {
        return tempMax5;
    }

    public void setTempMax5(String tempMax5) {
        this.tempMax5 = tempMax5;
    }

    public String getRainProbability1() {
        return rainProbability1;
    }

    public void setRainProbability1(String rainProbability1) {
        this.rainProbability1 = rainProbability1;
    }

    public String getRainProbability2() {
        return rainProbability2;
    }

    public void setRainProbability2(String rainProbability2) {
        this.rainProbability2 = rainProbability2;
    }

    public String getRainProbability3() {
        return rainProbability3;
    }

    public void setRainProbability3(String rainProbability3) {
        this.rainProbability3 = rainProbability3;
    }

    public String getRainProbability5() {
        return rainProbability5;
    }

    public void setRainProbability5(String rainProbability5) {
        this.rainProbability5 = rainProbability5;
    }

    public String getRainProbability4() {
        return rainProbability4;
    }

    public void setRainProbability4(String rainProbability4) {
        this.rainProbability4 = rainProbability4;
    }

    public String getDayOfWeek1() {
        return dayOfWeek1;
    }

    public void setDayOfWeek1(String dayOfWeek1) {
        this.dayOfWeek1 = dayOfWeek1;
    }

    public String getDayOfWeek2() {
        return dayOfWeek2;
    }

    public void setDayOfWeek2(String dayOfWeek2) {
        this.dayOfWeek2 = dayOfWeek2;
    }

    public String getDayOfWeek3() {
        return dayOfWeek3;
    }

    public void setDayOfWeek3(String dayOfWeek3) {
        this.dayOfWeek3 = dayOfWeek3;
    }

    public String getDayOfWeek4() {
        return dayOfWeek4;
    }

    public void setDayOfWeek4(String dayOfWeek4) {
        this.dayOfWeek4 = dayOfWeek4;
    }

    public String getDayOfWeek5() {
        return dayOfWeek5;
    }

    public void setDayOfWeek5(String dayOfWeek5) {
        this.dayOfWeek5 = dayOfWeek5;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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

    public String getRainProbability() {
        return rainProbability;
    }

    public void setRainProbability(String rainProbability) {
        this.rainProbability = rainProbability;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "City{" + "latitude=" + latitude + ", longitude=" + longitude + ", completeName=" + completeName + ", modernName=" + modernName + ", latinName=" + latinName + ", countryCode=" + countryCode + '}';
    }

    public void addCountryCode() {

        setCompleteName(getModernName() + "," + getCountryCode());
        System.out.println("El nombre completo es: " + getCompleteName());
    }

    public JSONObject getCityListJSON() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/com/proyectofinal/atmosphaera/json/cityList.json")));
        JSONObject json = (JSONObject) new JSONTokener(content).nextValue();
        return json;
    }

    // En base al JSON que contiene las coordenadas de la ciudad, modificamos los atributos
    // latitude y longitude
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

                setLatitude("latitude=" + (result.getFloat("latitude")));
                setLongitude("longitude=" + (result.getFloat("longitude")));

                System.out.println("Latitude: " + getLatitude());
                System.out.println("Longitude: " + getLongitude());

                return;
            }
        }

    }

}
