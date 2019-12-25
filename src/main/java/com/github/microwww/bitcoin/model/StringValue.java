package com.github.microwww.bitcoin.model;

import com.github.microwww.bitcoin.JsonRpcResult;

public class StringValue extends JsonRpcResult<String> {
    public String getValue() {
        return this.getResult();
    }
}
