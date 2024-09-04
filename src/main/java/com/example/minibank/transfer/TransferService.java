package com.example.minibank.transfer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class TransferService {
    private final BankAccountRepository bankAccountRepository;
    private final TransferRepository transferRepository;


    public Integer executeTransfer(TransferDTO transferDTO) throws BankAccountByIdNotFoundException,
            BankAccountBalanceNotEnoughException {

        var from = bankAccountRepository
                .findById(transferDTO.fromBankAccountId())
                .orElseThrow(() -> new BankAccountByIdNotFoundException(transferDTO.fromBankAccountId()));

        var to  = bankAccountRepository
                .findById(transferDTO.toBankAccountId())
                .orElseThrow(() -> new BankAccountByIdNotFoundException(transferDTO.toBankAccountId()));

        if (from.getBalance() > transferDTO.ammount()) {
            from.setBalance(from.getBalance() - transferDTO.ammount());
            to.setBalance(to.getBalance() + transferDTO.ammount());
        }
        else {
            throw new BankAccountBalanceNotEnoughException(from);
        }
        // Throw exception not enough balance/funds
        bankAccountRepository.save(from);
        bankAccountRepository.save(to);

        Transfer transfer = Transfer.builder()
                .fromBankAccountId(transferDTO.fromBankAccountId())
                .toBankAccountId(transferDTO.toBankAccountId())
                .transferredAmount(transferDTO.ammount())
                .commodity(transferDTO.commodity())
                .build();
        return transferRepository.save(transfer).getId();
    }

    public Set<Transfer> getBankAccountTransfers(Integer bankAccountId) {
        return transferRepository.findByFromBankAccountId(bankAccountId);
    }

    public Transfer getTransferById(Integer transferId) throws TransferByIdNotFoundException{
        return transferRepository.findById(transferId).orElseThrow(
                () -> new TransferByIdNotFoundException(transferId)
        );
    }

    public Transfer getTransferByCommodity(Commodity commodity) throws TransferByCommodityNotFoundException {
        return transferRepository.findByCommodity(commodity).orElseThrow(
                () -> new TransferByCommodityNotFoundException(commodity)
        );
    }

}
