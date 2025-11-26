package com.app.examenapp.data.mapper

import com.app.examenapp.data.local.entity.CharacterEntity
import com.app.examenapp.data.remote.dto.CharacterDto
import com.app.examenapp.domain.model.Character

fun CharacterDto.toEntity(): CharacterEntity =
    CharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        imageUrl = image,
    )

fun CharacterEntity.toDomain(): Character =
    Character(
        id = id,
        name = name,
        status = status,
        species = species,
        imageUrl = imageUrl,
    )
