package com.example.Local.Library.config;

import com.example.Local.Library.dto.MetadataDto;
import com.example.Local.Library.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity defaultHandler() {
        ResponseDto responseDto = new ResponseDto();
        MetadataDto metadataDto = new MetadataDto();
        metadataDto.setMessage("Some error.");
        metadataDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        responseDto.setMeta(metadataDto);
        ResponseEntity entity = new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }
}
