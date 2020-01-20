package com.github.microwww.bitcoin.api;

import com.github.microwww.bitcoin.model.LoggerStatus;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ControlApiTest {
    private static final Logger logger = LoggerFactory.getLogger(ControlApiTest.class);
    private ControlApi api = new ControlApi("btcrpc", "123456789", "http://192.168.1.184:8332/");

    @Test
    public void getMemoryInfo() {
        api.getMemoryInfo();
    }

    @Test
    public void help() {
        api.help();
    }

    @Test
    public void logging() {
        LoggerStatus info = api.logging();
        LoggerStatus str = api.logging(new String[]{"all"}, new String[]{"http"});
    }

    @Test
    public void stop() {
        LoggerStatus info = api.logging();
        LoggerStatus str = api.logging(new String[]{"all"}, new String[]{"http"});
    }

    @Test
    public void uptime() {
    }
}