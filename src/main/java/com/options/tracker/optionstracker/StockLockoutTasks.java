package com.options.tracker.optionstracker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.options.tracker.optionstracker.models.StockLockupModel;
import com.options.tracker.optionstracker.models.StockPriceModel;
import com.options.tracker.optionstracker.utils.ExcelUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class StockLockoutTasks {

    ExcelUtil excelUtil = new ExcelUtil();

    public void getHistoricalPricesInFile() throws IOException {
        List<StockLockupModel> stockLockupList = excelUtil.getStockLockupsFromExcel();

        for(StockLockupModel stockLockupModel : stockLockupList) {
            getHistoricalPriceInFile(stockLockupModel.getCompanyTicker());
        }
    }

    public void getHistoricalPriceInFile(String stockTicker) throws IOException {
        //iex cloud production
        // https://cloud.iexapis.com/stable/stock/zi/chart/5d?token=pk_c3b06cc78ada4c9aa8f5ece6ff75b2f9&symbols=aapl
        //iex sandbox
        //https://sandbox.iexapis.com/stable/time-series/REPORTED_FINANCIALS/AAPL?token=Tsk_6a62ae79b5134c3192eca8ef699d46f4

        String historicalPriceUrl = 
        "https://financialmodelingprep.com/api/v3/historical-price-full/" +
        stockTicker +
        "?timeseries=50000&apikey=6652e62776dadcff1b5f687d3c08c4c2";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.getForEntity(historicalPriceUrl, String.class);

        String createFileLocation = System.getProperty("user.dir") + 
        "/src/resources/historicalPrices/historicalPrice" + stockTicker + ".txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(createFileLocation));

        writer.write(resp.getBody());
        writer.close();
        
    }

    

    public void createLineGraph(StockLockupModel stockLockupModel, Stage stage) throws JsonParseException, JsonMappingException, IOException {
        List<StockPriceModel> historicalPriceList = getHistoricalPriceFromFile(stockLockupModel.getCompanyTicker());
        // List<StockPriceModel> historicalPriceList = (List<StockPriceModel>) historicalPriceForCurStockMap.get("historical");

        // Stage stage = new Stage();
        stage.setTitle("Stage title");
        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Dates");

        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("value");

        final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
        lineChart.setTitle("Chart title");

        XYChart.Series series = new XYChart.Series();
        series.setName("Series name");

        for(StockPriceModel curHistoricalPrice : historicalPriceList) {
            series.getData().add(
                new XYChart.Data(curHistoricalPrice.getDate(), curHistoricalPrice.getOpen())
            );
        }

        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().add(series);

        stage.setScene(scene);
        stage.show();
    }

    public List<StockPriceModel> getHistoricalPriceFromFile(String tickerName) throws JsonParseException, JsonMappingException, IOException {
        String fileLocation = System.getProperty("user.dir") + 
        "/src/resources/historicalPrices/historicalPrice" + tickerName + ".txt";        
        File file = new File(fileLocation);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(file, Map.class);
        List<Map<String, String>> historicalPricesAsMapList = (List<Map<String, String>>) map.get("historical");


        List<StockPriceModel> historicalPriceList = new ArrayList<StockPriceModel>();

        // for(Map<String, String> curHistoricalPrice : historicalPricesAsMapList) {  
        for(int i = historicalPricesAsMapList.size() - 1; i > 0; i--) {
            historicalPriceList.add(
                objectMapper.convertValue(historicalPricesAsMapList.get(i), StockPriceModel.class));
        }


        return historicalPriceList;
    }
    
}
