package network.unique.sdk

import network.unique.service.*
import network.unique.service.impl.*

class UniqueSdk(basePath: String) {
    
    var accountService: AccountService = AccountServiceImpl(basePath) 
    var addressUtilsService: AddressUtilsService = AddressUtilsServiceImpl(basePath) 
    var balanceService: BalanceService = BalanceServiceImpl(basePath) 
    var collectionService: CollectionService = CollectionServiceImpl(basePath) 
    var commonService: CommonService = CommonServiceImpl(basePath) 
    var ERC721Service: ERC721Service = ERC721ServiceImpl(basePath) 
    var evmService: EvmService = EvmServiceImpl(basePath) 
    var extrinsicService: ExtrinsicService = ExtrinsicServiceImpl(basePath) 
    var fungibleService: FungibleService = FungibleServiceImpl(basePath) 
    var IPFSService: IPFSService = IPFSServiceImpl(basePath) 
    var queryService: QueryService = QueryServiceImpl(basePath) 
    var tokenService: TokenService = TokenServiceImpl(basePath)
    
}