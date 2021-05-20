package com.bank.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.bank.domain.Customer;
import com.bank.dto.CustomerDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
class CustomerMapperTest {

	@Autowired
	CustomerMapper customerMapper;

	@Test
	void debeMapearDeCustomerDTOaCustomer() {

		// Arrange
		CustomerDTO customerDTO = new CustomerDTO();
		Customer customer = null;

		customerDTO.setAddress("Cra 102 #321");
		customerDTO.setCustId(2020);
		customerDTO.setEmail("prueba@hotmail.com");
		customerDTO.setEnable("S");
		customerDTO.setName("Prueba");
		customerDTO.setPhone("12345678");
		customerDTO.setToken(UUID.randomUUID().toString().toUpperCase());
		customerDTO.setDotyId(1);
		// Act
		customer = customerMapper.toCustomer(customerDTO);
		// Assert
		assertNotNull(customer);
	}

	@Test
	void debeMapearDeCustomeraCustomerDTO() {

		// Arrange
		Customer customer = new Customer();
		CustomerDTO customerDTO = null;

		customer.setAccounts(null);
		customer.setAddress("Cra 123 #321");
		customer.setCustId(2020);
		customer.setEmail("prueba@hotmail.com");
		customer.setEnable("S");
		customer.setName("Prueba");
		customer.setPhone("12345678");
		customer.setRegisteredAccounts(null);
		customer.setToken(UUID.randomUUID().toString().toUpperCase());


		// Act
		customerDTO = customerMapper.toCustomerDTO(customer);

		// Assert
		assertNotNull(customerDTO);
	}

}
