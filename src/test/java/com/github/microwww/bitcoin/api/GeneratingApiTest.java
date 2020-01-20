package com.github.microwww.bitcoin.api;

import org.junit.Test;

public class GeneratingApiTest {
    //private static final Logger logger = LoggerFactory.getLogger(ControlApiTest.class);
    private GeneratingApi api = new GeneratingApi("btcrpc", "123456789", "http://192.168.1.184:8332/");

    @Test
    public void generate() {
        api.generate(1);
    }
}