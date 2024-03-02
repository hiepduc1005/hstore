package com.hstore.vn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hstore.vn.exception.auth.EmailAlreadyExitsException;
import com.hstore.vn.exception.cart.CartNotFoundException;
import com.hstore.vn.exception.category.NotFoundCategoryException;
import com.hstore.vn.exception.privilege.PrivilegeNotFoundException;
import com.hstore.vn.exception.product.CreateProductFailuerException;
import com.hstore.vn.exception.product.DeleteProductFailuer;
import com.hstore.vn.exception.product.NotFoundProductException;
import com.hstore.vn.exception.product.UpdateProductFailuer;
import com.hstore.vn.exception.role.RoleNotFoundException;
import com.hstore.vn.exception.user.CreateUserFailureException;
import com.hstore.vn.exception.user.UserNotFoundException;
import com.hstore.vn.payload.response.ApiResponse;

@RestControllerAdvice
public class ErrorControllerAdvisor extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<ApiResponse<ResponseEntity<String>>> handleUnauthenticatedUser(AuthenticationCredentialsNotFoundException ex) {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        ApiResponse<ResponseEntity<String>> apiResponse = new ApiResponse<>(ex.getMessage(), responseEntity, -1);
        return new ResponseEntity<ApiResponse<ResponseEntity<String>>>(apiResponse, HttpStatus.UNAUTHORIZED);
    }
	
	@ExceptionHandler(value = EmailAlreadyExitsException.class)
    public ResponseEntity<ApiResponse<ResponseEntity<String>>> handleEmailAlreadyExists(EmailAlreadyExitsException ex) {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        ApiResponse<ResponseEntity<String>> apiResponse = new ApiResponse<>(ex.getMessage(), responseEntity, -1);
        return new ResponseEntity<ApiResponse<ResponseEntity<String>>>(apiResponse, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(value = NotFoundProductException.class)
	public ResponseEntity<ApiResponse<ResponseEntity<String>>> handleNotFoundProduct(NotFoundProductException ex ){
		 ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
	     ApiResponse<ResponseEntity<String>> apiResponse = new ApiResponse<>(ex.getMessage(), responseEntity, -1);
	     return new ResponseEntity<ApiResponse<ResponseEntity<String>>>(apiResponse, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(value = CreateProductFailuerException.class)
	public ResponseEntity<String> handleCreateProductFailuer(CreateProductFailuerException ex){
		return new ResponseEntity<String>("Can not create product",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = NotFoundCategoryException.class)
    public ResponseEntity<ApiResponse<ResponseEntity<String>>> handleNotFoundCategory(NotFoundCategoryException ex) {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ApiResponse<ResponseEntity<String>> apiResponse = new ApiResponse<>(ex.getMessage(), responseEntity, -1);
        return new ResponseEntity<ApiResponse<ResponseEntity<String>>>(apiResponse, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(value = CartNotFoundException.class)
    public ResponseEntity<ApiResponse<ResponseEntity<String>>> handleNotFoundCart(CartNotFoundException ex) {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ApiResponse<ResponseEntity<String>> apiResponse = new ApiResponse<>(ex.getMessage(), responseEntity, -1);
        return new ResponseEntity<ApiResponse<ResponseEntity<String>>>(apiResponse, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(value = DeleteProductFailuer.class)
    public ResponseEntity<ApiResponse<ResponseEntity<String>>> handleDeleteProductFailure(DeleteProductFailuer ex) {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        ApiResponse<ResponseEntity<String>> apiResponse = new ApiResponse<>(ex.getMessage(), responseEntity, -1);
        return new ResponseEntity<ApiResponse<ResponseEntity<String>>>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(value = UpdateProductFailuer.class)
    public ResponseEntity<ApiResponse<ResponseEntity<String>>> handleUpdateProductFailure(UpdateProductFailuer ex) {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        ApiResponse<ResponseEntity<String>> apiResponse = new ApiResponse<>(ex.getMessage(), responseEntity, -1);
        return new ResponseEntity<ApiResponse<ResponseEntity<String>>>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(value = CreateUserFailureException.class)
    public ResponseEntity<ApiResponse<ResponseEntity<String>>> handleCreateUserFailure(CreateUserFailureException ex) {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        ApiResponse<ResponseEntity<String>> apiResponse = new ApiResponse<>(ex.getMessage(), responseEntity, -1);
        return new ResponseEntity<ApiResponse<ResponseEntity<String>>>(apiResponse, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ApiResponse<ResponseEntity<String>>> handleUserNotFound(UserNotFoundException ex) {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ApiResponse<ResponseEntity<String>> apiResponse = new ApiResponse<>(ex.getMessage(), responseEntity, -1);
        return new ResponseEntity<ApiResponse<ResponseEntity<String>>>(apiResponse, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(value = RoleNotFoundException.class)
    public ResponseEntity<ApiResponse<ResponseEntity<String>>> handleRoleNotFound(RoleNotFoundException ex) {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ApiResponse<ResponseEntity<String>> apiResponse = new ApiResponse<>(ex.getMessage(), responseEntity, -1);
        return new ResponseEntity<ApiResponse<ResponseEntity<String>>>(apiResponse, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(value = PrivilegeNotFoundException.class)
    public ResponseEntity<ApiResponse<ResponseEntity<String>>> handlePrivilegeNotFound(PrivilegeNotFoundException ex) {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ApiResponse<ResponseEntity<String>> apiResponse = new ApiResponse<>(ex.getMessage(), responseEntity, -1);
        return new ResponseEntity<ApiResponse<ResponseEntity<String>>>(apiResponse, HttpStatus.NOT_FOUND);
    }
	
}
