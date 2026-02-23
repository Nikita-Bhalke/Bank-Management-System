package com.Jspiders.Bank.service;

import java.util.List;

import com.Jspiders.Bank.ResponseDto.AccountDto;

public interface IAccountService {
	
	AccountDto create(AccountDto dto);
	List<AccountDto> getAllAccounts();
	AccountDto getAccountById(Long id);
	AccountDto updateAccount(Long id, AccountDto dto);
	void deleteAccount(Long id);
	List<AccountDto> getAccountByBankId(Long bankId);
	AccountDto deposite(Long id, double amount);
	AccountDto withdraw(Long id, double amount);
	double getBalance(Long id);

}
