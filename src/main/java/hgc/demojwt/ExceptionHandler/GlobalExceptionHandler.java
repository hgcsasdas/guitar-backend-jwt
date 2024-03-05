package hgc.demojwt.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handlerArgumentException (IllegalArgumentException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handlerRunTimeException(RuntimeException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_GATEWAY);
	}
	
	
}
