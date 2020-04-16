package com.demo.FresherproProject.repository;

import com.demo.FresherproProject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query(value = "SELECT c FROM Product c WHERE c.productCode LIKE '%' || :keyword || '%'" + " OR c.name LIKE '%' || :keyword || '%'")
    public List<Product> search(@Param("keyword") String keyword);
}