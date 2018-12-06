package com.androidchekhov.pagingrecyclerview.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.androidchekhov.pagingrecyclerview.arch.StateObserver
import com.androidchekhov.pagingrecyclerview.domain.Comment
import com.androidchekhov.pagingrecyclerview.domain.CommentsState
import com.androidchekhov.pagingrecyclerview.domain.CommentsStore
import com.androidchekhov.pagingrecyclerview.repository.PagedListCommentsRepository

class CommentsViewModel(
    store: CommentsStore,
    private val commentsRepository: PagedListCommentsRepository
) : ViewModel(), StateObserver<CommentsState> {

    init {
        store.observe(this)
    }

    val state = MutableLiveData<CommentsState>()
    lateinit var pagedList : LiveData<PagedList<Comment>>

    fun getComments() { pagedList = commentsRepository.getPagedList() }

    override fun stateChanged(oldState: CommentsState?, newState: CommentsState) {
        state.value = newState
    }
}


