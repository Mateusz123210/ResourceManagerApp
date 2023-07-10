package com.example.resourcemanagerapp.controller;


import com.example.resourcemanagerapp.additionalTypes.EnumChecker;
import com.example.resourcemanagerapp.additionalTypes.MyUserType;
import com.example.resourcemanagerapp.service.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyUserController {

    private final MyUserServiceImpl myUserService;

    @Autowired
    public MyUserController(MyUserServiceImpl myUserService) {
        this.myUserService = myUserService;
    }

    @PostMapping(value = "/user/add")
    public ResponseEntity addUser(@RequestParam  String nick, @RequestParam String name, @RequestParam String surname,
                                  @RequestParam String userType){
        if(nick.length() == 0 || name.length() == 0 || surname.length() == 0 || userType.length() == 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        if(!checkNick(nick))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        if(!checkNameOrSurname(name) || !checkNameOrSurname(surname))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        MyUserType myUserType = EnumChecker.containsUserType(userType);
        if( myUserType == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        myUserService.addUser(nick, name, surname, myUserType);
        return ResponseEntity.ok("");
    }

    @DeleteMapping(value = "/user/delete")
    public ResponseEntity deleteUser(@RequestParam Integer id){





        return null;
    }

    @PutMapping(value = "/user/change-nick")
    public ResponseEntity changeUserNick(@RequestParam Integer id, @RequestParam String newNick){





        return null;
    }

    private Boolean checkNick(String nick){
        for(int i = 0; i < nick.length(); i++){
            if(!Character.isLetter(nick.charAt(i)) && !Character.isDigit(nick.charAt(i))){
                return false;
            }
        }
        return true;
    }

    private Boolean checkNameOrSurname(String str){

        for(int i = 0; i < str.length(); i++){
            if(!Character.isLetter(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
