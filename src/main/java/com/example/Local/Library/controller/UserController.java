package com.example.Local.Library.controller;

import com.example.Local.Library.dto.UserDto;
import com.example.Local.Library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // get user details
    @GetMapping("/user/{userId}/details")
    public UserDto getUserDetails(@PathVariable Long userId) throws Exception {
        return userService.getUserDetails(userId);
    }

    // get wish list details
    @GetMapping("/user/{userId}/wishlist")
    public void getUserWishlistdetails(@PathVariable Long userId) {

    }

    // rent a book
    @PostMapping("/user/{userId}/book/{bookId}/rentBook")
    public void rentBook(@PathVariable Long userId, @PathVariable Long bookId) throws Exception {
        userService.rentBook(userId, bookId);
    }

    // request for book

}
