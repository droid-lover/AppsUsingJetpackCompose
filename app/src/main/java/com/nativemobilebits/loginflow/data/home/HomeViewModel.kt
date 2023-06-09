package com.nativemobilebits.loginflow.data.home

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.nativemobilebits.loginflow.data.NavigationItem
import com.nativemobilebits.loginflow.data.signup.SignupViewModel
import com.nativemobilebits.loginflow.navigation.PostOfficeAppRouter
import com.nativemobilebits.loginflow.navigation.Screen

class HomeViewModel : ViewModel() {

    private val TAG = HomeViewModel::class.simpleName

    val navigationItemsList = listOf<NavigationItem>(
        NavigationItem(
            title = "Home",
            icon = Icons.Default.Home,
            description = "Home Screen",
            itemId = "homeScreen"
        ),
        NavigationItem(
            title = "Settings",
            icon = Icons.Default.Settings,
            description = "Settings Screen",
            itemId = "settingsScreen"
        ),
        NavigationItem(
            title = "Favorite",
            icon = Icons.Default.Favorite,
            description = "Favorite Screen",
            itemId = "favoriteScreen"
        )
    )

    val isUserLoggedIn: MutableLiveData<Boolean> = MutableLiveData()

    fun logout() {

        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signOut()

        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                Log.d(TAG, "Inside sign outsuccess")
                PostOfficeAppRouter.navigateTo(Screen.LoginScreen)
            } else {
                Log.d(TAG, "Inside sign out is not complete")
            }
        }

        firebaseAuth.addAuthStateListener(authStateListener)

    }

    fun checkForActiveSession() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            Log.d(TAG, "Valid session")
            isUserLoggedIn.value = true
        } else {
            Log.d(TAG, "User is not logged in")
            isUserLoggedIn.value = false
        }
    }


    val emailId: MutableLiveData<String> = MutableLiveData()

    fun getUserData() {
        FirebaseAuth.getInstance().currentUser?.also {
            it.email?.also { email ->
                emailId.value = email
            }
        }
    }

}