package com.example.demo.controller;

import com.example.demo.APIs.Product;
import com.example.demo.entity.Rule;
import com.example.demo.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rule")
public class RuleHandler {
    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseBody
    @GetMapping("/test")
    public int map() throws IOException {
//        int a = 1962;
//        String sql = "select * from rule where name = \"energy_100g\" AND min_bound > 1962";
//        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
//        return (int) (list.get(0).get("points")) - 1;


        Product product = new Product("https://fr.openfoodfacts.org/api/v0/produit/7622210449283.json");

        return product.getNutritionScore();
    }

    @GetMapping("/findAll")
    public List<Rule> findAll(){
        return ruleRepository.findAll();
    }

}
