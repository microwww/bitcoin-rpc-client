package com.github.microwww.bitcoin.model;

import com.github.microwww.bitcoin.JsonRpcResult;

public class IntValue extends JsonRpcResult<Integer> {
    public int getValue() {
        return this.getResult().intValue();
    }
}
