package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.model.Customer;
import com.app.service.CustomerService;

@RestController
@RequestMapping("/api/client")
public class CustomerController {

	
		
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//-----------Test
	
	@GetMapping("/hello")
	public String testHandler() {
		return "Welcome to Spring Security";
	}
	
	
	/*
	
	 {
        "name": "ram",
        "email": "ram@gmail.com",
        "password": "1234",
        "address": "delhi",
        "authorities":[
            {
                "name": "ROLE_USER"
            },
            {
                "name": "ROLE_ADMIN"
            }
        ]
    }
	
	
	
	*/
	
	@PostMapping("/signUp")
	public ResponseEntity<Customer> saveCustomerHandler(@RequestBody Customer customer){

		
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		
		Customer registeredCustomer= customerService.registerCustomer(customer);
		
		return new ResponseEntity<>(registeredCustomer,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getClientByEmail/{email}")
	public ResponseEntity<Customer> getCustomerByEmailHandler(@PathVariable("email") String email){
		
		
		Customer customer= customerService.getCustomerDetailsByEmail(email);
		
		return new ResponseEntity<>(customer,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getAllClients")
	public ResponseEntity<List<Customer>> getAllCustomerHandler(){
		
		
		List<Customer> customers= customerService.getAllCustomerDetails();
		
		return new ResponseEntity<>(customers,HttpStatus.ACCEPTED);
		
	}
	
//	---------------
	@GetMapping("/getClientById/{custId}")
	public ResponseEntity<Customer> getCustomerByIdHandler(@PathVariable("custId") Integer custId){
		Customer cst = customerService.getCustomerbyId(custId);
		return new ResponseEntity<Customer>(cst,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update/{custId}")
	public ResponseEntity<Customer> updateClientDetailsHandler(@RequestBody Customer customer,@PathVariable("custId") Integer custId){
		Customer updatedCust = customerService.updateCustomerDetails(customer, custId);
		return new ResponseEntity<Customer>(updatedCust,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{custId}")
	public ResponseEntity<Customer> deleteCustomerHandler(@PathVariable("custId") Integer custId){
		Customer cust = customerService.deleteCustomerDetails(custId);
		return new ResponseEntity<Customer>(cust,HttpStatus.ACCEPTED);
	}
}
