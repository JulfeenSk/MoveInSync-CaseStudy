package com.examples.moveinsynccasestudy.API

import com.examples.moveinsynccasestudy.dataclass.GenresDataClass
import retrofit2.Response
import retrofit2.http.GET


interface TodoApi {
    @GET("29965988-e33c-4631-87db-69fafb0277ca")
    suspend fun getTodos(): Response<GenresDataClass>
}