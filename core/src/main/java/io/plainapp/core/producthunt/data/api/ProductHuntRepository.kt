package io.plainapp.core.producthunt.data.api

import retrofit2.Call

class ProductHuntRepository(private val service: ProductHuntService) {

    private val inflight: MutableMap<String, Call<*>> = HashMap()


    fun cancelAllRequests() {
        for (request in inflight.values) request.cancel()
    }

    companion object {
        @Volatile
        private var INSTANCE: ProductHuntRepository? = null

        fun getInstance(service: ProductHuntService): ProductHuntRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE
                        ?: ProductHuntRepository(service).also { INSTANCE = it }
            }
        }
    }

}