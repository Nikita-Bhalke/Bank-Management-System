package com.Jspiders.Bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Jspiders.Bank.ResponseDto.bankDto;
import com.Jspiders.Bank.service.IBankService;

@RestController
@RequestMapping("/banks")
public class BankController {
	@Autowired
	private IBankService bankService;

	@PostMapping
	public ResponseEntity<bankDto> createBank(@RequestBody bankDto BankDto) {

		return new ResponseEntity<>(bankService.createBank(BankDto), HttpStatus.CREATED);

	}

	@GetMapping
	public ResponseEntity<List<bankDto>> getAllBanks() {
		return ResponseEntity.ok(bankService.getAllBanks());

	}

	@PutMapping("/{id}")
	public ResponseEntity<bankDto> updateBank(@PathVariable Long id, @RequestBody bankDto BankDto) {
		return ResponseEntity.ok(bankService.updateBank(id, BankDto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> deleteBank(@PathVariable Long id) {
		bankService.deleteBank(id);
		return ResponseEntity.ok("Bank deleted Sussefully");
	}

	@GetMapping("/search")
	public ResponseEntity<bankDto> getBankByName(@RequestParam String name) {
		return ResponseEntity.ok(bankService.getByBankName(name));
	}

	@GetMapping("/{id}/total-balance")
	public ResponseEntity<Double> getTotalBalance(@PathVariable Long id) {
		return ResponseEntity.ok(bankService.getTotalBalance(id));
	}

}
