package com.github.microwww.bitcoin.model;

import com.github.microwww.bitcoin.JsonRpcResult;

public class ArrayValue<T> extends JsonRpcResult<T[]> {
    public T[] getValue() {
        return this.getResult();
    }

    public static class StringArray extends ArrayValue<String>{
    }
    public static class PoolAcceptArray extends ArrayValue<PoolAccept>{
    }
}
