package com.sandbox.cache.aux;

import java.util.Optional;

public class CustomerRepository {
    public Optional<Customer> findById(Long id) {
        return Optional.of(new Customer());
    }
}
