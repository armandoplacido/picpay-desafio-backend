package br.com.armandoplacido.picpaydesafiobackend.authorization;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

import br.com.armandoplacido.picpaydesafiobackend.authorization.exception.UnauthorizedTransactionException;
import br.com.armandoplacido.picpaydesafiobackend.authorization.exception.UnavailableAuthorizationServiceException;
import br.com.armandoplacido.picpaydesafiobackend.transaction.Transaction;

@Service
public class AuthorizationService {
  private RestClient restClient;

  public AuthorizationService(
    RestClient.Builder builder
  ){
    this.restClient = builder
    .baseUrl("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc")
    .build();
  }

  public void authorizeTransaction(Transaction transaction){
    try {
      var response = restClient.get()
        .retrieve()
        .toEntity(Authorization.class);

      if(response.getStatusCode().isError() || response.getBody().isAuthorized()){
        throw new UnauthorizedTransactionException(transaction);
      }

    } catch (HttpServerErrorException.ServiceUnavailable e) {
      throw new UnavailableAuthorizationServiceException(transaction);
    }
  }
}
