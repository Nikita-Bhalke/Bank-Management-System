package com.Jspiders.Bank.ResponseDto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class bankDto {
	private Long bankId;
	private String bankName;
	private String bank;
	private AddressDto address;
	List<AccountDto> accounts;
}
