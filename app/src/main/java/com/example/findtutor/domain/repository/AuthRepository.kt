package com.example.findtutor.domain.repository

import com.example.findtutor.data.models.AuthState

interface AuthRepository {

    suspend fun signUpWithEmailAndPassword(email: String, password: String): Result<Unit>

    suspend fun signInWithEmailAndPassword(email: String, password: String): Result<Unit>

    suspend fun sendVerificationLink():AuthState

    suspend fun signUpWithGoogle(idToken: String): Result<Unit>

    suspend fun signOut()

    fun getCurrentUser(): String?

    suspend fun sendPasswordResetEmail(email: String):Result<Unit>
    suspend fun saveUsernameToFirestore(username: String)
    suspend fun getUsernameFromFirestore(): String?
}