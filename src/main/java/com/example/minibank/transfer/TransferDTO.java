package com.example.minibank.transfer;

public record TransferDTO(
        Integer fromBankAccountId,
        Integer toBankAccountId,
        double ammount,
        Transfer.Commodity commodity
) {
}
