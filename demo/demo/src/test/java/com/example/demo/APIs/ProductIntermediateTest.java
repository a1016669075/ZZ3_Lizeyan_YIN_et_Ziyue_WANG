package com.example.demo.APIs;

import com.example.demo.service.OkHttpGet;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ProductIntermediateTest {

    @Test
    public void whenUsingAnnotations_thenOk() throws IOException {
        OkHttpGet okHttpGet = new OkHttpGet();
        String response = okHttpGet.run("https://fr.openfoodfacts.org/api/v0/produit/7622210449283.json");
        Product_intermediate productIntermediate = new ObjectMapper()
                .readerFor(Product_intermediate.class)
                .readValue(response);

        assertEquals(productIntermediate.getEnergy_100g(), 1962);
    }

}