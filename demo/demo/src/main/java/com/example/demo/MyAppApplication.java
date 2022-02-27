package com.example.demo;

import com.example.demo.APIs.Product_intermediate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MyAppApplication {

	public static void main(String[] args) throws IOException {

		Product_intermediate productIntermediate = new Product_intermediate("https://fr.openfoodfacts.org/api/v0/produit/7622210449283.json");
		System.out.println(productIntermediate.getEnergy_100g());
		SpringApplication.run(MyAppApplication.class, args);
	}

}
