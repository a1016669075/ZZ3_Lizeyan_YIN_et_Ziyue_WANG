package com.example.demo.APIs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Product {
    private int id;
    private String barCode;
    private String name;
    private int nutritionScore;
    private String classe;
    private String color;

    JdbcTemplate jdbcTemplate;

    public Product(){}

    public Product(String url) throws IOException {
        this.id = 0;
        Product_intermediate productIntermediate = new Product_intermediate(url);
        this.barCode = productIntermediate.getCode();
        this.name = productIntermediate.getName();

        //nutritionScore = calculScore(productIntermediate);
    }

    public int map() throws IOException {
        int a = 1962;
        String sql = "select * from rule where name = \"energy_100g\" AND min_bound > 1962";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return (int) (list.get(0).get("points")) - 1;

//        Product product = new Product(JdbcTemplate jdbcTemplate, "https://fr.openfoodfacts.org/api/v0/produit/7622210449283.json");
//        return product.getNutritionScore();
    }


/*
    c'est pour le calcul de nutritionScore,
    j'ai récupéré les données de la base de données, et choisir les données
    utile pour faire la comparaison
 */
    private int calculScore(Product_intermediate productIntermediate)
    {
        int N_sum = 0, P_sum = 0;
        int energy_100g = productIntermediate.getEnergy_100g();
        float saturated_fat_100g = productIntermediate.getSaturated_fat_100g();
        float sugars_100g = productIntermediate.getSugars_100g();
        float salt_100g = productIntermediate.getSalt_100g();
        float fiber_100g = productIntermediate.getFiber_100g();
        float proteins_100g = productIntermediate.getProteins_100g();
        String sql1 = "select points from rule where name = \"energy_100g\" AND min_bound >" + energy_100g;
        String sql2 = "select points from rule where name = \"saturated-fat_100g\" AND min_bound<" + saturated_fat_100g;
        String sql3 = "select points from rule where name = \"sugars_100g\" AND min_bound<" + sugars_100g;
        String sql4 = "select points from rule where name = \"salt_100g\" AND min_bound<" + salt_100g;
        List<Map<String, Object>> list_energy_100 = jdbcTemplate.queryForList(sql1);
        List<Map<String, Object>> list_saturated_fat_100g = jdbcTemplate.queryForList(sql2);
        List<Map<String, Object>> list_sugars_100g = jdbcTemplate.queryForList(sql3);
        List<Map<String, Object>> list_salt_100g = jdbcTemplate.queryForList(sql4);

        N_sum = (int) list_energy_100.get(0).get("points") +
                (int) list_saturated_fat_100g.get(-1).get("points") +
                (int) list_sugars_100g.get(-1).get("points") +
                (int) list_salt_100g.get(-1).get("points") ;

        String sql5 = "select points from rule where name = \"fiber_100g\" AND min_bound<" + fiber_100g;
        String sql6 = "select points from rule where name = \"proteins_100g\" AND min_bound<" + proteins_100g;

        List<Map<String, Object>> list_fiber_100g = jdbcTemplate.queryForList(sql5);
        List<Map<String, Object>> list_proteins_100g = jdbcTemplate.queryForList(sql6);

        P_sum =  (int) list_fiber_100g.get(-1).get("points") +
                (int) list_proteins_100g.get(-1).get("points") ;

        return N_sum - P_sum;
    }

    public String getName() {
        return name;
    }

    public int getNutritionScore() {
        return nutritionScore;
    }

    public void setNutritionScore(int nutritionScore) {
        this.nutritionScore = nutritionScore;
        if(nutritionScore < 0)
        {
            classe = "trop bon";
            color = "green";
        }
        else if(nutritionScore < 2 && nutritionScore > 0)
        {
            classe = "light green";
            color = "Bon";
        }
        else if (nutritionScore < 10 && nutritionScore >3)
        {
            classe = "Mangeable";
            color = "yellow";
        }
        else if (nutritionScore < 18 && nutritionScore > 11)
        {
            classe = "Mouai";
            color = "orange";
        }
        else {
            classe = "Degueu";
            color = "red";
        }
    }

    public String getClasse() {
        return classe;
    }

    public String getColor() {
        return color;
    }

    public String getBarCode() {
        return barCode;
    }

    public int getId() {
        return id;
    }
}
