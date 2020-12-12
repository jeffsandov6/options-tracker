package com.options.tracker.optionstracker.models;

public class StockPriceModel {
    private String date;
    private String open;
    private String high;
    private String low;
    private String close;
    private String adjClose;
    private String volume;
    private String unadjustedVolume;
    private String change;
    private String changePercent;
    private String vwap; //volume weighted average prrice
    private String label;
    private String changeOverTime;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(String adjClose) {
        this.adjClose = adjClose;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getUnadjustedVolume() {
        return unadjustedVolume;
    }

    public void setUnadjustedVolume(String unadjustedVolume) {
        this.unadjustedVolume = unadjustedVolume;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(String changePercent) {
        this.changePercent = changePercent;
    }

    public String getVwap() {
        return vwap;
    }

    public void setVwap(String vwap) {
        this.vwap = vwap;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getChangeOverTime() {
        return changeOverTime;
    }

    public void setChangeOverTime(String changeOverTime) {
        this.changeOverTime = changeOverTime;
    }

    @Override
    public String toString() {
        return "StockPriceModel [adjClose=" + adjClose + ", change=" + change + ", changeOverTime=" + changeOverTime
                + ", changePercent=" + changePercent + ", close=" + close + ", date=" + date + ", high=" + high
                + ", label=" + label + ", low=" + low + ", open=" + open + ", unadjustedVolume=" + unadjustedVolume
                + ", volume=" + volume + ", vwap=" + vwap + "]";
    }
}
