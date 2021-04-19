package id.xxx.map.box.search.data.repository

import id.xxx.base.domain.mediator.flow.NetworkBoundResourceImpl
import id.xxx.base.domain.model.ApiResponse
import id.xxx.base.domain.model.Resource
import id.xxx.map.box.search.data.helper.Address
import id.xxx.map.box.search.data.mapper.toAddressModel
import id.xxx.map.box.search.data.mapper.toListPlacesEntity
import id.xxx.map.box.search.data.mapper.toListPlacesModel
import id.xxx.map.box.search.data.source.local.LocalDataSource
import id.xxx.map.box.search.data.source.remote.RemoteDataSource
import id.xxx.map.box.search.domain.repository.IRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val address: Address
) : IRepository {

    override fun getPlaces(query: String?) = NetworkBoundResourceImpl(
        blockResult = {
            localDataSource.getPlaces(query).map { it.toListPlacesModel() }
        },
        blockShouldRequest = { localDataSource.isShouldFetch(query) },
        blockRequest = { remoteDataSource.getPlaces(query) },
        blockOnRequest = { apiResponse, _ ->
            if (apiResponse is ApiResponse.Success) {
                localDataSource.saveDataPlaces(apiResponse.data.toListPlacesEntity(query))
            }
        }
    ).asFlow()

    override fun getAddress(value: String?) = flow {
        emit(Resource.Loading)

        val result =
            if (value.isNullOrBlank()) {
                Resource.Empty
            } else {
                when (val data = address.geoCoder(value)) {
                    is ApiResponse.Success -> Resource.Success(data.data.toAddressModel())
                    is ApiResponse.Error -> Resource.Error(error = data.error)
                    is ApiResponse.Empty -> Resource.Empty
                }
            }
        emit(result)
    }
}