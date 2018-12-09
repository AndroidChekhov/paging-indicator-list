package com.androidchekhov.pagingrecyclerview.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidchekhov.pagination.PagingAdapter
import com.androidchekhov.pagingrecyclerview.CommentsApplication
import com.androidchekhov.pagingrecyclerview.R
import com.androidchekhov.pagingrecyclerview.arch.observeNonNull
import com.androidchekhov.pagingrecyclerview.domain.LoadingFirstPage
import com.androidchekhov.pagingrecyclerview.domain.Paging
import com.androidchekhov.pagingrecyclerview.domain.Results
import com.androidchekhov.pagingrecyclerview.domain.Starting
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Level
import java.util.logging.Logger
import javax.inject.Inject

class CommentsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: CommentsViewModel

    private val adapter = CommentsPagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as CommentsApplication).commentsComponent.inject(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProviders.of(this, viewModelFactory)[CommentsViewModel::class.java]

        viewModel.state.observeNonNull(this) {
            when (it) {
                is LoadingFirstPage -> progressBar.visibility = View.VISIBLE
                is Paging -> adapter.isPaging = true
                is Results -> {
                    progressBar.visibility = View.GONE
                    adapter.isPaging = false
                }
            }
        }

        viewModel.pagedList.observeNonNull(this) {
            logger.log(Level.INFO, "submitting paged list")

            adapter.submitList(it)
        }
    }

    companion object {
        val logger = Logger.getLogger(CommentsActivity::class.java.simpleName)
    }
}
