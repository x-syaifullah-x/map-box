package id.xxx.map.box.search.data.source.remote

import id.xxx.base.domain.model.getApiResponseAsFlow
import id.xxx.map.box.search.data.source.remote.service.ApiService

class RemoteDataSource(private val apiService: ApiService) {

    fun getPlaces(query: String?) = getApiResponseAsFlow(
        blockFirst = { !query.isNullOrBlank() },
        blockFetch = {
            apiService.getPlaces(query)
        }
    )
}