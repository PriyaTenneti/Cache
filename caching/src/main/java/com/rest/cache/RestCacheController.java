package com.rest.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestCacheController {
	static Boolean isFromSource = false;
	@Autowired
	private ProductService productService;

	@GetMapping("getcache/{id}")
	public String getProduct(@PathVariable("id") String id) {
		Product product = new Product();
		product = productService.getProduct(id);
		if (!isFromSource && product.getIsFromTrue()) {
			isFromSource = true;
			return "Resource not found";
		} else {
			return "{" + product.getId() + ":" + product.getMessage() + "}";

		}
	}

	@PostMapping("insertcache")
	public Product updateProduct(@RequestBody Product product) {
		product.setId(product.getId());
		productService.updateProduct(product);
		return null;
	}

	@DeleteMapping("deletecache")
	public String deleteCache() {
		productService.cacheEvict();
		return (" Cache got cleared");
	}

}
