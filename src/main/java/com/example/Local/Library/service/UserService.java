package com.example.Local.Library.service;

import com.example.Local.Library.dto.BookInstanceDto;
import com.example.Local.Library.dto.UserDto;

import java.util.List;

public interface UserService {

    // get current status of user
    UserDto getUserDetails(Long userId) throws Exception;

    void rentBook(Long userId, Long bookId) throws Exception;

    UserDto createUser(UserDto userDto);

    List<BookInstanceDto> submitBook(Long userId, Long bookId) throws Exception;


    // check if book is availabe or not // this can be independent of user


    // wishlist controllers


    // request controllers

}
