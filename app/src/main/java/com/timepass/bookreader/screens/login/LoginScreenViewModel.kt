package com.timepass.bookreader.screens.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.timepass.bookreader.model.BooksUser
import kotlinx.coroutines.launch

class LoginScreenViewModel:ViewModel() {
    enum class LoginResult {
        Success,
        Error
    }
//        var loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading
    var showErrorState =
        mutableStateOf(false)


    fun userSignIn(email: String, password: String, home: () -> Unit) = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isComplete) {
                        home()

                    } else {

                    }

                }

        } catch (ex: Exception) {



        }

    }




    fun userAccountCreate(
        email: String,
        password: String,
        home: () -> Unit
    ) {
        if (_loading.value == false) {
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val displayName = task.result.user?.email?.split('@')?.get(0)
                        createUser(displayName)
                        home()
                    } else {
                        Log.d("fb", "userAccountCreate: ${task.result}")
                    }
                    _loading.value = false

                }
        }
    }
@SuppressLint("SuspiciousIndentation")
private fun createUser(displayName: String?) {
            val userId = auth.currentUser?.uid
    val userEmail = auth.currentUser?.email
    val user = BooksUser(
        userId = userId.toString(),
                     id = null,
        displayName = displayName.toString(),
        avatarUrl = "",
        userEmail = userEmail.toString()
    ).toMap()

           FirebaseFirestore.getInstance().collection("users")
               .add(user)



        }

}
