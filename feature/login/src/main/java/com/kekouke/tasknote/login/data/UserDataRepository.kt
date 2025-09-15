package com.kekouke.tasknote.login.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.kekouke.tasknote.login.domain.UserRepository
import com.kekouke.tasknote.utils.models.runCatchingNonCancellation
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserDataRepository @Inject constructor() : UserRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()

    override val isAuthenticated: Boolean
        get() {
            return firebaseAuth.currentUser != null
        }

    override val authFlow: Flow<Boolean> = callbackFlow {
        val listener = AuthStateListener { auth -> trySend(auth.currentUser != null) }

        firebaseAuth.addAuthStateListener(listener)

        awaitClose { firebaseAuth.removeAuthStateListener(listener) }
    }

    override suspend fun signIn(email: String, password: String) = runCatchingNonCancellation {
        val result = firebaseAuth.signInWithEmailAndPassword(
            email,
            password
        ).await()

        check(result.user != null)

    }

    override suspend fun signUp(email: String, password: String) = runCatchingNonCancellation {
        val result = firebaseAuth.createUserWithEmailAndPassword(
            email,
            password
        ).await()

        check(result.user != null)
    }

}
