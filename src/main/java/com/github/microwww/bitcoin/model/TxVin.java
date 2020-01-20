/**
 * Copyright 2020 aTool.org
 */
package com.github.microwww.bitcoin.model;

public class TxVin {

    private String txid;
    private int vout;
    private ScriptSig scriptSig;
    private int sequence;

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public String getTxid() {
        return txid;
    }

    public void setVout(int vout) {
        this.vout = vout;
    }

    public int getVout() {
        return vout;
    }

    public ScriptSig getScriptSig() {
        return scriptSig;
    }

    public void setScriptSig(ScriptSig scriptSig) {
        this.scriptSig = scriptSig;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getSequence() {
        return sequence;
    }

}