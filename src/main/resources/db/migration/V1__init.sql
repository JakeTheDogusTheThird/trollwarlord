CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY,
    license_id VARCHAR(13) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    last_updated_at TIMESTAMP NOT NULL
);

CREATE TYPE account_type AS ENUM (
    'checking_account',
    'savings_account',
    'money_market_account',
    'certificate_of_deposit_account'
);

CREATE TABLE IF NOT EXISTS bank_accounts (
    id INTEGER PRIMARY KEY,
    user_id INTEGER REFERENCES users(id) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    account account_type NOT NULL,
    balance INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    last_updated_at TIMESTAMP NOT NULL
);

CREATE TYPE commodity_enum AS ENUM (
    'not_specified',
    'income',
    'a2a_transfer',
    'food_drinks',
    'clothing',
    'medical_services',
    'loan',
    'rent_payment'
);

CREATE TABLE IF NOT EXISTS transfers (
    id INTEGER PRIMARY KEY,
    from_bank_account_id INTEGER REFERENCES bank_accounts(id) NOT NULL,
    to_bank_account_id INTEGER REFERENCES bank_accounts(id) NOT NULL,
    transferred_amount MONEY NOT NULL,
    commodity commodity_enum  DEFAULT 'not_specified',
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);