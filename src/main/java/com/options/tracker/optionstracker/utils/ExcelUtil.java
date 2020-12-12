package com.options.tracker.optionstracker.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.options.tracker.optionstracker.models.StockLockupModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    public List<StockLockupModel> getStockLockupsFromExcel() throws IOException {
        String fileLocation = System.getProperty("user.dir") + "/src/resources/RecentIPOLockupExpirations.xlsx";
        FileInputStream file = new FileInputStream(fileLocation);

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        
        List<StockLockupModel> stockLists = new ArrayList<>();
        for(Row row : sheet) {
            if(row.getRowNum() == 0) {
                continue;
            }

            StockLockupModel stock = new StockLockupModel(
                row.getCell(0).toString(),
                row.getCell(1).toString(),
                row.getCell(2).toString(),
                row.getCell(3).toString(),
                row.getCell(4).toString(),
                row.getCell(5).toString(),
                row.getCell(6).toString(),
                row.getCell(7).toString()
            );

            stockLists.add(stock);
        }
        workbook.close();

        return stockLists;
    }
    
}
