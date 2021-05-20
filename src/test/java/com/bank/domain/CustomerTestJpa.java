package com.bank.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import javax.persistence.EntityManager;

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

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@Rollback(false)
class CustomerTestJpa {

	@Autowired
	EntityManager entityManager;

	@Test
	@Order(1)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void debeCrearUnCustomer() {
		// Arrange

		Customer customer = new Customer();
		DocumentType documentType = entityManager.find(DocumentType.class, 1);

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
		entityManager.persist(customer);

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
		customer = entityManager.find(Customer.class, 2020);

//		Assert
		assertNotNull(customer);
	}

	@Test
	@Order(3)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void debeModificarUnCustomer() {

		// Arrange

		Customer customer = null;
		customer = entityManager.find(Customer.class, 2020);
		
		// Act
		customer.setEnable("N");
		entityManager.merge(customer);

//		Assert
		assertNotNull(customer);

	}

	@Test
	@Order(4)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void debeBorrarUnCustomer() {

		// Arrange

		Customer customer = null;
		customer = entityManager.find(Customer.class, 2020);

		// Act
		entityManager.remove(customer);

//		Assert
		assertNotNull(customer);

	}
}
