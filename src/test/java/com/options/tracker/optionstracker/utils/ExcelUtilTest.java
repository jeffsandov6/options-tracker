package com.options.tracker.optionstracker.utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import com.options.tracker.optionstracker.DividendDataTasks;
import com.options.tracker.optionstracker.StockLockoutTasks;
import com.options.tracker.optionstracker.models.DividendDataModel;
import com.options.tracker.optionstracker.models.StockLockupModel;
import com.options.tracker.optionstracker.thirdPartyApi.FinancialModelingPrep;

import org.junit.jupiter.api.Test;
import javafx.stage.Stage;

public class ExcelUtilTest {

    ExcelUtil excelUtil = new ExcelUtil();
    StockLockoutTasks stockLockoutTasks = new StockLockoutTasks();
    FinancialModelingPrep financialModelingPrep = new FinancialModelingPrep();

    @Test
    public void getStockLockupsFromExcelTest() throws Exception {
        excelUtil.getStockLockupsFromExcel();
    }

    @Test
    public void getHistoricalPriceFromFileTest() throws Exception {
        stockLockoutTasks.getHistoricalPriceFromFile("ZI");
    }

    @Test
    public void getHistoricalPricesInFileTest() throws Exception {
        stockLockoutTasks.getHistoricalPricesInFile();
    }

    @Test
    public void test() throws IOException {
        DividendDataTasks dividendDataTasks = new DividendDataTasks();

        String resp = dividendDataTasks.getDividendDataFromApi("2020-12-02");
        List<DividendDataModel> listOfDividendDataModel = dividendDataTasks.getDividendDataFromResponse(resp);
        
        for(DividendDataModel curDividendDataModel: listOfDividendDataModel) {
            financialModelingPrep.putHistoricalPricesWithinTimeFrameInFile(
                curDividendDataModel.getSymbol(), "historicalPricesSince2019",
                "2019-01-01", "2021-02-01"
            );

            financialModelingPrep.putHistoricalDividendInFile(
                curDividendDataModel.getSymbol(), "historicalDividends");
        }
        
    }
    
}
