package com.github.microwww.bitcoin.api;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RawTransactionsApiTest {
    private RawTransactionsApi api = new RawTransactionsApi("btcrpc", "9pDbNxQJjXUpZKQNYoFQiWDqYXyyrd29", "http://192.168.1.184:8332/");

    @Test
    public void disconnectNode() {
        api.combinePSBT("");
        Assert.fail();
    }
}