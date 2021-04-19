package id.xxx.map.box.search.data.repository

import id.xxx.base.domain.model.Resource
import id.xxx.map.box.search.data.Helper
import id.xxx.map.box.search.data.source.local.LocalDataSource
import id.xxx.map.box.search.data.source.remote.RemoteDataSource
import id.xxx.map.box.search.data.source.remote.service.ApiService
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class RepositoryImplTest {

    private val remoteDataSource = RemoteDataSource(Helper.apiService(ApiService::class.java))
    private val localDataSource = LocalDataSource(Helper.databaseInMemory.placesDao())

    private val repositoryImpl = RepositoryImpl(remoteDataSource, localDataSource)

    @Test
    fun test() {
        runBlocking {
            val flow = repositoryImpl.getPlaces("jakarta")
            Assert.assertTrue(flow.firstOrNull() is Resource.Loading)
            Assert.assertTrue(flow.drop(1).firstOrNull() is Resource.Success)
        }
    }
}