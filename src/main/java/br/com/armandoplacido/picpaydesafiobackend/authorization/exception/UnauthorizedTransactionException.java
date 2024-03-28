package br.com.armandoplacido.picpaydesafiobackend.authorization.exception;

import br.com.armandoplacido.picpaydesafiobackend.transaction.Transaction;

public class UnauthorizedTransactionException extends RuntimeException{
  public UnauthorizedTransactionException(Transaction transaction) {
    super("Transação não autorizada - %s".formatted(transaction));
  }
}
