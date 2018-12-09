package com.androidchekhov.pagingrecyclerview.repository

import com.androidchekhov.pagingrecyclerview.domain.Comment
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

class MockCommentsRepository : CommentsRepository {

    override suspend fun getComments(page: Int): List<Comment> {
        delay(NETWORK_DELAY)

        val comments = mutableListOf<Comment>()
        if (page < MAX_PAGES) {
            repeat(COMMENTS_PER_PAGE) {
                comments.add(
                    createMockComment(it * page)
                )
            }
        }
        return comments
    }

    companion object {
        private const val MAX_PAGES = 5
        private const val COMMENTS_PER_PAGE = 20

        private val NETWORK_DELAY = TimeUnit.SECONDS.toMillis(3)
    }

    private fun createMockComment(id: Int): Comment = Comment(
        id.toString(),
        "username",
        "Dec 1",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec nec nibh molestie, consectetur risus at, semper nisl. Nulla nulla lectus, bibendum ac arcu sed, convallis vulputate urna."
    )
}