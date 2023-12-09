package com.lab.library.service;


import com.lab.library.domain.User;
import com.lab.library.domain.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}