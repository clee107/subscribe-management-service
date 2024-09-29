package com.subscribe.management.util;

public class StringUtils {
    public static final String REGEX_PHONE_NUMBER = "^\\d{2,3}\\d{3,4}\\d{4}$";

    public static Boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches(REGEX_PHONE_NUMBER);
    }
}
