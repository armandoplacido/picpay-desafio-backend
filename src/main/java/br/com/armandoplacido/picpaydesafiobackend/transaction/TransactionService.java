package br.com.armandoplacido.picpaydesafiobackend.transaction;

import br.com.armandoplacido.picpaydesafiobackend.transaction.exception.InvalidTransactionException;
import br.com.armandoplacido.picpaydesafiobackend.wallet.Wallet;
import br.com.armandoplacido.picpaydesafiobackend.wallet.WalletRepository;
import br.com.armandoplacido.picpaydesafiobackend.wallet.WalletType;

public class TransactionService {
  private final TransactionRepository transactionRepository;
  private final WalletRepository walletRepository;

  public TransactionService(
    TransactionRepository transactionRepository, 
    WalletRepository walletRepository
    ) {
    this.transactionRepository = transactionRepository;
    this.walletRepository = walletRepository;
  }

  public void createTransaction(Transaction transaction){

  }

  private void validate(Transaction transaction) {
    walletRepository.findById(transaction.payee())
    .map(payee -> walletRepository.findById(transaction.payer())
      .map(payer -> isValidTransaction(transaction, payer) ? transaction : null)
      .orElseThrow(() -> new InvalidTransactionException(transaction)))
    .orElseThrow(() -> new InvalidTransactionException(transaction));
  }

  private boolean isValidTransaction(Transaction transaction, Wallet payer) {
    return payer.walletType() == WalletType.COMMON.getValue() && 
      payer.balance().compareTo(transaction.value()) >= 0 &&
      !payer.id().equals(transaction.payee());
  }
}
