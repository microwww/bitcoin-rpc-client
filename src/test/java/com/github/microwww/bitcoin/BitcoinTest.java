package com.github.microwww.bitcoin;

import com.github.microwww.bitcoin.model.BlockHeader;
import com.github.microwww.bitcoin.model.BlockStats;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BitcoinTest {

    private Bitcoin api = new Bitcoin("btcrpc", "9pDbNxQJjXUpZKQNYoFQiWDqYXyyrd29", "http://localhost:8332/");

    @Test
    public void getBestBlockHash() {
        //String basic = Credentials.basic("btcrpc", "9pDbNxQJjXUpZKQNYoFQiWDqYXyyrd29");
        String hash = api.getBestBlockHash();
        // {"result":"0000000000000000000fc70207790a878fa2b7f58769454e84eeec93b1ccc1e4","error":null,"id":1}
    }

    @Test
    public void getBlock() {
        api.getBlock("0000000000000000000fc70207790a878fa2b7f58769454e84eeec93b1ccc1e4");
    }

    @Test
    public void getblockchaininfo() {
        api.getBlockChainInfo();
    }

    @Test
    public void getBlockCount() {
        int count = api.getBlockCount();
        assertTrue(count > 100);
    }

    @Test
    public void getBlockHash() {
        String hash = api.getBlockHash(10);
        assertTrue(hash.startsWith("00000000"));
    }

    @Test
    public void getBlockHeader() {
        BlockHeader header = api.getBlockHeader("0000000000000000000fc70207790a878fa2b7f58769454e84eeec93b1ccc1e4");
        assertEquals(609557, header.getHeight());
    }

    @Test
    public void getBlockStats() throws IOException {
        BlockStats header = api.getBlockStats(100);
        header = api.getBlockStats(header.getBlockhash(), "height", "txs");
        assertEquals(100, header.getHeight());
    }

}