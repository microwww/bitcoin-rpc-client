package com.github.microwww.bitcoin.api;

import com.github.microwww.bitcoin.JsonRpc20;
import com.github.microwww.bitcoin.JsonRpcClient;
import com.github.microwww.bitcoin.model.StringValue;

public class RawTransactionsApi extends JsonRpcClient {

    public RawTransactionsApi(String username, String password, String url) {
        super(username, password, url);
    }

    // combinepsbt ["psbt",...]
    public String combinePSBT(String... psbt) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("combinepsbt").appendParams(psbt).getJson();
        return this.post(json, StringValue.class);
    }

    // combinerawtransaction ["hexstring",...]
    public String combineRawTransaction(String... hexstring) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("combinerawtransaction").appendParams(hexstring).getJson();
        return this.post(json, StringValue.class);
    }

    //converttopsbt "hexstring" ( permitsigdata iswitness )
    public String convertToPSBT(String hexstring) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("converttopsbt").appendParams(hexstring).getJson();
        return this.post(json, StringValue.class);
    }

    //createpsbt [{"txid":"id","vout":n},...] [{"address":amount},{"data":"hex"},...] ( locktime ) ( replaceable )
    public String createPSBT(Object node) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("createpsbt").appendParams(node).getJson();
        return this.post(json, StringValue.class);
    }

    //createrawtransaction [{"txid":"id","vout":n},...] [{"address":amount},{"data":"hex"},...] ( locktime ) ( replaceable )
    public String createRawTransaction(String node, NetworkApi.Options opt) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("createrawtransaction").appendParams(node).appendParams(opt.name()).getJson();
        return this.post(json, StringValue.class);
    }

    //decodepsbt "psbt"
    public String decodePSBT(String psbt) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("decodepsbt").appendParams(psbt).getJson();
        return this.post(json, StringValue.class);
    }

    //decoderawtransaction "hexstring" ( iswitness )
    public String decodeRawTransaction(String hexstring, boolean iswitness) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("decoderawtransaction").appendParams(hexstring).appendParams(iswitness).getJson();
        return this.post(json, StringValue.class);
    }

    //decodescript "hexstring"
    public String decodeScript(String hexstring) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("decodescript").appendParams(hexstring).getJson();
        return this.post(json, StringValue.class);
    }

    //finalizepsbt "psbt" ( extract )
    public String finalizePSBT(String psbt) {
        return this.finalizePSBT(psbt, null);
    }

    public String finalizePSBT(String psbt, String extract) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("finalizepsbt").appendParams(psbt).appendParams(extract).getJson();
        return this.post(json, StringValue.class);
    }

    //fundrawtransaction "hexstring" ( options iswitness )
    public String fundRawTransaction(String hexstring) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("fundrawtransaction").appendParams(hexstring).getJson();
        return this.post(json, StringValue.class);
    }

    //getrawtransaction "txid" ( verbose "blockhash" )
    public String getRawTransaction(String txid) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("getrawtransaction").appendParams(txid).getJson();
        return this.post(json, StringValue.class);
    }

    //sendrawtransaction "hexstring" ( allowhighfees )
    public String sendRawTransaction(String hexstring) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("sendrawtransaction").appendParams(hexstring).getJson();
        return this.post(json, StringValue.class);
    }

    //signrawtransaction "hexstring" ( [{"txid":"id","vout":n,"scriptPubKey":"hex","redeemScript":"hex"},...] ["privatekey1",...] sighashtype )
    public String signRawTransaction(String hexstring) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("signrawtransaction").appendParams(hexstring).getJson();
        return this.post(json, StringValue.class);
    }

    //signrawtransactionwithkey "hexstring" ["privatekey1",...] ( [{"txid":"id","vout":n,"scriptPubKey":"hex","redeemScript":"hex"},...] sighashtype )
    public String signRawTransactionWithKey(String hexstring) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("signrawtransactionwithkey").appendParams(hexstring).getJson();
        return this.post(json, StringValue.class);
    }

    //testmempoolaccept ["rawtxs"] ( allowhighfees )
    public String testMemPoolAccept(String rawtxs) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("testmempoolaccept").appendParams(rawtxs).getJson();
        return this.post(json, StringValue.class);
    }

}
