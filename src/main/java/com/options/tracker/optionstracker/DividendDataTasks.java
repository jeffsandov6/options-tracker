package com.options.tracker.optionstracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.options.tracker.optionstracker.models.DividendDataModel;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class DividendDataTasks {

    private String nasdaqDividendDateApiUrl = "https://api.nasdaq.com/api/calendar/dividends?date=";
    private String nasdaqDividendDateApiFormat = "yyyy-MM-dd";

    public void getDividendDataInFile() {
        // lets do june 1 through december 1 of 2020

        LocalDateTime date = getStartingDate(2020, 6, 1);
        String formattedDate = formatDateAsString(date, nasdaqDividendDateApiFormat);
        String endDateString = "2020-12-02";

        while (!endDateString.equals(formattedDate)) {
            System.out.println(formattedDate);

            String dividendDataObjResponse = getDividendDataFromApi(formattedDate);

            date = date.plusDays(1);
            formattedDate = formatDateAsString(date, nasdaqDividendDateApiFormat);
        }

    }

    // TODO: finish this function
    public void putDividendDataInFile(String dividendDataObjResponse) {
        JsonNode rootNode;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            rootNode = objectMapper.readTree(dividendDataObjResponse);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("read value error");
            return;
        }
        JsonNode dividendData = rootNode.get("data").get("calendar").get("rows");
        System.out.println(dividendData);

    }

    // this return the object
    public String getDividendDataFromApi(String formattedDate) {
        String dividendDataRequestUrl = nasdaqDividendDateApiUrl + formattedDate;

        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<String> requestEntity = getRequestEntity(dividendDataRequestUrl);
        ResponseEntity<String> resp = restTemplate.exchange(requestEntity, String.class);

        // System.out.println(resp.getBody());
        return resp.getBody();
    }

    public List<DividendDataModel> getDividendDataFromResponse(String responseBody) {
        JsonNode rootNode;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            rootNode = objectMapper.readTree(responseBody);
        } catch (Exception e) {
            System.out.println("read value error");
            return null;
        }


        String dividendDataRowsAsString = rootNode.get("data").get("calendar").get("rows").toString();
        List<DividendDataModel> dividendDataList = new ArrayList<>();
        try {
            List<Map<String, String>> dividendDataListAsString = (List<Map<String, String>>) objectMapper.readValue(dividendDataRowsAsString, List.class);
            System.out.println(dividendDataListAsString);
            for(int i = 0; i < dividendDataListAsString.size(); i++) {
                dividendDataList.add(
                    objectMapper.convertValue(dividendDataListAsString.get(i), DividendDataModel.class)
                );
            }
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return dividendDataList;

    }


    public RequestEntity<String> getRequestEntity(String apiUrl) {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("accept", "application/json, text/plain, */*");
        // headers.add("accept-encoding", "gzip, deflate, br");
        headers.add("accept-language", "en-US,en;q=0.9");
        headers.add("origin", "https://www.nasdaq.com");
        headers.add("referer", "https://www.nasdaq.com/");
        headers.add("sec-ch-ua", "\"Google Chrome\";v=\"87\", \" Not;A Brand\";v=\"99\", \"Chromium\";v=\"87\"");
        headers.add("sec-ch-ua-mobile", "?0");
        headers.add("sec-fetch-dest", "empty");
        headers.add("sec-fetch-mode", "cors");
        headers.add("sec-fetch-site", "same-site");
        headers.add("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");

        
        try {
            return new RequestEntity(
                headers, 
                HttpMethod.GET,
                new URI(apiUrl));
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            System.out.println("exception");
            return null;
        }
    }

    public LocalDateTime getStartingDate(int year, int month, int day) {
        return LocalDateTime.now()
            .withYear(year)
            .withMonth(month)
            .withDayOfMonth(day);
    }

    public String formatDateAsString(LocalDateTime date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(date);
    }

}