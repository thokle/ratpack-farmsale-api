package com.kleistit.farmapi.config;

import java.util.Calendar;
import java.util.Locale;

public class DateUtil {

    private Calendar calendar;


    private Calendar getCalendar(){
        if(calendar == null) {
            calendar = Calendar.getInstance(Locale.getDefault());
            calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DATE);
        }
     return  calendar;
    }

    public int getWeek(){
      return   this.getCalendar().get(Calendar.WEEK_OF_YEAR);

    }

}
