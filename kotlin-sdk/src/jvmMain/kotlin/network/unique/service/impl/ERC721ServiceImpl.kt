package network.unique.service.impl

import network.unique.api.ERC721Api
import network.unique.service.ERC721Service

class ERC721ServiceImpl(basePath: String): ERC721Service {

    private val api: ERC721Api = ERC721Api(basePath)

}