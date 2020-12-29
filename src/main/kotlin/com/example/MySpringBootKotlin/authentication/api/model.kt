package com.example.MySpringBootKotlin.authentication.api

import com.example.MySpringBootKotlin.developers.domain.Developer
import com.example.MySpringBootKotlin.developers.domain.PasswordEncoder

data class NewDeveloperRequest(
        val username: String,
        val email: String?,
        val password: String
)

data class LoginRequest(
        val username: String,
        val password: String
)

fun NewDeveloperRequest.toDomain(encoder: PasswordEncoder): Developer = Developer(
        username = username,
        email = email,
        password = encoder(password)
)