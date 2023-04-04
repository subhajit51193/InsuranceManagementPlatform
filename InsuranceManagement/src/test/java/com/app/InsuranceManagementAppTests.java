package com.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.exception.CustomerException;
import com.app.exception.InsurancePolicyException;
import com.app.model.Authority;
import com.app.model.Customer;
import com.app.model.InsurancePolicy;
import com.app.repository.CustomerRepository;
import com.app.repository.InsurancePolicyRepository;
import com.app.service.CustomerService;
import com.app.service.InsurancePolicyService;

import static org.mockito.ArgumentMatchers.anyInt;


@SpringBootTest
@RunWith(SpringRunner.class)
class InsuranceManagementAppTests {

	
	@Autowired
	private CustomerService customerService;
	
	@MockBean
	private CustomerRepository customerRepository;
	
	
	@Autowired
	private InsurancePolicyService insurancePolicyService;
	
	@MockBean
	private InsurancePolicyRepository insurancePolicyRepository;
	
//	@BeforeEach
//	void setup() throws Exception{
//		MockitoAnnotations.openMocks(this);
//	}
	
	@Test
	public void saveCustomerTestWithException() {
	
		Customer customer = new Customer();
		customer.setCustId(10);
		customer.setName("demo");
		customer.setEmail("demo@gamil.com");
		customer.setAddress("demoaddress");
		customer.setContact("9999999999");
		customer.setPassword("demopassword");
		customer.getAuthorities().add(new Authority(12, "ROLE_USER", customer));
		
		when(customerRepository.save(customer)).thenReturn(null);
		assertThrows(CustomerException.class, 
				() ->{
					customerService.registerCustomer(customer);
				});
		
	}
	
	@Test
	public void saveCustomerTestWithoutException() {
	
		Customer customer = new Customer();
		customer.setCustId(10);
		customer.setName("demo");
		customer.setEmail("demo@gamil.com");
		customer.setAddress("demoaddress");
		customer.setContact("9999999999");
		customer.setPassword("demopassword");
		customer.getAuthorities().add(new Authority(12, "ROLE_USER", customer));
		
		when(customerRepository.save(customer)).thenReturn(customer);
		assertEquals(customer, customerService.registerCustomer(customer));
		
	}
	
	@Test
	public void getAllCustomersTestWithException() {
		
		
		when(customerRepository.findAll()).thenReturn(Collections.emptyList());
		assertThrows(CustomerException.class, 
				() ->{
					customerService.getAllCustomerDetails();
				});
	}
	
	@Test
	public void getAllCustomersTestWithoutException() {
		
		Customer customer1 = new Customer();
		customer1.setCustId(10);
		customer1.setName("demo1");
		customer1.setEmail("demo1@gamil.com");
		customer1.setAddress("demo1address");
		customer1.setContact("9999999999");
		customer1.setPassword("demo1password");
		customer1.getAuthorities().add(new Authority(12, "ROLE_USER", customer1));
		
		Customer customer2 = new Customer();
		customer2.setCustId(12);
		customer2.setName("demo2");
		customer2.setEmail("demo2@gamil.com");
		customer2.setAddress("demo2address");
		customer2.setContact("9999999999");
		customer2.setPassword("demo2password");
		customer2.getAuthorities().add(new Authority(12, "ROLE_ADMIN", customer2));
		
		when(customerRepository.findAll()).thenReturn(Stream
				.of(customer1,customer2).collect(Collectors.toList()));
		assertEquals(2, customerService.getAllCustomerDetails().size());
	}
	
	@Test
	public void getCustomerByIdTestWithException() {
		
		when(customerRepository.findById(anyInt())).thenReturn(null);
		assertThrows(CustomerException.class, 
				() ->{
					customerService.getCustomerbyId((int)3);
				});
	}
	
