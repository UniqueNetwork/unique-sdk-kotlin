package network.unique.service.impl

import network.unique.api.EvmApi
import network.unique.model.EvmCallArgumentsDto
import network.unique.model.EvmContractExistsResponseDto
import network.unique.model.EvmSendArgumentsDto
import network.unique.model.Signer
import network.unique.service.EvmService
import network.unique.service.MutationService
import network.unique.service.impl.evm.EvmSendMutationServiceImpl

class EvmServiceImpl(signer: Signer, basePath: String) : EvmService {

    private val api: EvmApi = EvmApi(basePath)

    private val evmSendMutationService: MutationService<EvmSendArgumentsDto> =
        EvmSendMutationServiceImpl(signer, basePath)

    override fun isContractExists(contractAddress: String): EvmContractExistsResponseDto {
        return api.evmControllerEvmExist(contractAddress)
    }

    override fun call(body: EvmCallArgumentsDto): Any {
        return api.evmControllerEvmCall(body)
    }

    override fun getEvmSendMutationService(): MutationService<EvmSendArgumentsDto> {
        return evmSendMutationService
    }

}