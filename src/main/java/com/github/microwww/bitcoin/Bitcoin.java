package com.github.microwww.bitcoin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.microwww.bitcoin.model.*;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class Bitcoin {
    private static Logger logger = LoggerFactory.getLogger(Bitcoin.class);
    private static final ObjectMapper mapper = new ObjectMapper(); //.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            //.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.1.32", 3128)))
            .build();
    private OkHttpClient client = okHttpClient;
    private String username;
    private String password;
    private String url;
    private static transient AtomicInteger inc = new AtomicInteger(0);

    public Bitcoin(String username, String password, String url) {
        this.username = username;
        this.password = password;
        this.url = url;
    }

    public Bitcoin setOkHttpClient(OkHttpClient client) {
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

    public int getBlockCount() {
        JsonRpc20 json = new JsonRpc20.Builder().setId(this.getId()).setMethod("getblockcount").getJson();
        return this.post(json, IntValue.class).intValue();
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

    public int getId() {
        int ii = inc.addAndGet(1);
        if (ii >= 9_999) {
            inc.set(0);
        }
        return ii;
    }
}
