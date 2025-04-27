package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerResponse> findAll() {
        return customerService.findAll().stream()
                .map(customer -> new CustomerResponse(customer.getId().intValue(), customer.getEmail(), customer.getSalary()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CustomerResponse find(@PathVariable Long id) {
        Customer customer = customerService.find(id);
        return new CustomerResponse(customer.getId().intValue(), customer.getEmail(), customer.getSalary());
    }

    @PostMapping
    public CustomerResponse save(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.save(customer);
        return new CustomerResponse(savedCustomer.getId().intValue(), savedCustomer.getEmail(), savedCustomer.getSalary());
    }

    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable Long id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.update(id, customer);
        return new CustomerResponse(updatedCustomer.getId().intValue(), updatedCustomer.getEmail(), updatedCustomer.getSalary());
    }

    @DeleteMapping("/{id}")
    public CustomerResponse delete(@PathVariable Long id) {
        Customer deletedCustomer = customerService.delete(id);
        return new CustomerResponse(deletedCustomer.getId().intValue(), deletedCustomer.getEmail(), deletedCustomer.getSalary());
    }
}