package com.myappartments.laundry.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static String ddMMMMyyyy(String strDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        try {
            Date d = simpleDateFormat.parse(strDate);
            simpleDateFormat = new SimpleDateFormat("dd MMM yyyy hh:mm:ss a", Locale.ENGLISH);
            return simpleDateFormat.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
