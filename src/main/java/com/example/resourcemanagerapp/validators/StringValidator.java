package com.example.resourcemanagerapp.validators;

public class StringValidator {

    public static Boolean checkIfContainsOnlyLetters(String str){
        for(int i = 0; i < str.length(); i++){
            if(!Character.isLetter(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public static Boolean checkIfContainsOnlyLettersAndDigits(String nick){
        for(int i = 0; i < nick.length(); i++){
            if(!Character.isLetter(nick.charAt(i)) && !Character.isDigit(nick.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
