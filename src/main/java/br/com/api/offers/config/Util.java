package br.com.api.offers.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public final class Util {

    public static String formatSimpleDate(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  

        return dateFormat.format(date);
    }
}