	@Test
	public void getCustomerByIdTestWithoutException() {
		
		Customer customer = new Customer();
		customer.setCustId(10);
		customer.setName("demo");
		customer.setEmail("demo@gamil.com");
		customer.setAddress("demoaddress");
		customer.setContact("9999999999");
		customer.setPassword("demopassword");
		customer.getAuthorities().add(new Authority(12, "ROLE_USER", customer));
		 
		
		when(customerRepository.findById(anyInt())).thenReturn(Optional.of(customer));
		assertEquals(Optional.of(customer).get(), customerService.getCustomerbyId(anyInt()));
	}
	@Test
	public void saveInsurancePolicyTestWithException() {
		
		InsurancePolicy ip = new InsurancePolicy();
		ip.setPolicyId(12);
		ip.setPolicyNo(123);
		ip.setPolicyType("Life");
		ip.setCoverageAmount(6000.00);
		ip.setPremiumAmount(600.00);
		ip.setStartDate(LocalDate.parse("2023-01-01"));
		ip.setEndDate(LocalDate.parse("2053-01-01"));
		
		when(insurancePolicyRepository.save(ip)).thenReturn(null);
		assertThrows(InsurancePolicyException.class, 
				() ->{
					insurancePolicyService.createInsurance(ip);
				});
		
		
		
	}
	@Test
	public void saveInsuranceTestWithoutException() throws InsurancePolicyException {
		
		InsurancePolicy ip = new InsurancePolicy();
		ip.setPolicyId(12);
		ip.setPolicyNo(123);
		ip.setPolicyType("Life");
		ip.setCoverageAmount(6000.00);
		ip.setPremiumAmount(600.00);
		ip.setStartDate(LocalDate.parse("2023-01-01"));
		ip.setEndDate(LocalDate.parse("2053-01-01"));
		
		when(insurancePolicyRepository.save(ip)).thenReturn(ip);
		assertEquals(ip, insurancePolicyService.createInsurance(ip));
	}
	
	@Test
	public void getAllInsuranceWithException() {
		
		when(insurancePolicyRepository.findAll()).thenReturn(Collections.emptyList());
		assertThrows(InsurancePolicyException.class, 
				() ->{
					insurancePolicyService.getAllInsurances();
				});
	}
	
	public void getAllInsuranceWithoutException() throws InsurancePolicyException {
		
		InsurancePolicy ip1 = new InsurancePolicy();
		ip1.setPolicyId(12);
		ip1.setPolicyNo(123);
		ip1.setPolicyType("Life");
		ip1.setCoverageAmount(6000.00);
		ip1.setPremiumAmount(600.00);
		ip1.setStartDate(LocalDate.parse("2023-01-01"));
		ip1.setEndDate(LocalDate.parse("2053-01-01"));
		
		InsurancePolicy ip2 = new InsurancePolicy();
		ip2.setPolicyId(13);
		ip2.setPolicyNo(124);
		ip2.setPolicyType("Term");
		ip2.setCoverageAmount(6000.00);
		ip2.setPremiumAmount(600.00);
		ip2.setStartDate(LocalDate.parse("2023-01-01"));
		ip2.setEndDate(LocalDate.parse("2053-01-01"));
		
		when(insurancePolicyRepository.findAll()).thenReturn(Stream
				.of(ip1,ip2).collect(Collectors.toList()));
		assertEquals(2, insurancePolicyService.getAllInsurances().size());
	}
	
	@Test
	public void getInsuranceByIdWithException() {
		
		when(insurancePolicyRepository.findById(anyInt())).thenReturn(null);
		assertThrows(InsurancePolicyException.class, 
				() ->{
					insurancePolicyService.getInsuranceById((int)3);
				});
	}
	
	public void getInsuranceByIdWithoutException() throws InsurancePolicyException {
		
		InsurancePolicy ip = new InsurancePolicy();
		ip.setPolicyId(12);
		ip.setPolicyNo(123);
		ip.setPolicyType("Life");
		ip.setCoverageAmount(6000.00);
		ip.setPremiumAmount(600.00);
		ip.setStartDate(LocalDate.parse("2023-01-01"));
		ip.setEndDate(LocalDate.parse("2053-01-01"));
		
		when(insurancePolicyRepository.findById(anyInt())).thenReturn(Optional.of(ip));
		assertEquals(Optional.of(ip).get(), insurancePolicyService.getInsuranceById(anyInt()));
	}
	

}
