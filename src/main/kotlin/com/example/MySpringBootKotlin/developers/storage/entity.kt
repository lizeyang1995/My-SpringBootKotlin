package com.example.MySpringBootKotlin.developers.storage

import sun.jvm.hotspot.memory.Generation
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class DeveloperEntity(
    val name: String,
    val email: String?,
    val password: String,
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: UUID?
)

