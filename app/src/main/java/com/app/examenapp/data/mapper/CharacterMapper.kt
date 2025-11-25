package com.app.examenapp.data.mapper

import com.app.examenapp.data.remote.dto.CharacterDto
import com.app.examenapp.domain.model.Character

fun CharacterDto.toDomain(): Character =
    @Suppress("ktlint:standard:multiline-expression-wrapping")
    Character(
        id = id,
        name = name,
        status = status,
        species = species,
        imageUrl = image,
    )
