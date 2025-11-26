package com.app.examenapp.data.repository

import com.app.examenapp.data.local.dao.CharacterDao
import com.app.examenapp.data.mapper.toDomain
import com.app.examenapp.data.mapper.toEntity
import com.app.examenapp.data.remote.RickAndMortyApi
import com.app.examenapp.domain.model.Character
import com.app.examenapp.domain.repository.CharacterRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharacterRepositoryImpl
    @Inject
    constructor(
        private val api: RickAndMortyApi,
        private val dao: CharacterDao,
    ) : CharacterRepository {
        override suspend fun getCharacters(): List<Character> {
            try {
                val response = api.getCharacters()
                val dtos = response.results

                dao.clearCharacters()

                dao.insertCharacters(dtos.map { it.toEntity() })
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: HttpException) {
                e.printStackTrace()
            }

            return dao.getCharacters().map { it.toDomain() }
        }
    }
