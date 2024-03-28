package br.com.armandoplacido.picpaydesafiobackend.notification;

import org.springframework.stereotype.Service;

import br.com.armandoplacido.picpaydesafiobackend.transaction.Transaction;

@Service
public class NotificationService {
  private NotificationProducer notificationProducer;

  public NotificationService(NotificationProducer notificationProducer) {
    this.notificationProducer = notificationProducer;
  }

  public void sendNotification(Transaction transaction) {
    notificationProducer.sendNotification(transaction);
  }
}
