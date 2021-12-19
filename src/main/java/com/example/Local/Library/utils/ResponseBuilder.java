package com.example.Local.Library.utils;

import com.example.Local.Library.dto.MetadataDto;
import com.example.Local.Library.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseBuilder {

    public <T> ResponseEntity getSuccessfulResponse(HttpStatus status, T data) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(data);
        MetadataDto metadataDto = new MetadataDto();
        metadataDto.setMessage("Success");
        metadataDto.setStatus(status);
        responseDto.setMeta(metadataDto);
        ResponseEntity entity = new ResponseEntity( responseDto, status);
        return entity;
    }

}
