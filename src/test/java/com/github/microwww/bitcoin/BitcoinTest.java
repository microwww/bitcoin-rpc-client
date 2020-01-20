package com.github.microwww.bitcoin;

import com.github.microwww.bitcoin.api.BlockChainApi;
import com.github.microwww.bitcoin.api.RawTransactionsApi;
import com.github.microwww.bitcoin.model.*;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class BitcoinTest {

    private BlockChainApi api = new BlockChainApi("btcrpc", "123456789", "http://192.168.1.31:8332/");

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
        long count = api.getBlockCount();
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
    public void getBlockStats() {
        BlockStats header = api.getBlockStats(100);
        header = api.getBlockStats(header.getBlockhash(), "height", "txs");
        assertEquals(100, header.getHeight());
    }

    @Test
    public void getChainTips() {
        ChainTip[] header = api.getChainTips();
        assertTrue(header.length > 0);
    }

    @Test
    public void getChainTxStats() {
        ChainTxStats header = api.getChainTxStats();
        assertTrue(header.getWindowFinalBlockHash().startsWith("000000"));
    }

    @Test
    public void getDifficulty() {
        long val = api.getDifficulty();
        assertTrue(val > 100);
    }

    @Test
    public void getMemPoolInfo() {
        MemPoolInfo val = api.getMemPoolInfo();
        assertTrue(val.getSize() > 0);
    }

    @Test
    public void getRawMemPool() {
        String[] val = api.getRawMemPool();
        assertTrue(val.length > 0);
    }

    @Test
    public void getRawMemPoolTx() {
        Map<String, MemPoolTxInfo> val = api.getRawMemPoolTx();
        assertTrue(val.values().size() > 0);
    }

    @Test
    public void getMemPoolAncestors() {
        String[] val = api.getRawMemPool();
        api.getMemPoolAncestors(val[2]);
    }

    @Test
    public void getMemPoolDescendants() {
        String[] val = api.getRawMemPool();
        api.getMemPoolDescendants(val[2]);
    }

    @Test
    public void getMemPoolEntry() {
        String[] val = api.getRawMemPool();
        api.getMemPoolEntry(val[2]);
    }

    @Test
    public void getTxOut() {
        String tx = api.getBlock("0000000000000000000fc70207790a878fa2b7f58769454e84eeec93b1ccc1e4").getTx()[5];
        TransactionOut val = api.getTxOut(tx, 1, true);
        assertEquals("1Em2AiirvVmifJ297YH6v1XJmLBinWVG1t", val.getScriptPubKey().getAddresses()[0]);
    }

    @Test
    public void getTxOutProof() {
        String block = "0000000000000000000fc70207790a878fa2b7f58769454e84eeec93b1ccc1e4";
        String[] txs = api.getBlock(block).getTx();
        //TransactionOut val = api.getTxOutProofByBlock("00000000000000000008f029112b79dceff814dcf02e1902474ac8051af16a8c", tx);
        String val = api.getTxOutProof(txs[5], txs[6]);
        assertTrue(val.startsWith("00200020e093663f97ba1af46ec0a5114f0ab2c436ed137c053d04"));
        try {
            api.getTxOutProof(txs[5], "9ee02950bf0f95dc08f9b05048d190eedae3095d3828a8f57262d66382768a5f");
            fail();
        } catch (HttpException ex) {
            assertEquals(-5, ex.tryJsonError().getCode());
        }
    }

    @Test
    public void verifyChain() {
        api.verifyChain(3, 6);
    }

}