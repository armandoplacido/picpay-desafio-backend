package br.com.armandoplacido.picpaydesafiobackend.transaction.exception;

import br.com.armandoplacido.picpaydesafiobackend.transaction.Transaction;

public class InvalidTransactionException extends RuntimeException {
   public InvalidTransactionException(Transaction transaction) {
    super("A transação é inválida - %s".formatted(transaction));
  }
}
