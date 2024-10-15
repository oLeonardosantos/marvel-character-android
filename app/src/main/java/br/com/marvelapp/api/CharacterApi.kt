package br.com.marvelapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterApi {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com:443/v1/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}