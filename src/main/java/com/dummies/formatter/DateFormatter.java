package com.dummies.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author taumal
 * @since 11/20/17 3:59 PM
 */
public class DateFormatter implements Formatter<Date> {

    @Autowired
    private MessageSource messageSource;

    public DateFormatter() {
    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        SimpleDateFormat dateFormat = createDateFormate(locale);
        return dateFormat.parse(text);
    }

    @Override
    public String print(Date date, Locale locale) {
        SimpleDateFormat dateFormat = createDateFormate(locale);
        return dateFormat.format(date);
    }

    private SimpleDateFormat createDateFormate(Locale locale) {
        String format = this.messageSource.getMessage("date.format", null, locale);
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.setLenient(false);
        return dateFormat;
    }
}
