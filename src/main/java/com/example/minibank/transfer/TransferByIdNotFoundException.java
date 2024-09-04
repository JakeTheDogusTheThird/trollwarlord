package com.example.minibank.transfer;

public class TransferByIdNotFoundException extends RuntimeException {

    public TransferByIdNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public TransferByIdNotFoundException(Integer id) {
        super("There is no transfer with id " + id);
    }
}
