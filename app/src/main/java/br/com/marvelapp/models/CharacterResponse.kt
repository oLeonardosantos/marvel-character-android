package br.com.marvelapp.models

import java.io.Serializable

data class CharacterResponse(
    val data: CharacterData
): Serializable

data class CharacterData(
    val results: List<CharacterMarvel>
): Serializable

data class CharacterMarvel(
    val id: Long,
    val name: String,
    val thumbnail: Thumbnail,
    val description: String,
    val series: Series,
): Serializable{
    fun getImage(): String = "${thumbnail.path}.${thumbnail.extension}".replace("http", "https")
}

data class Thumbnail(
    val path: String,
    val extension: String
): Serializable

data class Series(
    val items: List<SeriesItem>
): Serializable

data class SeriesItem(
    val name: String
): Serializable