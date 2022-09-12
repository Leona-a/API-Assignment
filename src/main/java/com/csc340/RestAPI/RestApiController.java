package com.csc340.RestAPI;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author sentini
 */
@RestController
public class RestApiController {

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello, %s!", name);
    }

    /**
     * Get a random joke from official-joke-api.appspot.com
     * and make it available at this endpoint.
     *
     * @return
     */
    @GetMapping("/joke")
    public Object getJoke() {
        String url = "https://official-joke-api.appspot.com/random_joke";
        RestTemplate restTemplate = new RestTemplate();
        Object jSonJoke = restTemplate.getForObject(url, Object.class);

        //Print the whole response to console.
        String joke = restTemplate.getForObject(url, String.class);
        //Parse out the most important info from the response.
        JSONObject jo = new JSONObject(joke);
        System.out.println(jo.toString());
        String jokeSetup = jo.getString("setup");
        String jokePunchline = jo.getString("punchline");
        System.out.println(jokeSetup);
        System.out.println(jokePunchline);

        return jSonJoke;
    }
}
