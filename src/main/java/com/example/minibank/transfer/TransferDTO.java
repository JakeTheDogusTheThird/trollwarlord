package com.example.minibank.transfer;

import java.math.BigDecimal;

public record TransferDTO(Integer fromBankAccountId, Integer toBankAccountId, double ammount, Commodity commodity) { }
