package com.options.tracker.optionstracker;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.options.tracker.optionstracker.models.StockLockupModel;
import com.options.tracker.optionstracker.models.StockPriceModel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.stage.Stage;

@SpringBootApplication
// public class OptionsTrackerApplication {

public class OptionsTrackerApplication extends Application {

	@Override
	public void start(Stage stage) throws JsonParseException, JsonMappingException, IOException {
		StockLockoutTasks stockLockoutTasks = new StockLockoutTasks();
		StockLockupModel stockLockupModel = new StockLockupModel(
            "AZEK", "ZoomInfo Technologies", "$41.76", "12/1/20", "44,500,000", "$21.00", "$934,500,000", "6/4/20"
		);
		
		List<StockPriceModel> historicalPriceList = stockLockoutTasks.getHistoricalPriceFromFile(stockLockupModel.getCompanyTicker());
        // List<StockPriceModel> historicalPriceList =//= (List<StockPriceModel>) historicalPriceForCurStockMap.get("historical");

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
                new XYChart.Data(
					curHistoricalPrice.getDate(), 
					Double.valueOf(curHistoricalPrice.getOpen()))
            );
        }

        Scene scene = new Scene(lineChart, 800, 600);
		lineChart.getData().add(series);

        stage.setScene(scene);
        stage.show();
	}

	public static void main(String[] args) {
		// launch(args); uncomment to run graph
		SpringApplication.run(OptionsTrackerApplication.class, args);	
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("lets inspect the beans provided by Spring Boot");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);

			for(String beanName : beanNames) {
				System.out.println(beanName);
			}
		};
	}

}
