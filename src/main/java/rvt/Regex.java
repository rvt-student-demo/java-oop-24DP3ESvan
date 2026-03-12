package rvt;

import java.util.regex.*;
import javax.xml.validation.Validator;

public class Regex {

    public static boolean validateName(String name) {
        return name.matches("[A-Za-z]{3,}");
    }

    public static boolean validateEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean validatePersonalCode(String code) {
        return code.matches("\\d{11}");
    }
}
