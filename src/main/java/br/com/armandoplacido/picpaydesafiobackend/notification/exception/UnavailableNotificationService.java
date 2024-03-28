package br.com.armandoplacido.picpaydesafiobackend.notification.exception;

import br.com.armandoplacido.picpaydesafiobackend.transaction.Transaction;

public class UnavailableNotificationService extends RuntimeException {
  public UnavailableNotificationService(Transaction transaction){
    super("O Serviço de notificação está fora do ar");
  }
}
