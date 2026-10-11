package com.sandbox.cache.aux;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.ArrayList;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
}
