package model.utils;

import java.util.regex.Pattern;

public class ValidateUtil {
    public static final String NUMBER_PHONE_REGEX = "^[0][1-9][0-9]{8}$";
    public static final String NUMBER_REGEX = "^\\d*\\.?\\d*$";

    public static final String INT_REGEX = "^\\d+$";
    public static final String EMAIL_REGEX = "[A-Aa-z][A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$"; //Bat dau bang chu cai...

    public static final String USERNAME_REGEX = "^([a-z])([a-z0-9]){7,19}$";  //Bat dau chu thuong, do dai 8-20 ki tu
    public static final String PASSWORD_REGEX = "^([\\d\\w]{6,30})$"; // 6-30 ki tu k bao gom ki tu dac biet

    public static boolean isPasswordVailid(String pass) {
        return Pattern.compile(PASSWORD_REGEX).matcher(pass).matches();
    }

    public static boolean isNumberPhoneVailid(String number) {
        return Pattern.compile(NUMBER_PHONE_REGEX).matcher(number).matches();
    }

    public static boolean isUserNameVailid(String username) {
        return Pattern.compile(USERNAME_REGEX).matcher(username).matches();
    }

    public static boolean isNumberVailid(String number) {
        return Pattern.compile(NUMBER_REGEX).matcher(number).matches();
    }

    public static boolean isIntValid(String number) {
        return Pattern.compile(INT_REGEX).matcher(number).matches();
    }

    public static boolean isEmailValid(String email) {
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }

}
