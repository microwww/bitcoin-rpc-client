/**
 * Copyright 2020 bejson.com
 */
package com.github.microwww.bitcoin.model;

public class ScriptPubKey {

    private String asm;
    private String hex;
    private int reqSigs;
    private String type;
    private String[] addresses;

    public void setAsm(String asm) {
        this.asm = asm;
    }

    public String getAsm() {
        return asm;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public String getHex() {
        return hex;
    }

    public void setReqSigs(int reqSigs) {
        this.reqSigs = reqSigs;
    }

    public int getReqSigs() {
        return reqSigs;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String[] getAddresses() {
        return addresses;
    }

    public void setAddresses(String[] addresses) {
        this.addresses = addresses;
    }
}