package com.example.demo;

import com.example.demo.APIs.Product_intermediate;
import com.example.demo.service.OkHttpGet;
import com.google.gson.Gson;

import java.io.IOException;

public class GetInfos {

    public void getinfos() throws IOException {
        OkHttpGet okHttpGet = new OkHttpGet();
        for(int i = 0; i< 10; i++) {
            String url =  "https://fr.openfoodfacts.org/api/v0/produit/" + String.valueOf(i) + ".json";
            String response = okHttpGet.run(url);
            Product_intermediate productIntermediate = new Product_intermediate();
            Gson gson = new Gson();
            productIntermediate = gson.fromJson(response, Product_intermediate.class);
            System.out.println(productIntermediate.getEnergy_100g());
            System.out.println(response);
        }
    }
}
