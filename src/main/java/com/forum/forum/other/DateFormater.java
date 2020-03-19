package com.forum.forum.other;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateFormater {

    public static String posted(LocalDateTime date){
        LocalDateTime actualDate = LocalDateTime.now();
        long years = ChronoUnit.YEARS.between(date, actualDate);
        if(years > 0){
            return years + (years > 1? " years" : " year");
        }
        long months = ChronoUnit.MONTHS.between(date, actualDate);
        if(months > 0){
            return months + (months > 1? " months" : " month");
        }
        long days = ChronoUnit.DAYS.between(date, actualDate);
        if(days > 0){
            return days + (days > 1? " days" : " day");
        }
        long hours = ChronoUnit.HOURS.between(date, actualDate);
        if(hours > 0){
            return hours + (hours > 1? " hours" : " hour");
        }
        long minutes = ChronoUnit.MINUTES.between(date, actualDate);
        if(minutes > 0){
            return minutes + " min";
        }
        long seconds = ChronoUnit.SECONDS.between(date, actualDate);
        return seconds + " sec";
    }
}
