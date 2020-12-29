package com.example.MySpringBootKotlin.security

import com.example.MySpringBootKotlin.common.orThrow
import com.example.MySpringBootKotlin.common.toTry
import com.example.MySpringBootKotlin.developers.storage.DeveloperDao
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
        private val developerDao: DeveloperDao
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails  =
        developerDao.getByUsername(username)
                .flatMap{it.toTry { UsernameNotFoundException("User $username not found") }}
                .orThrow()
                .toSecurityUser()

}