package network.unique.service.impl

import network.unique.api.AddressUtilsApi
import network.unique.model.AddressDto
import network.unique.model.EthereumAddressDto
import network.unique.model.NestingAddressDto
import network.unique.model.TokenIdQuery
import network.unique.service.AddressUtilsService
import java.math.BigDecimal

class AddressUtilsServiceImpl(basePath: String) : AddressUtilsService {

    private val api: AddressUtilsApi = AddressUtilsApi(basePath)

    override fun idsToAddress(collectionId: BigDecimal, tokenId: BigDecimal, at: String): NestingAddressDto {
        return api.addressUtilsControllerNestingTokenIdToAddress(collectionId, tokenId, at)
    }

    override fun addressToIds(address: String): TokenIdQuery {
        return api.addressUtilsControllerNestingAddressToCollection(address)
    }

    override fun substrateToEthereum(address: String): EthereumAddressDto {
        return api.addressUtilsControllerSubstrateToEthereum(address)
    }

    override fun ethereumToSubstrate(address: String): AddressDto {
        return api.addressUtilsControllerEthereumToSubstrate(address)
    }

    override fun normalize(address: String, ss58prefix: BigDecimal): AddressDto {
        return api.addressUtilsControllerNormalize(address, ss58prefix)
    }

}