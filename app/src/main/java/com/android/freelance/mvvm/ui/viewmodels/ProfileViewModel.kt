package com.android.freelance.mvvm.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.android.freelance.mvvm.data.repositories.UserRepository

class ProfileViewModel(repository: UserRepository) : ViewModel() {

    val user = repository.getUser()
}
