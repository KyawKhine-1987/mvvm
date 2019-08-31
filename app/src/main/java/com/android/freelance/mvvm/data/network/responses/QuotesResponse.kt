package com.android.freelance.mvvm.data.network.responses

import com.android.freelance.mvvm.data.db.entities.Quotes

data class QuotesResponse (

    //TODO: Create this attribute in table in MySQL and PHP.
    val isSuccessful: Boolean,
    val quotes: List<Quotes>
)