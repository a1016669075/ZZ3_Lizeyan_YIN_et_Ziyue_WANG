package com.example.demo.service.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class ApplicationConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(customGsonHttpMessageConverter());
        super.configureMessageConverters(converters);
    }

    private GsonHttpMessageConverter customGsonHttpMessageConverter() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();


        GsonHttpMessageConverter gsonMessageConverter = new GsonHttpMessageConverter();
        gsonMessageConverter.setGson(gson);

        return gsonMessageConverter;
    }
}


//将数据从对象转换为json

/*
* @RunWith(SpringRunner.class)
@SpringBootTest
public class GsonSpringBootApplicationTests {

    private Product product;

    @Before
    public void setup(){
        product =new  Product("123","Demo Product",123);
    }

    @Test
    public void simpleGsonTest() throws JSONException {
        String expected = "{\n" +
                "\"code\": \"123\",\n" +
                "\"name\": \"Demo Product\",\n" +
                "\"price\": 123\n" +
                "}";

        Gson gson = new GsonBuilder().create();
        String data= gson.toJson(product);

        JSONAssert.assertEquals(expected,data,false);
    }

    @Test
    public void errorGsonTest() throws JSONException {
        String expected = "{\n" +
                "\"code\": \"1233\",\n" +
                "\"name\": \"Demo Product\",\n" +
                "\"price\": 123\n" +
                "}";

        Gson gson = new GsonBuilder().create();
        String data= gson.toJson(product);

        JSONAssert.assertEquals(expected,data,false);
    }
}
*
*
* */
