package com.android.freelance.mvvm.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.freelance.mvvm.data.db.entities.AppDatabase
import com.android.freelance.mvvm.data.db.entities.Quotes
import com.android.freelance.mvvm.data.network.MyRestApi
import com.android.freelance.mvvm.data.network.SafeApiRequest
import com.android.freelance.mvvm.data.preferences.PreferenceProvider
import com.android.freelance.mvvm.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private val MINIMUM_INTERVAL = 6

class QuotesRepository(
    private val api: MyRestApi,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
) : SafeApiRequest() {
    private val quotes = MutableLiveData<List<Quotes>>()

    init {
        //Callback
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    suspend fun getQuotes(): LiveData<List<Quotes>> {
        return withContext(Dispatchers.IO) {
            fetchQuotes()
            db.quoteDao().getQuotes()
        }
    }

    private suspend fun fetchQuotes() {

        val lastSavedAt = prefs.getLastSavedAt()
        if (lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))) {
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean {

        return ChronoUnit.HOURS.between(savedAt, LocalDateTime.now()) > MINIMUM_INTERVAL
    }

    private fun saveQuotes(quotes: List<Quotes>) {
        Coroutines.io {
            //TODO convert datetime in realtime production app
            prefs.savelastSaveAt(LocalDateTime.now().toString())
            db.quoteDao().saveAllQuotes(quotes)
        }
    }
}