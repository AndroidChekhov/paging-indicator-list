package com.androidchekhov.pagingrecyclerview.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.androidchekhov.pagingrecyclerview.domain.Comment
import com.androidchekhov.pagingrecyclerview.domain.CommentsDataSource
import com.androidchekhov.pagingrecyclerview.domain.CommentsStore
import javax.inject.Inject

class PagedListCommentsRepository @Inject constructor(
    private val store: CommentsStore,
    private val commentsRepository: CommentsRepository

) {
    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(NUM_COMMENTS_INITIAL_LOAD)
        .setPageSize(PAGE_SIZE)
        .build()

    fun getPagedList() : LiveData<PagedList<Comment>>   {
        val factory = CommentsDataSource.Factory(store, commentsRepository)

        return LivePagedListBuilder(factory, pagedListConfig)
            .build()
    }

    companion object {
        private const val NUM_COMMENTS_INITIAL_LOAD = 50;
        private const val PAGE_SIZE = 25;
    }
}