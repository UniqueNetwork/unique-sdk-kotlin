package network.unique.service.impl

import network.unique.api.EvmApi
import network.unique.model.EvmCallArgumentsDto
import network.unique.model.EvmContractExistsResponseDto
import network.unique.service.EvmService

class EvmServiceImpl(basePath: String) : EvmService {

    private val api: EvmApi = EvmApi(basePath)

    override fun isContractExists(contractAddress: String): EvmContractExistsResponseDto {
        return api.evmControllerEvmExist(contractAddress)
    }

    override fun call(body: EvmCallArgumentsDto): Any {
        return api.evmControllerEvmCall(body)
    }

}