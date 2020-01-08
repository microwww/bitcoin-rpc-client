package com.github.microwww.bitcoin.api;

import com.github.microwww.bitcoin.JsonRpc20;
import com.github.microwww.bitcoin.JsonRpcClient;
import com.github.microwww.bitcoin.model.StringArray;

public class GeneratingApi extends JsonRpcClient {

    public GeneratingApi(String username, String password, String url) {
        super(username, password, url);
    }

    public String[] generate(int count) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("generate").appendParams(count).getJson();
        return this.post(json, StringArray.class);
    }

    public String[] generateToAddress(int count, String address) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("generatetoaddress").appendParams(count).appendParams(address).getJson();
        return this.post(json, StringArray.class);
    }

}
