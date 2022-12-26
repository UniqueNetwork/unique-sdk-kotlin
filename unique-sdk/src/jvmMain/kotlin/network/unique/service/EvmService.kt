package network.unique.service

import network.unique.model.EvmCallArgumentsDto
import network.unique.model.EvmContractExistsResponseDto
import network.unique.model.EvmSendArgumentsDto
import network.unique.model.EvmSendMutationRequest

interface EvmService {

    fun isContractExists(contractAddress: String): EvmContractExistsResponseDto

    //TODO Empty response?
    fun call(body: EvmCallArgumentsDto): Any

    fun getEvmSend(): MutationService<EvmSendMutationRequest>

}