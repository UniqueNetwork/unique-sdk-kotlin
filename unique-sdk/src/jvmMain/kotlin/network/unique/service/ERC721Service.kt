package network.unique.service

import network.unique.model.CreateERC721CollectionBody
import network.unique.model.CreateERC721TokenBody

interface ERC721Service {

    fun getCreateERC721CollectionMutationService(): MutationService<CreateERC721CollectionBody>

    fun getCreateERC721TokenMutationService(): MutationService<CreateERC721TokenBody>

}