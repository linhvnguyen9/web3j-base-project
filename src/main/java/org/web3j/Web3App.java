package org.web3j;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.generated.contracts.HelloWorld;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;

/**
 * <p>This is the generated class for <code>web3j new helloworld</code></p>
 * <p>It deploys the Hello World contract in src/main/solidity/ and prints its address</p>
 * <p>For more information on how to run this project, please refer to our <a href="https://docs.web3j.io/quickstart/#deployment">documentation</a></p>
 */
public class Web3App {

   private static final String nodeUrl = System.getenv().getOrDefault("WEB3J_NODE_URL", "http://localhost:8545");
   private static final String walletPassword = System.getenv().getOrDefault("WEB3J_WALLET_PASSWORD", "");
   private static final String walletPath = System.getenv().getOrDefault("WEB3J_WALLET_PATH", "/home/linh/.ethereum/testnet/keystore/test.json");

   public static void main(String[] args) throws Exception {
        Credentials credentials = Credentials.create("fc03b915f2dc9c1ab6136e2a7328a68405d04ca261365b558c82a27d6029a6a9");
        Web3j web3j = Web3j.build(new HttpService(nodeUrl));
        System.out.println("Deploying HelloWorld contract ...");
        HelloWorld helloWorld = HelloWorld.deploy(web3j, credentials, new BigInteger("20000000000", 10), new BigInteger("6721975", 10), "Hello Blockchain World!").send();
        System.out.println("Contract address: " + helloWorld.getContractAddress());
        System.out.println("Greeting method result: " + helloWorld.greeting().send());
   }
}

