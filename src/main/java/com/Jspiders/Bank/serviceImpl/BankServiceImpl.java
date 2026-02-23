package com.Jspiders.Bank.serviceImpl;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Jspiders.Bank.Exception.BadRequestException;
import com.Jspiders.Bank.Exception.ResourceNotFoundException;
import com.Jspiders.Bank.Repository.BankRepository;
import com.Jspiders.Bank.ResponseDto.bankDto;
import com.Jspiders.Bank.entity.Bank;
import com.Jspiders.Bank.service.IBankService;

@Service
public class BankServiceImpl implements IBankService {

	@Autowired
    private  BankRepository bankRepository;
	@Autowired
    private ModelMapper modelMapper;

    @Override
    public bankDto createBank(bankDto bankDto) {

        if (bankDto.getAddress().getPincode().length() != 6) {
            throw new BadRequestException("Pin code must be 6 digits");
        }

        Bank bank = modelMapper.map(bankDto, Bank.class);
        Bank saved = bankRepository.save(bank);

        return modelMapper.map(saved, bankDto.class);
    }

    @Override
    public List<bankDto> getAllBanks() {
        return bankRepository.findAll()
                .stream()
                .map(bank -> modelMapper.map(bank, bankDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public bankDto getBankById(Long id) {

        Bank bank = bankRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Bank not found with id: " + id));

        return modelMapper.map(bank, bankDto.class);
    }

    @Override
    public bankDto updateBank(Long id, bankDto bankDto) {

        Bank bank = bankRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Bank not found"));

        bank.setBankName(bankDto.getBankName());

        Bank updated = bankRepository.save(bank);

        return modelMapper.map(updated, bankDto.class);
    }

    @Override
    public void deleteBank(Long id) {

        Bank bank = bankRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Bank not found"));

        double totalBalance = bank.getAccount()
                .stream()
                .mapToDouble(acc -> acc.getBalance())
                .sum();

        if (totalBalance > 0) {
            throw new BadRequestException("Cannot delete bank. Accounts still have balance.");
        }

        bankRepository.delete(bank);
    }

    @Override
    public bankDto getByBankName(String name) {

        Bank bank = bankRepository.findByBankName(name)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Bank not found"));

        return modelMapper.map(bank, bankDto.class);
    }

    @Override
    public double getTotalBalance(Long bankId) {

        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Bank not found"));

        return bank.getAccount()
                .stream()
                .mapToDouble(acc -> acc.getBalance())
                .sum();
    }
}

