package com.example.demo.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.ecommerce.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
