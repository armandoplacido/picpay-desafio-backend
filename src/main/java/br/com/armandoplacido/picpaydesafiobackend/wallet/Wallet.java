package br.com.armandoplacido.picpaydesafiobackend.wallet;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("WALLETS")
public record Wallet(
  @Id Long id,
  String fullName,
  String document,
  String email,
  String password,
  int walletType,
  BigDecimal balance
) {

  public Wallet pay(BigDecimal value) {
    return new Wallet(id,fullName,document,email,password,walletType, balance.subtract(value));
  }

  public Wallet recieve(BigDecimal value) {
    return new Wallet(id,fullName,document,email,password,walletType, balance.add(value));
  }
  
}