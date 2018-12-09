package com.androidchekhov.pagingrecyclerview.domain

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.androidchekhov.pagingrecyclerview.repository.CommentsRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.logging.Level
import java.util.logging.Logger
import javax.inject.Inject


class CommentsDataSource @Inject constructor(
    private val store: CommentsStore,
    private val commentsRepository: CommentsRepository
) : PageKeyedDataSource<Int, Comment>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Comment>) {
        store.logAndSend(OnFirstPageLoading, "loading first page")

        GlobalScope.launch {
            val comments = commentsRepository.getComments(1)

            store.logAndSend(OnFirstPageResults,"result size: ${comments.size}")

            callback.onResult(
                comments,
                null,
                2
            )
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Comment>) {
        store.logAndSend(OnNextPageLoading, "loading page ${params.key}")

        GlobalScope.launch {
            val comments = commentsRepository.getComments(params.key)

            store.logAndSend(OnNextPageResults, "result size: ${comments.size}")

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

    private fun CommentsStore.logAndSend(event: CommentsEvent, message: String) {
        logger.log(Level.INFO, message)
        send(event)
    }

    companion object {
        val logger = Logger.getLogger(CommentsDataSource::class.java.simpleName)
    }
}