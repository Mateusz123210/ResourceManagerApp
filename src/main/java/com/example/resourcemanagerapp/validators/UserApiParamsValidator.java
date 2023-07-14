package com.example.resourcemanagerapp.validators;

import com.example.resourcemanagerapp.additionalTypes.UserType;
import com.example.resourcemanagerapp.exceptions.ApplicationException;

public class UserApiParamsValidator {

    public static void validateAddUserParameters(String nick, String name, String surname,
                                                       UserType type) {
        if(nick == null || nick.isEmpty() || name == null || name.isEmpty() ||
                surname == null || surname.isEmpty() || type == null) {
            throw new ApplicationException("Minimum one of arguments was not given!");
        }
        if(!StringValidator.checkIfContainsOnlyLettersAndDigits(nick)){
            throw new ApplicationException("Nick should consists of letters and numbers only!");
        }
        if(!StringValidator.checkIfContainsOnlyLetters(name) || !StringValidator.checkIfContainsOnlyLetters(surname)) {
            throw new ApplicationException("Name and surname should consist of letters only!");
        }
    }

    public static void validateUserId(Integer id){
        if(id <= 0) {
            throw new ApplicationException("Given user id is invalid!");
        }
    }

    public static void checkUpdateUserNickParameters(Integer id, String nick){
        if(id <= 0 ) {
            throw new ApplicationException("Given user id is invalid!");
        }
        if(nick == null || nick.isEmpty()){
            throw new ApplicationException("Nick was not given!");
        }
        if(!StringValidator.checkIfContainsOnlyLettersAndDigits(nick)){
            throw new ApplicationException("Nick should consists only of letters and digits!");
        }
    }
}
