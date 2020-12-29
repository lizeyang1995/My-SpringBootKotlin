package com.example.MySpringBootKotlin.security

import com.example.MySpringBootKotlin.developers.domain.Developer
import com.example.MySpringBootKotlin.developers.domain.DeveloperValidator
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class SecurityUser(
        val id: String,
        private val username: String,
        private val password: String,
        private val authorities: List<GrantedAuthority> = emptyList()
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return username
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
}

fun Developer.toSecurityUser(): SecurityUser = SecurityUser(
        id.toString(), username, password, getRole()
)

private fun Developer.getRole() = listOf(
        if (DeveloperValidator.isKarumiDeveloper(this)) {
            GrantedAuthority { "ROLE_KARUMIER" }
        } else {
            GrantedAuthority { "ROLE_USER" }
        }
)