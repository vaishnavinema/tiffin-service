package com.tiffinservice.tiffin_service.service;

import com.tiffinservice.tiffin_service.exception.CustomerNotFoundException;
import com.tiffinservice.tiffin_service.model.Customer;
import com.tiffinservice.tiffin_service.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer saveCustomer(Customer customer) {
        return repository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer not found"));
    }

    public Customer updateCustomer(Long id, Customer customer) {

        Customer existingCustomer = repository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer not found"));

        existingCustomer.setName(customer.getName());
        existingCustomer.setPhone(customer.getPhone());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setActive(customer.isActive());

        return repository.save(existingCustomer);
    }

    public void deleteCustomer(Long id) {

        Customer existingCustomer = repository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer not found"));

        repository.delete(existingCustomer);
    }

    public Customer updateCustomerStatus(Long id, boolean active) {

        Customer customer = repository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer not found"));

        customer.setActive(active);

        return repository.save(customer);
    }
}