package com.github.microwww.bitcoin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.microwww.bitcoin.JsonRpcResult;

public class BumpFee {
    public static class Result extends JsonRpcResult<BumpTransactionFee> {
    }

    public static class BumpTransactionFee {
        private String txid;
        private double origfee;
        private double fee;
        private String[] errors;

        public String getTxid() {
            return txid;
        }

        public void setTxid(String txid) {
            this.txid = txid;
        }

        public double getOrigfee() {
            return origfee;
        }

        public void setOrigfee(double origfee) {
            this.origfee = origfee;
        }

        public double getFee() {
            return fee;
        }

        public void setFee(double fee) {
            this.fee = fee;
        }

        public String[] getErrors() {
            return errors;
        }

        public void setErrors(String[] errors) {
            this.errors = errors;
        }
    }

    public static class Options {
        private Integer confTarget;
        private Double totalFee;
        private Boolean replaceable;
        @JsonProperty("estimate_mode")
        private EstimateMode estimateMode;

        public Integer getConfTarget() {
            return confTarget;
        }

        public void setConfTarget(Integer confTarget) {
            this.confTarget = confTarget;
        }

        public Double getTotalFee() {
            return totalFee;
        }

        public void setTotalFee(Double totalFee) {
            this.totalFee = totalFee;
        }

        public Boolean getReplaceable() {
            return replaceable;
        }

        public void setReplaceable(Boolean replaceable) {
            this.replaceable = replaceable;
        }

        public EstimateMode getEstimateMode() {
            return estimateMode;
        }

        public void setEstimateMode(EstimateMode estimateMode) {
            this.estimateMode = estimateMode;
        }
    }

    public enum EstimateMode {
        UNSET, ECONOMICAL, CONSERVATIVE
    }
}
