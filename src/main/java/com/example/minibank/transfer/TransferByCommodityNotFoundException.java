package com.example.minibank.transfer;

public class TransferByCommodityNotFoundException extends RuntimeException {
    public TransferByCommodityNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public TransferByCommodityNotFoundException(Commodity commodity) {
        super("There are no transfers that cover the given commodity " + commodity);
    }
}
