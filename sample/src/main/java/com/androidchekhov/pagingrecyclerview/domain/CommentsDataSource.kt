package com.androidchekhov.pagingrecyclerview.domain

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.androidchekhov.pagingrecyclerview.repository.CommentsRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.logging.Level
import java.util.logging.Logger


class CommentsDataSource(
    private val store: CommentsStore,
    private val commentsRepository: CommentsRepository
) : PageKeyedDataSource<Int, Comment>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Comment>) {
        store.logAndSend(OnFirstPageResults)

        GlobalScope.launch {
            val comments = commentsRepository.getComments(1)

            store.logAndSend(OnFirstPageResults)

            callback.onResult(
                comments,
                null,
                2
            )
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Comment>) {
        store.logAndSend(OnNextPageLoading)

        store.logAndSend(OnFirstPageResults)

        GlobalScope.launch {
            val comments = commentsRepository.getComments(params.key)

            store.logAndSend(OnNextPageResults)

            callback.onResult(
                comments,
                params.key + 1
            )
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Comment>) {
        throw UnsupportedOperationException()
    }

    class Factory(
        private val store: CommentsStore,
        private val commentsRepository: CommentsRepository
    ) : DataSource.Factory<Int, Comment>() {

        override fun create() = CommentsDataSource(
            store,
            commentsRepository
        )
    }

    private fun CommentsStore.logAndSend(event: CommentsEvent) {
        logger.log(Level.INFO, event::class.java.simpleName)
        send(event)
    }

    companion object {
        val logger = Logger.getLogger(CommentsDataSource::class.java.simpleName)
    }
}