package network.unique.service

import network.unique.model.EvmCallArgumentsDto
import network.unique.model.EvmContractExistsResponseDto

interface EvmService {

    fun isContractExists(contractAddress: String): EvmContractExistsResponseDto

    //TODO Empty response?
    fun call(body: EvmCallArgumentsDto): Any

}