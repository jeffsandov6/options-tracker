package com.options.tracker.optionstracker.models;

public class DividendDataModel {
    private String announcement_Date;
    private String companyName;
    private String dividend_Ex_Date;
    private String dividend_Rate;
    private String indicated_Annual_Dividend;
    private String payment_Date;
    private String record_Date;
    private String symbol;

    public String getAnnouncement_Date() {
        return announcement_Date;
    }

    public void setAnnouncement_Date(String announcement_Date) {
        this.announcement_Date = announcement_Date;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDividend_Ex_Date() {
        return dividend_Ex_Date;
    }

    public void setDividend_Ex_Date(String dividend_Ex_Date) {
        this.dividend_Ex_Date = dividend_Ex_Date;
    }

    public String getDividend_Rate() {
        return dividend_Rate;
    }

    public void setDividend_Rate(String dividend_Rate) {
        this.dividend_Rate = dividend_Rate;
    }

    public String getIndicated_Annual_Dividend() {
        return indicated_Annual_Dividend;
    }

    public void setIndicated_Annual_Dividend(String indicated_Annual_Dividend) {
        this.indicated_Annual_Dividend = indicated_Annual_Dividend;
    }

    public String getPayment_Date() {
        return payment_Date;
    }

    public void setPayment_Date(String payment_Date) {
        this.payment_Date = payment_Date;
    }

    public String getRecord_Date() {
        return record_Date;
    }

    public void setRecord_Date(String record_Date) {
        this.record_Date = record_Date;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "DividendDataModel [announcement_Date=" + announcement_Date + ", companyName=" + companyName
                + ", dividend_Ex_Date=" + dividend_Ex_Date + ", dividend_Rate=" + dividend_Rate
                + ", indicated_Annual_Dividend=" + indicated_Annual_Dividend + ", payment_Date=" + payment_Date
                + ", record_Date=" + record_Date + ", symbol=" + symbol + "]";
    }
}
