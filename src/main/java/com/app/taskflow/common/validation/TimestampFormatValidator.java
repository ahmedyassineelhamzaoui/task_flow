package com.app.taskflow.common.validation;

import com.app.taskflow.common.validation.interfaces.TimesTamp;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampFormatValidator implements ConstraintValidator<TimesTamp, Date> {

    private static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(TIMESTAMP_PATTERN);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(value.toString());
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
