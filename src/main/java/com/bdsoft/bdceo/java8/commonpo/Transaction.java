package com.bdsoft.bdceo.java8.commonpo;

/**
 * 功能
 *
 * @author 丁辰叶
 * @version 1.0
 * @date 2018/5/8 9:39
 */
public class Transaction {

    private Trader trader;
    private int year;
    private int value;

    private Currency currency;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
