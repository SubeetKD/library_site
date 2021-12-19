package com.example.Local.Library.controller;

import com.example.Local.Library.dto.UserDto;
import com.example.Local.Library.service.UserService;
import com.example.Local.Library.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private ResponseBuilder responseBuilder;

    @Autowired
    private UserService userService;

    // get user details
    @GetMapping("/user/{userId}/details")
    public ResponseEntity getUserDetails(@PathVariable Long userId) throws Exception {
        return this.responseBuilder.getSuccessfulResponse(HttpStatus.OK, userService.getUserDetails(userId));
    }

    @PostMapping("create/user")
    public ResponseEntity createUser(@RequestBody UserDto userDto) {
        return this.responseBuilder.getSuccessfulResponse(HttpStatus.CREATED, userService.createUser(userDto));
    }

    @RequestMapping(value = "/user/{userId}/book/{bookId}/submit", method = RequestMethod.PATCH)
    public ResponseEntity submitbook(@PathVariable Long userId, @PathVariable Long bookId) throws Exception {
        return this.responseBuilder.getSuccessfulResponse(HttpStatus.ACCEPTED, this.userService.submitBook(userId, bookId));
    }

    // todo get wish list details
    @GetMapping("/user/{userId}/wishlist")
    public void getUserWishlistdetails(@PathVariable Long userId) {

    }

    // rent a book
    @PostMapping("/user/{userId}/book/{bookId}/rentBook")
    public ResponseEntity rentBook(@PathVariable Long userId, @PathVariable Long bookId) throws Exception {
        userService.rentBook(userId, bookId);
        return this.responseBuilder.getSuccessfulResponse(HttpStatus.CREATED, null);
    }

    // request for book

}
