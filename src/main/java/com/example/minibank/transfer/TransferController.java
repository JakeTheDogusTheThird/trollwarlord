package com.example.minibank.transfer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TransferController {
    private final TransferService transferService;


    public Integer executeTransfer(TransferDTO transferDTO) {
        return transferService.executeTransfer(transferDTO);
    }
}
