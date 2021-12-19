package com.example.Local.Library.service.impl;

import com.example.Local.Library.enums.BookStatusEnum;
import com.example.Local.Library.dto.BookInstanceDto;
import com.example.Local.Library.dto.UserDto;
import com.example.Local.Library.entity.BookEntity;
import com.example.Local.Library.entity.BookInstanceEntity;
import com.example.Local.Library.entity.UserEntity;
import com.example.Local.Library.repository.BookInstanceRepository;
import com.example.Local.Library.repository.BookRepository;
import com.example.Local.Library.repository.UserRepository;
import com.example.Local.Library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*

TODO:
    1. move this user not found and book not found.
    2. add db lock in the renting of book.
 */


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookInstanceRepository bookInstanceRepository;

    @Override
    public UserDto getUserDetails(Long userId) throws Exception {
        Optional<UserEntity> entity = this.userRepository.findById(userId);
        if (!entity.isPresent()) {
            throw new Exception("No User id found");
        }
        return convertToDto(entity.get());
    }

    @Override
    public void rentBook(Long userId, Long bookId) throws Exception {
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        BookEntity bookEntity = bookRepository.findById(bookId).orElse(null);

        if (userEntity == null) {
            throw new Exception("No Book Found");
        }

        if (bookEntity == null) {
            throw new Exception("No user found");
        }

        // check if user already taken that book
        List<BookInstanceEntity> currentEntry = this.bookInstanceRepository.findByUserIdAndBookId(userId, bookId);

        // don't add if already there
        if (!currentEntry.isEmpty()) {
            throw new Exception("User already has the book");
        }

        BookInstanceEntity entity = new BookInstanceEntity();
        entity.setBookId(bookId);
        entity.setUserId(userId);
        entity.setStatus(BookStatusEnum.RENTED.getValue());

        // update user status
        userEntity.setCost(userEntity.getCost() + bookEntity.getCost());
        userEntity.incrementRentBook();

        // todo book related stuff in the book service ?
        // update book status
        bookEntity.incrementRent();

        this.bookInstanceRepository.saveAll(currentEntry);
        this.userRepository.save(userEntity);
        this.bookRepository.save(bookEntity);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = convertToEntity(userDto);
        return convertToDto(this.userRepository.save(userEntity));
    }

    @Override
    public List<BookInstanceDto> submitBook(Long userId, Long bookId) throws Exception {
        List<BookInstanceEntity> userCurrentBook = this.bookInstanceRepository.findByUserIdAndBookIdAndStatus(userId, bookId, BookStatusEnum.RENTED.getValue());
        if (userCurrentBook == null || userCurrentBook.isEmpty()) {
            throw new Exception("No book found");
        }
        userCurrentBook.forEach(val -> val.setStatus(BookStatusEnum.RETURNED.getValue()));
        this.bookInstanceRepository.saveAll(userCurrentBook);
        return userCurrentBook.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private BookInstanceDto convertToDto(BookInstanceEntity entity) {
        BookInstanceDto result = new BookInstanceDto();
        result.setUserId(entity.getUserId());
        result.setBookId(entity.getBookId());
        result.setStatus(entity.getStatus());
        return result;
    }

    private UserEntity convertToEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.getName());
        userEntity.setRentBook(0);
        return userEntity;
    }

    private UserDto convertToDto(UserEntity userEntity) {
        UserDto dto = new UserDto();
        dto.setId(userEntity.getId());
        dto.setName(userEntity.getName());
        dto.setRentBook(userEntity.getRentBook());
        dto.setCost(userEntity.getCost());
        return dto;
    }


}
