package com.springboot.phasezero_catalog_service.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.phasezero_catalog_service.entity.Product;
import com.springboot.phasezero_catalog_service.repository.ProductRepository;
@Repository
public class ProductDao {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}
	
	public Boolean existsByPartNumber(String partNumber) {
		return productRepository.existsByPartNumber(partNumber);
	}
	
	public Boolean existsByPartName(String partName) {
		return productRepository.existsByPartName(partName);
	}
	
	 public List<Product> getAllProducts() {
	        return productRepository.findAll();
	    }
	 public List<Product> searchByName(String name) {
	        return productRepository.findByPartNameContainingIgnoreCase(name);
	    }
	 public List<Product> filterByCategory(String category) {
		    return productRepository.findByCategoryIgnoreCase(category);
		}
	 public Double getTotalInventoryValue() {
		    List<Product> products = productRepository.findAll();

		    double total = 0;

		    for (Product p : products) {
		        total += p.getPrice() * p.getStock();
		    }

		    return total;
		}





}
