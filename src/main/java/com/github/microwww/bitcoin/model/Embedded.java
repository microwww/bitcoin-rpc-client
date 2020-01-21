/**
 * Copyright 2020 aTool.org
 */
package com.github.microwww.bitcoin.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Auto-generated: 2020-01-20 14:56:55
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool9.com/json2javabean.php
 */
public class Embedded {


    @JsonProperty("isscript")
    private boolean script;
    @JsonProperty("iswitness")
    private boolean witness;
    @JsonProperty("witness_version")
    private int witnessVersion;
    @JsonProperty("witness_program")
    private String witnessProgram;
    private String pubkey;
    private String address;
    private String scriptPubKey;

    public boolean isScript() {
        return script;
    }

    public void setScript(boolean script) {
        this.script = script;
    }

    public boolean isWitness() {
        return witness;
    }

    public void setWitness(boolean witness) {
        this.witness = witness;
    }

    public int getWitnessVersion() {
        return witnessVersion;
    }

    public void setWitnessVersion(int witnessVersion) {
        this.witnessVersion = witnessVersion;
    }

    public String getWitnessProgram() {
        return witnessProgram;
    }

    public void setWitnessProgram(String witnessProgram) {
        this.witnessProgram = witnessProgram;
    }

    public String getPubkey() {
        return pubkey;
    }

    public void setPubkey(String pubkey) {
        this.pubkey = pubkey;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getScriptPubKey() {
        return scriptPubKey;
    }

    public void setScriptPubKey(String scriptPubKey) {
        this.scriptPubKey = scriptPubKey;
    }
}