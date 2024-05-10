package com.proyectofinal.atmosphaera;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utilities {

    public static String capitalizeString(String str) {

        if (str == null || str.isEmpty()) {
            return str;
        }

        String formattedString;

        formattedString = str.substring(0, 1).toUpperCase().concat(str.substring(1));

        return formattedString;
    }

    public static String calculateSingleNumber(int number) {

        StringBuilder romanNumber = new StringBuilder();

        while (number > 0) {

            if (number > 999) {
                romanNumber.append("M");
                number -= 1000;
                continue;
            }

            if (number > 499) {
                romanNumber.append("D");
                number -= 500;
                continue;
            }

            if (number > 99) {
                romanNumber.append("C");
                number -= 100;
                continue;
            }

            if (number > 49) {
                romanNumber.append("L");
                number -= 50;
                continue;
            }

            if (number > 9) {
                romanNumber.append("X");
                number -= 10;
                continue;
            }

            if (number == 9) {
                romanNumber.append("IX");
                number -= 9;
                continue;
            }

            if (number > 4) {
                romanNumber.append("V");
                number -= 5;
                continue;
            }

            if (number == 4) {
                romanNumber.append("IV");
                number -= 4;
                continue;
            }

            romanNumber.append("I");
            number -= 1;

        }

        return romanNumber.toString();
    }

    public static String calculateMonth(int monthIndex) {

        String month;

        month = switch (monthIndex) {
            case 1 -> "Ianuarius";
            case 2 -> "Februarius";
            case 3 -> "Martius";
            case 4 -> "Aprilis";
            case 5 -> "Maius";
            case 6 -> "Iunius";
            case 7 -> "Iulius";
            case 8 -> "Augustus";
            case 9 -> "September";
            case 10 -> "October";
            case 11 -> "November";
            case 12 -> "December";
            default -> "";
        };

        return month;
    }

    public static String calculateDayOfWeek(String date) {

        int dayOfWeekIndex;
        String dayOfWeek = "";

        try {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
            dayOfWeekIndex = calendar.get(Calendar.DAY_OF_WEEK);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        dayOfWeek = switch (dayOfWeekIndex) {
            case 1 -> "Dominicus";
            case 2 -> "Lunae";
            case 3 -> "Martis";
            case 4 -> "Mercurii";
            case 5 -> "Iovis";
            case 6 -> "Veneris";
            case 7 -> "Saturni";
            default -> dayOfWeek;
        };

        return dayOfWeek;
    }

    public static String[] calculateRomanDate(String regularDate) {

        String[] date = new String[4];

        int year = Integer.parseInt(regularDate.substring(0, 4));
        int month = Integer.parseInt(regularDate.substring(6, 7));
        int day = Integer.parseInt(regularDate.substring(9, 10));

        String yearString;
        String monthString;
        String dayString;
        String dayOfWeekString;

        // Day of week
        dayOfWeekString = calculateDayOfWeek(regularDate);
        date[0] = dayOfWeekString;

        // Day
        dayString = calculateSingleNumber(day);
        date[1] = dayString;

        // Month
        monthString = calculateMonth(month);
        date[2] = monthString;

        // Year
        yearString = calculateSingleNumber(year);
        date[3] = yearString;


        return date;
    }
}
