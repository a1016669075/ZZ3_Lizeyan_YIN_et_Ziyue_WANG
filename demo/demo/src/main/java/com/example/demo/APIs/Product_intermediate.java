package com.example.demo.APIs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class Product_intermediate {
    private String code;
    private int energy_100g;
    private float saturated_fat_100g;
    private float sugars_100g;
    private float salt_100g;
    private float fiber_100g;
    private float proteins_100g;
    private String name;

    public Product_intermediate() {
    }

    public Product_intermediate(String url) throws IOException {
        JsonObject job = getJsonObject(url);
        code = String.valueOf(job.get("code"));
        name = String.valueOf(job.getAsJsonObject("product").getAsJsonObject().get("generic_name"));
        JsonElement entry = job.getAsJsonObject("product").getAsJsonObject("nutriments");
        JsonObject object = entry.getAsJsonObject();
        energy_100g = Integer.parseInt(object.get("energy_100g").toString());
        saturated_fat_100g = Float.parseFloat(object.get("saturated-fat_100g").toString());
        sugars_100g = Integer.parseInt(object.get("sugars_100g").toString());
        salt_100g = Float.parseFloat(object.get("salt_100g").toString());
        fiber_100g = Integer.parseInt(object.get("fiber_100g").toString());
        proteins_100g = Float.parseFloat(object.get("proteins_100g").toString());
    }

    private JsonObject getJsonObject(String string_url) throws IOException {
        URL url = new URL(string_url);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                //.header(/****/)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();
        Gson gson = new GsonBuilder().create();

        JsonObject job = gson.fromJson(response.body().string(), JsonObject.class);
        return job;
    }


//
    public String getCode() {
        return code;
    }

    public int getEnergy_100g() {
        return energy_100g;
    }

    public float getFiber_100g() {
        return fiber_100g;
    }

    public float getProteins_100g() {
        return proteins_100g;
    }

    public float getSugars_100g() {
        return sugars_100g;
    }

    public float getSalt_100g() {
        return salt_100g;
    }

    public float getSaturated_fat_100g() {
        return saturated_fat_100g;
    }

    public String getName() {
        return name;
    }

    public void setEnergy_100g(int energy_100g) {

        this.energy_100g = energy_100g;
    }

        public void setCode(String code) {
        this.code = code;
    }
//
    public void setFiber_100g(float fiber_100g) {
        this.fiber_100g = fiber_100g;
    }

    public void setProteins_100g(float proteins_100g) {
        this.proteins_100g = proteins_100g;
    }

    public void setSalt_100g(int salt_100g) {
        this.salt_100g = salt_100g;
    }

    public void setSaturated_fat_100g(int saturated_fat_100g) {
        this.saturated_fat_100g = saturated_fat_100g;
    }

    public void setSugars_100g(int sugars_100g) {
        this.sugars_100g = sugars_100g;
    }

//    public Product(String code, int energy_100g, int saturated_fat_100g, float sugars_100g, int salt_100g, float fiber_100g, float proteins_100g, String[] nutriments) {
//        this.code = code;
//        this.energy_100g = energy_100g;
//        this.saturated_fat_100g = saturated_fat_100g;
//        this.sugars_100g = sugars_100g;
//        this.salt_100g = salt_100g;
//        this.fiber_100g = fiber_100g;
//        this.proteins_100g = proteins_100g;
//        this.nutriments = nutriments;
//    }
}

