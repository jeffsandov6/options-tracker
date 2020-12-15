package com.options.tracker.optionstracker.utils;

import com.options.tracker.optionstracker.JavaFXThreadingRule;
import com.options.tracker.optionstracker.JfxTestRunner;
import com.options.tracker.optionstracker.StockLockoutTasks;
import com.options.tracker.optionstracker.models.StockLockupModel;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import javafx.stage.Stage;

@RunWith(JfxTestRunner.class)
public class ExcelUtilTest {

    ExcelUtil excelUtil = new ExcelUtil();
    StockLockoutTasks stockLockoutTasks = new StockLockoutTasks();

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

    @Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
    @Test
    public void createLineGraphTest() throws Exception {
        StockLockupModel stockLockupModel = new StockLockupModel(
            "ZI", "ZoomInfo Technologies", "$41.76", "12/1/20", "44,500,000", "$21.00", "$934,500,000", "6/4/20"
        );

        Stage stage = new Stage();
        stockLockoutTasks.createLineGraph(stockLockupModel, stage);
    }
    
}
