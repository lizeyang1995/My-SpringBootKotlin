package com.example.MySpringBootKotlin.authentication.domain.usecase

import arrow.core.Option
import arrow.core.Try
import arrow.core.failure
import com.example.MySpringBootKotlin.developers.domain.AlreadyRegistered
import com.example.MySpringBootKotlin.developers.domain.Developer
import com.example.MySpringBootKotlin.developers.storage.DeveloperDao
import org.springframework.stereotype.Component

@Component
class RegisterDeveloper(
        private val developerDao: DeveloperDao
) {
    operator fun invoke(developer: Developer): Try<Developer> =
            developerDao.getByUsername(developer.username)
                    .flatMap(createIfNotRegistered(developer))

    private fun createIfNotRegistered(developer: Developer): (Option<Developer>) -> Try<Developer> = {
        it.fold({
            developerDao.create(developer)
        }, {
            AlreadyRegistered.failure()
        })
    }
}