package com.demo.FresherproProject.service;

import java.util.List;

import com.demo.FresherproProject.model.Product;
import com.demo.FresherproProject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> listAll() {
        return repo.findAll();
    }

    public void save(Product product) {
        repo.save(product);
    }

    public Product get(Long code) {
        return repo.findById(code).get();
    }

    public void delete(Long code) {
        repo.deleteById(code);
    }
}