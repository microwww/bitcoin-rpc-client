package com.github.microwww.bitcoin.model;

import com.github.microwww.bitcoin.JsonRpcResult;

public class BooleanValue extends JsonRpcResult<Boolean> {
    public boolean getValue() {
        return this.getResult().booleanValue();
    }
}
