package com.example.MySpringBootKotlin.developers.storage

import com.example.MySpringBootKotlin.developers.domain.Developer
import sun.jvm.hotspot.memory.Generation
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class DeveloperEntity(
    val username: String,
    val email: String?,
    val password: String,
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: UUID?
)

fun Developer.toEntity(): DeveloperEntity = DeveloperEntity(username, email, password, id)

fun DeveloperEntity.toDomain(): Developer = Developer(username, email, password, id)