package com.Jspiders.Bank.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Jspiders.Bank.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {

	Optional<Bank> findByBankName(String bankName);
}
