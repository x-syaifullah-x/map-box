package id.xxx.map.box.search.data.source.remote.service

import id.xxx.map.box.search.data.BuildConfig
import id.xxx.test.rule.RuleUnitTestWithCoroutine
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ApiConfigTest : RuleUnitTestWithCoroutine() {

    companion object {
        val apiService = ApiConfig.service(BuildConfig.BASE_URL)
            .create(ApiService::class.java)
    }

    @Test
    fun test() = runBlocking {

        println(
            apiService.getPlaces("jakarta")
        )
    }
}