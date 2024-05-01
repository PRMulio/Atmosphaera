package com.practicas.atmosphaer;

public class Utilities {

    public static String capitalizeString(String str) {

        if (str == null || str.length() == 0) {
            return str;
        }

        String formattedString = str.substring(0, 1).toUpperCase().concat(str.substring(1));

        return formattedString;
    }
    
    public static String calculateRomanDate(String regularDate) {
        
        int year = Integer.parseInt(regularDate.substring(0,4));
        int month = Integer.parseInt(regularDate.substring(6,7));
        int day = Integer.parseInt(regularDate.substring(9,10));
        
        String yearString = "";
        String monthString = "";
        String dayString = "";
        
        
        while (day > 0) {
            
            if(day > 9) {
                dayString = dayString + "X";
                day -= 10;
            } 
            
            if(day == 9) {
                dayString = dayString + "IX";
                day -= 9;
            }
            
            if(day > 4) {
                dayString = dayString + "V";
                day -= 5;
            }
            
            if(day > 0) {
                dayString = dayString + "I";
                day -= 1;
            }
            
        }
        
        String romanDate = "Diem ** " + dayString + " " + monthString + " Anno Domini " + yearString;

        return romanDate;
    }
}
