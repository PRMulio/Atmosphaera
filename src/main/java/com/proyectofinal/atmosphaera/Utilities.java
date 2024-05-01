package com.proyectofinal.atmosphaera;

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
        StringBuilder monthSB = new StringBuilder();
        StringBuilder daySB = new StringBuilder();

        while (day > 0 || month > 0) {

            // Mes
            if(month > 9) {
                monthSB.append("X");
                month -= 10;
            }

            if(month == 9) {
                monthSB.append("IX");
                month -= 9;
            }

            if(month > 4) {
                monthSB.append("V");
                month -= 5;
            }

            if(month > 0) {
                monthSB.append("I");
                month -= 1;
            }

            // Día

            if(day > 9) {
                daySB.append("X");
                day -= 10;
            } 
            
            if(day == 9) {
                daySB.append("IX");
                day -= 9;
            }
            
            if(day > 4) {
                daySB.append("V");
                day -= 5;
            }
            
            if(day > 0) {
                daySB.append("I");
                day -= 1;
            }
            
        }
        
        String romanDate = "Diem ** " + daySB + " " + monthSB + " Anno Domini " + yearString;

        return romanDate;
    }
}
