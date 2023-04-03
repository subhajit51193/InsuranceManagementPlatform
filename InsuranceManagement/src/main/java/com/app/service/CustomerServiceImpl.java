package com.app.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.app.exception.CustomerException;
import com.app.model.Authority;
import com.app.model.Customer;
import com.app.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	
	
	
	@Override
	public Customer registerCustomer(Customer customer) throws CustomerException {
		
		
			List<Authority> authorities= customer.getAuthorities();
			
			for(Authority authority:authorities) {
				authority.setCustomer(customer);
			}
			
//			return customerRepository.save(customer);
			Customer cst = customerRepository.save(customer);
			if (cst != null) {
				return cst;
			}
			else {
				throw new CustomerException();
			}
		
	}

	@Override
	public Customer getCustomerDetailsByEmail(String email)throws CustomerException {
		
		return customerRepository.findByEmail(email).orElseThrow(() -> new CustomerException("Customer Not found with Email: "+email));
	}

	@Override
	public List<Customer> getAllCustomerDetails()throws CustomerException {
		
		List<Customer> customers= customerRepository.findAll();
		
		if(customers.isEmpty())
			throw new CustomerException("No Customer find");
		
		return customers;
		
	}

	@Override
	public Customer getMyDetails() throws CustomerException {
		
		Optional<Customer> opt = customerRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println(opt.get());
		if (opt.isEmpty()) {
			throw new CustomerException("Not found");
		}
		else {
			return opt.get();
		}
	}

	@Override
	public Customer getCustomerbyId(Integer custId) throws CustomerException {
		
		if (custId == null) {
			throw new CustomerException("No client found");
		}
		else {
			Optional<Customer> opt = customerRepository.findById(custId);
			if (opt == null) {
				throw new CustomerException("No client found");
			}
			else {
				if (opt.isEmpty()) {
					throw new CustomerException("No client found");
				}
				else {
					return opt.get();
					
				}
			}
			

		}
		
	}

	@Override
	public Customer updateCustomerDetails(Customer customer, Integer custId) throws CustomerException {
		
		Optional<Customer> opt = customerRepository.findById(custId);
		if (opt.isEmpty()) {
			throw new CustomerException("not found");
		}
		else {
			Customer updated = opt.get();
			updated.setAddress(customer.getAddress());
			updated.setContact(customer.getContact());
			updated.setEmail(customer.getEmail());
			updated.setName(customer.getName());
			updated.setPassword(customer.getPassword());
			return customerRepository.save(updated);
		}
	}

	@Override
	public Customer deleteCustomerDetails(Integer custId) throws CustomerException {
		
		Optional<Customer> opt = customerRepository.findById(custId);
		if (opt.isEmpty()) {
			throw new CustomerException("Not found");
		}
		else {
			Customer cust = opt.get();
			customerRepository.delete(cust);
			return cust;
		}
	}





}
