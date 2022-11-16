package network.unique.service

interface MutationService<T> {

    fun getUrl(): String

    fun execute()

    fun buildBatch()

    fun build()

    fun sign()

    fun submit()

    fun submitWatch()

    fun submitWaitResult()

}