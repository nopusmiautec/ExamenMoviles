package com.app.examenapp.presentation.screens.characters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.app.examenapp.core.Resource
import com.app.examenapp.domain.model.Character

@Suppress("ktlint:standard:function-naming")
@Composable
fun CharactersScreen(viewModel: CharactersViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    Scaffold { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            when (val result = state) {
                is Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is Resource.Success -> {
                    CharacterList(characters = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    Text(
                        text = result.message ?: "Unknown error",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun CharacterList(characters: List<Character>) {
    LazyColumn {
        items(characters) { character ->
            CharacterItem(character)
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun CharacterItem(character: Character) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = character.imageUrl,
                contentDescription = character.name,
                modifier = Modifier.size(60.dp),
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = character.name, style = MaterialTheme.typography.titleMedium)
                Text(text = character.species, style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = character.status,
                    color = if (character.status == "Alive") Color.Green else Color.Gray,
                )
            }
        }
    }
}
