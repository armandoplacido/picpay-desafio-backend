package br.com.armandoplacido.picpaydesafiobackend.notification.exception;

import br.com.armandoplacido.picpaydesafiobackend.transaction.Transaction;

public class NotificationException extends RuntimeException {
  public NotificationException(Transaction transaction){
    super("Não foi possível enviar uma notificação para a trazação - %s".formatted(transaction));
  }
}
