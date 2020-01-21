package com.github.microwww.bitcoin.model;

public class TransactionInput {
    private String txid;
    private int vout;
    //(numeric, optional, default=depends on the value of the 'replaceable' and 'locktime' arguments) The sequence number
    private Integer sequence;

    public TransactionInput() {
    }

    public TransactionInput(String txid, int vout) {
        this.txid = txid;
        this.vout = vout;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public int getVout() {
        return vout;
    }

    public void setVout(int vout) {
        this.vout = vout;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
