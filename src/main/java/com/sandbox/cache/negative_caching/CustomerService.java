package com.sandbox.cache.negative_caching;

import com.sandbox.cache.aux.Customer;
import com.sandbox.cache.aux.CustomerRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Cacheable(
            cacheNames = "customerById",
            key = "#id",
            sync = true
    )
    public Optional<Customer> findById(Long id) {
        return repository.findById(id);
    }
}
