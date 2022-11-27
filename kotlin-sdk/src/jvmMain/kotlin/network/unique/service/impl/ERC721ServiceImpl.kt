package network.unique.service.impl

import network.unique.model.CreateERC721CollectionBody
import network.unique.model.CreateERC721TokenBody
import network.unique.model.Signer
import network.unique.service.ERC721Service
import network.unique.service.MutationService
import network.unique.service.impl.erc721.CreateERC721CollectionMutationServiceImpl
import network.unique.service.impl.erc721.CreateERC721TokenMutationServiceImpl

class ERC721ServiceImpl(signer: Signer, basePath: String) : ERC721Service {

    private val createERC721CollectionMutationService: MutationService<CreateERC721CollectionBody> =
        CreateERC721CollectionMutationServiceImpl(signer, basePath)
    private val createERC721TokenMutationService: MutationService<CreateERC721TokenBody> =
        CreateERC721TokenMutationServiceImpl(signer, basePath)

    override fun getCreateERC721CollectionMutationService(): MutationService<CreateERC721CollectionBody> {
        return createERC721CollectionMutationService
    }

    override fun getCreateERC721TokenMutationService(): MutationService<CreateERC721TokenBody> {
        return createERC721TokenMutationService
    }

}