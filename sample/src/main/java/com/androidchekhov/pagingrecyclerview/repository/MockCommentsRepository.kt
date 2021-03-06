package com.androidchekhov.pagingrecyclerview.repository

import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

/**
 * A mock [CommentsRepository] implementation.  Note, that [getComments] uses a mock network delay of [DELAY_SEC].
 */
class MockCommentsRepository : CommentsRepository {

    override suspend fun getComments(page: Int): List<Comment> {
        delay(DELAY_SEC)

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

        private val DELAY_SEC = TimeUnit.SECONDS.toMillis(2)
    }

    private fun createMockComment(id: Int): Comment =
        Comment(
            id.toString(),
            "username",
            "date",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec nec nibh molestie, consectetur risus at, semper nisl. Nulla nulla lectus, bibendum ac arcu sed, convallis vulputate urna."
        )
}