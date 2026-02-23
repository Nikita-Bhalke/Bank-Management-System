package com.Jspiders.Bank.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Jspiders.Bank.Exception.ResourceNotFoundException;
import com.Jspiders.Bank.Repository.AddressRepository;
import com.Jspiders.Bank.ResponseDto.AddressDto;
import com.Jspiders.Bank.entity.Address;
import com.Jspiders.Bank.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public AddressDto create(AddressDto dto) {

		Address address = modelMapper.map(dto, Address.class);
		Address saved = addressRepository.save(address);

		return modelMapper.map(saved, AddressDto.class);
	}

	@Override
	public AddressDto getById(Long id) {

		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Address not found"));

		return modelMapper.map(address, AddressDto.class);
	}

	@Override
	public AddressDto update(Long id, AddressDto dto) {

		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Address not found"));

		address.setCity(dto.getCity());
		address.setState(dto.getState());
		address.setPincode(dto.getPincode());

		Address updated = addressRepository.save(address);

		return modelMapper.map(updated, AddressDto.class);
	}

	@Override
	public void delete(Long id) {

		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Address not found"));

		addressRepository.delete(address);
	}

}
