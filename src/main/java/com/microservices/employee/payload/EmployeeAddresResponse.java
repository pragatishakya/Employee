package com.microservices.employee.payload;

public class EmployeeAddresResponse {
	
	private EmployeeDto employeeDto;
	private AddressResponse addressResponse;
	
	
	public EmployeeDto getEmployeeDto() {
		return employeeDto;
	}
	public void setEmployeeDto(EmployeeDto employeeDto) {
		this.employeeDto = employeeDto;
	}

	public AddressResponse getAddressResponse() {
		return addressResponse;
	}
	public void setAddressResponse(AddressResponse addressResponse) {
		this.addressResponse = addressResponse;
	}
	 
}
