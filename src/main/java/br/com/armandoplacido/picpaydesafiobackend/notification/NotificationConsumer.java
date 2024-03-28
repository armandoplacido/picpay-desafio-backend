package br.com.armandoplacido.picpaydesafiobackend.notification;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

import br.com.armandoplacido.picpaydesafiobackend.authorization.exception.UnavailableAuthorizationServiceException;
import br.com.armandoplacido.picpaydesafiobackend.notification.exception.NotificationException;
import br.com.armandoplacido.picpaydesafiobackend.transaction.Transaction;

public class NotificationConsumer {
  private final RestClient restClient;


  public NotificationConsumer(
    RestClient.Builder builder
  ){
    this.restClient = builder
      .baseUrl("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6")
      .build();
  }

  @KafkaListener(topics = "transaction-notification", groupId = "picpay-desafio-backend")
  public void recieveNotification(Transaction transaction){


    try{
      var response = restClient.get().retrieve().toEntity(Notification.class);
      if(response.getStatusCode().isError() || !response.getBody().message()){
        throw new NotificationException(transaction);
      }

    } catch (HttpServerErrorException.ServiceUnavailable e) {
      throw new UnavailableAuthorizationServiceException(transaction);
    }
  }
}