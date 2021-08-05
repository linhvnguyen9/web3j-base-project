package org.web3j

import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import java.math.BigInteger

private val nodeUrl = System.getenv().getOrDefault("WEB3J_NODE_URL", "http://localhost:8545")
private val walletPassword = System.getenv().getOrDefault("WEB3J_WALLET_PASSWORD", "")
private val walletPath =
    System.getenv().getOrDefault("WEB3J_WALLET_PATH", "/home/linh/.ethereum/testnet/keystore/test.json")

fun main(args: Array<String>) {
    val credentials = Credentials.create("fc03b915f2dc9c1ab6136e2a7328a68405d04ca261365b558c82a27d6029a6a9")
    val web3j = Web3j.build(HttpService(nodeUrl))
    println("Deploying HelloWorld contract ...")
    val helloWorld = MyContract.deploy(
        web3j,
        credentials,
        BigInteger("20000000000", 10),
        BigInteger("6721975", 10),
        "Hello Blockchain World!"
    ).send()
    println("Contract address: " + helloWorld.contractAddress)
    println("Greeting method result: " + helloWorld.greeting().send())
}