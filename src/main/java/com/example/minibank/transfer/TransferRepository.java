package com.example.minibank.transfer;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Set;
import java.util.Optional;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {
    Set<Transfer> findByFromBankAccountId(Integer fromBankAccountId);
    Optional<Transfer> findByCommodity(Commodity commodity);

}
