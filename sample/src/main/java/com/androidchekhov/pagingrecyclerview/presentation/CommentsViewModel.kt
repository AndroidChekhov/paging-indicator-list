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
import java.util.logging.Logger
import javax.inject.Inject

class CommentsViewModel @Inject constructor(
    store: CommentsStore,
    private val commentsRepository: PagedListCommentsRepository
) : ViewModel(), StateObserver<CommentsState> {

    val state = MutableLiveData<CommentsState>()

    val pagedList: LiveData<PagedList<Comment>> by lazy {
        commentsRepository.getPagedList()
    }

    init {
        store.observe(this)
    }

    override fun stateChanged(oldState: CommentsState?, newState: CommentsState) {
        state.postValue(newState)
    }

    companion object {
        val logger = Logger.getLogger(CommentsViewModel::class.java.simpleName)
    }
}


