package com.github.microwww.bitcoin.api;

import com.github.microwww.bitcoin.HttpException;
import com.github.microwww.bitcoin.model.AccountTransaction;
import com.github.microwww.bitcoin.model.TransactionInput;
import com.github.microwww.bitcoin.model.TransactionOutput;
import com.github.microwww.bitcoin.model.TransactionSign;
import org.junit.Test;

public class RawTransactionsApiTest {

    private RawTransactionsApi api = new RawTransactionsApi("btcrpc", "123456789", "http://192.168.1.31:8332/");
    private WalletApi wallet = new WalletApi("btcrpc", "123456789", "http://192.168.1.31:8332/");

    @Test
    public void combineRawTransaction() {
        //api.combineRawTransaction();
    }

    @Test
    public void createRawTransaction() {
        AccountTransaction[] txs = wallet.listTransactions();
        AccountTransaction tx0 = txs[txs.length - 1];
        TransactionInput in = tx0.toTransactionInput();
        String address = wallet.getAccountAddress("target");
        TransactionOutput out = new TransactionOutput.Transaction(address, tx0.getAmount() - 0.001);
        try {
            String hex = api.createRawTransaction(new TransactionInput[]{in}, new TransactionOutput[]{out, new TransactionOutput.Data("test message".getBytes())});
            //api.sendRawTransaction(hex);
            TransactionSign signHex = api.signRawTransaction(hex);
            api.sendRawTransaction(signHex.getHex());
        }catch (HttpException e){
            e.logger();
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateRawTransaction() {
    }

    @Test
    public void decodeRawTransaction() {
    }

    @Test
    public void decodeScript() {
    }

    @Test
    public void fundRawTransaction() {
    }

    @Test
    public void getRawTransaction() {
    }

    @Test
    public void getRawTransactionHex() {
    }

    @Test
    public void sendRawTransaction() {
    }

    @Test
    public void testSendRawTransaction() {
    }

    @Test
    public void signRawTransaction() {
    }

    @Test
    public void signRawTransactionWithKey() {
    }

    @Test
    public void testMemPoolAccept() {
    }
}