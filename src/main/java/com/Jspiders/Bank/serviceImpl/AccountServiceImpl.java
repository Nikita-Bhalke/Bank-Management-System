package com.Jspiders.Bank.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Jspiders.Bank.Exception.BadRequestException;
import com.Jspiders.Bank.Exception.ResourceNotFoundException;
import com.Jspiders.Bank.Repository.AccountRepository;
import com.Jspiders.Bank.Repository.BankRepository;
import com.Jspiders.Bank.ResponseDto.AccountDto;
import com.Jspiders.Bank.entity.Account;
import com.Jspiders.Bank.entity.Bank;
import com.Jspiders.Bank.service.IAccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private final AccountRepository accountRepository;
	@Autowired
	private final BankRepository bankRepository;
	@Autowired
	private final ModelMapper modelMapper;

	@Override
	public AccountDto create(AccountDto dto) {

		Bank bank = bankRepository.findById(dto.getBankId())
				.orElseThrow(() -> new ResourceNotFoundException("Bank not found"));

		if (accountRepository.findByAccountNumber(dto.getAccountNumber()).isPresent()) {
			throw new BadRequestException("Account number already exists");
		}

		Account account = modelMapper.map(dto, Account.class);
		account.setBank(bank);
		account.setBalance(0);

		Account saved = accountRepository.save(account);

		return modelMapper.map(saved, AccountDto.class);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		return accountRepository.findAll().stream().map(acc -> modelMapper.map(acc, AccountDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public AccountDto getAccountById(Long id) {

		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found"));

		return modelMapper.map(account, AccountDto.class);
	}

	@Override
	public AccountDto updateAccount(Long id, AccountDto dto) {

		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found"));

		account.setAccountNumber(dto.getAccountNumber());

		Account updated = accountRepository.save(account);

		return modelMapper.map(updated, AccountDto.class);
	}

	@Override
	public void deleteAccount(Long id) {

		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found"));

		accountRepository.delete(account);
	}

	@Override
	public List<AccountDto> getAccountByBankId(Long bankId) {

		return accountRepository.findByBankBankId(bankId).stream().map(acc -> modelMapper.map(acc, AccountDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public AccountDto deposite(Long id, double amount) {

		if (amount <= 0) {
			throw new BadRequestException("Deposit amount must be greater than 0");
		}

		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found"));

		account.setBalance(account.getBalance() + amount);

		Account updated = accountRepository.save(account);

		return modelMapper.map(updated, AccountDto.class);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {

		if (amount <= 0) {
			throw new BadRequestException("Withdraw amount must be greater than 0");
		}

		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found"));

		if (account.getBalance() < amount) {
			throw new BadRequestException("Insufficient balance");
		}

		account.setBalance(account.getBalance() - amount);

		Account updated = accountRepository.save(account);

		return modelMapper.map(updated, AccountDto.class);
	}

	@Override
	public double getBalance(Long id) {

		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found"));

		return account.getBalance();
	}

}
