package com.options.tracker.optionstracker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.options.tracker.optionstracker.models.StockLockupModel;
import com.options.tracker.optionstracker.models.StockPriceModel;
import com.options.tracker.optionstracker.thirdPartyApi.FinancialModelingPrep;
import com.options.tracker.optionstracker.utils.ExcelUtil;


public class StockLockoutTasks {

    ExcelUtil excelUtil = new ExcelUtil();
    FinancialModelingPrep financialModelingPrep = new FinancialModelingPrep();

    public void getHistoricalPricesInFile() throws IOException {
        List<StockLockupModel> stockLockupList = excelUtil.getStockLockupsFromExcel();

        for(StockLockupModel stockLockupModel : stockLockupList) {
            financialModelingPrep.putHistoricalPricesInFile(
                stockLockupModel.getCompanyTicker(), "historicalPrices");
        }
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
