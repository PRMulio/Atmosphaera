package com.proyectofinal.atmosphaera;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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

    public static String[] calculateDayOfWeek(String date) {

        int dayOfWeekIndex;
        String[] week1 = new String[] {"Dominicus", "Lunae", "Martis", "Mercurii", "Iovis", "Veneris", "Saturnii"};
        String[] week2 = week1.clone();


        try {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
            dayOfWeekIndex = calendar.get(Calendar.DAY_OF_WEEK);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        System.out.println("1 " + Arrays.toString(week1));
        System.arraycopy(week1, dayOfWeekIndex-1, week1, 0, 7-dayOfWeekIndex+1);
        System.out.println("2 " + Arrays.toString(week1));
        System.arraycopy(week2, 0, week1, 7-dayOfWeekIndex+1, dayOfWeekIndex-1);
        System.out.println("3 " + Arrays.toString(week1));

        return week1;
    }

    public static String[] calculateRomanDate(String regularDate) {

        String[] date = new String[10];

        int year = Integer.parseInt(regularDate.substring(0, 4));
        int month = Integer.parseInt(regularDate.substring(5, 7));
        int day = Integer.parseInt(regularDate.substring(8, 10));

        String yearString;
        String monthString;
        String dayString;
        String[] weekArray;

        // Day of week
        weekArray = calculateDayOfWeek(regularDate);
        date[0] = weekArray[0];
        date[1] = weekArray[1];
        date[2] = weekArray[2];
        date[3] = weekArray[3];
        date[4] = weekArray[4];
        date[5] = weekArray[5];
        date[6] = weekArray[6];

        // Day
        dayString = calculateSingleNumber(day);
        date[7] = dayString;

        // Month
        monthString = calculateMonth(month);
        date[8] = monthString;

        // Year
        yearString = calculateSingleNumber(year);
        date[9] = yearString;


        return date;
    }
}
