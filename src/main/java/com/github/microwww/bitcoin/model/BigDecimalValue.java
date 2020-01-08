package com.github.microwww.bitcoin.model;

import com.github.microwww.bitcoin.JsonRpcResult;

import java.math.BigDecimal;

public class BigDecimalValue extends JsonRpcResult<BigDecimal> {
    public BigDecimal getValue() {
        return this.getResult();
    }
}
