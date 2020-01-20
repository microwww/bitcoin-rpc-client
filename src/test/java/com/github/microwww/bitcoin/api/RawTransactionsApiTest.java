package com.github.microwww.bitcoin.api;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RawTransactionsApiTest {

    private RawTransactionsApi api = new RawTransactionsApi("btcrpc", "123456789", "http://192.168.1.31:8332/");

    @Test
    public void disconnectNode() {
        api.combinePSBT("");
        Assert.fail();
    }
}