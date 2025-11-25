package com.app.examenapp.presentation.screens.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.examenapp.core.Resource
import com.app.examenapp.domain.model.Character
import com.app.examenapp.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel
    @Inject
    constructor(
        private val getCharactersUseCase: GetCharactersUseCase,
    ) : ViewModel() {
        private val _state = MutableStateFlow<Resource<List<Character>>>(Resource.Loading())
        val state: StateFlow<Resource<List<Character>>> = _state

        init {
            getCharacters()
        }

        private fun getCharacters() {
            getCharactersUseCase()
                .onEach { result ->
                    _state.value = result
                }.launchIn(viewModelScope)
        }
    }
