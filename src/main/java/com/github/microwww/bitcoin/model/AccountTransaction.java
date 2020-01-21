/**
 * Copyright 2020 aTool.org
 */
package com.github.microwww.bitcoin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.microwww.bitcoin.JsonRpcResult;
import com.github.microwww.bitcoin.annotation.Version;

public class AccountTransaction {
    public static class Result extends JsonRpcResult<AccountTransaction[]> {
    }

    private String account;
    @Version(since = "0.17.0")
    @JsonIgnore
    private String label;
    private String address;
    private String category;
    private double amount;
    private int vout;
    private String comment;
    private String to; // comment_to

    // before transaction confirmations, (confirmations = 0)
    private Boolean trusted;

    private int confirmations;
    // base-coin transaction
    private boolean generated;

    private String blockhash;
    private int blockindex;
    private int blocktime;

    private String txid;
    private String[] walletconflicts;
    private int time;
    private int timereceived;
    @JsonProperty("bip125-replaceable")
    private String bip125Replaceable;

    private Double fee;
    private Boolean abandoned;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getVout() {
        return vout;
    }

    public void setVout(int vout) {
        this.vout = vout;
    }

    public int getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(int confirmations) {
        this.confirmations = confirmations;
    }

    public boolean isGenerated() {
        return generated;
    }

    public void setGenerated(boolean generated) {
        this.generated = generated;
    }

    public String getBlockhash() {
        return blockhash;
    }

    public void setBlockhash(String blockhash) {
        this.blockhash = blockhash;
    }

    public int getBlockindex() {
        return blockindex;
    }

    public void setBlockindex(int blockindex) {
        this.blockindex = blockindex;
    }

    public int getBlocktime() {
        return blocktime;
    }

    public void setBlocktime(int blocktime) {
        this.blocktime = blocktime;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public String[] getWalletconflicts() {
        return walletconflicts;
    }

    public void setWalletconflicts(String[] walletconflicts) {
        this.walletconflicts = walletconflicts;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTimereceived() {
        return timereceived;
    }

    public void setTimereceived(int timereceived) {
        this.timereceived = timereceived;
    }

    public String getBip125Replaceable() {
        return bip125Replaceable;
    }

    public void setBip125Replaceable(String bip125Replaceable) {
        this.bip125Replaceable = bip125Replaceable;
    }

    public TransactionInput toTransactionInput() {
        return new TransactionInput(this.getTxid(), this.vout);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Boolean getAbandoned() {
        return abandoned;
    }

    public void setAbandoned(Boolean abandoned) {
        this.abandoned = abandoned;
    }

    public Boolean getTrusted() {
        return trusted;
    }

    public void setTrusted(Boolean trusted) {
        this.trusted = trusted;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}