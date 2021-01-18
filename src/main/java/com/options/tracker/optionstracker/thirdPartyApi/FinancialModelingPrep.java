package com.options.tracker.optionstracker.thirdPartyApi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class FinancialModelingPrep {
    private String apiKey = "apikey=6652e62776dadcff1b5f687d3c08c4c2";

    public void putHistoricalPricesInFile(String stockTicker, String folderName) throws IOException {
        String historicalPrices = getFullHistoricalPrices(stockTicker);
        putDataInFile(stockTicker, historicalPrices, folderName, "historicalPrice");
    }

    public String getFullHistoricalPrices(String stockTicker) {
        String historicalPriceUrl = 
        "https://financialmodelingprep.com/api/v3/historical-price-full/" +
        stockTicker +
        "?timeseries=50000&" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.getForEntity(historicalPriceUrl, String.class);

        return resp.getBody();
    }

    public void putHistoricalPricesWithinTimeFrameInFile(String stockTicker, String folderName, String fromDate, String toDate)
            throws IOException {
        String pricesWithinTimeFrame = getPriceWithinTimeFrame(stockTicker, fromDate, toDate);
        putDataInFile(stockTicker, pricesWithinTimeFrame, folderName, "historicalPrice");
    }

    public String getPriceWithinTimeFrame(String stockTicker, String fromDate, String toDate) {
        String priceWithinTimeFrameUrl = 
        "https://financialmodelingprep.com/api/v3/historical-price-full/" + stockTicker +
        "?from=" + fromDate + "&to=" + toDate + "&" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.getForEntity(priceWithinTimeFrameUrl, String.class);

        return resp.getBody();
    }

    public void putHistoricalDividendInFile(String stockTicker, String folderName) throws IOException {
        String historicalDividends = getHistoricalDividends(stockTicker);
        putDataInFile(stockTicker, historicalDividends, folderName, "historicalDividends");
    }

    public String getHistoricalDividends(String stockTicker) {
        String historicalDividendsUrl = 
        "https://financialmodelingprep.com/api/v3/historical-price-full/stock_dividend/" +
        stockTicker + "?" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.getForEntity(historicalDividendsUrl, String.class);

        return resp.getBody();
    }

    public void putDataInFile(String stockTicker, String data, String folderName, String fileType) throws IOException {
        String createFileLocation = System.getProperty("user.dir") + 
        "/src/resources/" + folderName + "/" +
        fileType + stockTicker + ".txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(createFileLocation));

        writer.write(data);
        writer.close();
    }

}
