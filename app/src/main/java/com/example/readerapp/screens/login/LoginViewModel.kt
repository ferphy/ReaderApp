package com.example.readerapp.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.auth.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow<SignInStates>(SignInStates.Nothing)
    val state = _state

    fun singIn(email: String, password: String){
        _state.value = SignInStates.Loading
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                _state.value = SignInStates.Success
            }
            .addOnFailureListener {
                _state.value = SignInStates.Error
            }
    }

}

sealed class SignInStates{
    data object Nothing : SignInStates()
    data object Loading : SignInStates()
    data object Success : SignInStates()
    data object Error : SignInStates()
}