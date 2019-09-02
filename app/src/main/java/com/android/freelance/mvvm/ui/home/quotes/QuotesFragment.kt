package com.android.freelance.mvvm.ui.home.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.freelance.mvvm.R
import com.android.freelance.mvvm.data.db.entities.Quotes
import com.android.freelance.mvvm.ui.viewmodels.ProfileViewModelFactory
import com.android.freelance.mvvm.ui.viewmodels.QuotesViewModel
import com.android.freelance.mvvm.ui.viewmodels.QuotesViewModelFactory
import com.android.freelance.mvvm.util.Coroutines
import com.android.freelance.mvvm.util.hide
import com.android.freelance.mvvm.util.show
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.quotes_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    private lateinit var mQuotesViewModel: QuotesViewModel
    private val factory: QuotesViewModelFactory by instance()
    //private val factory: ProfileViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mQuotesViewModel = ViewModelProviders.of(this, factory).get(QuotesViewModel::class.java)

        /*Coroutines.main {
         val quotes = mQuotesViewModel.quotes.await()
            quotes.observe(this, Observer {
                context?.toast(it.size.toString())//show the data result from network
            })
        }*/
        bindUI()
    }

    private fun bindUI() = Coroutines.main {
        pb_Loading.show()
        mQuotesViewModel.quotes.await().observe(this, Observer {
            pb_Loading.hide()
            initRecyclerView(it.toQuoteItem())
        })
    }

    private fun List<Quotes>.toQuoteItem(): List<QuotesItem> {
        return this.map {
            QuotesItem(it)
        }
    }

    private fun initRecyclerView(quoteItem: List<QuotesItem>) {

        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(quoteItem)
        }

        groupieRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }
}
