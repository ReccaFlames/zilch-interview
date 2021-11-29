DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS transaction;

CREATE TABLE account (
    account_id RAW(16) NOT NULL,
    creation_time TIMESTAMP NOT NULL,
    last_modification_time TIMESTAMP NOT NULL
);
COMMENT ON TABLE account IS 'Stores users accounts';
COMMENT ON COLUMN account.account_id IS 'Unique account id';
COMMENT ON COLUMN account.creation_time IS 'Date of account creation';
COMMENT ON COLUMN account.last_modification_time IS 'Date of last account data modification';

CREATE UNIQUE INDEX pk_account
   ON account (account_id);

CREATE TABLE transaction (
    transaction_id RAW(16) NOT NULL,
    booking_date TIMESTAMP NOT NULL,
    value_date TIMESTAMP NOT NULL,
    amount NUMBER NOT NULL,
    currency VARCHAR(3) NOT NULL,
    type VARCHAR(20) NOT NULL,
    account_id RAW(16) NOT NULL
);

COMMENT ON TABLE transaction IS 'Stores users transactions';
COMMENT ON COLUMN transaction.transaction_id IS 'Unique transaction id';
COMMENT ON COLUMN transaction.booking_date IS 'Date of transaction booking';
COMMENT ON COLUMN transaction.value_date IS 'Date of transaction value';
COMMENT ON COLUMN transaction.amount IS 'Amount of transaction';
COMMENT ON COLUMN transaction.currency IS 'Currency of transaction';
COMMENT ON COLUMN transaction.account_id IS 'Id of account for each transaction was created';

CREATE UNIQUE INDEX pk_transaction
   ON transaction (transaction_id);

INSERT INTO account (account_id, creation_time, last_modification_time) VALUES ('f47cae7222be4d3bb530dca92ecfa718', '2020-10-02 16:32:45', '2020-10-02 16:32:45');
INSERT INTO transaction (transaction_id, booking_date, value_date, amount, currency, type, account_id) VALUES ('1b410e5aedbe4a9580e7d7ae88fae5de', '2020-10-03 16:32:45', '2020-10-03 16:32:45', 100, 'EUR', 'PAYIN', 'f47cae7222be4d3bb530dca92ecfa718');
INSERT INTO transaction (transaction_id, booking_date, value_date, amount, currency, type, account_id) VALUES ('d9e296dcabbb4f1595eae6b5ae4e0de7', '2020-11-03 16:32:45', '2020-11-03 16:32:45', 50, 'EUR', 'PAYOUT', 'f47cae7222be4d3bb530dca92ecfa718');
INSERT INTO transaction (transaction_id, booking_date, value_date, amount, currency, type, account_id) VALUES ('95482e40b4df44a5b1df816c74c4ec2f', '2020-10-07 16:32:45', '2020-10-07 16:32:45', 25, 'EUR', 'PAYIN','f47cae7222be4d3bb530dca92ecfa718');
