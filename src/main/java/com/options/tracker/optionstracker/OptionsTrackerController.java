package com.options.tracker.optionstracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.options.tracker.optionstracker.models.Stock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("optionsTracker")
public class OptionsTrackerController {

    @Value("${spring.app.name}")
    String appName;


    public String financialModelPrepUrl = "https://financialmodelingprep.com/api/v3/quote-short/";


    @GetMapping("/getStockPrice")
    public String getStockPrice(@RequestParam String stockName) {

        String urlEnd = stockName + "?apikey=demo";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> resp = restTemplate.getForEntity(financialModelPrepUrl + urlEnd, String.class);

        System.out.println(resp.getBody());

        return resp.getBody();
    }


    /* curl command
        curl -i \
        -H "Accept: application/json" \
        -H "Content-Type:application/json" \
        -X GET --data '{"stockTicker": "AAPL", "stockPrice": "1"}' "localhost:8081/optionsTracker/getStockPriceWithObject/"
    */

    @GetMapping("/getStockPriceWithObject")
    public Stock getStockPriceWithObject(@RequestBody Stock stockObject) {
        RestTemplate restTemplate = new RestTemplate();

        String urlEnd = stockObject.getStockTicker() + "?apikey=demo";

        ResponseEntity<String> resp = restTemplate.getForEntity(financialModelPrepUrl + urlEnd, String.class);


        ObjectMapper objectMapper = new ObjectMapper();

        List<Map<Object, Object>> respListMap = new ArrayList<>();
        try {
            respListMap = objectMapper.readValue(resp.getBody(), List.class);
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        stockObject.setStockPrice(respListMap.get(0).get("price").toString());

        return stockObject;
    } 


}