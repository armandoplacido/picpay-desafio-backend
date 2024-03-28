/* Clear WALLETS */
DELETE FROM TRANSACTIONS;

DELETE FROM WALLETS;

/* Insert wallets */
INSERT INTO
    WALLETS (
        ID, FULL_NAME, DOCUMENT, EMAIL, "PASSWORD", WALLET_TYPE, BALANCE
    )
VALUES (
        1, 'Armando - Usuário', 12345678900, 'armando@test.com', '123456', 1, 50000.00
    );

INSERT INTO
    WALLETS (
        ID, FULL_NAME, DOCUMENT, EMAIL, "PASSWORD", WALLET_TYPE, BALANCE
    )
VALUES (
        2, 'Maria - Lojista', 12345678901, 'maria@test.com', '123456', 2, 0.00
    );