package com.rest.cache;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	Product product = new Product();

	@Cacheable("product")

	public Product getProduct(String id) {

		HashMap<String, String> map = getData(id);
		System.err.println(map);
		product.setId(id);
		product.setMessage(map.get(id));
		product.setIsFromTrue(true);

		RestCacheController.isFromSource = false;
		return product;

	}

	public void updateProduct(Product product) {
		String filePath = "C:\\Users\\priya\\Desktop\\" + product.getId() + ".txt";
		filePath = filePath.replace("\\", "/");
		try {

			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream outputStream = new FileOutputStream(file);
			outputStream.close();
			BufferedWriter wirter = new BufferedWriter(new FileWriter(filePath, true));
			System.err.println(product.getMessage());
			wirter.write(product.getId() + ":" + product.getMessage());
			wirter.newLine();
			wirter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@CacheEvict(allEntries = true, cacheNames = { "product" })
	@Scheduled(fixedDelay = 30000)
	public void cacheEvict() {
	}

	public HashMap<String, String> getData(String id) {
		String filePath = "C:\\Users\\priya\\Desktop\\" + id + ".txt";
		filePath = filePath.replace("\\", "/");
		HashMap<String, String> map = new HashMap<String, String>();

		String line;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(":", 2);
				if (parts.length >= 2) {
					String key = parts[0];
					String value = parts[1];
					map.put(key, value);
				} else {
					System.out.println("ignoring line: " + line);
				}
			}

			for (String key : map.keySet()) {
				System.out.println(key + ":" + map.get(key));
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
}
