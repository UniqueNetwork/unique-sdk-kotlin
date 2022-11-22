package network.unique.service

import network.unique.model.*
import java.io.File

interface IPFSService {

    fun uploadFile(file: File): IpfsUploadResponse

    fun uploadFiles(files: List<File>): IpfsUploadResponse

    fun addFiles(files: List<File>, cid: String): IpfsUploadResponse
}