package com.example.MySpringBootKotlin.authentication.domain.usecase

import arrow.core.Try
import com.example.MySpringBootKotlin.common.TryLogger
import com.example.MySpringBootKotlin.security.SetAuthentication
import com.example.MySpringBootKotlin.security.TokenHelper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component

@Component
class LoginDeveloper(
        private val authenticationManager: AuthenticationManager,
        private val tokenHelper: TokenHelper,
        private val setAuthentication: SetAuthentication
) {
    operator fun invoke(userPass: UsernamePasswordAuthenticationToken): Try<String> = TryLogger {
        val authentication = authenticationManager.authenticate(userPass)
        setAuthentication(authentication)
        tokenHelper.generateToken(userPass.principal.toString())
    }
}