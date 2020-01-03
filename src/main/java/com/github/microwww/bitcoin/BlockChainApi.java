package com.github.microwww.bitcoin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.microwww.bitcoin.annotation.NoComplete;
import com.github.microwww.bitcoin.model.*;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockChainApi {
    private static Logger logger = LoggerFactory.getLogger(BlockChainApi.class);
    private static final ObjectMapper mapper = new ObjectMapper(); //.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            //.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.1.32", 3128)))
            .build();
    private OkHttpClient client = okHttpClient;
    private String username;
    private String password;
    private String url;
    private static transient AtomicInteger inc = new AtomicInteger(0);

    public BlockChainApi(String username, String password, String url) {
        this.username = username;
        this.password = password;
        this.url = url;
    }

    public BlockChainApi setOkHttpClient(OkHttpClient client) {
        this.client = client;
        return this;
    }

    public <U, T extends JsonRpcResult<U>> U post(JsonRpc20 json, Class<T> result) {
        if (json.getId() <= 0) {
            json.setId(this.getId());
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json.toJson());
        String basic = Credentials.basic(this.username, this.password);
        Request request = new Request.Builder()
                .addHeader("Authorization", basic)
                .url(this.url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            if (response.code() / 100 != 2) {
                throw new HttpException(response, "Response Not 2xx ERROR, [%s,%s]", response.code(), response.message());
            } else {
                String resp = response.body().string();
                if (logger.isDebugEnabled()) {
                    logger.debug("Request {} Headers ::: \n{}\nContent:: {}", request.url(), response.headers().toString(), resp);
                }
                T value = mapper.readValue(resp, result);
                if (value.getId() != json.getId()) {
                    throw new RpcException("Id NOT compare %s : %s", value.getId(), json.getId());
                }
                return value.getResult();
            }
        } catch (IOException ex) {
            throw new RuntimeException("IO exception !", ex);
        }
    }

    public String getBestBlockHash() {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).setMethod("getbestblockhash").getJson();
        return this.post(json, StringValue.class);
    }

    public Block getBlock(String hash) {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).appendParams(hash).appendParams(1).setMethod("getblock").getJson();
        return this.post(json, Block.Result.class);
    }

    public BlockChainInfo getBlockChainInfo() {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).setMethod("getblockchaininfo").getJson();
        return this.post(json, BlockChainInfo.Result.class);
    }

    public long getBlockCount() {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).setMethod("getblockcount").getJson();
        return this.post(json, LongValue.class).longValue();
    }

    public String getBlockHash(int height) {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).setMethod("getblockhash").appendParams(height).getJson();
        return this.post(json, StringValue.class);
    }

    public BlockHeader getBlockHeader(String hash) {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).setMethod("getblockheader").appendParams(hash).getJson();
        return this.post(json, BlockHeader.Result.class);
    }

    /**
     * @param hash
     * @param stats BlockStats properties
     * @return
     */
    public BlockStats getBlockStats(String hash, String... stats) {
        return this.queryBlockStats(hash, stats);
    }

    /**
     * @param height
     * @param stats  BlockStats properties
     * @return
     */
    public BlockStats getBlockStats(int height, String... stats) {
        return this.queryBlockStats(height, stats);
    }

    private BlockStats queryBlockStats(Object param, String... stats) {
        JsonRpc20.Builder builder = new JsonRpc20.Builder().setId(this.getId()).setMethod("getblockstats").appendParams(param);
        if (stats != null && stats.length > 0) {
            builder.appendParams(stats);
        }
        JsonRpc20 json = builder.getJson();
        return this.post(json, BlockStats.Result.class);
    }

    public ChainTip[] getChainTips() {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).setMethod("getchaintips").getJson();
        return this.post(json, ChainTip.Result.class);
    }

    public ChainTxStats getChainTxStats() {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).setMethod("getchaintxstats").getJson();
        return this.post(json, ChainTxStats.Result.class);
    }

    public Long getDifficulty() {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).setMethod("getdifficulty").getJson();
        return this.post(json, LongValue.class).longValue();
    }

    public MemPoolInfo getMemPoolInfo() {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).setMethod("getmempoolinfo").getJson();
        return this.post(json, MemPoolInfo.Result.class);
    }

    public String[] getRawMemPool() {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).setMethod("getrawmempool").getJson();
        return this.post(json, StringArray.class);
    }

    public Map<String, MemPoolTxInfo> getRawMemPoolTx() {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).setMethod("getrawmempool").appendParams(true).getJson();
        return this.post(json, MemPoolTxInfo.MapResult.class);
    }

    public String[] getMemPoolAncestors(String txHash) {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).setMethod("getmempoolancestors").appendParams(txHash).getJson();
        return this.post(json, StringArray.class);
    }

    public String[] getMemPoolDescendants(String txHash) {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).setMethod("getmempooldescendants").appendParams(txHash).getJson();
        return this.post(json, StringArray.class);
    }

    public MemPoolTxInfo getMemPoolEntry(String txHash) {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).setMethod("getmempoolentry").appendParams(txHash).getJson();
        return this.post(json, MemPoolTxInfo.Result.class);
    }

    public TransactionOut getTxOut(String txHash, int n, boolean pool) {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).setMethod("gettxout")
                .appendParams(txHash).appendParams(n).appendParams(pool).getJson();
        return this.post(json, TransactionOut.Result.class);
    }

    public TransactionOut getTxOutProofByBlock(String blockHash, String... txHash) {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).setMethod("gettxoutproof")
                .appendParams(txHash).appendParams(blockHash).getJson();
        return this.post(json, TransactionOut.Result.class);
    }

    public String getTxOutProof(String... txHash) {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).setMethod("gettxoutproof")
                .appendParams(txHash).getJson();
        return this.post(json, StringValue.class);
    }

    public TxOutSetInfo getTxOutSetInfo() {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).setMethod("gettxoutsetinfo").getJson();
        return this.post(json, TxOutSetInfo.Result.class);
    }

    @NoComplete
    private String preciousBlock(String block) {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).setMethod("preciousblock").appendParams(block).getJson();
        return this.post(json, StringValue.class);
    }

    @NoComplete
    private String pruneBlockChain() {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).setMethod("pruneblockchain").getJson();
        return this.post(json, StringValue.class);
    }

    public String saveMemPool() {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).setMethod("savemempool").getJson();
        return this.post(json, StringValue.class);
    }

    @NoComplete
    public String scanTxOutSet(ScanAction action, ScanObject... obj) {
        JsonRpc20.Builder builder = new JsonRpc20.Builder().setId(this.getId()).setMethod("scantxoutset");
        if (obj != null && obj.length > 0) {
            builder.appendParams(action);
        }
        return this.post(builder.getJson(), StringValue.class);
    }

    public static enum ScanAction {
        start, abort, status
    }

    public static class ScanObject {
    }

    /**
     * @param level   (numeric, optional, 0-4, default=3) How thorough the block verification is.
     * @param nblocks (numeric, optional, default=6, 0=all) The number of blocks to check.
     * @return
     */
    public boolean verifyChain(int level, int nblocks) {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).setMethod("verifychain")
                .appendParams(level).appendParams(nblocks).getJson();
        return this.post(json, BooleanValue.class).booleanValue();
    }

    /**
     * @param proof (string, required) The hex-encoded proof generated by gettxoutproof
     * @return
     */
    public boolean verifyTxOutProof(String proof) {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).setMethod("verifytxoutproof")
                .appendParams(proof).getJson();
        return this.post(json, BooleanValue.class).booleanValue();
    }

    public boolean verifyOutProof(String... tx) {
        String proof = this.getTxOutProof(tx);
        return this.verifyTxOutProof(proof);
    }


    public int getId() {
        int ii = inc.addAndGet(1);
        if (ii >= 9_999) {
            inc.set(0);
        }
        return ii;
    }
}
