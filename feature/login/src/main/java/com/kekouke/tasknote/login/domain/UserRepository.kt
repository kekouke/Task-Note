package com.kekouke.tasknote.login.domain

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val isAuthenticated: Boolean

    val authFlow: Flow<Boolean>

    suspend fun signIn(email: String, password: String): Result<Unit>
    suspend fun signUp(email: String, password: String): Result<Unit>
}
