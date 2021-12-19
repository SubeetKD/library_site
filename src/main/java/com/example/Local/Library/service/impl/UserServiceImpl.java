package com.example.Local.Library.service.impl;

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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        entity.setStatus("RENTED");

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
