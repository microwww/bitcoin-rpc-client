/**
 * Copyright 2020 bejson.com
 */
package com.github.microwww.bitcoin.model;

import com.github.microwww.bitcoin.JsonRpcResult;

/**
 * Auto-generated: 2020-01-08 17:4:28
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class NetInfo {
    public static class Result extends JsonRpcResult<NetInfo> {
    }

    private int totalbytesrecv;
    private int totalbytessent;
    private long timemillis;
    private UploadTarget uploadtarget;

    public void setTotalbytesrecv(int totalbytesrecv) {
        this.totalbytesrecv = totalbytesrecv;
    }

    public int getTotalbytesrecv() {
        return totalbytesrecv;
    }

    public void setTotalbytessent(int totalbytessent) {
        this.totalbytessent = totalbytessent;
    }

    public int getTotalbytessent() {
        return totalbytessent;
    }

    public void setTimemillis(long timemillis) {
        this.timemillis = timemillis;
    }

    public long getTimemillis() {
        return timemillis;
    }

    public void setUploadtarget(UploadTarget uploadtarget) {
        this.uploadtarget = uploadtarget;
    }

    public UploadTarget getUploadtarget() {
        return uploadtarget;
    }

}