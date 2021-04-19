package id.xxx.map.box.search.domain.repository

import id.xxx.base.domain.model.Resource
import id.xxx.map.box.search.domain.model.AddressModel
import id.xxx.map.box.search.domain.model.PlacesModel
import kotlinx.coroutines.flow.Flow

interface IRepository {

    fun getPlaces(query: String?): Flow<Resource<List<PlacesModel>>>

    fun getAddress(value: String?): Flow<Resource<AddressModel>>
}