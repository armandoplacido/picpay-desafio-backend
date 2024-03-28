package br.com.armandoplacido.picpaydesafiobackend.authorization.exception;

import br.com.armandoplacido.picpaydesafiobackend.transaction.Transaction;

public class UnavailableAuthorizationServiceException extends RuntimeException {

  public UnavailableAuthorizationServiceException(Transaction transaction){
    super("Serviço autorizador fora do ar");
  }
}
