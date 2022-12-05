package network.unique.service.impl

import network.unique.api.AccountApi
import network.unique.model.AccountDataResponse
import network.unique.model.GenerateAccountDataBody
import network.unique.service.AccountService

class AccountServiceImpl(basePath: String) : AccountService {

    private val api: AccountApi = AccountApi(basePath)

    override fun getAccount(
        mnemonic: String,
        pairType: AccountApi.PairType_accountControllerGetAccount?,
        meta: Any?
    ): AccountDataResponse {
        return api.accountControllerGetAccount(mnemonic, pairType, meta)
    }

    override fun generateAccount(body: GenerateAccountDataBody): AccountDataResponse {
        return api.accountControllerGenerate(body)
    }

}