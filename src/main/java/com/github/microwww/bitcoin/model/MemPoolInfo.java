/**
 * Copyright 2019 bejson.com
 */
package com.github.microwww.bitcoin.model;

import com.github.microwww.bitcoin.JsonRpcResult;

public class MemPoolInfo {

    public static class Result extends JsonRpcResult<MemPoolInfo> {
    }

    private int size;
    private long bytes;
    private long usage;
    private long maxmempool;
    private double mempoolminfee;
    private double minrelaytxfee;

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setBytes(long bytes) {
        this.bytes = bytes;
    }

    public long getBytes() {
        return bytes;
    }

    public void setUsage(long usage) {
        this.usage = usage;
    }

    public long getUsage() {
        return usage;
    }

    public void setMaxmempool(long maxmempool) {
        this.maxmempool = maxmempool;
    }

    public long getMaxmempool() {
        return maxmempool;
    }

    public void setMempoolminfee(double mempoolminfee) {
        this.mempoolminfee = mempoolminfee;
    }

    public double getMempoolminfee() {
        return mempoolminfee;
    }

    public void setMinrelaytxfee(double minrelaytxfee) {
        this.minrelaytxfee = minrelaytxfee;
    }

    public double getMinrelaytxfee() {
        return minrelaytxfee;
    }
}