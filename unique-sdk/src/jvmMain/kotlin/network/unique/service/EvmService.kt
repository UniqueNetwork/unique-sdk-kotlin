package network.unique.service

import network.unique.model.*

interface EvmService {

    fun isContractExists(contractAddress: String): EvmContractExistsResponseDto

    //TODO Empty response?
    fun call(body: EvmCallArgumentsDto): Any

    fun getEvmSend(): MutationService<EvmSendMutationRequest, EvmSendMutationDefaultResponse>

}