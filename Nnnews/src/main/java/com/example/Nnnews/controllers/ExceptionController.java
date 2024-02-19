package com.example.Nnnews.controllers;

import com.example.Nnnews.dto.exceptiondto.ExceptionResponseDTO;
import com.example.Nnnews.errors.BusinessException;
import com.example.Nnnews.errors.SortException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(SortException.class)
    public ResponseEntity<?> exception(SortException ex){
        ExceptionResponseDTO response = new ExceptionResponseDTO(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> businessException(BusinessException ex){
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(ex.getMessage());
        return new ResponseEntity<>(responseDTO,ex.getHttpStatus());
    }
}
