package br.com.armandoplacido.picpaydesafiobackend.transaction;

import br.com.armandoplacido.picpaydesafiobackend.wallet.WalletRepository;

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
}
