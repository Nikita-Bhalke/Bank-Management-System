package com.Jspiders.Bank.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
	private Long accountId;
	private String accountNumber;
	private String accountHolderName;
	private Double balance;
	private Long bankId;

}
