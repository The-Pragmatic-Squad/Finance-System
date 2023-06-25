CREATE SEQUENCE accounts_id_seq;

CREATE TABLE IF NOT EXISTS accounts
(
    id               INT          NOT NULL PRIMARY KEY DEFAULT nextval('accounts_id_seq'),
    username         VARCHAR(255) NOT NULL UNIQUE,
    password         VARCHAR(255) NOT NULL CHECK ( LENGTH(password) >= 8 ),
    email            VARCHAR(255) NOT NULL UNIQUE CHECK ( email ~* '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$' ),
    phone            VARCHAR(255) NOT NULL UNIQUE CHECK ( phone ~* '^[0-9]{11}$' ),
    created_at       TIMESTAMP                         DEFAULT CURRENT_TIMESTAMP,
    credit_number    VARCHAR(255),
    balance          NUMERIC                           DEFAULT 0,
    last_transaction TIMESTAMP,
    account_type     VARCHAR(255)                      DEFAULT 'STANDARD',
    active           BOOLEAN                           DEFAULT TRUE
);

ALTER SEQUENCE accounts_id_seq OWNED BY accounts.id;

CREATE SEQUENCE transactions_id_seq;

CREATE TABLE IF NOT EXISTS transactions
(
    id               INT          NOT NULL PRIMARY KEY DEFAULT nextval('transactions_id_seq'),
    account_id       INT          NOT NULL REFERENCES accounts (id),
    status           VARCHAR(255) NOT NULL check ( status in ('PENDING', 'SUCCESS', 'FAILED') ),
    transaction_type VARCHAR(255) NOT NULL check ( transaction_type in ('DEPOSIT', 'WITHDRAWAL', 'TRANSFER') ),
    amount           NUMERIC      NOT NULL,
    transaction_date TIMESTAMP                         DEFAULT CURRENT_TIMESTAMP,
    description      VARCHAR(255)                      DEFAULT 'N/A',
    balance_before   NUMERIC,
    balance_after    NUMERIC,
    details          VARCHAR(255)                      DEFAULT 'N/A'
);

ALTER SEQUENCE transactions_id_seq OWNED BY transactions.id;