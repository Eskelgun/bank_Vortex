package com.bank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;

import com.bank.domain.Customer;
import com.bank.domain.DocumentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@Rollback(false)
class CustomerServiceTest {

	@Autowired
	CustomerService customerService;

	@Autowired
	DocumentTypeService documentTypeService;

	@Test
	@Order(1)
	void debeCrearUnCustomer() throws Exception {
		// Arrange

		Customer customer = new Customer();
		DocumentType documentType = documentTypeService.findById(1).get();

		assertNotNull(documentType);

		customer.setAccounts(null);
		customer.setAddress("Cra 123 #321");
		customer.setCustId(2020);
		customer.setDocumentType(documentType);
		customer.setEmail("prueba@hotmail.com");
		customer.setEnable("S");
		customer.setName("Prueba");
		customer.setPhone("12345678");
		customer.setRegisteredAccounts(null);
		customer.setToken(UUID.randomUUID().toString().toUpperCase());

		// Act
		customerService.save(customer);

		// Assert
		assertNotNull(customer);

	}

	@Test
	@Order(2)

	void debeBuscarUnCustomer() throws Exception {
		// Arrange

		Customer customer = null;

		// Act
		customer = customerService.findById(2020).get();

//		Assert
		assertNotNull(customer);
	}

	@Test
	@Order(3)

	void debeModificarUnCustomer() throws Exception {

		// Arrange

		Customer customer = null;
		customer = customerService.findById(2020).get();
		assertNotNull(customer);

		// Act
		customer.setEnable("N");
		customerService.update(customer);

//		Assert
		assertEquals("N", customer.getEnable());

	}

	@Test
	@Order(4)

	void debeBorrarUnCustomer() throws Exception {

		// Arrange

		Customer customer = null;
		customer = customerService.findById(2020).get();
		assertNotNull(customer);

		// Act
		customerService.delete(customer);

//		Assert

	}

}
