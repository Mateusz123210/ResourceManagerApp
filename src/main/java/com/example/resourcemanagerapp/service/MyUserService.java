package com.example.resourcemanagerapp.service;

import com.example.resourcemanagerapp.additionalTypes.MyUserType;

public interface MyUserService {
    public void addUser(String nick, String name, String surname, MyUserType userType);

}
