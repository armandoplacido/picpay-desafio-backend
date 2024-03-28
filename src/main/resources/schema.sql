CREATE TABLE IF NOT EXISTS WALLETS (
    ID SERIAL PRIMARY KEY, 
    FULL_NAME VARCHAR(100), 
    DOCUMENT VARCHAR(20), 
    EMAIL VARCHAR(100), 
    "PASSWORD" VARCHAR(100), 
    WALLET_TYPE INT, 
    BALANCE DECIMAL(10, 2)
);

CREATE UNIQUE INDEX IF NOT EXISTS document_idx ON WALLETS (DOCUMENT);

CREATE UNIQUE INDEX IF NOT EXISTS email_idx ON WALLETS (EMAIL);

CREATE TABLE IF NOT EXISTS TRANSACTIONS (
    ID SERIAL PRIMARY KEY, 
    PAYER INT, 
    PAYEE INT, 
    "VALUE" DECIMAL(10, 2), 
    CREATED_AT TIMESTAMP, 
    FOREIGN KEY (PAYER) REFERENCES WALLETS (ID), 
    FOREIGN KEY (PAYEE) REFERENCES WALLETS (ID)
);