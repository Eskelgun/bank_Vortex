package com.bank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.domain.Customer;
import com.bank.dto.CustomerDTO;
import com.bank.mapper.CustomerMapper;
import com.bank.service.CustomerService;

@RestController
@RequestMapping("api/v1/customers")
@CrossOrigin("*")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@Autowired
	CustomerMapper customerMapper;

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() throws Exception {
		List<Customer> listCustomer = customerService.findAll();
		List<CustomerDTO> lisCustomerDTO = customerMapper.toCustomerDTOs(listCustomer);

		return ResponseEntity.ok().body(lisCustomerDTO);

	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) throws Exception {
		Customer customer = customerService.findById(id).get();
		CustomerDTO customerDTO = null;

		if (customer == null) {
			return ResponseEntity.ok().body(null);
		}

		customerDTO = customerMapper.toCustomerDTO(customer);
		return ResponseEntity.ok().body(customerDTO);

	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody @Valid CustomerDTO customerDTO) throws Exception {

		Customer customer = customerMapper.toCustomer(customerDTO);

		customerService.save(customer);
		customerDTO = customerMapper.toCustomerDTO(customer);


		return ResponseEntity.ok().body(customer);

	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody @Valid CustomerDTO customerDTO) throws Exception {

		Customer customer = customerMapper.toCustomer(customerDTO);

		customerService.update(customer);
		customerDTO = customerMapper.toCustomerDTO(customer);

		return ResponseEntity.ok().body(customer);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) throws Exception {

		customerService.deleteById(id);

		return ResponseEntity.ok().body(id);

	}

}
