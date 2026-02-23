package com.Jspiders.Bank.service;

import java.util.List;

import com.Jspiders.Bank.ResponseDto.bankDto;

public interface IBankService {

	bankDto createBank(bankDto BankDto);
	List<bankDto> getAllBanks();
	bankDto getBankById(Long id);
	void deleteBank(Long id);
	bankDto updateBank(Long id,bankDto bankDto);
	bankDto getByBankName(String name);
	double getTotalBalance(Long bankId);
	
}
