package id.xxx.map.box.search.data.source.remote

import id.xxx.map.box.search.data.source.remote.service.ApiConfigTest
import id.xxx.test.rule.RuleUnitTestWithCoroutine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.junit.Test

class RemoteDataSourceTest : RuleUnitTestWithCoroutine() {

    private val remoteDataSource = RemoteDataSource(ApiConfigTest.apiService)

    @Test
    fun getPlacesTest() = runBlocking<Unit> {
        val result = remoteDataSource.getPlaces("").firstOrNull()

        println(result)
    }
}