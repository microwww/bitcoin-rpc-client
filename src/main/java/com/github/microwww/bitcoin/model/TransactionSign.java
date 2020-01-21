package com.github.microwww.bitcoin.model;

import com.github.microwww.bitcoin.JsonRpcResult;

public class TransactionSign {
    public static class Result extends JsonRpcResult<TransactionSign> {
    }
    private String hex;
    private boolean complete;

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
