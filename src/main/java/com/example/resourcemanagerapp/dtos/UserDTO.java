package com.example.resourcemanagerapp.dtos;


import com.example.resourcemanagerapp.additionalTypes.UserType;
import lombok.Getter;

@Getter
public class UserDTO {
    String nick;
    String name;
    String surname;
    UserType type;
}
