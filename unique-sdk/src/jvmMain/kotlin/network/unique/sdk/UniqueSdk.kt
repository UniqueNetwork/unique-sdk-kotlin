package network.unique.sdk

import network.unique.model.SignerWrapper
import network.unique.service.*
import network.unique.service.impl.*

class UniqueSdk(signerWrapper: SignerWrapper, basePath: String) {
    
    var accountService: AccountService = AccountServiceImpl(basePath) 
    var addressUtilsService: AddressUtilsService = AddressUtilsServiceImpl(basePath) 
    var balanceService: BalanceService = BalanceServiceImpl(signerWrapper, basePath)
    var collectionService: CollectionService = CollectionServiceImpl(signerWrapper, basePath)
    var commonService: CommonService = CommonServiceImpl(basePath) 
    var ERC721Service: ERC721Service = ERC721ServiceImpl(signerWrapper, basePath)
    var evmService: EvmService = EvmServiceImpl(signerWrapper, basePath)
    var extrinsicService: ExtrinsicService = ExtrinsicServiceImpl(basePath) 
    var fungibleService: FungibleService = FungibleServiceImpl(signerWrapper, basePath)
    var IPFSService: IPFSService = IPFSServiceImpl(basePath) 
    var queryService: QueryService = QueryServiceImpl(basePath) 
    var tokenService: TokenService = TokenServiceImpl(signerWrapper, basePath)
    
}