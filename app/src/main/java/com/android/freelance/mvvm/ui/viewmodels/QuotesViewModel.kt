package com.android.freelance.mvvm.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.android.freelance.mvvm.data.repositories.QuotesRepository
import com.android.freelance.mvvm.util.lazyDeferred

class QuotesViewModel(repository: QuotesRepository) : ViewModel() {

    val quotes by lazyDeferred{
        repository.getQuotes()
    }
}
