package com.example.MySpringBootKotlin.developers.storage

import arrow.core.Option
import arrow.core.Try
import arrow.core.toOption
import com.example.MySpringBootKotlin.common.TryLogger
import com.example.MySpringBootKotlin.developers.domain.Developer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
interface DeveloperRepository: CrudRepository<DeveloperEntity, UUID> {
    fun findByUsernameContainingIgnoreCase(username: String): DeveloperEntity?
}

@Component
class DeveloperDao(private val developerRepository: DeveloperRepository) {
    fun create(developer: Developer): Try<Developer> = TryLogger {
        developerRepository.save(developer.toEntity()).toDomain()
    }

    fun getById(developerId: UUID): Try<Option<Developer>> = TryLogger {
        developerRepository.findById(developerId)
                .orElse(null)
                .toOption()
                .map { it.toDomain() }
    }

    fun getByUsername(username: String): Try<Option<Developer>> = TryLogger {
        developerRepository.findByUsernameContainingIgnoreCase(username)
                .toOption()
                .map { it.toDomain() }
    }
}