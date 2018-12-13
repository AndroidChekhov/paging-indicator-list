package com.androidchekhov.pagingrecyclerview.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.androidchekhov.pagingrecyclerview.arch.StateObserver
import com.androidchekhov.pagingrecyclerview.repository.Comment
import com.androidchekhov.pagingrecyclerview.domain.CommentsState
import com.androidchekhov.pagingrecyclerview.domain.CommentsStore
import com.androidchekhov.pagingrecyclerview.domain.LoadingFirstPage
import com.androidchekhov.pagingrecyclerview.domain.Refreshing
import com.androidchekhov.pagingrecyclerview.repository.PagedListCommentsRepository
import javax.inject.Inject

class CommentsViewModel @Inject constructor(
    store: CommentsStore,
    private val commentsRepository: PagedListCommentsRepository
) : ViewModel(), StateObserver<CommentsState> {

    val state = MutableLiveData<CommentsState>()

    private var isRefreshing = false

    val pagedList: LiveData<PagedList<Comment>> by lazy {
        commentsRepository.getPagedList()
    }

    init {
        store.observe(this)
    }

    override fun stateChanged(oldState: CommentsState?, newState: CommentsState) {
        when(newState) {
            is LoadingFirstPage -> {
                if (isRefreshing) Refreshing else LoadingFirstPage
            }
            else -> newState
        }.also {
            isRefreshing = false
            state.postValue(it)
        }
    }

    fun refresh() {
        isRefreshing = true
        pagedList.value?.dataSource?.invalidate()
    }
}


