package com.github.microwww.bitcoin.api;

import com.github.microwww.bitcoin.JsonRpc20;
import com.github.microwww.bitcoin.JsonRpcClient;
import com.github.microwww.bitcoin.model.LoggerStatus;
import com.github.microwww.bitcoin.model.MemoryInfo;
import com.github.microwww.bitcoin.model.StringValue;

public class ControlApi extends JsonRpcClient {

    public ControlApi(String username, String password, String url) {
        super(username, password, url);
    }

    public MemoryInfo getMemoryInfo() {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("getmemoryinfo")
                .appendParams(MemoryInfoModel.stats).getJson();
        return this.post(json, MemoryInfo.Result.class);
    }

    public static enum MemoryInfoModel {
        stats, mallocinfo
    }

    public String help() {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("help").getJson();
        return this.post(json, StringValue.class);
    }

    public String help(String method) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("help").appendParams(method).getJson();
        return this.post(json, StringValue.class);
    }

    public LoggerStatus logging() {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("logging").getJson();
        return this.post(json, LoggerStatus.Result.class);
    }

    public LoggerStatus logging(String[] include, String[] exclude) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("logging").appendParams(include).appendParams(exclude).getJson();
        return this.post(json, LoggerStatus.Result.class);
    }

    public String stop(String hash) {
        JsonRpc20 json = new JsonRpc20.Builder().appendParams(hash).setMethod("stop").getJson();
        return this.post(json, StringValue.class);
    }

    public String uptime(String hash) {
        JsonRpc20 json = new JsonRpc20.Builder().appendParams(hash).setMethod("uptime").getJson();
        return this.post(json, StringValue.class);
    }


}
