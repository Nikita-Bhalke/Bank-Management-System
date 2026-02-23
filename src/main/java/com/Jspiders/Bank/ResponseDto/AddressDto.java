package com.Jspiders.Bank.ResponseDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDto {
    private Long addressId;
    private String street;
    private String state;
    private String city;
    private String pincode;
	
}
