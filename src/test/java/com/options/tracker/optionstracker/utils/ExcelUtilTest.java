package com.options.tracker.optionstracker.utils;

import org.junit.jupiter.api.Test;

public class ExcelUtilTest {

    ExcelUtil excelUtil = new ExcelUtil();

    @Test
    public void getStockLockupsFromExcelTest() throws Exception {
        excelUtil.getStockLockupsFromExcel();
    }
    
}
