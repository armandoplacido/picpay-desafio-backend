package br.com.armandoplacido.picpaydesafiobackend.transaction;

import org.springframework.stereotype.Service;

import br.com.armandoplacido.picpaydesafiobackend.authorization.AuthorizationService;
import br.com.armandoplacido.picpaydesafiobackend.notification.NotificationService;
import br.com.armandoplacido.picpaydesafiobackend.transaction.exception.InvalidTransactionException;
import br.com.armandoplacido.picpaydesafiobackend.wallet.Wallet;
import br.com.armandoplacido.picpaydesafiobackend.wallet.WalletRepository;
import br.com.armandoplacido.picpaydesafiobackend.wallet.WalletType;

@Service
public class TransactionService {
  private final TransactionRepository transactionRepository;
  private final WalletRepository walletRepository;
  private final AuthorizationService authorizationService;
  private final NotificationService notificationService;

  public TransactionService(
    TransactionRepository transactionRepository, 
    WalletRepository walletRepository,
    AuthorizationService authorizationService, 
    NotificationService notificationService
    ) {
    this.transactionRepository = transactionRepository;
    this.walletRepository = walletRepository;
    this.authorizationService = authorizationService;
    this.notificationService = notificationService;
  }

  public Transaction createTransaction(Transaction transaction){
    validate(transaction);

    var newTransaction = transactionRepository.save(transaction);
    var payerWallet = walletRepository.findById(transaction.payer()).get();
    var payeeWallet = walletRepository.findById(transaction.payee()).get();
    
    walletRepository.save(payerWallet.pay(transaction.value()));
    walletRepository.save(payeeWallet.recieve(transaction.value()));
    
    authorizationService.authorizeTransaction(transaction);
    notificationService.sendNotification(transaction);

    return newTransaction;
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
