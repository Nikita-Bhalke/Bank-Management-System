package com.Jspiders.Bank.service;

import com.Jspiders.Bank.ResponseDto.AddressDto;

public interface IAddressService {

	AddressDto create(AddressDto dto);

	AddressDto getById(Long id);

	AddressDto update(Long id, AddressDto dto);

	void delete(Long id);
}
