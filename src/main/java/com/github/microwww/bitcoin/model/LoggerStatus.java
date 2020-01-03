package com.github.microwww.bitcoin.model;

import com.github.microwww.bitcoin.JsonRpcResult;

public class LoggerStatus {
    public static class Result extends JsonRpcResult<LoggerStatus> {
    }
    private boolean net;
    private boolean tor;
    private boolean mempool;
    private boolean http;
    private boolean bench;
    private boolean zmq;
    private boolean db;
    private boolean rpc;
    private boolean estimatefee;
    private boolean addrman;
    private boolean selectcoins;
    private boolean reindex;
    private boolean cmpctblock;
    private boolean rand;
    private boolean prune;
    private boolean proxy;
    private boolean mempoolrej;
    private boolean libevent;
    private boolean coindb;
    private boolean qt;
    private boolean leveldb;

    public void setNet(boolean net) {
        this.net = net;
    }

    public boolean getNet() {
        return net;
    }

    public void setTor(boolean tor) {
        this.tor = tor;
    }

    public boolean getTor() {
        return tor;
    }

    public void setMempool(boolean mempool) {
        this.mempool = mempool;
    }

    public boolean getMempool() {
        return mempool;
    }

    public void setHttp(boolean http) {
        this.http = http;
    }

    public boolean getHttp() {
        return http;
    }

    public void setBench(boolean bench) {
        this.bench = bench;
    }

    public boolean getBench() {
        return bench;
    }

    public void setZmq(boolean zmq) {
        this.zmq = zmq;
    }

    public boolean getZmq() {
        return zmq;
    }

    public void setDb(boolean db) {
        this.db = db;
    }

    public boolean getDb() {
        return db;
    }

    public void setRpc(boolean rpc) {
        this.rpc = rpc;
    }

    public boolean getRpc() {
        return rpc;
    }

    public void setEstimatefee(boolean estimatefee) {
        this.estimatefee = estimatefee;
    }

    public boolean getEstimatefee() {
        return estimatefee;
    }

    public void setAddrman(boolean addrman) {
        this.addrman = addrman;
    }

    public boolean getAddrman() {
        return addrman;
    }

    public void setSelectcoins(boolean selectcoins) {
        this.selectcoins = selectcoins;
    }

    public boolean getSelectcoins() {
        return selectcoins;
    }

    public void setReindex(boolean reindex) {
        this.reindex = reindex;
    }

    public boolean getReindex() {
        return reindex;
    }

    public void setCmpctblock(boolean cmpctblock) {
        this.cmpctblock = cmpctblock;
    }

    public boolean getCmpctblock() {
        return cmpctblock;
    }

    public void setRand(boolean rand) {
        this.rand = rand;
    }

    public boolean getRand() {
        return rand;
    }

    public void setPrune(boolean prune) {
        this.prune = prune;
    }

    public boolean getPrune() {
        return prune;
    }

    public void setProxy(boolean proxy) {
        this.proxy = proxy;
    }

    public boolean getProxy() {
        return proxy;
    }

    public void setMempoolrej(boolean mempoolrej) {
        this.mempoolrej = mempoolrej;
    }

    public boolean getMempoolrej() {
        return mempoolrej;
    }

    public void setLibevent(boolean libevent) {
        this.libevent = libevent;
    }

    public boolean getLibevent() {
        return libevent;
    }

    public void setCoindb(boolean coindb) {
        this.coindb = coindb;
    }

    public boolean getCoindb() {
        return coindb;
    }

    public void setQt(boolean qt) {
        this.qt = qt;
    }

    public boolean getQt() {
        return qt;
    }

    public void setLeveldb(boolean leveldb) {
        this.leveldb = leveldb;
    }

    public boolean getLeveldb() {
        return leveldb;
    }

}