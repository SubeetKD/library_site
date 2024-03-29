package com.example.Local.Library.controller;

import com.example.Local.Library.dto.BookDto;
import com.example.Local.Library.dto.BookListDto;
import com.example.Local.Library.service.BookService;
import com.example.Local.Library.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private ResponseBuilder responseBuilder;

    @Autowired
    private BookService bookService;

    // get book details
    @GetMapping("/book/{bookId}/details")
    public ResponseEntity getBookdetails(@PathVariable(name = "bookId") Long bookId) throws Exception {
        return this.responseBuilder.getSuccessfulResponse(HttpStatus.OK, this.bookService.getBookDetails(bookId));
    }

    // add book
    @PostMapping("/add/book")
    public List<BookDto> addBook(@RequestBody BookListDto bookDtoList) {
        return this.bookService.addBook(bookDtoList.getBookDtoList());
    }

}
