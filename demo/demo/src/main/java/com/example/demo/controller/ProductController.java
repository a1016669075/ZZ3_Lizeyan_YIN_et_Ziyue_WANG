package com.example.demo.controller;

import com.example.demo.APIs.Product;
import com.example.demo.APIs.Product_intermediate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/product/{num}", method = RequestMethod.GET)
    public String getUser(@PathVariable(value = "num")String num) throws IOException {
        String url =  "https://fr.openfoodfacts.org/api/v0/produit/" + num + ".json";
        Product_intermediate productIntermediate = new Product_intermediate(url);

        Product product = new Product(url);
        int N_sum = 0, P_sum = 0;
        int energy_100g = productIntermediate.getEnergy_100g();
        float saturated_fat_100g = productIntermediate.getSaturated_fat_100g();
        float sugars_100g = productIntermediate.getSugars_100g();
        float salt_100g = productIntermediate.getSalt_100g();
        float fiber_100g = productIntermediate.getFiber_100g();
        float proteins_100g = productIntermediate.getProteins_100g();
        String sql1 = "select points from rule where name = \"energy_100g\" AND min_bound >" + energy_100g;
        String sql2 = "select points from rule where name = \"saturated-fat_100g\" AND min_bound>" + saturated_fat_100g;
        String sql3 = "select points from rule where name = \"sugars_100g\" AND min_bound>" + sugars_100g;
        String sql4 = "select points from rule where name = \"salt_100g\" AND min_bound>" + salt_100g;
        List<Map<String, Object>> list_energy_100 = jdbcTemplate.queryForList(sql1);
        List<Map<String, Object>> list_saturated_fat_100g = jdbcTemplate.queryForList(sql2);
        List<Map<String, Object>> list_sugars_100g = jdbcTemplate.queryForList(sql3);
        List<Map<String, Object>> list_salt_100g = jdbcTemplate.queryForList(sql4);

        N_sum = (int) list_energy_100.get(0).get("points") +
        (int) list_saturated_fat_100g.get(0).get("points") +
        (int) list_sugars_100g.get(0).get("points") +
        (int) list_salt_100g.get(0).get("points") ;

        String sql5 = "select points from rule where name = \"fiber_100g\" AND min_bound>" + fiber_100g;
        String sql6 = "select points from rule where name = \"proteins_100g\" AND min_bound>" + proteins_100g;

        List<Map<String, Object>> list_fiber_100g = jdbcTemplate.queryForList(sql5);
        List<Map<String, Object>> list_proteins_100g = jdbcTemplate.queryForList(sql6);

        P_sum =  (int) list_fiber_100g.get(0).get("points") +
                (int) list_proteins_100g.get(0).get("points") ;

        product.setNutritionScore(N_sum - P_sum);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(product);
        return jsonString;
    }
}
