package com.app.taskflow.common.validation;

import com.app.taskflow.common.validation.interfaces.TimesTamp;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampFormatValidator implements ConstraintValidator<TimesTamp, Date> {

    private static final String TIMESTAMP_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(TIMESTAMP_PATTERN);
        dateFormat.setLenient(false);
        String dateString = dateFormat.format(value);
        try {
            Date parsedDate = dateFormat.parse(dateString);
            String parsedDateString = dateFormat.format(parsedDate);
            if (!dateString.equals(parsedDateString)) {
                throw new IllegalArgumentException("Invalid timestamp format. Please use 'yyyy-MM-dd'T'HH:mm:ss' format");
            }
            return true;
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid timestamp format. Please use 'yyyy-MM-dd'T'HH:mm:ss' format");
        }
    }
}