package com.demo.FresherproProject.repository;

import com.demo.FresherproProject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}