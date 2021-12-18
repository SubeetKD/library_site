package com.example.Local.Library.service;

import com.example.Local.Library.dto.BookDto;

import java.util.List;

public interface BookService {

    // get information of book
    BookDto getBookDetails(Long bookId) throws Exception;

    List<BookDto> addBook(List<BookDto> bookDtoList);


    // get list of book based on different things
}
