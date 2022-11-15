package network.unique.service

import network.unique.model.balance.AllBalanceResponse
import network.unique.model.tokens.TokenByIdResponse

interface TokensService {

    suspend fun getToken(at: String, collectionId: Long, tokenId: Long): TokenByIdResponse

    suspend fun createToken(at: String, collectionId: Long, tokenId: Long): TokenByIdResponse

    suspend fun deleteToken(at: String, collectionId: Long, tokenId: Long): TokenByIdResponse

}