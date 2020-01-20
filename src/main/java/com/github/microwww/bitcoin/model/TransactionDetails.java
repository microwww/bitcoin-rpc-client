/**
 * Copyright 2020 aTool.org
 */
package com.github.microwww.bitcoin.model;

/**
 * Auto-generated: 2020-01-19 18:33:59
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool9.com/json2javabean.php
 */
public class TransactionDetails {

    private String account;
    private String address;
    private String category;
    private int amount;
    private String label;
    private int vout;
    private double fee;
    private boolean abandoned;

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setVout(int vout) {
        this.vout = vout;
    }

    public int getVout() {
        return vout;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getFee() {
        return fee;
    }

    public void setAbandoned(boolean abandoned) {
        this.abandoned = abandoned;
    }

    public boolean getAbandoned() {
        return abandoned;
    }

}