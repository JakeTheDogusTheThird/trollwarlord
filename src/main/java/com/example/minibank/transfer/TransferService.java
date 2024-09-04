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

        var from = this.bankAccountRepository
                .findById(transferDTO.fromBankAccountId())
                .orElseThrow(() -> new BankAccountByIdNotFoundException(transferDTO.fromBankAccountId()));

        var to  = this.bankAccountRepository
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
        this.bankAccountRepository.save(from);
        this.bankAccountRepository.save(to);

        final Transfer transfer = Transfer.builder()
                .fromBankAccountId(from)
                .toBankAccountId(to)
                .transferredAmount(transferDTO.ammount())
                .commodity(transferDTO.commodity())
                .build();
        return this.transferRepository.save(transfer).getId();
    }

    public Set<Transfer> getBankAccountTransfers(BankAccount bankAccountId) {
        return this.transferRepository.findAllByFromBankAccountId(bankAccountId);
    }

    public Transfer getTransferById(Integer transferId) throws TransferByIdNotFoundException{
        return this.transferRepository.findById(transferId).orElseThrow(
                () -> new TransferByIdNotFoundException(transferId)
        );
    }

    public Transfer getTransferByCommodity(Transfer.Commodity commodity) throws TransferByCommodityNotFoundException {
        return this.transferRepository.findByCommodity(commodity).orElseThrow(
                () -> new TransferByCommodityNotFoundException(commodity)
        );
    }
}
