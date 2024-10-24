package br.com.marvelapp.adapter.api

import br.com.marvelapp.models.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("characters")
    suspend fun getCharacters(
        @Query("ts") ts: String = "1",
        @Query("apikey") apikey: String = "db1a1a64cd3f03a196e0eab46acb4290",
        @Query("hash") hash: String = "26c93fa53474af905bbaf730ba8bbdb2"
    ): Response<CharacterResponse>
}