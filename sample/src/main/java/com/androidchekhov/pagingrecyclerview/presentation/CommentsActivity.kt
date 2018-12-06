package com.androidchekhov.pagingrecyclerview.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.androidchekhov.pagingrecyclerview.R
import com.androidchekhov.pagingrecyclerview.domain.LoadingFirstPage
import com.androidchekhov.pagingrecyclerview.domain.Paging
import com.androidchekhov.pagingrecyclerview.domain.Results
import com.androidchekhov.pagingrecyclerview.domain.Starting

class CommentsActivity : AppCompatActivity() {

    private lateinit var viewModel: CommentsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO get ViewModel from factory

        viewModel.state.observe(this, Observer {
            when (it) {
                is Starting -> TODO()
                is LoadingFirstPage -> TODO()
                is Paging -> TODO()
                is Results -> TODO()
            }
        })

        viewModel.pagedList.observe(this, Observer {
            TODO("submit list to adapter")
        })
    }
}
