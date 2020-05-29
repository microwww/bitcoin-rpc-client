package com.github.microwww.bitcoin.api;

import com.github.microwww.bitcoin.JsonRpc20;
import com.github.microwww.bitcoin.JsonRpcClient;
import com.github.microwww.bitcoin.JsonRpcResult;
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

    // anandontransaction 调用可以将一个钱包交易及其后代标记为放弃，这样 该交易中的输入UTXO就可以重新利用。
    // abandontransaction "txid"
    public void abandonTransaction(String txid) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("abandontransaction").appendParams(txid).getJson();
        this.post(json, StringValue.class);
    }

    // Stops current wallet rescan triggered by an RPC call, e.g. by an importprivkey call.
    public void abortRescan() {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("abortrescan").getJson();
        this.post(json, StringValue.class);
    }

    /**
     * @param number      (numeric, required) The number of required signatures out of the n keys or addresses.
     * @param address     (string) bitcoin address or hex-encoded public key
     * @param lable       (string, optional) A label to assign the addresses to.
     * @param addressType (string, optional) The address type to use. Options are "legacy", "p2sh-segwit", and "bech32". Default is set by -addresstype.
     */
    public MultiSignAddress addmultisigaddress(int number, String[] address, String lable, AccountType addressType) {
        JsonRpc20.Builder builder = new JsonRpc20.Builder().setMethod("addmultisigaddress").appendParams(number).appendParams(address);
        if (lable != null) {
            builder.appendParams(lable);
            if (addressType != null) {
                builder.appendParams(addressType.getType());
            }
        }
        return this.post(builder.getJson(), MultiSignAddress.Result.class);
    }

    /**
     * backupwallet "destination"
     *
     * Safely copies current wallet file to destination, which can be a directory or a path with filename.
     *
     * @param destination (string) The destination directory or file
     */
    public void backupWallet(String destination) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("backupwallet").appendParams(destination).getJson();
        this.post(json, StringValue.class);
    }

    /**
     * Bumps the fee of an opt-in-RBF transaction T, replacing it with a new transaction B.
     * An opt-in RBF transaction with the given txid must be in the wallet.
     * The command will pay the additional fee by decreasing (or perhaps removing) its change output.
     * If the change output is not big enough to cover the increased fee, the command will currently fail
     * instead of adding new inputs to compensate. (A future implementation could improve this.)
     * The command will fail if the wallet or mempool contains a transaction that spends one of T's outputs.
     * By default, the new fee will be calculated automatically using estimatesmartfee.
     * The user can specify a confirmation target for estimatesmartfee.
     * Alternatively, the user can specify totalFee, or use RPC settxfee to set a higher fee rate.
     * At a minimum, the new fee rate must be high enough to pay an additional new relay fee (incrementalfee
     * returned by getnetworkinfo) to enter the node's mempool.
     *
     * @param txid (string, required) The txid to be bumped
     * @param options
     * @return (object, optional), nullable
     */
    public BumpFee.BumpTransactionFee bumpFee(String txid, BumpFee.Options options) {
        JsonRpc20.Builder builder = new JsonRpc20.Builder().setMethod("bumpfee").appendParams(txid);
        if (options != null) {
            builder.appendParams(options);
        }
        return this.post(builder.getJson(), BumpFee.Result.class);
    }

    // createwallet "wallet_name" ( disable_private_keys )
    public WalletName createWallet(String name, boolean disable_private_keys) {
        JsonRpc20.Builder builder = new JsonRpc20.Builder().setMethod("createwallet").appendParams(name);
        builder.appendParams(disable_private_keys);
        return this.post(builder.getJson(), WalletName.Result.class);
    }

    public static class WalletName {
        public static class Result extends JsonRpcResult<WalletName> {
        }

        private String name;
        private String warning;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getWarning() {
            return warning;
        }

        public void setWarning(String warning) {
            this.warning = warning;
        }
    }

    //dumpprivkey "address"
    public String dumpPrivateKey(String address) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("dumpprivkey").appendParams(address).getJson();
        return this.post(json, StringValue.class);
    }

    /**
     * Dumps all wallet keys in a human-readable format to a server-side file. This does not allow overwriting existing files.
     * Imported scripts are included in the dumpfile, but corresponding BIP173 addresses, etc. may not be added automatically by importwallet.
     * Note that if your wallet contains keys which are not derived from your HD seed (e.g. imported keys), these are not covered by
     * only backing up the seed itself, and must be backed up too (e.g. ensure you back up the whole dumpfile).
     *
     * @param filename
     */
    // dumpwallet "filename"
    public String dumpWallet(String filename) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("dumpwallet").appendParams(filename).getJson();
        return (String) this.post(json, MapValue.class).get("filename");
    }

    // encryptwallet "passphrase"
    public void encryptWallet(String passphrase) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("encryptwallet").appendParams(passphrase).getJson();
        this.post(json, StringValue.class);
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

    /**
     * Returns a new Bitcoin address, for receiving change.
     * This is for use with raw transactions, NOT normal use.
     *
     * @param address_type (string, optional) nullable The address type to use. Options are "legacy", "p2sh-segwit", and "bech32". Default is set by -changetype
     * @return
     */
    public String getRawChangeAddress(AccountType address_type) {
        JsonRpc20.Builder json = new JsonRpc20.Builder().setMethod("getrawchangeaddress");
        if (address_type != null) {
            json.appendParams(address_type);
        }
        return this.post(json.getJson(), StringValue.class);
    }

    // getreceivedbylabel "label" ( minconf )
    public double getReceivedByLabel(String label, int minconf) {
        JsonRpc20.Builder json = new JsonRpc20.Builder().setMethod("getreceivedbylabel").appendParams(label).appendParams(minconf);
        return this.post(json.getJson(), BigDecimalValue.class).doubleValue();
    }

    // getreceivedbyaddress "address" ( minconf )
    public double getReceivedByAddress(String address, int minconf) {
        JsonRpc20.Builder json = new JsonRpc20.Builder().setMethod("getreceivedbyaddress").appendParams(address).appendParams(minconf);
        return this.post(json.getJson(), BigDecimalValue.class).doubleValue();
    }

    @NoComplete //gettransaction "txid" ( include_watchonly )
    public WalletTransaction getTransaction(String txhash, boolean watchonly) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("gettransaction").appendParams(txhash).appendParams(watchonly).getJson();
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

    public String[] listLabels() {
        return this.listLabels(null);
    }

    /**
     * receive / send
     *
     * @param purpose (string, optional) [receive | send] Address purpose to list labels for ('send','receive'). An empty string is the same as not providing this argument.
     * @return Label name
     */
    @NoComplete //listlabels ( "purpose" )
    public String[] listLabels(Purpose purpose) {
        JsonRpc20.Builder builder = new JsonRpc20.Builder().setMethod("listlabels");
        if (purpose != null) {
            builder.appendParams(purpose.name());
        }
        JsonRpc20 json = builder.getJson();
        return this.post(json, ArrayValue.StringArray.class);
    }

    public enum Purpose {
        receive, send;
    }


    /**
     * Returns list of temporarily unspendable outputs.
     * See the lockunspent call to lock and unlock transactions for spending.
     *
     * @return List the locked transactions
     */
    @NoComplete
    public OutTransaction[] listLockUnspent() {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("listlockunspent").getJson();
        return this.post(json, OutTransaction.Result.class);
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

    /**
     * @param unlock ( boolean, required) Whether to unlock (true) or lock (false) the specified transactions
     * @return true|false    (boolean) Whether the command was successful or not
     */
    @NoComplete // lockunspent unlock ([{"txid":"txid","vout":n},...])
    public boolean lockUnspent(boolean unlock, OutTransaction[] transactions) {
        JsonRpc20 json = new JsonRpc20.Builder().setMethod("lockunspent").appendParams(unlock)
                .appendParams(transactions).getJson();
        return this.post(json, BooleanValue.class).booleanValue();
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
