package com.example.Local.Library.service;

import com.example.Local.Library.dto.ResponseDto;
import com.example.Local.Library.dto.UserDto;

public interface UserService {

    // get current status of user
    UserDto getUserDetails(Long userId) throws Exception;

    void rentBook(Long userId, Long bookId) throws Exception;

    UserDto createUser(UserDto userDto);


    // check if book is availabe or not // this can be independent of user


    // wishlist controllers


    // request controllers

}
