package com.github.microwww.bitcoin.api;

import com.github.microwww.bitcoin.JsonRpc20;
import com.github.microwww.bitcoin.JsonRpcClient;
import com.github.microwww.bitcoin.annotation.NoComplete;
import com.github.microwww.bitcoin.model.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WalletApi extends JsonRpcClient {

    public WalletApi(String username, String password, String url) {
        super(username, password, url);
    }

    @NoComplete    //abandontransaction "txid"
    public void abandonTransaction(String txid) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("abandontransaction").appendParams(txid).getJson();
        this.post(json, StringValue.class);
    }

    @NoComplete    //abortrescan
    public void abortRescan() {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("abortrescan").getJson();
        this.post(json, StringValue.class);
    }

    @NoComplete    //addmultisigaddress nrequired ["key",...] ( "label" "address_type" )
    public void addmultisigaddress() {
    }

    @NoComplete //backupwallet "destination"
    public void backupwallet(String destination) {
    }

    @NoComplete //bumpfee "txid" ( options )
    public void bumpfee(String txid) {
    }

    @NoComplete //createwallet "wallet_name" ( disable_private_keys )
    public void createwallet(String name) {
    }

    //dumpprivkey "address"
    public String dumpPrivateKey(String address) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("dumpprivkey").appendParams(address).getJson();
        return this.post(json, StringValue.class);
    }

    @NoComplete //dumpwallet "filename"
    public void dumpWallet(String filename) {
    }

    @NoComplete //encryptwallet "passphrase"
    public void encryptwallet(String passphrase) {
    }

    //getaccount "address"
    public String getAccount(String address) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("getaccount").appendParams(address).getJson();
        return this.post(json, StringValue.class);
    }

    //getaccountaddress "account"
    public String getAccountAddress(String account) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("getaccountaddress").appendParams(account).getJson();
        return this.post(json, StringValue.class);
    }

    //getaddressesbyaccount "account"
    public String[] getAddressesByAccount(String account) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("getaddressesbyaccount").appendParams(account).getJson();
        return this.post(json, ArrayValue.StringArray.class);
    }

    //getaddressesbylabel "label"
    public String[] getAddressesByLabel(String label) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("getaddressesbylabel").appendParams(label).getJson();
        return this.post(json, ArrayValue.StringArray.class);
    }

    @NoComplete //getaddressinfo "address"
    public AddressInfo getAddressInfo(String address) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("getaddressinfo").appendParams(address).getJson();
        return this.post(json, AddressInfo.Result.class);
    }

    public double getBalance() {
        return this.getBalance("*", 0, false);
    }

    /**
     * @param account          Optional, Remains for backward compatibility. Must be excluded or set to “*”.
     * @param minConfirmed     Default=0, Only include transactions confirmed at least this many times.
     * @param includeWatchOnly Default=false, Also include balance in watch-only addresses (see ‘importaddress’)
     * @return
     */
    //getbalance ( "account" minconf include_watchonly )
    public double getBalance(String account, int minConfirmed, boolean includeWatchOnly) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("getbalance").appendParams(account).getJson();
        return this.post(json, BigDecimalValue.class).doubleValue();
    }

    //getnewaddress ( "label" "address_type" ) | “legacy”, “p2sh-segwit”, and “bech32”.
    public String getNewAddress() {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("getnewaddress").getJson();
        return this.post(json, StringValue.class);
    }

    public String getNewAddress(String label, AccountType type) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("getnewaddress").appendParams(label).appendParams(type.getType()).getJson();
        return this.post(json, StringValue.class);
    }

    @NoComplete //getrawchangeaddress ( "address_type" )
    public void getrawchangeaddress() {
    }

    @NoComplete //getreceivedbylabel "label" ( minconf )
    public void getreceivedbylabel(String label) {
    }

    @NoComplete //getreceivedbyaddress "address" ( minconf )
    public void getreceivedbyaddress(String address) {
    }

    @NoComplete //gettransaction "txid" ( include_watchonly )
    public WalletTransaction getTransaction(String txhash) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("gettransaction").appendParams(txhash).getJson();
        return this.post(json, WalletTransaction.Result.class);
    }

    @NoComplete //getunconfirmedbalance
    public void getunconfirmedbalance() {
    }

    @NoComplete //getwalletinfo
    public void getwalletinfo() {
    }

    public void importAddress(String address) {
        this.importAddress(address, "", true, false);
    }

    /**
     * Error ::{"result":null,"error":{"code":-5,"message":"Invalid Bitcoin address or script"},"id":1},
     * NOTE :: no private key ! not transaction
     *
     * @param address
     * @param label   Default=""
     * @param rescan  Rescan the wallet for transactions
     * @param p2sh    Default=false, Add the P2SH version of the script as well
     * @return
     */
    public void importAddress(String address, String label, boolean rescan, boolean p2sh) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("importaddress").appendParams(address)
                .appendParams(label)
                .appendParams(rescan)
                .appendParams(p2sh).getJson();
        this.post(json, StringValue.class);
    }

    @NoComplete //importmulti "requests" ( "options" )
    public void importmulti(String requests) {
    }

    @NoComplete //importprivkey "privkey" ( "label" ) ( rescan )
    public void importprivkey(String privkey) {
    }

    @NoComplete //importprunedfunds
    public void importprunedfunds() {
    }

    @NoComplete //importpubkey "pubkey" ( "label" rescan )
    public void importpubkey(String pubkey) {
    }

    @NoComplete //importwallet "filename"
    public void importwallet(String filename) {
    }

    @NoComplete //keypoolrefill ( newsize )
    public void keypoolrefill(String destination) {
    }

    @NoComplete //listaccounts ( minconf include_watchonly)
    public Map<String, Double> listAccounts() {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("listaccounts").getJson();
        return this.post(json, MapValue.class);
    }

    public String[] listAccountsName() {
        Set<String> keys = this.listAccounts().keySet();
        return keys.toArray(new String[keys.size()]);
    }

    @NoComplete
    public Object[][][] listAddressGroupings() {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("listaddressgroupings").getJson();
        return this.post(json, ArrayValue.ThreeArray.class);
    }

    public Map<String, Balance> listAddressGroupingsBalance() {
        Object[][][] post = this.listAddressGroupings();
        Map<String, Balance> list = new HashMap<>();
        for (Object[][] o1 : post) {
            for (Object[] o2 : o1) {
                Balance ce = new Balance(null, null, "");
                if (o2.length > 1) {
                    ce.setAddress((String) o2[0]);
                    ce.setBalance(new BigDecimal(o2[1].toString())); // 必须用string 否则会有精度问题
                } else {
                    throw new RuntimeException("listAddressGroupings format error ! must: [[[address,balance,???]]]");
                }
                if (o2.length > 2) {
                    ce.setAccount((String) o2[2]);
                }
                list.put(ce.getAddress(), ce);
            }
        }
        return list;
    }

    @NoComplete //listlabels ( "purpose" )
    public void listlabels() {
    }

    @NoComplete //listlockunspent
    public void listlockunspent() {
    }

    @NoComplete //listreceivedbylabel ( minconf include_empty include_watchonly)
    public void listreceivedbylabel() {
    }

    @NoComplete //listreceivedbyaddress ( minconf include_empty include_watchonly address_filter )
    public void listreceivedbyaddress() {
    }

    @NoComplete //listsinceblock ( "blockhash" target_confirmations include_watchonly include_removed )
    public void listsinceblock() {
    }

    public AccountTransaction[] listTransactions() {
        return listTransactions("*");
    }

    public AccountTransaction[] listTransactions(String account) {
        return listTransactions(account, 10, 0, false);
    }

    /**
     * @param account          Default=* , specified account label, or “*”
     * @param count            Default=10, The number of transactions to return
     * @param skip             Default=0, The number of transactions to skip (for page)
     * @param includeWatchOnly Default=false (see ‘importaddress’)
     * @return
     */
    //listtransactions ( "account" count skip include_watchonly)
    public AccountTransaction[] listTransactions(String account, int count, int skip, boolean includeWatchOnly) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("listtransactions").appendParams(account)
                .appendParams(count)
                .appendParams(skip)
                .appendParams(includeWatchOnly).getJson();
        return this.post(json, AccountTransaction.Result.class);
    }

    public UnspentTransaction[] listUnspent(int minConfirmed, String... addresses) {
        return listUnspent(minConfirmed, Integer.MAX_VALUE, addresses, true, null);
    }

    /**
     * @param minConfirmed  Default=1, The minimum confirmations to filter
     * @param maxCConfirmed Default=9999999, The maximum confirmations to filter
     * @param addresses     Default=empty array, A json array of bitcoin addresses to filter
     * @param includeUnsafe Default=true Include outputs that are not safe to spend See description of “safe” attribute below.
     * @param opt           Optional, JSON with query options
     * @return
     */
    @NoComplete //listunspent ( minconf maxconf  ["addresses",...] [include_unsafe] [query_options])
    public UnspentTransaction[] listUnspent(int minConfirmed, int maxCConfirmed, String[] addresses, boolean includeUnsafe, QueryOptions opt) {
        JsonRpc20.Builder params = new JsonRpc20.Builder().setMethod("listunspent").appendParams(minConfirmed)
                .appendParams(maxCConfirmed)
                .appendParams(addresses)
                .appendParams(includeUnsafe);
        if (opt != null) {
            params.appendParams(opt.toJson());
        }
        JsonRpc20 json = params.getJson();
        return this.post(json, UnspentTransaction.Result.class);
    }

    public void listwallets() {
    }

    @NoComplete //loadwallet "filename"
    public void loadwallet(String filename) {
    }

    @NoComplete // lockunspent unlock ([{"txid":"txid","vout":n},...])
    public void lockunspent(String unlock) {
    }

    @NoComplete //move "fromaccount" "toaccount" amount ( minconf "comment" )
    public void move(String fromAccount, String toAccount, double amount) {
    }

    @NoComplete //removeprunedfunds "txid"
    public void removeprunedfunds(String txid) {
    }

    @NoComplete //rescanblockchain ("start_height") ("stop_height")
    public void rescanblockchain() {
    }


    public String sendFrom(String fromAccount, String toAddress, double amount) {
        return sendFrom(fromAccount, toAddress, amount, 1, null, null);
    }

    /**
     * @param fromAccount
     * @param toAddress
     * @param amount
     * @param minConfirmed
     * @param comment
     * @param commentTo
     * @return
     */
    @NoComplete //sendfrom "fromaccount" "toaddress" amount ( minconf "comment" "comment_to" )
    public String sendFrom(String fromAccount, String toAddress, double amount, int minConfirmed, String comment, String commentTo) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("sendfrom")
                .appendParams(fromAccount).appendParams(toAddress).appendParams(amount)
                .appendParams(minConfirmed).appendParams(comment).appendParams(commentTo).getJson();
        return this.post(json, StringValue.class);
    }

    /**
     * @param fromAccount
     * @param tx
     * @param minConfirmed    Default=1
     * @param comment         Optional, A comment
     * @param subtractFeeFrom Optional, A json array with addresses. The fee will be equally deducted from the amount of each selected address. Those recipients will receive less bitcoins than you enter in their corresponding amount field. If no addresses are specified here, the sender pays the fee.
     * @return
     */
    @NoComplete
    //sendmany "" "fromaccount" {"address":amount,...} ( minconf "comment" ["address",...] replaceable conf_target "estimate_mode")
    public String sendMany(String fromAccount, TransactionOutput.Transaction[] tx, int minConfirmed, String comment, String[] subtractFeeFrom) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("sendmany").appendParams(fromAccount).appendParams(tx).getJson();
        return this.post(json, StringValue.class);
    }

    @NoComplete
    //sendtoaddress "address" amount ( "comment" "comment_to" subtractfeefromamount replaceable conf_target "estimate_mode")
    public void sendToAddress(String address, double amount) {
    }

    @NoComplete //setlabel "address" "label"
    public void setlabel(String address, String label) {
    }

    @NoComplete //sethdseed ( "newkeypool" "seed" )
    public void setHDseed() {
    }

    @NoComplete //settxfee amount
    public void settxfee(double amount) {
    }

    @NoComplete //signmessage "address" "message"
    public void signmessage(String address, String message) {
    }

    @NoComplete
    //signrawtransactionwithwallet "hexstring" ( [{"txid":"id","vout":n,"scriptPubKey":"hex","redeemScript":"hex"},...] sighashtype )
    public void signrawtransactionwithwallet(String hexstring) {
    }

    @NoComplete //unloadwallet ( "wallet_name" )
    public void unloadwallet(String wallet) {
    }

    @NoComplete
    //walletcreatefundedpsbt [{"txid":"id","vout":n},...] [{"address":amount},{"data":"hex"},...] ( locktime ) ( replaceable ) ( options bip32derivs )
    public void walletcreatefundedpsbt(String destination) {
    }

    @NoComplete //walletlock
    public void walletlock() {
    }

    @NoComplete //walletpassphrase "passphrase" timeout
    public void walletpassphrase(String passphrase, int timeout) {
    }

    @NoComplete //walletpassphrasechange "oldpassphrase" "newpassphrase"
    public void walletpassphrasechange(String oldPass, String newPass) {
    }

    @NoComplete //walletprocesspsbt "psbt" ( sign "sighashtype" bip32derivs )
    public void walletprocesspsbt(String psbt) {
    }

}
