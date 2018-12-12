package com.androidchekhov.pagingrecyclerview.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidchekhov.pagingrecyclerview.domain.CommentsStore
import com.androidchekhov.pagingrecyclerview.repository.PagedListCommentsRepository
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val store: CommentsStore,
    private val pagedListCommentsRepository: PagedListCommentsRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CommentsViewModel::class.java)) {
            CommentsViewModel(store, pagedListCommentsRepository) as T
        } else {
            throw IllegalArgumentException("view model not found")
        }
    }

}