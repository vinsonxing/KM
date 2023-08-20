package com.study.spring.mvc.TypeConversionDataBinding;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyDateFormatter implements Formatter<Date> {
    private final String desc;

    public MyDateFormatter(String desc) {

        this.desc = desc;
    }

    @Override
    public Date parse(String date, Locale locale) throws ParseException {
        System.out.println(desc);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy|MM|dd");
        return sdf.parse(date);
    }

    @Override
    public String print(Date date, Locale locale) {
        System.out.println(desc);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy|MM|dd");
        return sdf.format(date);
    }
}
