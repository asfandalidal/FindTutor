package com.example.findtutor.data

import android.util.Log
import com.example.findtutor.data.models.AuthState
import com.example.findtutor.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore

) : AuthRepository {

    override suspend fun signUpWithEmailAndPassword(email: String, password: String): Result<Unit> {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    override suspend fun signInWithEmailAndPassword(email: String, password: String): Result<Unit> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val user = firebaseAuth.currentUser
            user?.reload()?.await()
            if (user != null && user.isEmailVerified) {
                Result.success(Unit)
            } else {
                firebaseAuth.signOut()
                Result.failure(Exception("Email not verified. Please check your inbox for the verification link."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    override suspend fun signUpWithGoogle(idToken: String): Result<Unit> {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val authResult = firebaseAuth.signInWithCredential(credential).await()
            // Extract the display name from the Google account
            val googleAccount = authResult.user
            val displayName = googleAccount?.displayName
            // Save the display name to Firestore
            if (displayName != null) {
                saveUsernameToFirestore(displayName)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun sendVerificationLink(): AuthState {
        return try {
            val user = firebaseAuth.currentUser
            if (user != null) {
                user.sendEmailVerification().await()
                AuthState.Success("Verification email sent.")
            } else {
                AuthState.Failure("User is not logged in.")
            }
        } catch (e: Exception) {
            AuthState.Failure(e.message ?: "Failed to send verification email.")
        }
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }

    override fun getCurrentUser(): String? {
        return firebaseAuth.currentUser?.email
    }

    override suspend fun sendPasswordResetEmail(email: String): Result<Unit> {
        return try {
            if (email.isEmpty()) {
                return Result.failure(Exception("Please enter a valid email"))
            }
            suspendCoroutine<Result<Unit>> { continuation ->
                firebaseAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            continuation.resume(Result.success(Unit))
                        } else {
                            continuation.resume(
                                Result.failure(
                                    task.exception ?: Exception("Unknown error")
                                )
                            )
                        }
                    }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun saveUsernameToFirestore(username: String) {
        val userId = firebaseAuth.currentUser?.uid ?: return
        val user = hashMapOf(
            "username" to username
        )
        try {
            firestore.collection("users")
                .document(userId)
                .set(user)
                .await()
        } catch (e: Exception) {
            Log.e("error", e.localizedMessage)
        }
    }

    override suspend fun getUsernameFromFirestore(): String? {
        val userId = firebaseAuth.currentUser?.uid ?: return null
        val docRef = firestore.collection("users").document(userId)
        val document = docRef.get().await()
        return document.getString("username")
    }
}


