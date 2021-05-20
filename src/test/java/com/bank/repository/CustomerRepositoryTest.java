package com.bank.repository;

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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.domain.Customer;
import com.bank.domain.DocumentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@Rollback(false)
class CustomerRepositoryTest {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	DocumentTypeRepository documentTypeRepository;

	@Test
	@Order(1)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void debeCrearUnCustomer() {
		// Arrange

		Customer customer = new Customer();
		DocumentType documentType = documentTypeRepository.findById(1).get();

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
		customerRepository.save(customer);

		// Assert
		assertNotNull(customer);

	}

	@Test
	@Order(2)
	@Transactional(readOnly = true)
	void debeBuscarUnCustomer() {
		// Arrange

		Customer customer = null;

		// Act
		customer = customerRepository.findById(2020).get();

//		Assert
		assertNotNull(customer);
	}

	@Test
	@Order(3)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void debeModificarUnCustomer() {

		// Arrange

		Customer customer = null;
		customer = customerRepository.findById(2020).get();
		assertNotNull(customer);

		// Act
		customer.setEnable("N");
		customerRepository.save(customer);

//		Assert
		assertEquals("N", customer.getEnable());

	}

	@Test
	@Order(4)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void debeBorrarUnCustomer() {

		// Arrange

		Customer customer = null;
		customer = customerRepository.findById(2020).get();
		assertNotNull(customer);

		// Act
		customerRepository.delete(customer);

//		Assert

	}


}
