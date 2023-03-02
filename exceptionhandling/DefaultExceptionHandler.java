package com.byndr.boot.exceptionhandling;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
//optional to extend Abstract ResEntExcHanler only for validations at binding @valid in request body
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMsg> exceptionResponseforAll(Exception ex){
		
		ErrorMsg e=new ErrorMsg();
		e.setMsg(ex.getMessage());
		e.setDetails("error Occured");
		return new ResponseEntity<ErrorMsg>(e,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorMsg> exceptionResponseforUserNotFound(Exception ex){
		
		ErrorMsg e=new ErrorMsg();
		e.setMsg(ex.getMessage());
		e.setDetails("error Occured");
		return new ResponseEntity<ErrorMsg>(e,new HttpHeaders(),HttpStatus.NOT_FOUND);
		
	}
	//validations Exception handling
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ErrorMsg e=new ErrorMsg();
		e.setMsg(ex.getBindingResult().toString());
		e.setDetails("[Vallidation Failed]");
		return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(WrongInputException.class)
	public ResponseEntity<ErrorMsg> exceptionResponseforWrongInput(Exception ex){
		
		ErrorMsg e=new ErrorMsg();
		e.setMsg(ex.getMessage());
		e.setDetails("error Occured");
		return new ResponseEntity<ErrorMsg>(e,new HttpHeaders(),HttpStatus.NOT_ACCEPTABLE);
		
	}
	
}
class ErrorMsg{
	private String msg;
	private String details;
	public ErrorMsg(String msg, String details) {
		super();
		this.msg = msg;
		this.details = details;
	}
	public ErrorMsg() {
		super();
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	
}