package com.options.tracker.optionstracker.utils;

import com.options.tracker.optionstracker.StockLockoutTasks;

import org.junit.jupiter.api.Test;

public class ExcelUtilTest {

    ExcelUtil excelUtil = new ExcelUtil();
    StockLockoutTasks stockLockoutTasks = new StockLockoutTasks();

    @Test
    public void getStockLockupsFromExcelTest() throws Exception {
        excelUtil.getStockLockupsFromExcel();
    }

    @Test
    public void getHistoricalPriceFromFileTest() throws Exception {
        stockLockoutTasks.getHistoricalPriceFromFile();
    }

    @Test
    public void getHistoricalPriceInFileTest() throws Exception {
        stockLockoutTasks.getHistoricalPriceInFile();
    }
    
}
