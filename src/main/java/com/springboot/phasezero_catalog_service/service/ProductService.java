package com.springboot.phasezero_catalog_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.phasezero_catalog_service.dao.ProductDao;
import com.springboot.phasezero_catalog_service.dto.ResponseStructure;
import com.springboot.phasezero_catalog_service.entity.Product;
import com.springboot.phasezero_catalog_service.exception.NegativeValueException;
import com.springboot.phasezero_catalog_service.exception.ObjectIsNotValid;
import com.springboot.phasezero_catalog_service.exception.PartNumberAlreadyExists;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;

	public ResponseEntity<ResponseStructure<Product>> addProducts(Product product) {

		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		if (product.getPartNumber() != null && product.getPartName() != null && product.getCategory() != null
				&& product.getPrice() != null && product.getStock() != null) {
			String partNumber = product.getPartNumber().trim();
			String partName = product.getPartName().trim().toLowerCase();
			String category = product.getCategory().trim();
			if (!productDao.existsByPartNumber(partNumber)) {
				if (!(product.getPrice() < 0) && !(product.getStock() < 0)) {

					responseStructure.setMessage("Product details saved");
					product.setPartName(partName);
					product.setCategory(category);
					product.setPartNumber(partNumber);
					responseStructure.setData(productDao.addProduct(product));

					responseStructure.setStatusCode(HttpStatus.CREATED.value());
					return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.CREATED);
				} else {
					throw new NegativeValueException("The field is negative");
				}
			} else {
				// exp PartNumber already exists
				throw new PartNumberAlreadyExists(product.getPartNumber() + " this number is already exists");
			}
		} else {
			throw new ObjectIsNotValid("Must have to pass all the fields");
		}

	}
	
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProduct() {

	    List<Product> products = productDao.getAllProducts();

	    ResponseStructure<List<Product>> response = new ResponseStructure<>();
	    response.setMessage("All products fetched successfully");
	    response.setData(products);
	    response.setStatusCode(HttpStatus.OK.value());

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Product>>> searchProducts(String name) {

        List<Product> products = productDao.searchByName(name);

        ResponseStructure<List<Product>> response = new ResponseStructure<>();
        response.setMessage("Products matching search text");
        response.setData(products);
        response.setStatusCode(HttpStatus.OK.value());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	public ResponseEntity<ResponseStructure<List<Product>>> filterProducts(String category) {

	    List<Product> products = productDao.filterByCategory(category);

	    ResponseStructure<List<Product>> response = new ResponseStructure<>();
	    response.setMessage("Products filtered by category");
	    response.setData(products);
	    response.setStatusCode(HttpStatus.OK.value());

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}


	public ResponseEntity<ResponseStructure<List<Product>>> sortByPrice() {

	    List<Product> products = productDao.getAllProducts();

	    // Manual sorting (bubble sort style)
	    for (int i = 0; i < products.size(); i++) {
	        for (int j = i + 1; j < products.size(); j++) {
	            if (products.get(i).getPrice() > products.get(j).getPrice()) {
	                Product temp = products.get(i);
	                products.set(i, products.get(j));
	                products.set(j, temp);
	            }
	        }
	    }

	    ResponseStructure<List<Product>> response = new ResponseStructure<>();
	    response.setMessage("Products sorted by price (ascending)");
	    response.setData(products);
	    response.setStatusCode(HttpStatus.OK.value());

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	public ResponseEntity<ResponseStructure<Double>> getInventoryValue() {

	    Double totalValue = productDao.getTotalInventoryValue();

	    ResponseStructure<Double> response = new ResponseStructure<>();
	    response.setMessage("Total inventory value calculated");
	    response.setData(totalValue);
	    response.setStatusCode(HttpStatus.OK.value());

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}




	


}
