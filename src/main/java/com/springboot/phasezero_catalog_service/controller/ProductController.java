package com.springboot.phasezero_catalog_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.phasezero_catalog_service.dto.ResponseStructure;
import com.springboot.phasezero_catalog_service.entity.Product;
import com.springboot.phasezero_catalog_service.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Product>> addProduct(@RequestBody Product product){
		return productService.addProducts(product);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProduct(){
		return productService.getAllProduct();
	}
	@GetMapping("/search/name/{name}")
    public ResponseEntity<ResponseStructure<List<Product>>> search(@PathVariable String name) {
        return productService.searchProducts(name);
    }
	
	@GetMapping("/filter/{category}")
	public ResponseEntity<ResponseStructure<List<Product>>> filterByCategory(@PathVariable String category) {
	    return productService.filterProducts(category);
	}
	
	@GetMapping("/sort/price")
	public ResponseEntity<ResponseStructure<List<Product>>> sortByPrice() {
	    return productService.sortByPrice();
	}

	@GetMapping("/inventory/value")
	public ResponseEntity<ResponseStructure<Double>> getInventoryValue() {
	    return productService.getInventoryValue();
	}



}
