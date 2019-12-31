package com.github.microwww.bitcoin.model;

import com.github.microwww.bitcoin.JsonRpcResult;

public class LongValue extends JsonRpcResult<Long> {

    public long getValue() {
        return this.getResult().longValue();
    }
}
