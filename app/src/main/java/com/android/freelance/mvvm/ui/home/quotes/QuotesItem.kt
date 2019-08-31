package com.android.freelance.mvvm.ui.home.quotes

import com.android.freelance.mvvm.R
import com.android.freelance.mvvm.data.db.entities.Quotes
import com.android.freelance.mvvm.databinding.ItemQuotesBinding
import com.xwray.groupie.databinding.BindableItem

class QuotesItem(private val quotes: Quotes) : BindableItem<ItemQuotesBinding>() {

    override fun getLayout() = R.layout.item_quotes

    override fun bind(viewBinding: ItemQuotesBinding, position: Int) {

        viewBinding.setQuote(quotes)
    }
}