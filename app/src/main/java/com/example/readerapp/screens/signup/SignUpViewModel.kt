package com.example.readerapp.screens.signup

import androidx.lifecycle.ViewModel
import com.example.readerapp.screens.login.SignInStates
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.userProfileChangeRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow<SignUpStates>(SignUpStates.Nothing)
    val state = _state

    private val firebaseAuth = FirebaseAuth.getInstance()

    fun signUp(email : String, password : String, name: String){
        _state.value = SignUpStates.Loading
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                _state.value = SignUpStates.Success
                val user = firebaseAuth.currentUser
                user?.updateProfile(
                    UserProfileChangeRequest.Builder()
                        .setDisplayName(name)
                        .build()
                )
            }
            .addOnFailureListener {
                _state.value = SignUpStates.Error
            }
    }
}

sealed class SignUpStates{
    data object Nothing : SignUpStates()
    data object Loading : SignUpStates()
    data object Error : SignUpStates()
    data object Success : SignUpStates()
}