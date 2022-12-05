package network.unique.service

import network.unique.model.AddressDto
import network.unique.model.EthereumAddressDto
import network.unique.model.NestingAddressDto
import network.unique.model.TokenIdQuery
import java.math.BigDecimal

interface AddressUtilsService {

    fun idsToAddress(collectionId: BigDecimal, tokenId: BigDecimal, at: String): NestingAddressDto

    fun addressToIds(address: String): TokenIdQuery

    fun substrateToEthereum(address: String): EthereumAddressDto

    fun ethereumToSubstrate(address: String): AddressDto

    fun normalize(address: String, ss58prefix: BigDecimal): AddressDto

}