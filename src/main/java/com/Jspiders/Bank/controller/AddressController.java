package com.Jspiders.Bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Jspiders.Bank.ResponseDto.AddressDto;
import com.Jspiders.Bank.service.IAddressService;

@RestController
@RequestMapping("/api/addresses")

public class AddressController {

	@Autowired
	private IAddressService addressService;

	@PostMapping
	public ResponseEntity<AddressDto> create(@RequestBody AddressDto dto) {
		return new ResponseEntity<>(addressService.create(dto), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AddressDto> getById(@PathVariable Long id) {
		return ResponseEntity.ok(addressService.getById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<AddressDto> update(@PathVariable Long id, @RequestBody AddressDto dto) {

		return ResponseEntity.ok(addressService.update(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		addressService.delete(id);
		return ResponseEntity.ok("Address deleted successfully");
	}
}