package org.web3j;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.8.4.
 */
@SuppressWarnings("rawtypes")
public class NonFungibleToken4 extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b50604080518082018252601481527f4e6f6e2d66756e6769626c6520546f6b656e203400000000000000000000000060208083019182528351808501909452600484527f4e465434000000000000000000000000000000000000000000000000000000009084015281519192916200008c9160009162000139565b508051620000a290600190602084019062000139565b5050600b805460ff1916905550620000d5620000c6640100000000620000db810204565b640100000000620000df810204565b62000235565b3390565b600b8054600160a060020a0383811661010081810261010060a860020a031985161790945560405193909204169182907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e090600090a35050565b8280546200014790620001df565b90600052602060002090601f0160209004810192826200016b5760008555620001b6565b82601f106200018657805160ff1916838001178555620001b6565b82800160010185558215620001b6579182015b82811115620001b657825182559160200191906001019062000199565b50620001c4929150620001c8565b5090565b5b80821115620001c45760008155600101620001c9565b600281046001821680620001f457607f821691505b602082108114156200022f577f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b50919050565b6122e780620002456000396000f3fe608060405234801561001057600080fd5b5060043610610175576000357c0100000000000000000000000000000000000000000000000000000000900480635c975abb116100e057806395d89b411161009957806395d89b41146102da578063a22cb465146102e2578063b88d4fde146102f5578063c87b56dd14610308578063e985e9c51461031b578063f2fde38b1461035757600080fd5b80635c975abb146102835780636352211e1461028e57806370a08231146102a1578063715018a6146102b45780638456cb59146102bc5780638da5cb5b146102c457600080fd5b80632f745c59116101325780632f745c591461021c5780633f4ba83a1461022f57806340d097c31461023757806342842e0e1461024a57806342966c681461025d5780634f6ccce71461027057600080fd5b806301ffc9a71461017a57806306fdde03146101a2578063081812fc146101b7578063095ea7b3146101e257806318160ddd146101f757806323b872dd14610209575b600080fd5b61018d610188366004611f45565b61036a565b60405190151581526020015b60405180910390f35b6101aa61037b565b604051610199919061202c565b6101ca6101c5366004611f7d565b61040d565b604051600160a060020a039091168152602001610199565b6101f56101f0366004611f1c565b6104bb565b005b6008545b604051908152602001610199565b6101f5610217366004611dd2565b6105f3565b6101fb61022a366004611f1c565b610628565b6101f56106d3565b6101f5610245366004611d86565b610710565b6101f5610258366004611dd2565b610766565b6101f561026b366004611f7d565b610781565b6101fb61027e366004611f7d565b610808565b600b5460ff1661018d565b6101ca61029c366004611f7d565b6108c0565b6101fb6102af366004611d86565b61094e565b6101f56109eb565b6101f5610a28565b600b546101009004600160a060020a03166101ca565b6101aa610a63565b6101f56102f0366004611ee2565b610a72565b6101f5610303366004611e0d565b610b3a565b6101aa610316366004611f7d565b610b75565b61018d610329366004611da0565b600160a060020a03918216600090815260056020908152604080832093909416825291909152205460ff1690565b6101f5610365366004611d86565b610b80565b600061037582610c3b565b92915050565b60606000805461038a9061219d565b80601f01602080910402602001604051908101604052809291908181526020018280546103b69061219d565b80156104035780601f106103d857610100808354040283529160200191610403565b820191906000526020600020905b8154815290600101906020018083116103e657829003601f168201915b5050505050905090565b600081815260026020526040812054600160a060020a031661049f5760405160e560020a62461bcd02815260206004820152602c60248201527f4552433732313a20617070726f76656420717565727920666f72206e6f6e657860448201527f697374656e7420746f6b656e000000000000000000000000000000000000000060648201526084015b60405180910390fd5b50600090815260046020526040902054600160a060020a031690565b60006104c6826108c0565b905080600160a060020a031683600160a060020a031614156105535760405160e560020a62461bcd02815260206004820152602160248201527f4552433732313a20617070726f76616c20746f2063757272656e74206f776e6560448201527f72000000000000000000000000000000000000000000000000000000000000006064820152608401610496565b33600160a060020a038216148061056f575061056f8133610329565b6105e45760405160e560020a62461bcd02815260206004820152603860248201527f4552433732313a20617070726f76652063616c6c6572206973206e6f74206f7760448201527f6e6572206e6f7220617070726f76656420666f7220616c6c00000000000000006064820152608401610496565b6105ee8383610c79565b505050565b6105fe335b82610cf4565b61061d5760405160e560020a62461bcd028152600401610496906120d1565b6105ee838383610dff565b60006106338361094e565b82106106aa5760405160e560020a62461bcd02815260206004820152602b60248201527f455243373231456e756d657261626c653a206f776e657220696e646578206f7560448201527f74206f6620626f756e64730000000000000000000000000000000000000000006064820152608401610496565b50600160a060020a03919091166000908152600660209081526040808320938352929052205490565b600b54600160a060020a036101009091041633146107065760405160e560020a62461bcd0281526004016104969061209c565b61070e610fea565b565b600b54600160a060020a036101009091041633146107435760405160e560020a62461bcd0281526004016104969061209c565b61075581610750600c5490565b611089565b610763600c80546001019055565b50565b6105ee83838360405180602001604052806000815250610b3a565b61078a336105f8565b6107ff5760405160e560020a62461bcd02815260206004820152603060248201527f4552433732314275726e61626c653a2063616c6c6572206973206e6f74206f7760448201527f6e6572206e6f7220617070726f766564000000000000000000000000000000006064820152608401610496565b610763816110a7565b600061081360085490565b821061088a5760405160e560020a62461bcd02815260206004820152602c60248201527f455243373231456e756d657261626c653a20676c6f62616c20696e646578206f60448201527f7574206f6620626f756e647300000000000000000000000000000000000000006064820152608401610496565b600882815481106108ae5760e060020a634e487b7102600052603260045260246000fd5b90600052602060002001549050919050565b600081815260026020526040812054600160a060020a0316806103755760405160e560020a62461bcd02815260206004820152602960248201527f4552433732313a206f776e657220717565727920666f72206e6f6e657869737460448201527f656e7420746f6b656e00000000000000000000000000000000000000000000006064820152608401610496565b6000600160a060020a0382166109cf5760405160e560020a62461bcd02815260206004820152602a60248201527f4552433732313a2062616c616e636520717565727920666f7220746865207a6560448201527f726f2061646472657373000000000000000000000000000000000000000000006064820152608401610496565b50600160a060020a031660009081526003602052604090205490565b600b54600160a060020a03610100909104163314610a1e5760405160e560020a62461bcd0281526004016104969061209c565b61070e60006110b0565b600b54600160a060020a03610100909104163314610a5b5760405160e560020a62461bcd0281526004016104969061209c565b61070e611117565b60606001805461038a9061219d565b600160a060020a038216331415610ace5760405160e560020a62461bcd02815260206004820152601960248201527f4552433732313a20617070726f766520746f2063616c6c6572000000000000006044820152606401610496565b336000818152600560209081526040808320600160a060020a03871680855290835292819020805460ff191686151590811790915590519081529192917f17307eab39ab6107e8899845ad3d59bd9653f200f220920489ca2b5937696c31910160405180910390a35050565b610b443383610cf4565b610b635760405160e560020a62461bcd028152600401610496906120d1565b610b6f848484846111a2565b50505050565b6060610375826111d8565b600b54600160a060020a03610100909104163314610bb35760405160e560020a62461bcd0281526004016104969061209c565b600160a060020a038116610c325760405160e560020a62461bcd02815260206004820152602660248201527f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160448201527f64647265737300000000000000000000000000000000000000000000000000006064820152608401610496565b610763816110b0565b6000600160e060020a031982167f780e9d63000000000000000000000000000000000000000000000000000000001480610375575061037582611359565b6000818152600460205260409020805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0384169081179091558190610cbb826108c0565b600160a060020a03167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b92560405160405180910390a45050565b600081815260026020526040812054600160a060020a0316610d815760405160e560020a62461bcd02815260206004820152602c60248201527f4552433732313a206f70657261746f7220717565727920666f72206e6f6e657860448201527f697374656e7420746f6b656e00000000000000000000000000000000000000006064820152608401610496565b6000610d8c836108c0565b905080600160a060020a031684600160a060020a03161480610dc7575083600160a060020a0316610dbc8461040d565b600160a060020a0316145b80610df75750600160a060020a0380821660009081526005602090815260408083209388168352929052205460ff165b949350505050565b82600160a060020a0316610e12826108c0565b600160a060020a031614610e915760405160e560020a62461bcd02815260206004820152602960248201527f4552433732313a207472616e73666572206f6620746f6b656e2074686174206960448201527f73206e6f74206f776e00000000000000000000000000000000000000000000006064820152608401610496565b600160a060020a038216610f0f5760405160e560020a62461bcd028152602060048201526024808201527f4552433732313a207472616e7366657220746f20746865207a65726f2061646460448201527f72657373000000000000000000000000000000000000000000000000000000006064820152608401610496565b610f1a8383836113f4565b610f25600082610c79565b600160a060020a0383166000908152600360205260408120805460019290610f4e90849061215a565b9091555050600160a060020a0382166000908152600360205260408120805460019290610f7c90849061212e565b9091555050600081815260026020526040808220805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0386811691821790925591518493918716917fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef91a4505050565b600b5460ff1661103f5760405160e560020a62461bcd02815260206004820152601460248201527f5061757361626c653a206e6f74207061757365640000000000000000000000006044820152606401610496565b600b805460ff191690557f5db9ee0a495bf2e6ff9c91a7834c1ba4fdd244a5e8aa4e537bd38aeae4b073aa335b604051600160a060020a03909116815260200160405180910390a1565b6110a3828260405180602001604052806000815250611455565b5050565b6107638161148b565b600b8054600160a060020a0383811661010081810274ffffffffffffffffffffffffffffffffffffffff001985161790945560405193909204169182907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e090600090a35050565b600b5460ff161561116d5760405160e560020a62461bcd02815260206004820152601060248201527f5061757361626c653a20706175736564000000000000000000000000000000006044820152606401610496565b600b805460ff191660011790557f62e78cea01bee320cd4e420270b5ea74000d11b0c9f74754ebdbfc544b05a25861106c3390565b6111ad848484610dff565b6111b9848484846114cb565b610b6f5760405160e560020a62461bcd0281526004016104969061203f565b600081815260026020526040902054606090600160a060020a03166112685760405160e560020a62461bcd02815260206004820152603160248201527f45524337323155524953746f726167653a2055524920717565727920666f722060448201527f6e6f6e6578697374656e7420746f6b656e0000000000000000000000000000006064820152608401610496565b6000828152600a6020526040812080546112819061219d565b80601f01602080910402602001604051908101604052809291908181526020018280546112ad9061219d565b80156112fa5780601f106112cf576101008083540402835291602001916112fa565b820191906000526020600020905b8154815290600101906020018083116112dd57829003601f168201915b50505050509050600061130b61160d565b905080516000141561131e575092915050565b815115611350578082604051602001611338929190611fc1565b60405160208183030381529060405292505050919050565b610df78461162d565b6000600160e060020a031982167f80ac58cd0000000000000000000000000000000000000000000000000000000014806113bc5750600160e060020a031982167f5b5e139f00000000000000000000000000000000000000000000000000000000145b8061037557507f01ffc9a700000000000000000000000000000000000000000000000000000000600160e060020a0319831614610375565b600b5460ff161561144a5760405160e560020a62461bcd02815260206004820152601060248201527f5061757361626c653a20706175736564000000000000000000000000000000006044820152606401610496565b6105ee838383611719565b61145f83836117d1565b61146c60008484846114cb565b6105ee5760405160e560020a62461bcd0281526004016104969061203f565b61149481611932565b6000818152600a6020526040902080546114ad9061219d565b159050610763576000818152600a6020526040812061076391611d1c565b6000600160a060020a0384163b15611602576040517f150b7a02000000000000000000000000000000000000000000000000000000008152600160a060020a0385169063150b7a0290611528903390899088908890600401611ff0565b602060405180830381600087803b15801561154257600080fd5b505af1925050508015611572575060408051601f3d908101601f1916820190925261156f91810190611f61565b60015b6115cf573d8080156115a0576040519150601f19603f3d011682016040523d82523d6000602084013e6115a5565b606091505b5080516115c75760405160e560020a62461bcd0281526004016104969061203f565b805181602001fd5b600160e060020a0319167f150b7a0200000000000000000000000000000000000000000000000000000000149050610df7565b506001949350505050565b606060405180608001604052806046815260200161226c60469139905090565b600081815260026020526040902054606090600160a060020a03166116bd5760405160e560020a62461bcd02815260206004820152602f60248201527f4552433732314d657461646174613a2055524920717565727920666f72206e6f60448201527f6e6578697374656e7420746f6b656e00000000000000000000000000000000006064820152608401610496565b60006116c761160d565b905060008151116116e75760405180602001604052806000815250611712565b806116f1846119e6565b604051602001611702929190611fc1565b6040516020818303038152906040525b9392505050565b600160a060020a0383166117745761176f81600880546000838152600960205260408120829055600182018355919091527ff3f7a9fe364faab93b216da50a3214154f22a0a2b415b23a84c8169e8b636ee30155565b611797565b81600160a060020a031683600160a060020a031614611797576117978382611b59565b600160a060020a0382166117ae576105ee81611bf6565b82600160a060020a031682600160a060020a0316146105ee576105ee8282611cd8565b600160a060020a03821661182a5760405160e560020a62461bcd02815260206004820181905260248201527f4552433732313a206d696e7420746f20746865207a65726f20616464726573736044820152606401610496565b600081815260026020526040902054600160a060020a0316156118925760405160e560020a62461bcd02815260206004820152601c60248201527f4552433732313a20746f6b656e20616c7265616479206d696e746564000000006044820152606401610496565b61189e600083836113f4565b600160a060020a03821660009081526003602052604081208054600192906118c790849061212e565b9091555050600081815260026020526040808220805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03861690811790915590518392907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef908290a45050565b600061193d826108c0565b905061194b816000846113f4565b611956600083610c79565b600160a060020a038116600090815260036020526040812080546001929061197f90849061215a565b9091555050600082815260026020526040808220805473ffffffffffffffffffffffffffffffffffffffff1916905551839190600160a060020a038416907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef908390a45050565b606081611a2657505060408051808201909152600181527f3000000000000000000000000000000000000000000000000000000000000000602082015290565b8160005b8115611a505780611a3a816121db565b9150611a499050600a83612146565b9150611a2a565b60008167ffffffffffffffff811115611a7c5760e060020a634e487b7102600052604160045260246000fd5b6040519080825280601f01601f191660200182016040528015611aa6576020820181803683370190505b5090505b8415610df757611abb60018361215a565b9150611ac8600a866121f6565b611ad390603061212e565b7f010000000000000000000000000000000000000000000000000000000000000002818381518110611b185760e060020a634e487b7102600052603260045260246000fd5b60200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a905350611b52600a86612146565b9450611aaa565b60006001611b668461094e565b611b70919061215a565b600083815260076020526040902054909150808214611bc357600160a060020a03841660009081526006602090815260408083208584528252808320548484528184208190558352600790915290208190555b506000918252600760209081526040808420849055600160a060020a039094168352600681528383209183525290812055565b600854600090611c089060019061215a565b60008381526009602052604081205460088054939450909284908110611c415760e060020a634e487b7102600052603260045260246000fd5b906000526020600020015490508060088381548110611c735760e060020a634e487b7102600052603260045260246000fd5b6000918252602080832090910192909255828152600990915260408082208490558582528120556008805480611cbc5760e060020a634e487b7102600052603160045260246000fd5b6001900381819060005260206000200160009055905550505050565b6000611ce38361094e565b600160a060020a039093166000908152600660209081526040808320868452825280832085905593825260079052919091209190915550565b508054611d289061219d565b6000825580601f10611d38575050565b601f01602090049060005260206000209081019061076391905b80821115611d665760008155600101611d52565b5090565b8035600160a060020a0381168114611d8157600080fd5b919050565b600060208284031215611d97578081fd5b61171282611d6a565b60008060408385031215611db2578081fd5b611dbb83611d6a565b9150611dc960208401611d6a565b90509250929050565b600080600060608486031215611de6578081fd5b611def84611d6a565b9250611dfd60208501611d6a565b9150604084013590509250925092565b60008060008060808587031215611e22578081fd5b611e2b85611d6a565b9350611e3960208601611d6a565b925060408501359150606085013567ffffffffffffffff80821115611e5c578283fd5b818701915087601f830112611e6f578283fd5b813581811115611e8157611e8161223c565b604051601f8201601f19908116603f01168101908382118183101715611ea957611ea961223c565b816040528281528a6020848701011115611ec1578586fd5b82602086016020830137918201602001949094529598949750929550505050565b60008060408385031215611ef4578182fd5b611efd83611d6a565b915060208301358015158114611f11578182fd5b809150509250929050565b60008060408385031215611f2e578182fd5b611f3783611d6a565b946020939093013593505050565b600060208284031215611f56578081fd5b813561171281612255565b600060208284031215611f72578081fd5b815161171281612255565b600060208284031215611f8e578081fd5b5035919050565b60008151808452611fad816020860160208601612171565b601f01601f19169290920160200192915050565b60008351611fd3818460208801612171565b835190830190611fe7818360208801612171565b01949350505050565b6000600160a060020a038087168352808616602084015250836040830152608060608301526120226080830184611f95565b9695505050505050565b6020815260006117126020830184611f95565b60208082526032908201527f4552433732313a207472616e7366657220746f206e6f6e20455243373231526560408201527f63656976657220696d706c656d656e7465720000000000000000000000000000606082015260800190565b6020808252818101527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572604082015260600190565b60208082526031908201527f4552433732313a207472616e736665722063616c6c6572206973206e6f74206f60408201527f776e6572206e6f7220617070726f766564000000000000000000000000000000606082015260800190565b600082198211156121415761214161220a565b500190565b60008261215557612155612223565b500490565b60008282101561216c5761216c61220a565b500390565b60005b8381101561218c578181015183820152602001612174565b83811115610b6f5750506000910152565b6002810460018216806121b157607f821691505b602082108114156121d55760e060020a634e487b7102600052602260045260246000fd5b50919050565b60006000198214156121ef576121ef61220a565b5060010190565b60008261220557612205612223565b500690565b60e060020a634e487b7102600052601160045260246000fd5b60e060020a634e487b7102600052601260045260246000fd5b60e060020a634e487b7102600052604160045260246000fd5b600160e060020a03198116811461076357600080fdfe68747470733a2f2f6d792d6a736f6e2d7365727665722e74797069636f64652e636f6d2f6c696e68766e677579656e392f6e66742d7365727665722d746573742f6e6674732fa2646970667358221220d85ad0529188845231c57039f5ff9c81a54534ff3cd1351b511dc16ae100b9e864736f6c63430008040033";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BURN = "burn";

    public static final String FUNC_GETAPPROVED = "getApproved";

    public static final String FUNC_ISAPPROVEDFORALL = "isApprovedForAll";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_OWNEROF = "ownerOf";

    public static final String FUNC_PAUSE = "pause";

    public static final String FUNC_PAUSED = "paused";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_SAFEMINT = "safeMint";

    public static final String FUNC_safeTransferFrom = "safeTransferFrom";

    public static final String FUNC_SETAPPROVALFORALL = "setApprovalForAll";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOKENBYINDEX = "tokenByIndex";

    public static final String FUNC_TOKENOFOWNERBYINDEX = "tokenOfOwnerByIndex";

    public static final String FUNC_TOKENURI = "tokenURI";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_UNPAUSE = "unpause";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event APPROVALFORALL_EVENT = new Event("ApprovalForAll", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event PAUSED_EVENT = new Event("Paused", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event UNPAUSED_EVENT = new Event("Unpaused", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected NonFungibleToken4(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected NonFungibleToken4(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected NonFungibleToken4(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected NonFungibleToken4(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public List<ApprovalForAllEventResponse> getApprovalForAllEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVALFORALL_EVENT, transactionReceipt);
        ArrayList<ApprovalForAllEventResponse> responses = new ArrayList<ApprovalForAllEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalForAllEventResponse>() {
            @Override
            public ApprovalForAllEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVALFORALL_EVENT, log);
                ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVALFORALL_EVENT));
        return approvalForAllEventFlowable(filter);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public List<PausedEventResponse> getPausedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PAUSED_EVENT, transactionReceipt);
        ArrayList<PausedEventResponse> responses = new ArrayList<PausedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PausedEventResponse typedResponse = new PausedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PausedEventResponse> pausedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, PausedEventResponse>() {
            @Override
            public PausedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PAUSED_EVENT, log);
                PausedEventResponse typedResponse = new PausedEventResponse();
                typedResponse.log = log;
                typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PausedEventResponse> pausedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAUSED_EVENT));
        return pausedEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public List<UnpausedEventResponse> getUnpausedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(UNPAUSED_EVENT, transactionReceipt);
        ArrayList<UnpausedEventResponse> responses = new ArrayList<UnpausedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            UnpausedEventResponse typedResponse = new UnpausedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<UnpausedEventResponse> unpausedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, UnpausedEventResponse>() {
            @Override
            public UnpausedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(UNPAUSED_EVENT, log);
                UnpausedEventResponse typedResponse = new UnpausedEventResponse();
                typedResponse.log = log;
                typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<UnpausedEventResponse> unpausedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(UNPAUSED_EVENT));
        return unpausedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> burn(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BURN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getApproved(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAPPROVED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> isApprovedForAll(String owner, String operator) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISAPPROVEDFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Address(160, operator)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> ownerOf(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNEROF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> pause() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PAUSE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> paused() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PAUSED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeMint(String to) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SAFEMINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId, byte[] _data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId), 
                new org.web3j.abi.datatypes.DynamicBytes(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setApprovalForAll(String operator, Boolean approved) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETAPPROVALFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, operator), 
                new org.web3j.abi.datatypes.Bool(approved)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> tokenByIndex(BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENBYINDEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> tokenOfOwnerByIndex(String owner, BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENOFOWNERBYINDEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> tokenURI(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENURI, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> unpause() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UNPAUSE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static NonFungibleToken4 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new NonFungibleToken4(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static NonFungibleToken4 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new NonFungibleToken4(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static NonFungibleToken4 load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new NonFungibleToken4(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static NonFungibleToken4 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new NonFungibleToken4(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<NonFungibleToken4> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(NonFungibleToken4.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<NonFungibleToken4> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(NonFungibleToken4.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<NonFungibleToken4> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(NonFungibleToken4.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<NonFungibleToken4> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(NonFungibleToken4.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String approved;

        public BigInteger tokenId;
    }

    public static class ApprovalForAllEventResponse extends BaseEventResponse {
        public String owner;

        public String operator;

        public Boolean approved;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class PausedEventResponse extends BaseEventResponse {
        public String account;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger tokenId;
    }

    public static class UnpausedEventResponse extends BaseEventResponse {
        public String account;
    }
}
