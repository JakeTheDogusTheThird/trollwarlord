package com.example.minibank.transfer;

public class BankAccountByIdNotFoundException extends RuntimeException {
    public BankAccountByIdNotFoundException(Integer id) {
        super("There's no bank account with id " + id);
    }

    public BankAccountByIdNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
