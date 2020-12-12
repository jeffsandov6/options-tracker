package com.options.tracker.optionstracker.models;

import java.util.List;

public class StockLockupModel {
    private String companyTicker;
    private String companyName;
    private String currentPrice;
    private String lockupExpiration;
    private String numberOfShares;
    private String initialSharePrice;
    private String offerSize;
    private String ipoDate;

    private List<StockPriceModel> historicalPrices;

    public String getCompanyTicker() {
        return companyTicker;
    }

    public List<StockPriceModel> getHistoricalPrices() {
        return historicalPrices;
    }

    public void setHistoricalPrices(List<StockPriceModel> historicalPrices) {
        this.historicalPrices = historicalPrices;
    }

    public void setCompanyTicker(String companyTicker) {
        this.companyTicker = companyTicker;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getLockupExpiration() {
        return lockupExpiration;
    }

    public void setLockupExpiration(String lockupExpiration) {
        this.lockupExpiration = lockupExpiration;
    }

    public String getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(String numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public String getInitialSharePrice() {
        return initialSharePrice;
    }

    public void setInitialSharePrice(String initialSharePrice) {
        this.initialSharePrice = initialSharePrice;
    }

    public String getOfferSize() {
        return offerSize;
    }

    public void setOfferSize(String offerSize) {
        this.offerSize = offerSize;
    }

    public String getIpoDate() {
        return ipoDate;
    }

    public void setIpoDate(String ipoDate) {
        this.ipoDate = ipoDate;
    }

    @Override
    public String toString() {
        return "StockLockupModel [companyName=" + companyName + ", companyTicker=" + companyTicker + ", currentPrice="
                + currentPrice + ", historicalPrices=" + historicalPrices + ", initialSharePrice=" + initialSharePrice
                + ", ipoDate=" + ipoDate + ", lockupExpiration=" + lockupExpiration + ", numberOfShares="
                + numberOfShares + ", offerSize=" + offerSize + "]";
    }

    public StockLockupModel(String companyTicker, String companyName, String currentPrice, String lockupExpiration,
            String numberOfShares, String initialSharePrice, String offerSize, String ipoDate) {
        this.companyTicker = companyTicker;
        this.companyName = companyName;
        this.currentPrice = currentPrice;
        this.lockupExpiration = lockupExpiration;
        this.numberOfShares = numberOfShares;
        this.initialSharePrice = initialSharePrice;
        this.offerSize = offerSize;
        this.ipoDate = ipoDate;
    }
}
