package com.options.tracker.optionstracker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.options.tracker.optionstracker.models.StockPriceModel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class StockLockoutTasks {

    public void getHistoricalPriceInFile() throws IOException {
        String historicalPriceUrl = 
        "https://financialmodelingprep.com/api/v3/historical-price-full/" +
        "AMTI" +
        "?timeseries=50000&apikey=6652e62776dadcff1b5f687d3c08c4c2";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.getForEntity(historicalPriceUrl, String.class);

        String createFileLocation = System.getProperty("user.dir") + "/src/resources/historicalPriceAMTI.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(createFileLocation));

        writer.write(resp.getBody());
        writer.close();
        
    }

    public void getHistoricalPriceFromFile() throws JsonParseException, JsonMappingException, IOException {
        String fileLocation = System.getProperty("user.dir") + "/src/resources/historicalPriceZI.txt";
        File file = new File(fileLocation);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(file, Map.class);
        List<StockPriceModel> historicalPricesZI = (List<StockPriceModel>) map.get("historical");

        
    }

    public void getHistoricalPricesAsList() {
        //here we would first get the results from the api call


    }
    
}
