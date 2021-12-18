package com.example.Local.Library.service.impl;

import com.example.Local.Library.dto.BookDto;
import com.example.Local.Library.entity.BookEntity;
import com.example.Local.Library.repository.BookRepository;
import com.example.Local.Library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookDto getBookDetails(Long bookId) throws Exception {
        Optional<BookEntity> entity = this.bookRepository.findById(bookId);
        if (!entity.isPresent()) {
            throw new Exception("Entity not found");
        }
        return convertToDto(entity.get());
    }

    @Override
    public List<BookDto> addBook(List<BookDto> bookDtoList) {
        List<BookEntity> collect = bookDtoList.stream().map(this::convertToEntity).collect(Collectors.toList());
        this.bookRepository.saveAll(collect);
        return collect.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private BookDto convertToDto(BookEntity bookEntity) {
        BookDto bookDto = new BookDto();
        bookDto.setIsAvailable(false);
        bookDto.setId(bookEntity.getId());
        bookDto.setBookName(bookEntity.getBookName());
        if (bookEntity.getAvailable() > 0) {
            bookDto.setIsAvailable(true);
        }
        return bookDto;
    }

    private BookEntity convertToEntity(BookDto bookDto) {
        BookEntity entity = new BookEntity();
        entity.setId(bookDto.getId());
        entity.setBookName(bookDto.getBookName());
        entity.setAvailable(1);
        entity.setCost(1000.0);
        return entity;
    }
}
