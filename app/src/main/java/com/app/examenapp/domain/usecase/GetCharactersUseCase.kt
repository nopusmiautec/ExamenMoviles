package com.app.examenapp.domain.usecase

import com.app.examenapp.core.Resource
import com.app.examenapp.domain.model.Character
import com.app.examenapp.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharactersUseCase
    @Inject
    constructor(
        private val respository: CharacterRepository,
    ) {
        operator fun invoke(): Flow<Resource<List<Character>>> =
            flow {
                try {
                    emit(Resource.Loading())
                    val characters = respository.getCharacters()
                    emit(Resource.Success(characters))
                } catch (e: HttpException) {
                    emit(Resource.Error(e.localizedMessage ?: "Unexpected error"))
                } catch (e: IOException) {
                    emit(Resource.Error("Couldn't reach server. Check your internet connection"))
                }
            }
    }
