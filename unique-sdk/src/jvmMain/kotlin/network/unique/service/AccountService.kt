package network.unique.service

import network.unique.api.AccountApi
import network.unique.model.AccountDataResponse
import network.unique.model.GenerateAccountDataBody

interface AccountService {

    fun getAccount(
        mnemonic: String,
        pairType: AccountApi.PairType_accountControllerGetAccount?,
        meta: Any?
    ): AccountDataResponse

    fun generateAccount(body: GenerateAccountDataBody): AccountDataResponse

}