package org.example.validator;

public class IntegerValidator {

    public static boolean validateInteger(String value){

        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException ex){
            return false;
        }

    }
}
