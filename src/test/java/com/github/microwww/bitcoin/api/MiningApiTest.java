package com.github.microwww.bitcoin.api;

import com.github.microwww.bitcoin.HttpException;
import org.junit.Test;

import static org.junit.Assert.*;

public class MiningApiTest {
    private MiningApi api = new MiningApi("btcrpc", "123456789", "http://192.168.1.184:8332/");

    @Test
    public void getBlockTemplate() {
        api.getBlockTemplate();
    }
    @Test
    public void getMiningInfo(){
        api.getMiningInfo();
    }

    @Test
    public void getNetworkHashPS() {
        api.getNetworkHashPS();
    }

    @Test
    public void prioritiseTransaction() {
        //api.prioritiseTransaction();
    }
}