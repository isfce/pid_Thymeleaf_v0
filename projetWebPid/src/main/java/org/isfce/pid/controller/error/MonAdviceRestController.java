package org.isfce.pid.controller.error;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MonAdviceRestController {
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> gestionErreur(NoSuchElementException exc) {
		
		return new ResponseEntity<>(exc.getMessage(),HttpStatus.NOT_FOUND);
	}

}
