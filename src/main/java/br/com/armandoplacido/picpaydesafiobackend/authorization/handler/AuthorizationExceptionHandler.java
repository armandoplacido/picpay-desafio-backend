package br.com.armandoplacido.picpaydesafiobackend.authorization.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.armandoplacido.picpaydesafiobackend.authorization.exception.UnauthorizedTransactionException;
import br.com.armandoplacido.picpaydesafiobackend.authorization.exception.UnavailableAuthorizationServiceException;

@ControllerAdvice
public class AuthorizationExceptionHandler {
  @ExceptionHandler(UnauthorizedTransactionException.class)
  public ResponseEntity<Object> handle(UnauthorizedTransactionException e){
    return ResponseEntity.badRequest().body(e.getMessage());
  }

  @ExceptionHandler(UnavailableAuthorizationServiceException.class)
  public ResponseEntity<Object> handle(UnavailableAuthorizationServiceException e){
    return ResponseEntity.internalServerError().body(e.getMessage());
  }
}
