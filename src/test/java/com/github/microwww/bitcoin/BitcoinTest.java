package com.github.microwww.bitcoin;

import com.github.microwww.bitcoin.model.BlockHeader;
import com.github.microwww.bitcoin.model.BlockStats;
import org.junit.Test;

import java.io.IOException;

public class BitcoinTest {

    Bitcoin api = new Bitcoin("btcrpc", "9pDbNxQJjXUpZKQNYoFQiWDqYXyyrd29", "http://localhost:8332/");

    @Test
    public void getInfo() {
        //String basic = Credentials.basic("btcrpc", "9pDbNxQJjXUpZKQNYoFQiWDqYXyyrd29");
        String hash = api.getBestBlockHash();
        // {"result":"0000000000000000000fc70207790a878fa2b7f58769454e84eeec93b1ccc1e4","error":null,"id":1}
    }

    @Test
    public void getBlock() {
        api.getBlock("0000000000000000000fc70207790a878fa2b7f58769454e84eeec93b1ccc1e4");
    }

    @Test
    public void getBlockCount() {
        api.getBlockCount();
    }

    @Test
    public void getblockchaininfo() {
        api.getBlockChainInfo();
    }

    @Test
    public void getBlockHeader() {
        BlockHeader header = api.getBlockHeader("0000000000000000000fc70207790a878fa2b7f58769454e84eeec93b1ccc1e4");
        System.out.println(header);
    }

    @Test
    public void getBlockStats() throws IOException {
        BlockStats header = api.getBlockStats(100);
        try {
            header = api.getBlockStats(header.getBlockhash(), "height", "txs");
        }catch (HttpException e){
            System.out.println(e.getResponse().body().string());
        }
    }

}