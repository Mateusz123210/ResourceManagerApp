package com.example.resourcemanagerapp.service;

import com.example.resourcemanagerapp.additionalTypes.UserType;

public interface UserService {

    public void addUser(String nick, String name, String surname, UserType userType);

    public void deleteUser(Integer id);

    public void changeUserNick(Integer userId, String nick);
}
