package com.hstore.vn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hstore.vn.exception.auth.EmailAlreadyExitsException;
import com.hstore.vn.exception.product.CreateProductFailuerException;
import com.hstore.vn.exception.product.NotFoundProductException;
import com.hstore.vn.payload.response.ApiResponse;

@ControllerAdvice
public class ErrorControllerAdvisor extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = EmailAlreadyExitsException.class)
    public ResponseEntity<ApiResponse<ResponseEntity<String>>> handleEmailAlreadyExists(EmailAlreadyExitsException ex) {
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        ApiResponse<ResponseEntity<String>> apiResponse = new ApiResponse<>("Email already exists", responseEntity, -1);
        return new ResponseEntity<ApiResponse<ResponseEntity<String>>>(apiResponse, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(value = NotFoundProductException.class)
	public ResponseEntity<String> handleNotFoundProduct(){
		return new ResponseEntity<String>("Not found product",HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(value = CreateProductFailuerException.class)
	public ResponseEntity<String> handleCreateProductFailuer(){
		return new ResponseEntity<String>("Can not create product",HttpStatus.BAD_REQUEST);
	}
	
	
}
