package org.web3j

import org.web3j.crypto.Credentials
import org.web3j.crypto.MnemonicUtils
import org.web3j.crypto.WalletUtils
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameter
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.http.HttpService
import org.web3j.tx.gas.ContractGasProvider
import java.io.File
import java.math.BigInteger

private val nodeUrl = System.getenv().getOrDefault("WEB3J_NODE_URL", "http://localhost:8545")

fun main(args: Array<String>) {
    val credentials = Credentials.create("fc03b915f2dc9c1ab6136e2a7328a68405d04ca261365b558c82a27d6029a6a9")
    val web3j = Web3j.build(HttpService(nodeUrl))
//    val contract = NonFungibleToken4.deploy(
//        web3j, credentials, BigInteger("20000000000", 10),
//        BigInteger("6721975", 10)
//    ).send()

//    val wallet = WalletUtils.generateBip39Wallet("password", File("/home/linh/Documents/test"))
//    val mnemonic = wallet.mnemonic
//    val address = wallet.
//    println(mnemonic)
//    println(address)

    val walletBalance = web3j.ethGetBalance("0x3D3EB10Ac58D94700f06C6969C03A30A9099d39F", DefaultBlockParameterName.LATEST).send().balance
    println("Balance $walletBalance")

    val contract = NonFungibleToken4.load(
        "0x6aec55c34fcd7f874237becb83e2a2671caa06b9", web3j, credentials, object : ContractGasProvider {
            override fun getGasPrice(contractFunc: String?): BigInteger {
                return web3j.ethGasPrice().send().gasPrice
            }

            override fun getGasPrice(): BigInteger {
                return web3j.ethGasPrice().send().gasPrice
            }

            override fun getGasLimit(contractFunc: String?): BigInteger {
                return web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().block.gasLimit
            }

            override fun getGasLimit(): BigInteger {
                return web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().block.gasLimit
            }
        })

    println("Contract address: " + contract.contractAddress)
    println("Token symbol: " + contract.symbol().send())
    println("Token name: " + contract.name().send())
    println("Total supply: " + contract.totalSupply().send())
//    println("Token URI: " + contract.tokenURI(BigInteger("1", 10)).send())
    println("All tokens: ${contract.allURI.send()}")
//    println("Safe mint: " + contract.safeMint("0x3D3EB10Ac58D94700f06C6969C03A30A9099d39F").send())
}