package com.matheuscampelo.cursomc.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.matheuscampelo.cursomc.services.exceptions.DataIntegrityException;
import com.matheuscampelo.cursomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
@ExceptionHandler(ObjectNotFoundException.class)
public ResponseEntity <StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
	
	StandardError erro = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
}

@ExceptionHandler(DataIntegrityException.class)
public ResponseEntity <StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request){
	
	StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
}
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity <StandardError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request){
	
	ValidationError erro = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de Validação", System.currentTimeMillis());
	for(FieldError er : e.getBindingResult().getFieldErrors()) {
		erro.addError(er.getField(), er.getDefaultMessage());
	}
 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
}
}
