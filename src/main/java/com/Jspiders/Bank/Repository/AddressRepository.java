package com.Jspiders.Bank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Jspiders.Bank.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	
}
