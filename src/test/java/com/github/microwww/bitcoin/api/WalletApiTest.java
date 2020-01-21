package com.github.microwww.bitcoin.api;

import com.github.microwww.bitcoin.model.AccountTransaction;
import com.github.microwww.bitcoin.model.AccountType;
import com.github.microwww.bitcoin.model.RawTransaction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WalletApiTest {
    private WalletApi api = new WalletApi("btcrpc", "123456789", "http://192.168.1.31:8332/");
    private RawTransactionsApi txapi = new RawTransactionsApi("btcrpc", "123456789", "http://192.168.1.31:8332/");

    @Test
    public void testGetNewAddress() {
        {
            //mqzVGj7zVBpDWUaARroejuRHVkTCKZUoF3
            api.getNewAddress("legacy", AccountType.legacy);
            //2MtRi17d2R41E2CNKr4JJC54tdCRe6q5u17
            api.getNewAddress("p2shSegwit", AccountType.p2shSegwit);
            //bcrt1qjlw72z0cthvx8sz5h9d4acnda6k2hjd8hd89uv
            String address = api.getNewAddress("bech32", AccountType.bech32);
            assertEquals(44, address.length());
        }
        String address = api.getNewAddress();
        assertEquals(35, address.length());
        String prevkey = api.dumpPrivateKey(address);
        assertEquals(52, prevkey.length());
    }

    @Test
    public void testImportAddress() {
        api.importAddress("1EVbakhm8HiXXwqsmX4mexCXPiq5Ym7jjK");
    }

    @Test
    public void testListTransactions() {
        api.listTransactions("");
        AccountTransaction[] txs = api.listTransactions();
        for (AccountTransaction tx : txs) {
            txapi.getRawTransaction(tx.getTxid());
            //System.out.println(String.format("%s, %s, %s, %s, %s", rtx.getVin()[0].getVout(), rtx.getVout()[0].getN(), tx.getTxid(), tx.getVout(), tx.getBlockindex()));
        }
    }

    @Test
    public void testGetAccountAddress_GetAccount() {
        String eg = "test-java";
        String addr = api.getAccountAddress(eg);
        assertNotNull(addr);
        api.getAddressInfo(addr);
        String acc = api.getAccount(addr);
        assertEquals(eg, acc);
    }
}