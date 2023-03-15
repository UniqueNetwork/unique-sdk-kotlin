package network.unique.service.impl

import network.unique.api.EvmApi
import network.unique.model.*
import network.unique.service.EvmService
import network.unique.service.MutationService
import network.unique.service.impl.evm.EvmSendMutationServiceImpl

class EvmServiceImpl(basePath: String) : EvmService {

    private val api: EvmApi = EvmApi(basePath)

    private val evmSendMutationService: MutationService<EvmSendMutationRequest, EvmSendMutationDefaultResponse> = EvmSendMutationServiceImpl(basePath)

    override fun isContractExists(contractAddress: String): EvmContractExistsResponseDto {
        return api.evmControllerEvmExist(contractAddress)
    }

    override fun call(body: EvmCallArgumentsDto): Any {
        return api.evmControllerEvmCall(body)
    }

    override fun getEvmSend(): MutationService<EvmSendMutationRequest, EvmSendMutationDefaultResponse> {
        return evmSendMutationService
    }

}