package br.com.armandoplacido.picpaydesafiobackend.notification.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.armandoplacido.picpaydesafiobackend.notification.exception.NotificationException;
import br.com.armandoplacido.picpaydesafiobackend.notification.exception.UnavailableNotificationService;

@ControllerAdvice
public class NotificationExceptionHandler {
  
  @ExceptionHandler(NotificationException.class)
  public ResponseEntity<Object> handler(NotificationException e){
    return ResponseEntity.badRequest().body(e.getMessage());
  }

  @ExceptionHandler(UnavailableNotificationService.class)
  public ResponseEntity<Object> handler(UnavailableNotificationService e){
    return ResponseEntity.badRequest().body(e.getMessage());
  }
}
