package com.androidchekhov.pagingrecyclerview.repository

import com.androidchekhov.pagingrecyclerview.domain.Comment

class MockCommentsRepository: CommentsRepository {

    override suspend fun getComments(page: Int): List<Comment> {
       val comments = mutableListOf<Comment>()
       if (page < MAX_PAGES) {
           repeat(COMMENTS_PER_PAGE) {
               comments.add(
                   createMockComment()
               )
           }
       }
        return comments
    }

    companion object {
        private const val MAX_PAGES = 5
        private const val COMMENTS_PER_PAGE = 20
    }

    private fun createMockComment(): Comment
            = Comment(
        "username",
        "Dec 1",
        "This is a comment."
    )
}