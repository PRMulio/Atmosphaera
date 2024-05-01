package com.proyectofinal.atmosphaera;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utilities {

    public static String capitalizeString(String str) {

        if (str == null || str.length() == 0) {
            return str;
        }

        String formattedString = str.substring(0, 1).toUpperCase().concat(str.substring(1));

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

        String month = "";

        switch (monthIndex) {
            case 1:
                month = "Ianuarius";
                break;
            case 2:
                month = "Februarius";
                break;
            case 3:
                month = "Martius";
                break;
            case 4:
                month = "Aprilis";
                break;
            case 5:
                month = "Maius";
                break;
            case 6:
                month = "Iunius";
                break;
            case 7:
                month = "Iulius";
                break;
            case 8:
                month = "Augustus";
                break;
            case 9:
                month = "September";
                break;
            case 10:
                month = "October";
                break;
            case 11:
                month = "November";
                break;
            case 12:
                month = "December";
                break;
        }

        return month;
    }

    public static String calculateDayOfWeek(String date) {

        int dayOfWeekIndex;
        String dayOfWeek = "";

        try {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
            dayOfWeekIndex = calendar.get(Calendar.DAY_OF_WEEK);
            System.out.println(dayOfWeekIndex);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        switch (dayOfWeekIndex) {
            case 1:
                dayOfWeek = "Dominicus";
                break;
            case 2:
                dayOfWeek = "Lunae";
                break;
            case 3:
                dayOfWeek = "Martis";
                break;
            case 4:
                dayOfWeek = "Mercurii";
                break;
            case 5:
                dayOfWeek = "Iovis";
                break;
            case 6:
                dayOfWeek = "Veneris";
                break;
            case 7:
                dayOfWeek = "Saturni";
                break;

        }

        return dayOfWeek;
    }

    public static String calculateRomanDate(String regularDate) {

        int year = Integer.parseInt(regularDate.substring(0, 4));
        int month = Integer.parseInt(regularDate.substring(6, 7));
        int day = Integer.parseInt(regularDate.substring(9, 10));

        String yearString;
        String monthString;
        String dayString;
        String dayOfWeek;

        // Year
        yearString = calculateSingleNumber(year);

        // Month
        monthString = calculateMonth(month);

        // Day
        dayString = calculateSingleNumber(day);

        // Day of week
        dayOfWeek = calculateDayOfWeek(regularDate);


        return "iem " + dayOfWeek + " " + dayString + " " + monthString + " Anno Domini " + yearString;
    }
}
