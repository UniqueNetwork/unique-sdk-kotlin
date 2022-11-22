package network.unique.service.impl

import network.unique.api.IpfsApi
import network.unique.model.IpfsUploadResponse
import network.unique.service.IPFSService
import java.io.File

class IPFSServiceImpl(basePath: String) : IPFSService {

    private val api: IpfsApi = IpfsApi(basePath)

    override fun uploadFile(file: File): IpfsUploadResponse {
        return api.ipfsControllerUploadFile(file)
    }

    override fun uploadFiles(files: List<File>): IpfsUploadResponse {
        return api.ipfsControllerUploadFiles(files)
    }

    override fun addFiles(files: List<File>, cid: String): IpfsUploadResponse {
        return api.ipfsControllerAddFiles(files, cid)
    }
}