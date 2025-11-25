package com.app.examenapp.data.remote

import com.app.examenapp.data.remote.dto.CharacterResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int = 1,
    ): CharacterResponseDto
}
