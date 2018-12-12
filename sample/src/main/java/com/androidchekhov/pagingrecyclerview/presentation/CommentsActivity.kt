package com.androidchekhov.pagingrecyclerview.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidchekhov.pagingrecyclerview.CommentsApplication
import com.androidchekhov.pagingrecyclerview.R
import com.androidchekhov.pagingrecyclerview.arch.observeNonNull
import com.androidchekhov.pagingrecyclerview.domain.LoadingFirstPage
import com.androidchekhov.pagingrecyclerview.domain.Paging
import com.androidchekhov.pagingrecyclerview.domain.Results
import kotlinx.android.synthetic.main.activity_main.*
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

        pagingRecyclerView.layoutManager = GridLayoutManager(this, DEFAULT_SPAN_COUNT)
        pagingRecyclerView.adapter = adapter

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
            adapter.submitList(it)
        }
    }

    companion object {
        private const val DEFAULT_SPAN_COUNT = 2
    }
}
