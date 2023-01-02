package com.techmo.personalshopper.dto;

import io.swagger.models.HttpMethod;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Setter
@Getter
public class ResponseDto<T> {

    HttpStatus status;
    String message;
    T data;

    public ResponseDto(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
